/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Controller;

import Model.PaketModel;
import Model.User;
import Model.UserModel;
import View.Login;
import View.Paket;
import View.Register;

/**
 *
 * @author LENOVO
 */
public class LoginController {

    private UserModel userModel;
    private Login loginView;
    private Register registerView;

    public LoginController(UserModel userModel, Login loginView) {
        this.userModel = userModel;
        this.loginView = loginView;
        this.registerView = new Register(userModel, new RegisterController(userModel, this));
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
        for (User user : userModel.getUsers()) {
            if (user.getRole().equals(role) && user.getUsername().equals(username) && user.getPassword().equals(password)) {
                return user;
            }
        }
        return null;
    }

    // Metode untuk mendapatkan tampilan login
    // Metode untuk mengatur tampilan login
    // Metode untuk mendapatkan tampilan login
    public Login getLoginView() {
        return loginView;
    }

    public void switchToPaket(String username) {
        // Membuat objek PaketModel
        PaketModel paketModel = new PaketModel();

        // Membuat objek Paket
        Paket paketView = new Paket(paketModel, username);

        // Membuat objek PaketController dan menghubungkannya dengan PaketModel
        PaketController paketController = new PaketController(paketModel, paketView);
        paketView.setPaketController(paketController);

        // Menampilkan frame Paket
        java.awt.EventQueue.invokeLater(() -> {
            paketView.setVisible(true);
        });

        // Menyembunyikan frame login
        loginView.setVisible(false);
    }

}
