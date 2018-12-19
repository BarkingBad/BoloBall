package back;


import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public enum Tiles {
    EMPTY( "/home/andrzej/Dokumenty/PO/BoloBall/BoloBall/src/img/empty.png"),
    UPPER_FIELD( "/home/andrzej/Dokumenty/PO/BoloBall/BoloBall/src/img/upper_field.png"),
    BALL_RED( "/home/andrzej/Dokumenty/PO/BoloBall/BoloBall/src/img/ball_red.png"),
    BALL_GREEN( "/home/andrzej/Dokumenty/PO/BoloBall/BoloBall/src/img/ball_green.png"),
    ARROW_LEFT( "/home/andrzej/Dokumenty/PO/BoloBall/BoloBall/src/img/arrow_left.png"),
    ARROW_RIGHT( "/home/andrzej/Dokumenty/PO/BoloBall/BoloBall/src/img/arrow_right.png"),
    ARROW_DOWN("/home/andrzej/Dokumenty/PO/BoloBall/BoloBall/src/img/arrow_down.png"),
    BLOCK( "/home/andrzej/Dokumenty/PO/BoloBall/BoloBall/src/img/block.png"),
    TELEPORT( "/home/andrzej/Dokumenty/PO/BoloBall/BoloBall/src/img/teleport.png"),
    POINTS( "/home/andrzej/Dokumenty/PO/BoloBall/BoloBall/src/img/points.png"),
    SPRITE_RED("/home/andrzej/Dokumenty/PO/BoloBall/BoloBall/src/img/sprite_red.png"),
    SPRITE_GREEN("/home/andrzej/Dokumenty/PO/BoloBall/BoloBall/src/img/sprite_green.png");

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
