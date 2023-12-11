package Controller;

import Model.User;
import Model.UserModel;
import View.Login;
import View.Register;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
/**
 *
 * @author LENOVO
 */
public class RegisterController {
    private final UserModel userModel;
    private Register registerView;
    private final LoginController loginController;

    public RegisterController(UserModel userModel, LoginController loginController) {
        this.userModel = userModel;
        this.registerView = registerView;
        this.loginController = loginController;
        registerView.setRegisterController(this);
    }

    public void switchToLogin() {
        registerView.setVisible(false);
        loginController.switchToLogin();
    }

    // Metode setRegisterView
    public void setRegisterView(Register registerView) {
        this.registerView = registerView;
    }

    public void processRegister(String role, String username, String telepon, String password, String confirmPassword) {
        // Tambahkan validasi atau logika registrasi sesuai kebutuhan
        // ...

        // Contoh penambahan user ke dalam UserModel
        userModel.addUser(new User(role, username, telepon, password));

        // Setelah registrasi, kembali ke halaman login
        switchToLogin();
    }

    
}