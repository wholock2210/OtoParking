package otoparking.ui.Panel;

import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import javax.swing.JPanel;

public class PnCamera extends JPanel  {
    private static final long serialVersionUID = 1L;
    private BufferedImage image;

    public PnCamera() {
        setPreferredSize(new Dimension(640, 480));
        setBackground(java.awt.Color.BLACK);
    }

    public void updateImage(BufferedImage img) {
        this.image = img;
        repaint();
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        if (image != null) {
            g.drawImage(image, 0, 0, getWidth(), getHeight(), null);
        }
    }
}
