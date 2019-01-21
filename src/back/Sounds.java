package back;


import front.Frame;

import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.LineUnavailableException;
import java.io.File;



public enum Sounds {
    FALLING(Frame.samplesPaths + "freeFall.wav"),
    POINTS_INCREASE(Frame.samplesPaths + "points.wav"),
    TELEPORT(Frame.samplesPaths + "teleport.wav"),
    FULL_SCORE(Frame.samplesPaths + "fullScore.wav");

    private Clip sample;

    Sounds(String path) {
        try {
            sample = AudioSystem.getClip();
            sample.open(AudioSystem.getAudioInputStream(new File(path)));

        } catch (Exception e) {
            System.err.println("Problem z zaladowaniem sampla!");
        }
    }

    public Clip getSample() {
        return sample;
    }
}
