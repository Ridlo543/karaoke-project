package Controller;

import Model.PaketModel;
import Model.TransaksiModel;
import Model.User;
import View.Login;
import View.Paket;
import View.Register;
import java.util.List;

public class LoginController {

    private List<User> userList;
    private Login loginView;
    private Register registerView;

    public LoginController(List<User> userList, Login loginView) {
        this.userList = userList;
        this.loginView = loginView;
        loginView.setLocationRelativeTo(null);
        this.registerView = new Register(userList, new RegisterController(userList, this));
        loginView.setLoginController(this);
    }

    public void switchToRegister() {
        loginView.setVisible(false);
        registerView.setVisible(true);
    }

    public boolean processLogin(String role, String username, String password) {
        // Verifikasi login
        User user = findUser(role, username, password);

        if (user != null) {
            // Implementasi logika setelah login berhasil
            System.out.println("Login Berhasil!");
            return true;
        } else {
            // Implementasi logika setelah login gagal
            System.out.println("Login Gagal!");
            
            return false;
        }
    }

    private User findUser(String role, String username, String password) {
        for (User user : userList) {
            if (user.getRole().equals(role) && user.getUsername().equals(username) && user.getPassword().equals(password)) {
                return user;
            }
        }
        return null;
    }

    public Login getLoginView() {
        return loginView;
    }

    public void switchToPaket(String username) {
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
        loginView.setVisible(false);
    }
    
    public void switchToHistory(){
        
    }
}
