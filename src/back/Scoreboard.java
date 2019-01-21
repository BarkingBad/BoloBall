package back;

import java.io.Serializable;

public class Scoreboard implements Serializable {
    public static final long serialVersionUID = 2516394621718851614l;
    private ScoreWithName [] scoreWithNames;
    public Scoreboard() {
    }

    public void sampleDate() {
        scoreWithNames = new ScoreWithName[]{
            new ScoreWithName("Barry", 1000),
            new ScoreWithName("Garry", 900),
            new ScoreWithName("Harry", 800),
            new ScoreWithName("Larry", 700),
            new ScoreWithName("Marry", 600),
            new ScoreWithName("Parry", 500),
            new ScoreWithName("Darry", 400),
            new ScoreWithName("Carry", 300),
            new ScoreWithName("Zarry", 200),
            new ScoreWithName("Abraham", 100)
        };
    }

    public ScoreWithName[] getScoreWithNames() {
        return scoreWithNames;
    }

    public void setScore(int i, ScoreWithName scoreWithName) {
        scoreWithNames[i] = scoreWithName;
    }
}
