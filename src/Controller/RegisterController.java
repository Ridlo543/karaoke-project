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

    private UserModel userModel;
    private Register registerView;
    private LoginController loginController;

    public RegisterController(UserModel userModel, LoginController loginController) {
        this.userModel = userModel;
        this.loginController = loginController;
    }

    public void setRegisterView(Register registerView) {
        this.registerView = registerView;
    }

    public void switchToLogin() {
        if (registerView != null) {
            registerView.setVisible(false);
            loginController.getLoginView().setVisible(true);
        } else {
            System.err.println("RegisterView is null. Make sure it is set before calling switchToLogin.");
        }
    }

    public void processRegister(String role, String username, String telepon, String password, String confirmPassword) {
        try {
            if (password.equals(confirmPassword)) {
                User newUser = new User(role, username, telepon, password);
                userModel.addUser(newUser);
                System.out.println("Register Berhasil!");
                switchToLogin();
            } else {
                System.out.println("Password tidak sesuai dengan konfirmasi!");
            }
        } catch (Exception e) {
            System.err.println("Error: " + e.getMessage());
        }
    }
}
