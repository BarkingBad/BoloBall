package back;

import javax.sound.sampled.Clip;
import java.util.LinkedList;
import java.util.Queue;

public class Jukebox extends Thread {

    private Queue<Clip> queue = new LinkedList<>();

    @Override
    public void run() {
        while(true) {
            synchronized (queue) {
                if (queue.isEmpty()) {
                    try {
                        queue.wait();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                } else {
                    Clip sample = queue.remove();
                    sample.stop();
                    sample.setMicrosecondPosition(0);
                    sample.start();
                }

            }
        }
    }

    public void push(Sounds sound) {
        Clip sample = sound.getSample();
        synchronized (queue) {
            queue.add(sample);
            queue.notify();
        }
    }

}
