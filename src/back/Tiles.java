package back;


import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public enum Tiles {
    EMPTY(0, "../../img/empty.png"),
    UPPER_FIELD(1, "../../img/upper_field.png"),
    BALL_RED(2, "../../img/ball_red.png"),
    BALL_BLUE(3, "../../img/ball_blue.png"),
    ARROW_LEFT(4, "../../img/arrow_left.png"),
    ARROW_RIGHT(5, "../../img/arrow_right.png"),
    BLOCK(6, "../../img/block.png"),
    TELEPORT(7, "../../img/teleport.png"),
    POINTS(8, "../../img/points.png");

    private int code;
    private Image img;



    Tiles(int code, String path) {
        this.code = code;
        this.img = Toolkit.getDefaultToolkit().getImage(path);
    }

    public int getCode() {
        return this.code;
    }

    public Image getImg() {
        return img;
    }
}
