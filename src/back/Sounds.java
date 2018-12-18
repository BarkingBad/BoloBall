package back;

import sun.audio.AudioStream;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;

public enum Sounds {
    FALLING(),
    POINTS_INCREASE(),
    NEW_TURN(),
    STEP(),
    WIN();

    private AudioStream sample;

    Sounds(String path) {
        try {
            sample = new AudioStream(new FileInputStream(path));
        }
        catch(IOException e) {
            System.err.println("Blad odczytu sampla z pliku " + path);
            e.printStackTrace();
        }
    }

    public AudioStream getSample() {
        return sample;
    }
}
