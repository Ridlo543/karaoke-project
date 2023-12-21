package Controller;

import java.io.File;
import java.io.IOException;
import java.util.LinkedList;
import java.util.List;
import java.util.Observable;
import java.util.Random;
import java.util.Timer;
import java.util.TimerTask;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ScheduledFuture;
import static java.util.concurrent.TimeUnit.SECONDS;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.DefaultListModel;
import javax.swing.ImageIcon;
import javax.swing.JFileChooser;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JProgressBar;
import javax.swing.filechooser.FileNameExtensionFilter;
import Model.Song;
import Model.SongDAO;
import Model.SongLyric;
import Model.TransaksiModel;
import View.MediaPlayer;
import View.Transaksi;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import java.awt.Image;
import java.io.FileReader;
import java.nio.file.Path;
import java.nio.file.Paths;
import javax.swing.JTextPane;

public class MediaPlayerController {

    private final MusicPlayer musicPlayer;
    private final MediaPlayer mediaPlayer;

    private List<Song> songList;
    private final DefaultListModel defaultModel;
    public static int index;
    public static String playingState;

    public static int currentMusicPlaying;

    private static int progressBar, timeTrans, timeRem;

    public static JLabel labelDetail, labelMusicTitle, labelTimeElapsed, labelTimeRemaining, lebelImageAlbum;
    public static JList jlistPanel;
    public static JProgressBar jprogressBar;
    public static JTextPane PanelLyric;

    private static Automatic automatic = null;

    private boolean isFinishedPlaying;

    private static ScheduledExecutorService scheduler = null;

    private Runnable dynamicDetails = null;

    private ScheduledFuture<?> dynamicDetailsHandle = null;

    private final SongDAO songDAO;

    public MediaPlayerController(MediaPlayer mediaPlayer) {
        this.mediaPlayer = mediaPlayer;

        musicPlayer = new MusicPlayer();
        songDAO = new SongDAO();
        songList = new LinkedList<>();
        defaultModel = new DefaultListModel();
        currentMusicPlaying = 0;
        isFinishedPlaying = false;
        playingState = "";

        songList = songDAO.getSongs();
        if (songList != null) {
            if (!songList.isEmpty()) {
                for (Song music : songList) {

                    defaultModel.addElement(music.getFileName());
                }
            } else {
                infoMesagge();
            }

            jlistPanel.setModel(defaultModel);

        } else {
            infoMesagge();
            songList = new LinkedList<>();
        }
    }

    public void play(int index) {

        jlistPanel.setSelectedIndex(index);

        if (playingState.equals("PLAYING")) {
            pauseDynamicDetails();
        }

        String path = songList.get(index).getPath();
        musicPlayer.Play(path);
        currentMusicPlaying = index;

        playingState = "PLAYING";
        setDynamicDetails(currentMusicPlaying);

        automatic = new Automatic();
    }

