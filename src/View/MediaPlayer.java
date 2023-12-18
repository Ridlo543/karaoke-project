package View;

import java.awt.Image;
import java.awt.event.WindowEvent;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.Timer;
import Controller.MediaPlayerController;
import Controller.PaketController;
import Model.Lyric;
import Model.TransaksiModel;
import Util.LyricLoader;
import java.util.Map;
import javax.swing.SwingWorker;

public class MediaPlayer extends javax.swing.JFrame {

    private TransaksiModel transaksiModel;
    private int timeRemaining;
    private Timer timer;

    public static int count;
    public static String Display;
    public static boolean random;
    public static boolean repeat;

    public String playImageEntered;
    public String playImageExited;
    public String randomImageEntered;
    public String randomImageExited;
    public String repeatImageEntered;
    public String repeatImageExited;

    public String playState;

    /**
     * Creates new form MediaPlayer
     *
     * @param username
     * @param timeRemaining
     * @param transaksiModel
     */
    public MediaPlayer(String username, int timeRemaining, TransaksiModel transaksiModel) {
        initComponents();
        this.setLocationRelativeTo(null);
        MediaPlayerController.jlistPanel = jList1;
        controller = new MediaPlayerController(this);
        this.transaksiModel = transaksiModel;
        this.timeRemaining = timeRemaining;  // Inisialisasi timeRemaining

        random = false;
        repeat = false;
        playState = "";
        playImageEntered = "images/play_h.png";
        playImageExited = "images/play.png";
        randomImageEntered = "images/random_h.png";
        randomImageExited = "images/random.png";
        repeatImageEntered = "images/repeat_h.png";
        repeatImageExited = "images/repeat.png";

        MediaPlayerController.labelDetail = labelDetail;
        MediaPlayerController.labelMusicTitle = labelMusicTitle;
        MediaPlayerController.labelTimeElapsed = labelTimeElapsed;
        MediaPlayerController.labelTimeRemaining = labelTimeRemaining;
        MediaPlayerController.lebelImageAlbum = lebelImageAlbum;
        MediaPlayerController.jprogressBar = jProgressBar1;

        jLabelWelcome.setText("Welcome, " + username);

    }

    public void showMediaPlayer() {
        startUpdatingTimeRemaining();
        setVisible(true);
    }

    private void startUpdatingTimeRemaining() {
        SwingWorker<Void, Void> worker = new SwingWorker<Void, Void>() {
            @Override
            protected Void doInBackground() throws Exception {
                updateTimeRemaining(timeRemaining);
                return null;
            }
        };

        worker.execute();
    }

    public void setMusicController(MediaPlayerController controller) {
        this.controller = controller;
    }

    private int calculateTotalHarga(int durasi) {
        int hargaPerJam = 10000;
        return durasi * hargaPerJam;
    }

    private void updateTimeRemaining(int timeRemainingInHours) {
        int timeRemainingInSeconds = timeRemainingInHours * 3600; // konversi jam ke detik
        int hours, minutes, seconds;

        while (timeRemainingInSeconds >= 0) {
            hours = timeRemainingInSeconds / 3600;
            minutes = (timeRemainingInSeconds % 3600) / 60;
            seconds = timeRemainingInSeconds % 60;

            String formattedTime = String.format("%02d:%02d:%02d", hours, minutes, seconds);

            // Perbarui label dengan waktu yang diformat
            jLabelTimeRemaining.setText("Time Remaining: " + formattedTime);

            // Cek apakah waktu tersisa sudah habis
            if (timeRemainingInSeconds == 0) {
                // Lakukan tindakan yang diperlukan ketika waktu habis, misalnya, otomatis keluar
                jButtonLeaveActionPerformed(null);
            }

            // Tunggu 1 detik
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            // Kurangi waktu tersisa setiap detik
            timeRemainingInSeconds--;
        }
    }

