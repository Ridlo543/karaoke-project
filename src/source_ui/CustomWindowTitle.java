package source_ui;

import javax.swing.*;
import java.awt.*;

public class CustomWindowTitle {
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            JFrame frame = new JFrame("Custom Window Title");

            // Set warna judul jendela
            frame.setBackground(Color.BLUE);

            frame.setSize(400, 300);
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            frame.setVisible(true);
        });
    }
}
