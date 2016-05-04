package tiy.networking;

/**
 * Created by Justins PC on 4/27/2016.
 */
public class SampleThread implements Runnable {

    public void run() { // all thats being done is sleep , sleep for 2 seconds, makes the thread hold for 2 seconds then run again.
        System.out.println("Running " + Thread.currentThread().getId());

        try {
            Thread.sleep(2000);
        } catch (Exception exception) {
            exception.printStackTrace();
        }

        System.out.println("Done running " + Thread.currentThread().getId());
    }
}
