package front;


import javax.swing.*;
import java.awt.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import back.Panels;

public class Frame extends JFrame {
    public static int WIDTH;
    public static final int HEIGHT = 800;
    Rectangle rec = new Rectangle(0, 0, HEIGHT, WIDTH);
    public static String imgPaths;
    public static String samplesPaths;
    JPanel panel;
    public Frame(Panels panel) {
        super();
        checkOS();
        this.panel = panel.getPanel(this);
        init(this.panel);
    }

    private void init(JPanel panel) {
        add(panel);

        setResizable(false);
        pack();

        setTitle("BoloBall");
        setSize(WIDTH, HEIGHT);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }
    private void checkOS() {
        Pattern compiledPattern = Pattern.compile("indows");
        Matcher matcher = compiledPattern.matcher(System.getProperty("os.name"));

        if(matcher.find()) {
            WIDTH = 990;
            imgPaths = "src\\img\\";
            samplesPaths = "src\\samples\\";
        }
        else {
            WIDTH = 984;
            imgPaths = "src/img/";
            samplesPaths = "src/samples/";
        }
    }
    public static void main(String[] args) {

        EventQueue.invokeLater(new Runnable() {
            @Override
            public void run() {
                Frame frame = new Frame(Panels.MAIN_MENU_PANEL);
                frame.setVisible(true);
            }
        });
    }

    public void startGame() {
        EventQueue.invokeLater(new Runnable() {
            @Override
            public void run() {
                Frame frame = new Frame(Panels.GAME_PANEL);
                repaint();
                frame.setVisible(true);
            }
        });
        this.dispose();
    }

    public void quitGame() {
        this.dispose();
    }

    public void scoreboard() {
        EventQueue.invokeLater(new Runnable() {
            @Override
            public void run() {
                Frame frame = new Frame(Panels.SCOREBOARD_PANEL);
                repaint();
                frame.setVisible(true);
            }
        });
        this.dispose();
    }

    public void dashboard(int score1, int score2) {
        EventQueue.invokeLater(new Runnable() {
            @Override
            public void run() {
                Frame frame = new Frame(Panels.DASH_PANEL);
                ((DashPanel) frame.panel).setScore(score1, score2);
                repaint();
                frame.setVisible(true);
            }
        });
        this.dispose();
    }

    public void mainMenu() {
        EventQueue.invokeLater(new Runnable() {
            @Override
            public void run() {
                Frame frame = new Frame(Panels.MAIN_MENU_PANEL);
                frame.setVisible(true);
            }
        });
        this.dispose();
    }
}
