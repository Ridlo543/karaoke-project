package Controller;

import Main.Main;
import Model.TransaksiModel;
import Model.User;
import Util.FileHandler;
import View.Login;
import View.Transaksi;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class TransaksiController {

    private final TransaksiModel transaksiModel;
    private Transaksi transaksiView;

    public TransaksiController(TransaksiModel transaksiModel, Transaksi transaksiView) {
        this.transaksiModel = transaksiModel;
        this.transaksiView = transaksiView;

    

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

    // Transaksi to Login
    public void switchToLogin() throws IOException {
        LoginController loginController = Main.createLoginController(new ArrayList<>());
        java.awt.EventQueue.invokeLater(() -> {
            loginController.getLoginView().setVisible(true);
        });
        transaksiView.dispose();
    }
    
    public Transaksi getTransaksiView() {
        return transaksiView;
    }
}
