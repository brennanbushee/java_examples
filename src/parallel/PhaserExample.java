package parallel;

import java.util.concurrent.Phaser;

public class PhaserExample {
    public static void main(String[] args) {
        Phaser phaser = new Phaser(1);

        for (int i = 1; i <= 3; i++) {
            final int phaseNumber = i;
            new Thread(() -> {
                phaser.arriveAndAwaitAdvance();
                System.out.println("Phase " + phaseNumber + " completed by " + Thread.currentThread().getName());
            }).start();
        }

        System.out.println("Main thread waiting for phase 1 to complete...");
        phaser.arriveAndDeregister();
    }
}

