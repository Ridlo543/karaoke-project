package Controller;

import com.mpatric.mp3agic.ID3v1;
import com.mpatric.mp3agic.ID3v2;
import com.mpatric.mp3agic.InvalidDataException;
import com.mpatric.mp3agic.Mp3File;
import com.mpatric.mp3agic.UnsupportedTagException;
import java.io.BufferedInputStream;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javazoom.jl.decoder.JavaLayerException;
import javazoom.jl.player.Player;
import Model.Song;
import View.MediaPlayer;

public class MusicPlayer {

    FileInputStream FIS;
    BufferedInputStream BIS;

    public long pauseLocation;
    public long songTotalLength;
    public String fileLocation;

    private Player player;

    public MusicPlayer() {

    }

    // Basic Functions
    public void Play(String path) {

        Stop();

        try {
            FIS = new FileInputStream(path);
            BIS = new BufferedInputStream(FIS);

            player = new Player(BIS);
            songTotalLength = FIS.available();
            fileLocation = path + "";

        } catch (FileNotFoundException | JavaLayerException ex) {

        } catch (IOException ex) {
            Logger.getLogger(MusicPlayer.class.getName()).log(Level.SEVERE, null, ex);
        }

        new Thread() {
            @Override
            public void run() {
                try {
                    player.play();

                    if (player.isComplete() && MediaPlayer.count == 1) {
                        Play(fileLocation);
                    }
                    if (player.isComplete()) {
                        MediaPlayer.Display = "";
                    }
                } catch (JavaLayerException ex) {

                }
            }
        }.start();

    }

//==================================================================play END
    public void Pause() throws IOException {
        if (player != null) {
            try {
                pauseLocation = FIS.available();
                player.close();

            } catch (IOException ex) {

            }

        }
    }

    //====================================================================pause END
    public void Stop() {
        if (player != null) {
            player.close();

            pauseLocation = 0;
            songTotalLength = 0;

        }
    }

    //================================================================resume END
    public void Resume() {

        try {
            FIS = new FileInputStream(fileLocation);
            BIS = new BufferedInputStream(FIS);

            player = new Player(BIS);
            FIS.skip(songTotalLength - pauseLocation);

        } catch (FileNotFoundException | JavaLayerException ex) {

        } catch (IOException ex) {
            Logger.getLogger(MusicPlayer.class.getName()).log(Level.SEVERE, null, ex);
        }

        new Thread() {
            @Override
            public void run() {
                try {
                    player.play();
                } catch (JavaLayerException ex) {

                }
            }
        }.start();

    }

    //// set data  -------------------------------------------------------------------------------------------
    public void putMetaData(Song song, String path, String fileName) {
        try {

            song.setFileName(fileName);

            Mp3File mp3file = new Mp3File(path);

            song.setPath(path);

            song.setLengthInSeconds(mp3file.getLengthInSeconds());

            if (mp3file.hasId3v1Tag()) {

                ID3v1 id3v1Tag = mp3file.getId3v1Tag();

                song.setAtist(id3v1Tag.getArtist());

                song.setTitle(id3v1Tag.getTitle());

                song.setAlbum(id3v1Tag.getAlbum());

                song.setYear(id3v1Tag.getYear());

            }
            if (mp3file.hasId3v2Tag()) {

                ID3v2 id3v2Tag = mp3file.getId3v2Tag();

                if (id3v2Tag.getArtist() != null && id3v2Tag.getAlbum() != null) {
                    song.setAtist(id3v2Tag.getArtist());
                    song.setTitle(id3v2Tag.getTitle());
                    song.setAlbum(id3v2Tag.getAlbum());
                    song.setYear(id3v2Tag.getYear());
                } else {
                    song.setTitle(fileName);
                    song.setSummary("...");
                }

                byte[] imageData = id3v2Tag.getAlbumImage();

                if (imageData != null) {
                    song.setImageData(imageData);
                }
            }

        } catch (IOException | UnsupportedTagException | InvalidDataException ex) {
            Logger.getLogger(MusicPlayer.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
