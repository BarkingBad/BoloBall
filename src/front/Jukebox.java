package front;

import back.Sounds;
import sun.audio.AudioPlayer;

import javax.sound.sampled.AudioInputStream;

public class Jukebox {
    public static synchronized void playSound(Sounds sample) {
//        (new Runnable() {
//            AudioPlayer.player.start(sample.getSample());
//        }).start();
    }
}
