/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package source_ui;

/**
 *
 * @author Reni Uswatun Hasanah
 */
import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import javax.swing.BorderFactory;
import javax.swing.JTextField;

public class FTextField extends JTextField {

    private Color fillColor;
    private Color lineColor;
    private int strokeWidth;

    public FTextField() {
        fillColor = new Color(174, 210, 255);
        lineColor = new Color(148, 0, 255);
        strokeWidth = 1;
        setOpaque(false);
        setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        // Atur font untuk teks tombol
        Font font = new Font("Poppins", Font.TYPE1_FONT, 12);
        setFont(font);
        setForeground(new Color(148, 0, 255));
    }

    public Color getFillColor() {
        return fillColor;
    }

    public void setFillColor(Color fillColor) {
        this.fillColor = fillColor;
    }

    public Color getLineColor() {
        return lineColor;
    }

    public void setLineColor(Color lineColor) {
        this.lineColor = lineColor;
    }

    public int getStrokeWidth() {
        return strokeWidth;
    }

    public void setStrokeWidth(int strokeWidth) {
        this.strokeWidth = strokeWidth;
    }

    @Override
    protected void paintComponent(Graphics g) {
        if (!isOpaque()) {
            Graphics2D g2d = (Graphics2D) g;
            g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
            int s = strokeWidth;
            int w = getWidth() - (2 * s);
            int h = getHeight() - (2 * s);
            g2d.setColor(fillColor);
            g2d.fillRoundRect(s, s, w, h, h-3, h-3);
            g2d.setStroke(new BasicStroke(s));
            g2d.setColor(lineColor);
            g2d.drawRoundRect(s, s, w, h, h-3, h-3);
        }
        super.paintComponent(g);
    }

}
