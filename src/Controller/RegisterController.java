package Controller;

import Model.User;
import View.Register;
import java.util.List;


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
                switchToLogin();
            } else {
                System.out.println("Password tidak sesuai dengan konfirmasi!");
            }
        } catch (Exception e) {
            System.err.println("Error: " + e.getMessage());
        }
    }
}
