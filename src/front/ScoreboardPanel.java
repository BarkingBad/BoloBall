package front;

import back.ScoreWithName;
import back.Scoreboard;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.*;

public class ScoreboardPanel extends JPanel implements KeyListener {
    private int cursor = 0;
    String name = "";
    Frame frame;
    Scoreboard scoreboard;
    public ScoreboardPanel(Frame frame) {
        super(true);
        this.frame = frame;
        setBackground(Color.BLACK);
        setFocusable(true);
        addKeyListener(this);
        try {
            FileInputStream fileIn = new FileInputStream(".\\scoreboard.ser");
            ObjectInputStream in = new ObjectInputStream(fileIn);
            scoreboard = (Scoreboard) in.readObject();
            in.close();
            fileIn.close();
        } catch (IOException i) {
            i.printStackTrace();
        } catch (ClassNotFoundException c) {
            System.out.println("Scoreboard class not found");
            c.printStackTrace();
        }
    }


    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2d = (Graphics2D) g;
        g2d.setFont(new Font("TimesRoman", Font.PLAIN, 40));
        g2d.setColor(Color.WHITE);
        for(int i=0; i<10; i++) {
            g2d.drawString((i+1) + ". " + scoreboard.getScoreWithNames()[i].getName() + "    " + scoreboard.getScoreWithNames()[i].getScore() , 10, 60*(i+1));
        }
        repaint();

        Toolkit.getDefaultToolkit().sync();
    }

    @Override
    public void keyPressed(KeyEvent e) {
    }

    @Override
    public void keyTyped(KeyEvent e) {
        frame.mainMenu();
    }

    @Override
    public void keyReleased(KeyEvent e) {

    }
}

