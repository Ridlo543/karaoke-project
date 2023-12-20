package Controller;

import Model.User;
import View.Register;
import java.util.List;
import javax.swing.JOptionPane;

public class RegisterController {

    private List<User> userList;
    private Register registerView;
    private LoginController loginController;

    public RegisterController(List<User> userList, LoginController loginController) {
        this.userList = userList;
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
                userList.add(newUser);
                System.out.println("Register Berhasil!");
                showRegisterSuccessMessage("Register Berhasil!", "Registrasi Berhasil");
                switchToLogin();
            } else {
                System.out.println("Password tidak sesuai dengan konfirmasi!");
                showLoginFailedMessage("Login Gagal! Password tidak sesuai", "Login Gagal");
            }
        } catch (Exception e) {
            System.err.println("Error: " + e.getMessage());
        }
    }

    private void showRegisterSuccessMessage(String message, String title) {
        JOptionPane.showMessageDialog(null, message, title, JOptionPane.INFORMATION_MESSAGE);
    }

    private void showLoginFailedMessage(String message, String title) {
        JOptionPane.showMessageDialog(null, message, title, JOptionPane.ERROR_MESSAGE);
    }
}