    private void loadLyrics(String jsonFilePath) {
        Map<String, Lyric> lyricsMap = LyricLoader.loadLyrics(jsonFilePath);
        if (lyricsMap != null) {
            // Tampilkan lirik sesuai dengan lagu yang sedang diputar
            String currentSongTitle = "JudulLagu"; // Ganti dengan judul lagu yang sedang diputar
            Lyric currentLyric = lyricsMap.get(currentSongTitle);
            if (currentLyric != null) {
                PanelLyric.setText(currentLyric.getContent());
            }
        }
    }
    
    public void setLyric(String lyricText) {
        PanelLyric.setText(lyricText);
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        kGradientPanel1 = new keeptoo.KGradientPanel();
        jPanel1 = new javax.swing.JPanel();
        jPanel2 = new javax.swing.JPanel();
        jPanel7 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        jList1 = new javax.swing.JList<>();
        jScrollPane2 = new javax.swing.JScrollPane();
        PanelLyric = new javax.swing.JTextPane();
        jPanel3 = new javax.swing.JPanel();
        jPanel8 = new javax.swing.JPanel();
        jProgressBar1 = new javax.swing.JProgressBar();
        labelTimeElapsed = new javax.swing.JLabel();
        labelTimeRemaining = new javax.swing.JLabel();
        jPanel9 = new javax.swing.JPanel();
        labelMusicTitle = new javax.swing.JLabel();
        labelDetail = new javax.swing.JLabel();
        lebelImageAlbum = new javax.swing.JLabel();
        jPanel10 = new javax.swing.JPanel();
        jPanel4 = new javax.swing.JPanel();
        jPanel5 = new javax.swing.JPanel();
        btnRandom = new javax.swing.JButton();
        btnPrevious = new javax.swing.JButton();
        btnPlay = new javax.swing.JButton();
        btnNext = new javax.swing.JButton();
        btnRepeat = new javax.swing.JButton();
        jPanel6 = new javax.swing.JPanel();
        btnUpload = new javax.swing.JButton();
        btnDelete = new javax.swing.JButton();
        btnDeleteAll = new javax.swing.JButton();
        jButtonLeave = new javax.swing.JButton();
        jPanel11 = new javax.swing.JPanel();
        jLabelWelcome = new javax.swing.JLabel();
        jLabelTimeRemaining = new javax.swing.JLabel();
        jButtonAddDuration = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setResizable(false);

        kGradientPanel1.setkEndColor(new java.awt.Color(42, 41, 76));
        kGradientPanel1.setkStartColor(new java.awt.Color(189, 110, 111));
        kGradientPanel1.setPreferredSize(new java.awt.Dimension(1080, 610));

        jPanel1.setOpaque(false);

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 42, Short.MAX_VALUE)
        );

        jPanel2.setOpaque(false);

        jPanel7.setOpaque(false);

        jLabel1.setForeground(new java.awt.Color(255, 255, 255));
        jLabel1.setText("Play List");

        jList1.setBackground(new java.awt.Color(248, 248, 248));
        jList1.setForeground(new java.awt.Color(184, 169, 169));
        jList1.setSelectionBackground(new java.awt.Color(171, 98, 126));
        jList1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jList1MouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(jList1);

        jScrollPane2.setViewportView(PanelLyric);

        javax.swing.GroupLayout jPanel7Layout = new javax.swing.GroupLayout(jPanel7);
        jPanel7.setLayout(jPanel7Layout);
        jPanel7Layout.setHorizontalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel7Layout.createSequentialGroup()
                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel7Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 465, Short.MAX_VALUE))
                    .addGroup(jPanel7Layout.createSequentialGroup()
                        .addGap(112, 112, 112)
                        .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 82, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(jPanel7Layout.createSequentialGroup()
                        .addGap(8, 8, 8)
                        .addComponent(jScrollPane2)))
                .addContainerGap())
        );
        jPanel7Layout.setVerticalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel7Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 149, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 149, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel7, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addComponent(jPanel7, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jPanel3.setOpaque(false);

        jPanel8.setOpaque(false);

        labelTimeElapsed.setForeground(new java.awt.Color(184, 169, 169));
        labelTimeElapsed.setText("00:00:00");

        labelTimeRemaining.setForeground(new java.awt.Color(184, 169, 169));
        labelTimeRemaining.setText("00:00:00");

        javax.swing.GroupLayout jPanel8Layout = new javax.swing.GroupLayout(jPanel8);
        jPanel8.setLayout(jPanel8Layout);
        jPanel8Layout.setHorizontalGroup(
            jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel8Layout.createSequentialGroup()
                .addGap(23, 23, 23)
                .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel8Layout.createSequentialGroup()
                        .addComponent(labelTimeElapsed, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(labelTimeRemaining, javax.swing.GroupLayout.PREFERRED_SIZE, 54, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jProgressBar1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        jPanel8Layout.setVerticalGroup(
            jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel8Layout.createSequentialGroup()
                .addContainerGap(22, Short.MAX_VALUE)
                .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(labelTimeElapsed)
                    .addComponent(labelTimeRemaining))
                .addGap(18, 18, 18)
                .addComponent(jProgressBar1, javax.swing.GroupLayout.PREFERRED_SIZE, 8, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        jPanel9.setOpaque(false);

        labelMusicTitle.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        labelMusicTitle.setForeground(new java.awt.Color(255, 255, 255));
        labelMusicTitle.setText("Music Title");

        labelDetail.setForeground(new java.awt.Color(184, 169, 169));
        labelDetail.setText("Artist Name");

        javax.swing.GroupLayout jPanel9Layout = new javax.swing.GroupLayout(jPanel9);
        jPanel9.setLayout(jPanel9Layout);
        jPanel9Layout.setHorizontalGroup(
            jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel9Layout.createSequentialGroup()
                .addGap(27, 27, 27)
                .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(labelDetail, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(jPanel9Layout.createSequentialGroup()
                        .addComponent(labelMusicTitle, javax.swing.GroupLayout.PREFERRED_SIZE, 307, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
            .addGroup(jPanel9Layout.createSequentialGroup()
                .addGap(50, 50, 50)
                .addComponent(lebelImageAlbum, javax.swing.GroupLayout.PREFERRED_SIZE, 257, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel9Layout.setVerticalGroup(
            jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel9Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(lebelImageAlbum, javax.swing.GroupLayout.DEFAULT_SIZE, 209, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(labelMusicTitle)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 20, Short.MAX_VALUE)
                .addComponent(labelDetail))
        );

        jPanel10.setOpaque(false);

        javax.swing.GroupLayout jPanel10Layout = new javax.swing.GroupLayout(jPanel10);
        jPanel10.setLayout(jPanel10Layout);
        jPanel10Layout.setHorizontalGroup(
            jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );
        jPanel10Layout.setVerticalGroup(
            jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel8, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(jPanel9, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(jPanel10, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                .addComponent(jPanel10, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel9, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel8, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        jPanel4.setOpaque(false);

        jPanel5.setOpaque(false);

        btnRandom.setIcon(new javax.swing.ImageIcon(getClass().getResource("/view/images/random.png"))); // NOI18N
        btnRandom.setBorderPainted(false);
        btnRandom.setContentAreaFilled(false);
        btnRandom.setFocusPainted(false);
        btnRandom.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                randomClicked(evt);
            }
        });

        btnPrevious.setIcon(new javax.swing.ImageIcon(getClass().getResource("/view/images/previous.png"))); // NOI18N
        btnPrevious.setBorderPainted(false);
        btnPrevious.setContentAreaFilled(false);
        btnPrevious.setFocusPainted(false);
        btnPrevious.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                previousClicked(evt);
            }
        });

        btnPlay.setIcon(new javax.swing.ImageIcon(getClass().getResource("/view/images/play.png"))); // NOI18N
        btnPlay.setBorderPainted(false);
        btnPlay.setContentAreaFilled(false);
        btnPlay.setFocusPainted(false);
        btnPlay.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                playClicked(evt);
            }
        });

        btnNext.setIcon(new javax.swing.ImageIcon(getClass().getResource("/view/images/next.png"))); // NOI18N
        btnNext.setBorderPainted(false);
        btnNext.setContentAreaFilled(false);
        btnNext.setFocusPainted(false);
        btnNext.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                nextClicked(evt);
            }
        });

        btnRepeat.setIcon(new javax.swing.ImageIcon(getClass().getResource("/view/images/repeat.png"))); // NOI18N
        btnRepeat.setBorderPainted(false);
        btnRepeat.setContentAreaFilled(false);
        btnRepeat.setFocusPainted(false);
        btnRepeat.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                repeatClicked(evt);
            }
        });

        javax.swing.GroupLayout jPanel5Layout = new javax.swing.GroupLayout(jPanel5);
        jPanel5.setLayout(jPanel5Layout);
        jPanel5Layout.setHorizontalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addGap(23, 23, 23)
                .addComponent(btnRandom, javax.swing.GroupLayout.PREFERRED_SIZE, 42, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(26, 26, 26)
                .addComponent(btnPrevious, javax.swing.GroupLayout.PREFERRED_SIZE, 41, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(27, 27, 27)
                .addComponent(btnPlay, javax.swing.GroupLayout.PREFERRED_SIZE, 42, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(28, 28, 28)
                .addComponent(btnNext, javax.swing.GroupLayout.PREFERRED_SIZE, 42, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(27, 27, 27)
                .addComponent(btnRepeat, javax.swing.GroupLayout.PREFERRED_SIZE, 44, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(41, Short.MAX_VALUE))
        );
        jPanel5Layout.setVerticalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel5Layout.createSequentialGroup()
                        .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(btnRepeat, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                .addComponent(btnNext, javax.swing.GroupLayout.PREFERRED_SIZE, 41, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(btnPrevious, javax.swing.GroupLayout.PREFERRED_SIZE, 41, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(btnRandom, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addContainerGap())
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel5Layout.createSequentialGroup()
                        .addComponent(btnPlay)
                        .addGap(4, 4, 4))))
        );

        jPanel6.setOpaque(false);

        btnUpload.setForeground(new java.awt.Color(204, 255, 204));
        btnUpload.setText("add music");
        btnUpload.setBorderPainted(false);
        btnUpload.setContentAreaFilled(false);
        btnUpload.setFocusPainted(false);
        btnUpload.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                uploadClicked(evt);
            }
        });

        btnDelete.setForeground(new java.awt.Color(204, 255, 204));
        btnDelete.setText("delete music");
        btnDelete.setBorderPainted(false);
        btnDelete.setContentAreaFilled(false);
        btnDelete.setFocusPainted(false);
        btnDelete.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                deleteClicked(evt);
            }
        });

        btnDeleteAll.setForeground(new java.awt.Color(204, 255, 204));
        btnDeleteAll.setText("remove all playlist");
        btnDeleteAll.setBorderPainted(false);
        btnDeleteAll.setContentAreaFilled(false);
        btnDeleteAll.setFocusPainted(false);
        btnDeleteAll.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                deleteAllClicked(evt);
            }
        });

        javax.swing.GroupLayout jPanel6Layout = new javax.swing.GroupLayout(jPanel6);
        jPanel6.setLayout(jPanel6Layout);
        jPanel6Layout.setHorizontalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel6Layout.createSequentialGroup()
                .addGap(34, 34, 34)
                .addComponent(btnUpload)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnDelete, javax.swing.GroupLayout.PREFERRED_SIZE, 111, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 114, Short.MAX_VALUE)
                .addComponent(btnDeleteAll, javax.swing.GroupLayout.PREFERRED_SIZE, 135, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        jPanel6Layout.setVerticalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel6Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(btnDelete, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(btnDeleteAll, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(btnUpload, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(35, 35, 35))
        );

        jButtonLeave.setText("Leave");
        jButtonLeave.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonLeaveActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel6, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel4Layout.createSequentialGroup()
                        .addGap(413, 413, 413)
                        .addComponent(jButtonLeave)))
                .addContainerGap())
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addGap(0, 26, Short.MAX_VALUE)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel4Layout.createSequentialGroup()
                        .addComponent(jPanel5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(58, 58, 58))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel4Layout.createSequentialGroup()
                        .addComponent(jPanel6, javax.swing.GroupLayout.PREFERRED_SIZE, 47, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jButtonLeave)
                        .addContainerGap())))
        );

        jPanel11.setOpaque(false);

        jLabelWelcome.setText("Selamat Datang, ");

        jLabelTimeRemaining.setText("Time Remaining: ");

        javax.swing.GroupLayout jPanel11Layout = new javax.swing.GroupLayout(jPanel11);
        jPanel11.setLayout(jPanel11Layout);
        jPanel11Layout.setHorizontalGroup(
            jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel11Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabelWelcome)
                    .addComponent(jLabelTimeRemaining))
                .addContainerGap(238, Short.MAX_VALUE))
        );
        jPanel11Layout.setVerticalGroup(
            jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel11Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabelWelcome)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabelTimeRemaining)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jButtonAddDuration.setText("Add Duration");
        jButtonAddDuration.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonAddDurationActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout kGradientPanel1Layout = new javax.swing.GroupLayout(kGradientPanel1);
        kGradientPanel1.setLayout(kGradientPanel1Layout);
        kGradientPanel1Layout.setHorizontalGroup(
            kGradientPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, kGradientPanel1Layout.createSequentialGroup()
                .addGroup(kGradientPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(kGradientPanel1Layout.createSequentialGroup()
                        .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED))
                    .addGroup(kGradientPanel1Layout.createSequentialGroup()
                        .addGap(17, 17, 17)
                        .addComponent(jPanel11, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 234, Short.MAX_VALUE)))
                .addGroup(kGradientPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, kGradientPanel1Layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addGroup(kGradientPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jButtonAddDuration, javax.swing.GroupLayout.PREFERRED_SIZE, 101, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(16, 16, 16))))
            .addGroup(kGradientPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        kGradientPanel1Layout.setVerticalGroup(
            kGradientPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(kGradientPanel1Layout.createSequentialGroup()
                .addGroup(kGradientPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(kGradientPanel1Layout.createSequentialGroup()
                        .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jButtonAddDuration, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(23, 23, 23))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, kGradientPanel1Layout.createSequentialGroup()
                        .addComponent(jPanel11, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(21, 21, 21)))
                .addGroup(kGradientPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(14, 14, 14))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(kGradientPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(kGradientPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    //=====================================================================================================  hovers
    private void changeImage(String path, JButton button) {
        Image img = null;
        try {
            img = ImageIO.read(getClass().getResource(path));
        } catch (IOException ex) {
            Logger.getLogger(MediaPlayer.class.getName()).log(Level.SEVERE, null, ex);
        }
        button.setIcon(new ImageIcon(img));
    }
//======================================================================================================END hovers

///////////////////////////////////////////////////////////////////////////////////////end close y minimize operations    

    private void randomClicked(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_randomClicked
        repeat = false;
    }//GEN-LAST:event_randomClicked

    private void previousClicked(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_previousClicked
        controller.preview();
    }//GEN-LAST:event_previousClicked

    private void playClicked(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_playClicked

        //switch can be used
        if (playState.equals("PLAYING")) {
//            playImageEntered = "images/play_h.png";
            playImageExited = "images/play.png";
            changeImage(playImageExited, btnPlay);
            controller.pause();
            playState = "PAUSED";
            return;
        }

        if (playState.equals("SELECTED")) {
//            playImageEntered = "images/pause_h.png";
            playImageExited = "images/pause.png";
            changeImage(playImageExited, btnPlay);
            controller.play(selectedIndex);
            playState = "PLAYING";
            return;
        }

        if (playState.equals("PAUSED")) {
//            playImageEntered = "images/pause_h.png";
            playImageExited = "images/pause.png";
            changeImage(playImageExited, btnPlay);
            controller.resume();
            playState = "PLAYING";
            return;
        }
    }//GEN-LAST:event_playClicked

    private void nextClicked(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_nextClicked
        controller.next();
    }//GEN-LAST:event_nextClicked

    private void repeatClicked(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_repeatClicked
        repeat = !repeat;
        if (repeat) {
            repeatImageEntered = "images/repeat.png";
            repeatImageExited = "images/repeatSelected.png";
            changeImage(repeatImageExited, btnRepeat);
        } else {
            repeatImageEntered = "images/repeat_h.png";
            repeatImageExited = "images/repeat.png";
            changeImage(repeatImageExited, btnRepeat);
        }
        random = false;
    }//GEN-LAST:event_repeatClicked

    private void deleteClicked(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_deleteClicked
        controller.deleteMusic(jList1.getSelectedIndex());
        playImageEntered = "images/play_h.png";
        playImageExited = "images/play.png";
        changeImage(playImageExited, btnPlay);
    }//GEN-LAST:event_deleteClicked

    private void deleteAllClicked(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_deleteAllClicked
        controller.deleteAllMusics();
        playImageEntered = "images/play_h.png";
        playImageExited = "images/play.png";
        changeImage(playImageExited, btnPlay);
    }//GEN-LAST:event_deleteAllClicked

    private void jList1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jList1MouseClicked
        if (evt.getClickCount() == 2) {

            selectedIndex = jList1.getSelectedIndex();

            controller.play(selectedIndex);

            playImageEntered = "images/pause_h.png";
            playImageExited = "images/pause.png";
            changeImage(playImageExited, btnPlay);

            playState = "PLAYING";

        } else {

            selectedIndex = jList1.getSelectedIndex();

            controller.setDetail(selectedIndex);

            playState = "SELECTED";

        }
    }//GEN-LAST:event_jList1MouseClicked

    public void addDuration(int additionalHours) {
        timeRemaining += additionalHours; // Tambahkan durasi ke waktu tersisa
        updateTimeRemaining(timeRemaining); // Perbarui label waktu tersisa
    }

    private void jButtonAddDurationActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonAddDurationActionPerformed
        addDuration(1); // Tambahkan durasi satu jam

        // Perbarui label waktu tersisa
//        updateTimeRemaining(timeRemaining);
    }//GEN-LAST:event_jButtonAddDurationActionPerformed

    private void jButtonLeaveActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonLeaveActionPerformed
        if (controller != null) {
            controller.switchToTransaksi();
        } else {
            System.err.println("musicController is null");
        }
    }//GEN-LAST:event_jButtonLeaveActionPerformed

    private void uploadClicked(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_uploadClicked
        controller.addFiles(jList1);
    }//GEN-LAST:event_uploadClicked

    /**
     * @param args the command line arguments
     */

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JTextPane PanelLyric;
    private javax.swing.JButton btnDelete;
    private javax.swing.JButton btnDeleteAll;
    private javax.swing.JButton btnNext;
    private javax.swing.JButton btnPlay;
    private javax.swing.JButton btnPrevious;
    private javax.swing.JButton btnRandom;
    private javax.swing.JButton btnRepeat;
    private javax.swing.JButton btnUpload;
    private javax.swing.JButton jButtonAddDuration;
    private javax.swing.JButton jButtonLeave;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabelTimeRemaining;
    private javax.swing.JLabel jLabelWelcome;
    private javax.swing.JList<String> jList1;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel10;
    private javax.swing.JPanel jPanel11;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JPanel jPanel6;
    private javax.swing.JPanel jPanel7;
    private javax.swing.JPanel jPanel8;
    private javax.swing.JPanel jPanel9;
    private javax.swing.JProgressBar jProgressBar1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private keeptoo.KGradientPanel kGradientPanel1;
    private javax.swing.JLabel labelDetail;
    private javax.swing.JLabel labelMusicTitle;
    private javax.swing.JLabel labelTimeElapsed;
    private javax.swing.JLabel labelTimeRemaining;
    private javax.swing.JLabel lebelImageAlbum;
    // End of variables declaration//GEN-END:variables

    //variables
    private int selectedIndex;

    private MediaPlayerController controller;

    public TransaksiModel getTransaksiModel() {
        return transaksiModel;
    }
}
