package Controller;

import Model.PaketModel;
import Model.TransaksiModel;
import Model.User;
import View.HistoryTransaksi;
import View.Login;
import View.Paket;
import View.Register;
import java.util.List;
import javax.swing.JOptionPane;

public class LoginController {

    private final List<User> userList;
    private final Login loginView;
    private final Register registerView;

    public LoginController(List<User> userList, Login loginView) {
        this.userList = userList;
        this.loginView = loginView;
        loginView.setLocationRelativeTo(null);
        this.registerView = new Register(userList, new RegisterController(userList, this));
        loginView.setLoginController(this);
    }

    public Login getLoginView() {
        return loginView;
    }

    public boolean processLogin(String role, String username, String password) {
        // Mencari user berdasarkan username
        User userByUsername = findUserByUsername(username);

        if (userByUsername != null) {
            // Jika username ditemukan, periksa password
            if (userByUsername.getPassword().equals(password)) {
                // Jika password sesuai, periksa rolenya
                if (userByUsername.getRole().equals(role)) {
                    // Implementasi logika setelah login berhasil
                    System.out.println("Login Berhasil!");
                    return true;
                } else {
                    // Jika rolenya tidak sesuai, tampilkan pesan kesalahan
                    showLoginFailedMessage("Login Gagal! Role yang dipilih tidak sesuai dengan data user.", "Login Gagal");
                }
            } else {
                // Jika password tidak sesuai, tampilkan pesan kesalahan
                showLoginFailedMessage("Login Gagal! Password tidak sesuai", "Login Gagal");
            }
        } else {
            // Jika username tidak ditemukan, tampilkan pesan kesalahan
            showLoginFailedMessage("Login Gagal! Username tidak ditemukan", "Login Gagal");
        }

        // Jika sampai di sini, artinya login gagal
        System.out.println("Login Gagal!");
        return false;
    }

    // Metode untuk memeriksa apakah rolenya sesuai dengan data user
    public boolean isRoleValid(String selectedRole) {
        for (User user : userList) {
            if (user.getRole().equals(selectedRole)) {
                return true;
            }
        }
        return false;
    }

    // Metode untuk mencari user berdasarkan username saja
    private User findUserByUsername(String username) {
        for (User user : userList) {
            if (user.getUsername().equals(username)) {
                return user;
            }
        }
        return null;
    }

    public void nextPage(String role, String username) {
        if (role.equals("User")) {
            switchToPaket(username);
        } else if (role.equals("Staff")) {
            switchToHistory();
        }
    }

    public void switchToRegister() {
        loginView.setVisible(false);
        registerView.setVisible(true);
    }

    private void switchToPaket(String username) {
        // Membuat objek PaketModel
        PaketModel paketModel = new PaketModel();
        TransaksiModel transaksiModel = new TransaksiModel();

        // Membuat objek Paket
        Paket paketView = new Paket(paketModel, username, transaksiModel);

        // Membuat objek PaketController dan menghubungkannya dengan PaketModel
        PaketController paketController = new PaketController(paketModel, paketView, transaksiModel);
        paketView.setPaketController(paketController);

        // Menampilkan frame Paket
        java.awt.EventQueue.invokeLater(() -> {
            paketView.setVisible(true);
        });

        // Menyembunyikan frame login
        loginView.dispose();
    }

    private void switchToHistory() {
        // Membuat objek HistoryTransaksi dengan mengirimkan objek LoginController
        HistoryTransaksi historyView = new HistoryTransaksi(this);

        // Menampilkan frame History
        java.awt.EventQueue.invokeLater(() -> {
            historyView.setVisible(true);
        });

        // Menyembunyikan frame login
        loginView.dispose();
    }

    private void showLoginFailedMessage(String message, String title) {
        JOptionPane.showMessageDialog(null, message, title, JOptionPane.ERROR_MESSAGE);
    }
}
