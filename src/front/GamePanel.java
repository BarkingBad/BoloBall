package front;

import back.Colour;
import back.Player;
import back.Playground;
import back.Point;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;


public class GamePanel extends JPanel {

    private Playground playground;
    private Player[] players;
    private int currentTurn = 0;
    private Frame frame;
    public GamePanel(Frame frame) {
        super(true);
        this.frame = frame;
        setBackground(Color.BLACK);
        setFocusable(true);
        getInputMap().put(KeyStroke.getKeyStroke("DOWN"), "goDown");
        getActionMap().put("goDown", goDown);
        getInputMap().put(KeyStroke.getKeyStroke("LEFT"), "goLeft");
        getActionMap().put("goLeft", goLeft);
        getInputMap().put(KeyStroke.getKeyStroke("RIGHT"), "goRight");
        getActionMap().put("goRight", goRight);
        init();
    }

    private void init() {
        playground = new Playground();
        setFocusable(true);
        players = new Player[]{new Player(Colour.GREEN), new Player(Colour.RED)};
        players[0].update(playground, this);
    }



    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2d = (Graphics2D) g;
        for(int x=0; x< Playground.SIZE_X; x++)
            for(int y=0; y<Playground.SIZE_Y; y++)
                g2d.drawImage(playground.getGrid()[x][y].getImg(), x*34, y*34, null);

        g2d.drawImage(players[0].getColour().getBall().getImg(), 20, 660, null);
        g2d.drawImage(players[1].getColour().getBall().getImg(), Frame.WIDTH - 20 - 32, 660, null);
        g2d.drawString(players[0].getScoreToString(), 20, 710);
        g2d.drawString(players[1].getScoreToString(), Frame.WIDTH - 20 - 32, 710);
        for(Point p : playground.getTemporaryPoints()) {

            g2d.drawString("" + (p.getY()-1)*2, p.getX()*34+12, p.getY()*34+21 );
        }
        Toolkit.getDefaultToolkit().sync();
    }

    public void sleepAndPaint(int time) {
        this.paintImmediately(0,0, Frame.WIDTH, Frame.HEIGHT);
        try {
            Thread.sleep(time);
        }
        catch (InterruptedException e){
            System.err.println("Wątek został przerwany");
        }
    }


    public Player[] getPlayers() {
        return players;
    }

    public void changePlayer() {
        currentTurn^=1;
        if(!players[currentTurn].canContinue) {
            players[currentTurn].prepareView(playground);

            sleepAndPaint(3000);
            if(!players[currentTurn^1].canContinue) {
                playground.finishTheGame(this);
            } else {
                changePlayer();
            }
        }
    }

    public void setCurrentTurn(int i) {
        currentTurn = i;
    }

    public Player getCurrentPlayer() {
        return players[currentTurn];
    }

    public void newGame() {
        this.init();
    }


    Action goDown = new AbstractAction() {
        public void actionPerformed(ActionEvent e) {
            players[currentTurn].keyPressed(0, playground, GamePanel.this);
            players[currentTurn].update(playground, GamePanel.this);
            repaint();
        }
    };

    Action goLeft = new AbstractAction() {
        public void actionPerformed(ActionEvent e) {
            players[currentTurn].keyPressed(-1, playground, GamePanel.this);
            players[currentTurn].update(playground, GamePanel.this);
            repaint();
        }
    };

    Action goRight = new AbstractAction() {
        public void actionPerformed(ActionEvent e) {
            players[currentTurn].keyPressed(1, playground, GamePanel.this);
            players[currentTurn].update(playground, GamePanel.this);
            repaint();
        }
    };

    public Frame getFrame() {
        return frame;
    }
}
