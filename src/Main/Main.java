package Main;

import Controller.LoginController;
import Model.User;
import Util.FileHandler;
import View.Login;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Main {
    
    public static void main(String[] args) throws IOException {
        // Membuat file jika belum ada
        FileHandler.createTransaksiFile();
        
        List<User> userList = FileHandler.readUser();
        if (userList == null) {
            userList = new ArrayList();
        }

        // Membuat objek LoginController dan Login
        Login loginView = new Login(userList, null);
        LoginController loginController = new LoginController(userList, loginView);
        loginView.setLoginController(loginController);

        // Menampilkan halaman login
        java.awt.EventQueue.invokeLater(() -> {
            loginView.setVisible(true);
        });
        
        final List<User> finalUserList = userList;
        // Menyimpan data ke file JSON saat aplikasi ditutup
        Runtime.getRuntime().addShutdownHook(new Thread(() -> {
            try {
                FileHandler.writeUser(finalUserList);
            } catch (IOException ex) {
                Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
            }
        }));
    }
}