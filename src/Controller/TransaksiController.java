/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Controller;

/**
 *
 * @author LENOVO
 */
import Model.TransaksiModel;
import Util.FileHandler;
import View.Transaksi;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class TransaksiController {
    private final TransaksiModel transaksiModel;

    public TransaksiController(TransaksiModel transaksiModel) {
        this.transaksiModel = transaksiModel;
        initController();
    }

    private void initController() {
        // Logika inisialisasi controller transaksi jika diperlukan
    }

    public void saveTransaksi() {
        try {
            FileHandler.createTransaksiFile();
            // Baca data yang sudah ada dari file
            List<TransaksiModel> existingData = FileHandler.readTransaksiList();

            // Pastikan existingData tidak null
            if (existingData == null) {
                existingData = new ArrayList<>();
            }

            // Tambahkan transaksi baru ke dalam daftar
            existingData.add(transaksiModel);

            // Tulis kembali ke file
            FileHandler.writeTransaksiList(existingData);
        } catch (IOException e) {
            e.printStackTrace();
            // Handle exception, misalnya dengan menampilkan pesan error
        }
    }
    
}
