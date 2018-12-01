package front;

import back.Playground;
import back.Tiles;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class Panel extends JPanel {
    private BufferedImage image;
    private Playground p;
    public Panel(Playground p) {
        super();
        this.p = p;
        initPanel();
    }

    private void initPanel() {
        loadImage();
        //setPreferredSize(new Dimension(image.getWidth(this), image.getHeight(this)));
    }

    private void loadImage() {

    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2d = (Graphics2D) g;
        for(int x=0; x< Playground.SIZE_X; x++)
            for(int y=0; y<Playground.SIZE_Y; y++)
                g2d.drawImage(p.getGrid()[x][y].getImg(), x*34, y*34, null);
    }

}
