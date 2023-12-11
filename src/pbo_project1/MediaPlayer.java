/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package pbo_project1;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.time.Duration;
import java.time.Instant;

public class MediaPlayer extends JFrame {

    private TimeRemainingCalculator timeRemainingCalculator;
    private JLabel jLabelTimeRemaining;
    private Timer timer;
    private Instant target;
    private int duration;

    public MediaPlayer(int duration) {
        initComponents();
        this.duration = duration;
        this.timeRemainingCalculator = new TimeRemainingCalculator(duration);
        this.target = Instant.now().plus(Duration.ofHours(duration));
        initCustomComponents();
        startTimer();
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabelSelamatDatang = new javax.swing.JLabel();
        jButtonTambahDurasi = new javax.swing.JButton();
        jButtonLeave = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setPreferredSize(new java.awt.Dimension(1080, 608));
        setSize(new java.awt.Dimension(1080, 608));

        jLabelSelamatDatang.setText("Selamat Datang");

        jButtonTambahDurasi.setText("Tambah Durasi");
        jButtonTambahDurasi.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonTambahDurasiActionPerformed(evt);
            }
        });

        jButtonLeave.setText("Leave");
        jButtonLeave.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonLeaveActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jButtonTambahDurasi, javax.swing.GroupLayout.PREFERRED_SIZE, 124, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabelSelamatDatang, javax.swing.GroupLayout.PREFERRED_SIZE, 109, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 849, Short.MAX_VALUE)
                        .addComponent(jButtonLeave, javax.swing.GroupLayout.PREFERRED_SIZE, 95, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(15, 15, 15))))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jButtonLeave, javax.swing.GroupLayout.DEFAULT_SIZE, 36, Short.MAX_VALUE)
                    .addComponent(jLabelSelamatDatang, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(30, 30, 30)
                .addComponent(jButtonTambahDurasi, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(505, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jButtonTambahDurasiActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonTambahDurasiActionPerformed
        // TODO add your handling code here:
        tambahDurasi(1); // Menambah 1 jam
    }//GEN-LAST:event_jButtonTambahDurasiActionPerformed

    private void jButtonLeaveActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonLeaveActionPerformed
        // TODO add your handling code here:
        // Menampilkan frame Nota
//        NotaFrame notaFrame = new NotaFrame("Isi nota sesuai kebutuhan");
//        notaFrame.setVisible(true);

        // Menutup frame MediaPlayer
        this.dispose();
    }//GEN-LAST:event_jButtonLeaveActionPerformed

    /**
     * @param args the command line arguments
     */
    private void initCustomComponents() {
        jLabelTimeRemaining = new javax.swing.JLabel();
        jLabelTimeRemaining.setText("Time Remaining: " + timeRemainingCalculator.formatTimeRemaining());

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
                layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(layout.createSequentialGroup()
                                .addContainerGap()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addComponent(jLabelTimeRemaining)
                                        .addComponent(jLabelSelamatDatang))
                                .addContainerGap(296, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
                layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(layout.createSequentialGroup()
                                .addContainerGap()
                                .addComponent(jLabelSelamatDatang)
                                .addGap(4, 4, 4)
                                .addComponent(jLabelTimeRemaining)
                                .addContainerGap(258, Short.MAX_VALUE))
        );
        pack();
    }

    private void tambahDurasi(int hours) {
        // Menambah durasi sejumlah jam tertentu
        Instant newTarget = target.plus(Duration.ofHours(hours));
        Duration timeRemaining = Duration.between(Instant.now(), newTarget);

        if (timeRemaining.isNegative()) {
            // Jika waktu tambahan membuat target berada di masa lalu,
            // kita atur ulang target ke waktu sekarang
            target = Instant.now();
        } else {
            // Jika waktu tambahan masih di masa depan,
            // kita gunakan target yang baru
            target = newTarget;
        }

        jLabelTimeRemaining.setText("Time Remaining: " + timeRemainingCalculator.formatTimeRemaining());
    }

    private void startTimer() {
        int delay = 1000; // 1000 milliseconds = 1 second
        timer = new Timer(delay, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                updateTimer();
            }
        });
        timer.start();
    }

    private void updateTimer() {
        Duration timeRemaining = Duration.between(Instant.now(), target);
        if (timeRemaining.isNegative()) {
            // If the current moment is *after* the target, calculate a new target.
            target = Instant.now().plus(Duration.ofHours(duration));
            timeRemaining = Duration.between(Instant.now(), target);
        }
        jLabelTimeRemaining.setText("Time Remaining: " + formatTimeRemaining());
    }

    private String formatTimeRemaining() {
        Duration timeRemaining = Duration.between(Instant.now(), target);
        return String.format("%d hours, %d minutes, %d seconds",
                timeRemaining.toHoursPart(), timeRemaining.toMinutesPart(), timeRemaining.toSecondsPart());
    }

//    public static void main(String[] args) {
//        SwingUtilities.invokeLater(() -> {
//            new MediaPlayer(1).setVisible(true);
//        });
//    }
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(MediaPlayer.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(MediaPlayer.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(MediaPlayer.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(MediaPlayer.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new MediaPlayer(1).setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButtonLeave;
    private javax.swing.JButton jButtonTambahDurasi;
    private javax.swing.JLabel jLabelSelamatDatang;
    // End of variables declaration//GEN-END:variables
}