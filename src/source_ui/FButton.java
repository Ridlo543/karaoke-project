package source_ui;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.JButton;

/**
 *
 * @author Reni Uswatun Hasanah
 */
public class FButton extends JButton {

    private boolean over;
    private Color fill;
    private Color line;

    private Color fillOriginal;
    private Color fillOver;
    private Color fillClick;
    private Color lineOriginal;
    private Color lineOver;
    private Color lineClick;
    private int strokeWidth;

    public FButton() {
        fillOriginal = new Color(148, 0, 255);
        fillOver = new Color(191, 111, 249);
        fillClick = new Color(174, 210, 255);
        lineOriginal = new Color(228, 241, 255);
        lineOver = new Color(191, 111, 249);
        lineClick = new Color(148, 0, 255);

        strokeWidth = 1;
        fill = fillOriginal;
        line = lineOriginal;
        setOpaque(false);
        setBorder(null);
        setFocusPainted(false);
        setContentAreaFilled(false);
        setBackground(fillOriginal);
        setForeground(new Color(228, 241, 255));
        // Atur font untuk teks tombol
        Font font = new Font("Poppins", Font.BOLD, 14);
        setFont(font);

        //tambahkan mouse event
        addMouseListener(new MouseAdapter() {
            @Override
            public void mouseExited(MouseEvent e) {
                fill = fillOriginal;
                line = lineOriginal;
                over = false;
            }

            @Override
            public void mouseEntered(MouseEvent e) {
                fill = fillOver;
                line = lineOver;
                over = true;
            }

            @Override
            public void mouseReleased(MouseEvent e) {
                if (over) {
                    fill = fillOver;
                    line = lineOver;
                } else {
                    fill = fillOriginal;
                    line = lineOriginal;
                }
            }

            @Override
            public void mousePressed(MouseEvent e) {
                fill = fillClick;
                line = lineClick;
            }

        });

    }

    public Color getFillOriginal() {
        return fillOriginal;
    }

    public void setFillOriginal(Color fillOriginal) {
        this.fillOriginal = fillOriginal;
    }

    public Color getFillOver() {
        return fillOver;
    }

    public void setFillOver(Color fillOver) {
        this.fillOver = fillOver;
    }

    public Color getFillClick() {
        return fillClick;
    }

    public void setFillClick(Color fillClick) {
        this.fillClick = fillClick;
    }

    public Color getLineOriginal() {
        return lineOriginal;
    }

    public void setLineOriginal(Color lineOriginal) {
        this.lineOriginal = lineOriginal;
    }

    public Color getLineOver() {
        return lineOver;
    }

    public void setLineOver(Color lineOver) {
        this.lineOver = lineOver;
    }

    public Color getLineClick() {
        return lineClick;
    }

    public void setLineClick(Color lineClick) {
        this.lineClick = lineClick;
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
            //gambar background
            g2d.setColor(fill);
            g2d.fillRoundRect(s, s, w, h, h-10, h-10);

        }
        super.paintComponent(g);
    }
}
