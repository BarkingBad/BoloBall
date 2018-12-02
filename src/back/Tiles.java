package back;


import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public enum Tiles {
    EMPTY( "BoloBall/src/img/empty.png"),
    UPPER_FIELD( "BoloBall/src/img/upper_field.png"),
    BALL_RED( "BoloBall/src/img/ball_red.png"),
    BALL_GREEN( "BoloBall/src/img/ball_green.png"),
    ARROW_LEFT( "BoloBall/src/img/arrow_left.png"),
    ARROW_RIGHT( "BoloBall/src/img/arrow_right.png"),
    ARROW_DOWN("BoloBall/src/img/arrow_down.png"),
    BLOCK( "BoloBall/src/img/block.png"),
    TELEPORT( "BoloBall/src/img/teleport.png"),
    POINTS( "BoloBall/src/img/points.png"),
    SPRITE_RED("BoloBall/src/img/sprite_red.png"),
    SPRITE_GREEN("BoloBall/src/img/sprite_green.png");

    private BufferedImage img;

    Tiles(String path) {
        File imageFile = new File(path);
        try {
            this.img = ImageIO.read(imageFile);
        } catch (IOException e) {
            System.err.println("Blad odczytu obrazka");
            e.printStackTrace();
        }
    }

    public Image getImg() {
        return img;
    }
}
