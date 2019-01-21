package back;


import front.Frame;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public enum Tiles {
    EMPTY(Frame.imgPaths + "empty.png"),
    UPPER_FIELD(Frame.imgPaths + "upper_field.png"),
    BALL_RED(Frame.imgPaths + "ball_red.png"),
    BALL_GREEN(Frame.imgPaths + "ball_green.png"),
    ARROW_LEFT(Frame.imgPaths + "arrow_left.png"),
    ARROW_RIGHT(Frame.imgPaths + "arrow_right.png"),
    ARROW_DOWN(Frame.imgPaths + "arrow_down.png"),
    BLOCK(Frame.imgPaths + "block.png"),
    TELEPORT(Frame.imgPaths + "teleport.png"),
    POINTS(Frame.imgPaths + "points.png"),
    SPRITE_RED(Frame.imgPaths + "sprite_red.png"),
    SPRITE_GREEN(Frame.imgPaths + "sprite_green.png");

    private BufferedImage img;

    Tiles(String path) {
        File imageFile = new File(path);
        try {
            this.img = ImageIO.read(imageFile);
        } catch (IOException e) {
            System.err.println("Blad odczytu obrazka z pliku " + path);
            e.printStackTrace();
        }
    }

    public Image getImg() {
        return img;
    }
}
