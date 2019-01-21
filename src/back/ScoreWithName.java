package back;

import java.io.Serializable;

public class ScoreWithName implements Serializable, Comparable {
    public static final long serialVersionUID = -734986218653074695L;
    String name;
    int score;
    public ScoreWithName(String name, int score) {
        this.name = name;
        this.score = score;
    }

    public String getName() {
        return name;
    }

    public int getScore() {
        return score;
    }

    @Override
    public int compareTo(Object o) {
        return ((ScoreWithName) o).getScore() - this.getScore();
    }

}
