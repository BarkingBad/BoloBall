package back;


import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public enum Tiles {
    EMPTY( "C:\\Users\\andrz\\IdeaProjects\\BoloBall\\src\\img\\empty.png"),
    UPPER_FIELD( "C:\\Users\\andrz\\IdeaProjects\\BoloBall\\src\\img\\upper_field.png"),
    BALL_RED( "C:\\Users\\andrz\\IdeaProjects\\BoloBall\\src\\img\\ball_red.png"),
    BALL_GREEN( "C:\\Users\\andrz\\IdeaProjects\\BoloBall\\src\\img\\ball_green.png"),
    ARROW_LEFT( "C:\\Users\\andrz\\IdeaProjects\\BoloBall\\src\\img\\arrow_left.png"),
    ARROW_RIGHT( "C:\\Users\\andrz\\IdeaProjects\\BoloBall\\src\\img\\arrow_right.png"),
    ARROW_DOWN("C:\\Users\\andrz\\IdeaProjects\\BoloBall\\src\\img\\arrow_down.png"),
    BLOCK( "C:\\Users\\andrz\\IdeaProjects\\BoloBall\\src\\img\\block.png"),
    TELEPORT( "C:\\Users\\andrz\\IdeaProjects\\BoloBall\\src\\img\\teleport.png"),
    POINTS( "C:\\Users\\andrz\\IdeaProjects\\BoloBall\\src\\img\\points.png"),
    SPRITE_RED("C:\\Users\\andrz\\IdeaProjects\\BoloBall\\src\\img\\sprite_red.png"),
    SPRITE_GREEN("C:\\Users\\andrz\\IdeaProjects\\BoloBall\\src\\img\\sprite_green.png");

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
