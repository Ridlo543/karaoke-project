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
import java.util.Date;

public class TransaksiController {
    private TransaksiModel transaksiModel;
    private Transaksi transaksiView;

    public TransaksiController(TransaksiModel transaksiModel, Transaksi transaksiView) {
        this.transaksiModel = transaksiModel;
        this.transaksiView = transaksiView;
        initController();
    }

    private void initController() {
        // Logika inisialisasi controller transaksi jika diperlukan
    }

    // Metode untuk menyimpan data transaksi ke dalam file JSON
    public void saveTransaksi() {
        try {
            FileHandler.createTransaksiFile();
            FileHandler.writeTransaksi(transaksiModel);
        } catch (IOException e) {
            e.printStackTrace();
            // Handle exception, misalnya dengan menampilkan pesan error
        }
    }
}
