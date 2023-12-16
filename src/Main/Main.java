/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Main;

import Controller.LoginController;
import Model.User;
import Model.UserModel;
import Util.FileHandler;
import View.Login;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author LENOVO
 */
public class Main {
    
    public static void main(String[] args) throws IOException {
        // Membuat file jika belum ada
        FileHandler.createTransaksiFile();

        // Membaca data dari file JSON atau membuat objek UserModel baru jika tidak ada
        UserModel userModel = FileHandler.readUser();
        if (userModel == null) {
            userModel = new UserModel();
        }

        // Membuat objek LoginController dan Login
        Login loginView = new Login(userModel, null);
        LoginController loginController = new LoginController(userModel, loginView);
        loginView.setLoginController(loginController);

        // Menampilkan halaman login
        java.awt.EventQueue.invokeLater(() -> {
            loginController.getLoginView().setVisible(true);
        });
        
        final UserModel finalUserModel = userModel;
        // Menyimpan data ke file JSON saat aplikasi ditutup
        Runtime.getRuntime().addShutdownHook(new Thread(() -> {
            try {
                FileHandler.writeUser(finalUserModel);
            } catch (IOException ex) {
                Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
            }
        }));
    }
}