    public void pause() {

        try {
            musicPlayer.Pause();

            playingState = "PAUSED";

            pauseDynamicDetails();
        } catch (IOException ex) {
            Logger.getLogger(MediaPlayerController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void resume() {

        playingState = "RESUME";
        setDynamicDetails(currentMusicPlaying);
        musicPlayer.Resume();
        automatic = new Automatic();
    }

    public void next() {
        currentMusicPlaying++;

        if (songList.size() - 1 >= currentMusicPlaying) {

            setDetail(currentMusicPlaying);
            playingState = "PLAYING";
            play(currentMusicPlaying);

        } else {
            currentMusicPlaying = 0;
            setDetail(currentMusicPlaying);
            playingState = "PLAYING";
            play(currentMusicPlaying);
        }
    }

    public void preview() {

        currentMusicPlaying--;
        if (currentMusicPlaying >= 0) {
            setDetail(currentMusicPlaying);
            playingState = "PLAYING";
            play(currentMusicPlaying);
        } else {
            currentMusicPlaying = 0;
        }

    }

    public void playRandom() {

        int rnd = new Random().nextInt(songList.size());

        currentMusicPlaying = rnd;

        play(currentMusicPlaying);
    }

    public void stopMusic() {
        musicPlayer.Stop();
        pauseDynamicDetails();
    }

    private void pauseDynamicDetails() {
        automatic.pause();
        dynamicDetailsHandle.cancel(true);
        scheduler.shutdownNow();
    }

    public void addFiles(JList jlist) {

        JFileChooser fc1 = new JFileChooser();

        fc1.setFileSelectionMode(JFileChooser.FILES_AND_DIRECTORIES);

        FileNameExtensionFilter filter = new FileNameExtensionFilter("Archivos *MP3", "mp3");
        fc1.setFileFilter(filter);

        fc1.setMultiSelectionEnabled(true);

        int returnVal = fc1.showOpenDialog(null);

        if (returnVal == JFileChooser.APPROVE_OPTION) {

            int length = fc1.getSelectedFiles().length;

            File[] files = new File[length];

            files = fc1.getSelectedFiles();

            for (File f : files) {

                if (f.isFile()) {

                    String path = f.getAbsolutePath().replace('\'', '/');

                    File ff = new File(path);

                    Song song = new Song();

                    musicPlayer.putMetaData(song, path, ff.getName());

                    index += 1;

                    song.setId(index);

                    songList.add(song);

                    defaultModel.addElement(ff.getName());

                }

            }
            jlist.setModel(defaultModel);
            jlistPanel = jlist;
            saveMusics();
        }
    }

    public void setDynamicDetails(int indexMusic) {

        isFinishedPlaying = false;

        dynamicDetailsHandle = null;

        scheduler = null;

        dynamicDetails = null;

        dynamicDetails = new Runnable() {

            @Override
            public void run() {

                timeTrans++;
                timeRem--;
                progressBar++;

                MediaPlayerController.jprogressBar.setValue(progressBar);

                setTimeLabel(timeTrans, labelTimeElapsed);

                setTimeLabel(timeRem, labelTimeRemaining);
            }
        };

        int musicLength = (int) songList.get(currentMusicPlaying).getLengthInSeconds();

        MediaPlayerController.jprogressBar.setMaximum(musicLength);

        int lapse = 0;

        if (playingState.equals("PLAYING")) {
            timeTrans = 0;
            timeRem = (int) musicLength;
            progressBar = 0;
            lapse = timeRem;
        }

        if (playingState.equals("RESUME")) {
            lapse = timeRem;
        }

        scheduler = Executors.newScheduledThreadPool(1);
        dynamicDetailsHandle = scheduler.scheduleAtFixedRate(dynamicDetails, 1, 1, SECONDS);
        scheduler.schedule(new Runnable() {
            @Override
            public void run() {

                dynamicDetailsHandle.cancel(true);
                isFinishedPlaying = true;

            }
        }, lapse, SECONDS);

        playingState = "PLAYING";

    }

    private void setTimeLabel(int totalSecs, JLabel label) {
        String timeString = "";
        int hours = totalSecs / 3600;
        int minutes = (totalSecs % 3600) / 60;
        int seconds = totalSecs % 60;
        timeString = String.format("%02d:%02d:%02d", hours, minutes, seconds);
        label.setText(timeString);
    }

    // MusicController
    public void setDetail(int selectedIndex) {
        if (!songList.isEmpty() && selectedIndex >= 0 && selectedIndex < songList.size()) {
            currentMusicPlaying = selectedIndex;
            Song music = songList.get(currentMusicPlaying);
            MediaPlayerController.labelDetail.setText(music.getSummary());
            MediaPlayerController.labelMusicTitle.setText(music.getTitle());
            MediaPlayerController.jlistPanel.setSelectedIndex(currentMusicPlaying);
            MediaPlayerController.jprogressBar.setValue(0);
            ImageIcon imageIcon = null;
            if (music.getImageData() != null) {
                imageIcon = new ImageIcon(new ImageIcon(music.getImageData()).getImage().getScaledInstance(257, 207, java.awt.Image.SCALE_DEFAULT));
                lebelImageAlbum.setIcon(imageIcon);
            } else {
                imageIcon = new ImageIcon(new ImageIcon(getClass().getResource("/view/images/defaultIcon.png")).getImage().getScaledInstance(227, 192, java.awt.Image.SCALE_DEFAULT));
                lebelImageAlbum.setIcon(imageIcon);
            }

            // Mendapatkan gambar secara acak dari direktori
//            ImageIcon randomGifIcon = getRandomGif("build/classes/View/images/animation");
//
//            lebelImageAlbum.setIcon(randomGifIcon);

            // Cari lirik berdasarkan judul lagu di JSON
            String titleToSearch = music.getTitle();
            String lyricText = findLyricByTitle(titleToSearch, "lyric.json");

            // Tampilkan lirik di PanelLyric di kelas MediaPlayer
            if (lyricText != null && !lyricText.isEmpty()) {
                PanelLyric.setText(lyricText);
                PanelLyric.setCaretPosition(0);
            } else {
                PanelLyric.setText("Lirik tidak tersedia");
            }
        } else {
            // Tangani kasus di mana songList kosong atau indeks tidak valid
            System.out.println("Tidak ada musik yang tersedia atau indeks tidak valid.");
        }
    }
    // Metode untuk mencari lirik berdasarkan judul di JSON

    private String findLyricByTitle(String titleToSearch, String filePath) {
        try {
            // Baca file JSON menggunakan Gson
            Gson gson = new Gson();
            Path path = Paths.get(filePath);
            FileReader reader = new FileReader(path.toFile());

//            SongLyricList songLyricList = gson.fromJson(reader, SongLyricList.class);
            // Dapatkan daftar song_lyric
            List<SongLyric> songLyricListData = gson.fromJson(reader, new TypeToken<List<SongLyric>>() {
            }.getType());

            // Cari lirik berdasarkan judul
            for (SongLyric songLyric : songLyricListData) {
                if (titleToSearch.equals(songLyric.getTitle())) {
                    // Temukan judul yang cocok, kembalikan lirik
                    return songLyric.getLyric();
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        // Tidak ada judul yang cocok ditemukan atau terjadi kesalahan
        return null;
    }

    private ImageIcon getRandomGif(String directoryPath) {
        // Mendapatkan daftar file GIF dalam direktori
        File directory = new File(directoryPath);
        File[] files = directory.listFiles((dir, name) -> name.toLowerCase().endsWith(".gif"));

        if (files != null && files.length > 0) {
//            System.out.println("gif");
            // Memilih file GIF secara acak
            int randomIndex = (int) (Math.random() * files.length);
            File randomGifFile = files[randomIndex];

            // Membuat ImageIcon dari file GIF yang dipilih
            ImageIcon gifIcon = new ImageIcon(randomGifFile.getAbsolutePath());

            // Mengubah ukuran GIF sesuai kebutuhan
            Image gifImage = gifIcon.getImage().getScaledInstance(257, 207, Image.SCALE_DEFAULT);
            return new ImageIcon(gifImage);
        } else {
//            System.out.println("default");
            // Mengembalikan gambar default jika tidak ada file GIF dalam direktori
            return new ImageIcon(new ImageIcon(getClass().getResource("/view/images/defaultIcon.png")).getImage().getScaledInstance(227, 192, Image.SCALE_DEFAULT));
        }
    }

    public class Automatic extends Observable {

        Timer timer;

        public Automatic() {
            timer = new Timer();
            timer.scheduleAtFixedRate(timerTask, 0, 1000);
        }
        TimerTask timerTask = new TimerTask() {
            @Override
            public void run() {
                if (isFinishedPlaying) {
                    if (MediaPlayer.random) {
                        playRandom();
                    }
                    if (MediaPlayer.repeat) {
                        play(currentMusicPlaying);
                    } else {
                        next();
                    }
                }
            }

        };

        public void pause() {

            timerTask.cancel();
            timer.cancel();
        }

    }

    public void deleteMusic(int indexMusic) {
        if (!songList.isEmpty() && indexMusic >= 0 && indexMusic < songList.size()) {
            // Tangani penghapusan musik hanya jika songList tidak kosong dan indeks valid
            if (playingState.equals("PLAYING")) {
                stopMusic();
            } else {
                musicPlayer.Stop();
            }

            playingState = "";
            songDAO.deleteSong(songList, indexMusic);
            defaultModel.remove(indexMusic);
            jlistPanel.setModel(defaultModel);
            currentMusicPlaying = 0;
            setDetail(currentMusicPlaying);
            labelTimeElapsed.setText("00:00:00");
            labelTimeRemaining.setText("00:00:00");
        } else {
            // Tangani kasus di mana songList kosong atau indeks tidak valid
            System.out.println("Tidak ada musik yang tersedia atau indeks tidak valid.");
        }
    }

    public void deleteAllMusics() {
        // Tampilkan dialog konfirmasi
        int confirmResult = JOptionPane.showConfirmDialog(null, "Are you sure you want to delete all music?", "Confirmation", JOptionPane.YES_NO_OPTION);

        // Periksa hasil konfirmasi
        if (confirmResult == JOptionPane.YES_OPTION) {
            // Hapus semua musik jika pengguna menekan tombol Yes
            if (playingState.equals("PLAYING")) {
                stopMusic();
            }
            musicPlayer.Stop();
            playingState = "";
            songDAO.deleteAllSongs(songList);
            defaultModel.removeAllElements();
            jlistPanel.removeAll();
            ImageIcon imageIcon = new ImageIcon(new ImageIcon(getClass().getResource("/view/images/nomusic.png")).getImage().getScaledInstance(227, 192, java.awt.Image.SCALE_DEFAULT));
            lebelImageAlbum.setIcon(imageIcon);
            labelTimeElapsed.setText("00:00:00");
            labelTimeRemaining.setText("00:00:00");
            labelDetail.setText("");
            labelMusicTitle.setText("");
            infoMessage("All music has been deleted.");
        }
    }

    private void infoMessage(String message) {
        JOptionPane.showMessageDialog(null, message, "Information", JOptionPane.INFORMATION_MESSAGE);
    }

    public List<Song> getMusics() {
        return songList;
    }

    public void saveMusics() {
        songDAO.insertSongs(songList);
    }

    private void infoMesagge() {
        JOptionPane.showMessageDialog(null, "There are no music to play. Add music", "Add music", JOptionPane.INFORMATION_MESSAGE);
    }

    public void switchToTransaksi() {
        if (mediaPlayer != null) {
            musicPlayer.Stop();
            mediaPlayer.setVisible(false);

            // Ambil data dari mediaPlayer menggunakan getTransaksiModel()
            TransaksiModel transaksiModel = mediaPlayer.getTransaksiModel();

            if (transaksiModel != null) {
                // Buat objek TransaksiController dengan dua parameter
                Transaksi transaksiView = new Transaksi(transaksiModel, null);
                TransaksiController transaksiController = new TransaksiController(transaksiModel, transaksiView);
                transaksiView.setTransaksiController(transaksiController);
                // Simpan data transaksi
                transaksiController.saveTransaksi();
//            Transaksi transaksiView = new Transaksi(transaksiModel, null);

                // Tampilkan Transaksi
                transaksiView.setVisible(true);
            } else {
                System.err.println("TransaksiModel in mediaPlayer is null");
            }
        } else {
            System.err.println("mediaPlayer is null");
        }
    }

}
