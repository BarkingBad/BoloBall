package front;

import back.Colour;
import back.Player;
import back.Playground;
import back.Tiles;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;


public class Panel extends JPanel implements ActionListener {
    private final int DELAY = 10;
    private Timer timer;
    private Playground playground;
    private Player[] players;
    private int currentTurn = 0;
    public Panel() {
        super();
        init();
    }

    private void init() {
        addKeyListener(new TAdapter());
        setBackground(Color.BLACK);
        Timer timer = new Timer(DELAY, this);
        timer.start();
        setFocusable(true);
        playground = new Playground();
        players = new Player[]{new Player(Colour.RED), new Player(Colour.GREEN)};

    }



    @Override
    public void actionPerformed(ActionEvent actionEvent) {
        players[currentTurn].update(playground);
        repaint();
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2d = (Graphics2D) g;
        for(int x=0; x< Playground.SIZE_X; x++)
            for(int y=0; y<Playground.SIZE_Y; y++)
                g2d.drawImage(playground.getGrid()[x][y].getImg(), x*34, y*34, null);

        Toolkit.getDefaultToolkit().sync();
    }

    private class TAdapter extends KeyAdapter {

        @Override
        public void keyPressed(KeyEvent e) {
            players[currentTurn].keyPressed(e, playground);
        }


    }
}
