package back;


import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public enum Tiles {
    EMPTY(0, "../../img/empty.jpg"),
    UPPER_FIELD(1, "../../img/upper_field.jpg"),
    BALL_RED(2, "../../img/ball_red.jpg"),
    BALL_BLUE(3, "../../img/ball_blue.jpg"),
    ARROW_LEFT(4, "../../img/arrow_left.jpg"),
    ARROW_RIGHT(5, "../../img/arrow_right.jpg"),
    BLOCK(6, "../../img/block.jpg"),
    TELEPORT(7, "../../img/teleport.jpg"),
    POINTS(8, "../../img/points.jpg");

    private int code;
    private BufferedImage img;

    Tiles(int code, String path) {
        this.code = code;
        try{
            this.img = ImageIO.read(new File(path));
        }catch (IOException e) {
            System.exit(2137);
        }
    }

    public int getCode() {
        return this.code;
    }
}
