/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Controller;

import Model.User;
import Model.UserModel;
import View.Login;
import View.Register;

/**
 *
 * @author LENOVO
 */
public class LoginController {

    private final UserModel userModel;
    private Login loginView;
    private Register registerView;

    public LoginController(UserModel userModel, Login loginView) {
        this.userModel = userModel;
        this.loginView = loginView;
        this.registerView = registerView;
        loginView.setLoginController(this);
        registerView.setRegisterController(new RegisterController(userModel, this));
    }

    public void switchToRegister() {
        loginView.setVisible(false);
        registerView.setVisible(true);
    }

    public void switchToLogin() {
        registerView.setVisible(false);
        loginView.setVisible(true);
    }

    public void processLogin(String role, String username, String password) {
        // Verifikasi login
        User user = findUser(role, username, password);
        if (user != null) {
            // Implementasi logika setelah login berhasil
            System.out.println("Login Berhasil!");
        } else {
            // Implementasi logika setelah login gagal
            System.out.println("Login Gagal!");
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

}
