package Controller;

import Model.PaketModel;
import Model.Ruangan;
import Model.TransaksiModel;
import Util.FileHandler;
import View.MediaPlayer;
import View.Paket;
import View.Transaksi;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class PaketController {

    private final PaketModel paketModel;
    private final Paket paketView;
    private TransaksiModel transaksiModel;

    public PaketController(PaketModel paketModel, Paket paketView, TransaksiModel transaksiModel) {
        this.paketModel = paketModel;
        this.paketView = paketView;
        this.transaksiModel = transaksiModel;
        initController();
    }

    private void initController() {
        // Menambahkan ActionListener untuk menghitung total harga saat perubahan pada ComboBox durasi atau ruangan
        paketView.getjComboBoxDurasi().addActionListener(e -> updateTotal());
        paketView.getjComboBoxRuangan().addActionListener(e -> updateTotal());

        // Melakukan update total saat pertama kali aplikasi dijalankan
        updateTotal();
    }

    private void updateTotal() {
        String durasiSelected = "";
        if (paketView.getjComboBoxDurasi().getSelectedItem() != null) {
            durasiSelected = paketView.getjComboBoxDurasi().getSelectedItem().toString();
        }
        int durasi = Integer.parseInt(durasiSelected.split(" ")[0]);

        // Mengambil harga ruangan terpilih dari ComboBox
        String ruanganSelected = paketView.getjComboBoxRuangan().getSelectedItem().toString();
        int hargaRuanganPerJam = getHargaRuangan(ruanganSelected);

        // Menghitung total harga
        int totalHarga = durasi * hargaRuanganPerJam;

        // Pemeriksaan null sebelum memanggil metode pada objek transaksiModel
        if (transaksiModel != null) {
            transaksiModel.setTotalHarga(totalHarga);
        }

        // Menampilkan total harga pada JLabel
        paketView.getjLabelTotal().setText("Total: " + totalHarga);
    }

    private int getHargaRuangan(String ruanganSelected) {
        // Menemukan harga ruangan dari teks yang dipilih di ComboBox
        for (Ruangan ruangan : paketModel.getRuanganList()) {
            if (ruanganSelected.contains(ruangan.getNama())) {
                return ruangan.getHargaPerJam();
            }
        }
        return 0; // Mengembalikan 0 jika tidak ada harga yang ditemukan
    }

//    public void saveTransaksi(Date tanggalTransaksi, String username, int durasi, int totalHarga) {
//        // Menyimpan data transaksi ke dalam model
//        transaksiModel.setTanggalTransaksi(tanggalTransaksi);
//        transaksiModel.setUsername(username);
//        transaksiModel.setDurasi(durasi);
//        transaksiModel.setTotalHarga(totalHarga);
//
//        // Menyimpan model transaksi ke dalam file JSON
//        try {
//            FileHandler.createTransaksiFile();
//            // Baca data yang sudah ada dari file
//            List<TransaksiModel> existingData = FileHandler.readTransaksiList();
//
//            // Pastikan existingData tidak null
//            if (existingData == null) {
//                existingData = new ArrayList<>();
//            }
//
//            // Tambahkan transaksi baru ke dalam daftar
//            existingData.add(transaksiModel);
//
//            // Tulis kembali ke file
//            FileHandler.writeTransaksiList(existingData);
//        } catch (IOException e) {
//            e.printStackTrace();
//            // Handle exception, misalnya dengan menampilkan pesan error
//        }
//    }

//    public void switchToTransaksi() {
//
//        // Menampilkan halaman transaksi
//        Transaksi transaksiFrame = new Transaksi(transaksiModel);
//        paketView.setVisible(false);
//        paketView.dispose();
//        transaksiFrame.setVisible(true);
//    }
    public void switchToMediaPlayer(String username, int timeRemaining) {
        if (transaksiModel != null) {
            MediaPlayer mediaPlayerFrame = new MediaPlayer(username, timeRemaining, transaksiModel);
//            System.out.println("durasi: "+timeRemaining);
            paketView.setVisible(false);
            paketView.dispose();
            mediaPlayerFrame.showMediaPlayer();
        } else {
            System.err.println("TransaksiModel is null");
            // Lakukan penanganan kesalahan atau tindakan yang sesuai
        }
    }

    public void setWelcomeLabel(String username) {
        // Menampilkan selamat datang dengan nama pengguna pada JLabel
        paketView.getjLabelWelcome().setText("Selamat Datang, " + username);
    }

    public void setTransaksiModel(TransaksiModel transaksiModel) {
        this.transaksiModel = transaksiModel;
    }

    public TransaksiModel getTransaksiModel() {
        return transaksiModel;
    }
}
