package back;

import front.*;

import javax.swing.*;
import java.util.function.Function;

public enum Panels {
    MAIN_MENU_PANEL(1),
    GAME_PANEL(2),
    DASH_PANEL(3),
    SCOREBOARD_PANEL(4);

    int i;
    Panels(int i) {
        this.i = i;
    }

    public JPanel getPanel(Frame frame) {
        JPanel panel;
        switch (i) {
            case 2:
                panel = new GamePanel(frame);
                break;
            case 3:
                panel = new DashPanel(frame);
                break;
            case 4:
                panel = new ScoreboardPanel(frame);
                break;
            default:
                panel = new MainMenuPanel(frame);
                break;
        }
        return panel;
    }

}
