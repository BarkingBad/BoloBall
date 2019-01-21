package front;

import back.*;
import back.Point;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.Arrays;
import java.util.List;

public class MainMenuPanel extends JPanel {
    private int cursor = 0;
    private Frame frame;
    private List<String> mainMenu = Arrays.asList("Start", "Scoreboard", "Exit");
    public MainMenuPanel(Frame frame) {
        super(true);
        this.frame = frame;
        setBackground(Color.BLACK);
        setFocusable(true);
        getInputMap().put(KeyStroke.getKeyStroke("DOWN"), "goDown");
        getActionMap().put("goDown", goDown);
        getInputMap().put(KeyStroke.getKeyStroke("UP"), "goUp");
        getActionMap().put("goUp", goUp);
        getInputMap().put(KeyStroke.getKeyStroke("ENTER"), "chooseOption");
        getActionMap().put("chooseOption", chooseOption);
    }


    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2d = (Graphics2D) g;
        for(int i=0; i<mainMenu.size(); i++) {
            if(i == cursor) {
                g2d.setFont(new Font("TimesRoman", Font.PLAIN, 60));
                g2d.setColor(Color.WHITE);
                g2d.drawString(mainMenu.get(i), 400, (i+1)*100);
            }
            else {
                g2d.setFont(new Font("TimesRoman", Font.PLAIN, 60));
                g2d.setColor(Color.GREEN);
                g2d.drawString(mainMenu.get(i), 400, (i+1)*100);
            }
        }
        repaint();

        Toolkit.getDefaultToolkit().sync();
    }


    public void goUp() {
        cursor = (cursor-1+mainMenu.size())%mainMenu.size();
    }

    public void goDown() {
        cursor = (cursor+1)%mainMenu.size();
    }

    public void chooseOption() {
        switch (cursor) {
            case 0:
                frame.startGame();
                break;
            case 1:
                frame.scoreboard();
                break;
            case 2:
                frame.quitGame();
                break;

        }
    }


    Action goUp = new AbstractAction() {
        public void actionPerformed(ActionEvent e) {
            goUp();
        }
    };

    Action chooseOption = new AbstractAction() {
        public void actionPerformed(ActionEvent e) {
            chooseOption();
        }
    };

    Action goDown = new AbstractAction() {
        public void actionPerformed(ActionEvent e) {
            goDown();
        }
    };
}
