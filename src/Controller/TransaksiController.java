package Controller;

import Model.TransaksiModel;
import Model.User;
import Util.FileHandler;
import View.Login;
import View.Transaksi;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;

public class TransaksiController {

    private final TransaksiModel transaksiModel;
    private final Transaksi transaksiView;

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
        }
    }

    public void switchToLogin() throws IOException {
        int confirmResult = JOptionPane.showOptionDialog(
                null,
                "Terimakasih telah berkaraoke!\nKlik OK untuk kembali ke halaman login.",
                "Terimakasih",
                JOptionPane.DEFAULT_OPTION,
                JOptionPane.INFORMATION_MESSAGE,
                null,
                new Object[]{"OK"},
                "OK"
        );

        if (confirmResult == JOptionPane.OK_OPTION) {
            // Membuat objek LoginController dan Login
            List<User> userList = FileHandler.readUser();
            if (userList == null) {
                userList = new ArrayList();
            }
            LoginController loginController = new LoginController(userList, new Login(userList, null));
            loginController.getLoginView().setLoginController(loginController);

            // Menampilkan halaman login
            java.awt.EventQueue.invokeLater(() -> {
                loginController.getLoginView().setVisible(true);

            });

            // Menutup frame transaksi
            transaksiView.dispose();
        }
    }

    public Transaksi getTransaksiView() {
        return transaksiView;
    }
}
