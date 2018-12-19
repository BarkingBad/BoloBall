package front;

import back.Colour;
import back.Player;
import back.Playground;
import back.Tiles;
import back.Point;

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

    public void blit() {
        repaint();
    }

    @Override
    public void actionPerformed(ActionEvent actionEvent) {
        players[currentTurn].update(playground);
        blit();
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2d = (Graphics2D) g;
        for(int x=0; x< Playground.SIZE_X; x++)
            for(int y=0; y<Playground.SIZE_Y; y++)
                g2d.drawImage(playground.getGrid()[x][y].getImg(), x*34, y*34, null);

        g2d.drawImage(Tiles.BALL_RED.getImg(), 20, 660, null);
        g2d.drawImage(Tiles.BALL_GREEN.getImg(), Frame.WIDTH - 20 - 32, 660, null);
        g2d.drawString(players[0].getScoreToString(), 20, 710);
        for(Point p : playground.getTemporaryPoints()) {

            g2d.drawString("" + (p.getY()-1)*2, p.getX()*34+12, p.getY()*34+21 );
        }
        Toolkit.getDefaultToolkit().sync();
    }

    private class TAdapter extends KeyAdapter {

        @Override
        public void keyPressed(KeyEvent e) {
            players[currentTurn].keyPressed(e, playground, Panel.this);
        }


    }
}
