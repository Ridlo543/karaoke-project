package Controller;

import Model.PaketModel;
import Model.Ruangan;
import View.Paket;




/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author LENOVO
 */

public class PaketController {

    private final PaketModel paketModel;
    private final Paket paketView;

    public PaketController(PaketModel paketModel, Paket paketView) {
        this.paketModel = paketModel;
        this.paketView = paketView;
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
        String durasiSelected = paketView.getjComboBoxDurasi().getSelectedItem().toString();
        int durasi = Integer.parseInt(durasiSelected.split(" ")[0]);

        // Mengambil harga ruangan terpilih dari ComboBox
        String ruanganSelected = paketView.getjComboBoxRuangan().getSelectedItem().toString();
        int hargaRuanganPerJam = getHargaRuangan(ruanganSelected);

        // Menghitung total harga
        int totalHarga = durasi * hargaRuanganPerJam;
        
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

    public void switchToNextPage() {
        // Implementasi untuk pindah ke halaman berikutnya (MediaPlayer)
        // ...

        // Contoh: Setelah Next ditekan, kembali ke halaman login
        paketView.setVisible(false);
        paketView.dispose();  // Tutup frame Paket
        // Tambahkan logika untuk menampilkan halaman MediaPlayer atau yang lainnya
    }
}

