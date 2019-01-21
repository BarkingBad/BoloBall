package front;

import back.ScoreWithName;
import back.Scoreboard;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class DashPanel extends JPanel implements KeyListener {
    private int cursor = 0;
    Frame frame;
    String [] names = new String[]{"",""};
    int score1 = 0, score2 = 0;
    Scoreboard scoreboard;
    public DashPanel(Frame frame) {
        super(true);
        this.frame = frame;
        setBackground(Color.BLACK);
        setFocusable(true);
        addKeyListener(this);
    }


    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2d = (Graphics2D) g;
        g2d.setFont(new Font("TimesRoman", Font.PLAIN, 40));
        g2d.setColor(Color.GREEN);
        g2d.drawString("Zielony gracz zdobył " + score1 + " punktów. Podaj swoje imię", 10, 100);
        g2d.drawString(names[0], 400, 200);
        g2d.setColor(Color.RED);
        g2d.drawString("Czerwony gracz zdobył " + score2 + " punktów. Podaj swoje imię", 10, 300);
        g2d.drawString(names[1], 400, 400);
        repaint();

        Toolkit.getDefaultToolkit().sync();
    }

    @Override
    public void keyPressed(KeyEvent e) {
    }

    @Override
    public void keyTyped(KeyEvent e) {
        if(e.getKeyChar() == KeyEvent.VK_BACK_SPACE) {
            names[cursor]= names[cursor].substring(0, names[cursor].length() - 1);
        } else if(e.getKeyChar() == KeyEvent.VK_ENTER) {
            cursor++;
            if(cursor == 2) {
                updateScores();
                frame.scoreboard();
            }
        } else {
            names[cursor] = names[cursor] + e.getKeyChar();
        }

    }

    @Override
    public void keyReleased(KeyEvent e) {

    }

    public void setScore(int s1, int s2) {
        this.score1 = s1;
        this.score2 = s2;
    }

    private void updateScores() {
        try {
            FileInputStream fileIn = new FileInputStream(".\\scoreboard.ser");
            ObjectInputStream in = new ObjectInputStream(fileIn);
            scoreboard = (Scoreboard) in.readObject();
            in.close();
            fileIn.close();
        } catch (IOException i) {
            i.printStackTrace();
        } catch (ClassNotFoundException c) {
            System.out.println("Employee class not found");
            c.printStackTrace();
        }
        List<ScoreWithName> list = new ArrayList<>(Arrays.asList(scoreboard.getScoreWithNames()));
        list.addAll(Arrays.asList(new ScoreWithName[]{new ScoreWithName(names[0], score1), new ScoreWithName(names[1], score2)}));
        Collections.sort(list);
        for(int i=0; i<10; i++) {
            scoreboard.setScore(i, list.get(i));
        }
        try {
            FileOutputStream fileOut =
                    new FileOutputStream(".\\scoreboard.ser");
            ObjectOutputStream out = new ObjectOutputStream(fileOut);
            out.writeObject(scoreboard);
            out.close();
            fileOut.close();
            System.out.printf("Serialized data is saved in /tmp/employee.ser");
        } catch (IOException i) {
            i.printStackTrace();
        }
    }
}
