package back;


import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

public enum Tiles {
    EMPTY(0, "BoloBall/src/img/empty.png"),
    UPPER_FIELD(1, "BoloBall/src/img/upper_field.png"),
    BALL_RED(2, "BoloBall/src/img/ball_red.png"),
    BALL_GREEN(3, "BoloBall/src/img/ball_green.png"),
    ARROW_LEFT(4, "BoloBall/src/img/arrow_left.png"),
    ARROW_RIGHT(5, "BoloBall/src/img/arrow_right.png"),
    BLOCK(6, "BoloBall/src/img/block.png"),
    TELEPORT(7, "BoloBall/src/img/teleport.png"),
    POINTS(8, "BoloBall/src/img/points.png");

    private int code;
    private BufferedImage img;



    Tiles(int code, String path) {
        this.code = code;
        File imageFile = new File(path);
        try {
            this.img = ImageIO.read(imageFile);
        } catch (IOException e) {
            System.err.println("Blad odczytu obrazka");
            e.printStackTrace();
        }
    }

    public int getCode() {
        return this.code;
    }

    public Image getImg() {
        return img;
    }
}
