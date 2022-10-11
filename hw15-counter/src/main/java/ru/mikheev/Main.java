package ru.mikheev;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Main {

    private static final Logger logger = LoggerFactory.getLogger(Main.class);
    private String last = "slave";

    private int counter = 1;
    private int step = 1;
    private static final int MAX_COUNTER_VALUE = 10;
    private static final int MIN_COUNTER_VALUE = 1;

    public static void main(String[] args) {
        Main counter = new Main();

        Thread masterThread = new Thread(() -> counter.action("master", false));
        Thread slaveThread = new Thread(() -> counter.action("slave", true));

        masterThread.start();
        slaveThread.start();
    }

    private synchronized void action(String threadName, boolean slave) {
        while(!Thread.currentThread().isInterrupted()) {
            try {
                //spurious wakeup https://en.wikipedia.org/wiki/Spurious_wakeup
                //поэтому не if
                while (last.equals(threadName)) {
                    this.wait();
                }

                logger.info("Current counter {} for thread {}", counter, Thread.currentThread().getName());
                last = threadName;

                if (slave) {
                    if (counter == MAX_COUNTER_VALUE) {
                        step = -1;
                    } else if (counter == MIN_COUNTER_VALUE) {
                        step = 1;
                    }
                    counter += step;
                }

                sleep();
                notifyAll();
            } catch (InterruptedException ex) {
                Thread.currentThread().interrupt();
            }
        }
    }

    private static void sleep() {
        try {
            Thread.sleep(3_000);
        } catch (InterruptedException e) {
            logger.error(e.getMessage());
            Thread.currentThread().interrupt();
        }
    }

}
