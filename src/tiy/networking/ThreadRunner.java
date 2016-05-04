package tiy.networking;

import java.text.DecimalFormat;
import java.time.Instant;

/**
 * Created by Justins PC on 4/27/2016.
 */
public class ThreadRunner {
    public static void main(String[] args) {
        System.out.println("ThreadRunner running");

        int numThreadsStarted = 0; //keeps track of the threads you have started
        DecimalFormat timerFormatter = new DecimalFormat("###,###"); //formats a string, number of mili seconds to start threads

        long startMillis = Instant.now().toEpochMilli(); // gets the number of mili seconds from starting and ending thread runner

        while (true) { // starting loop, as long as your starting 10 threads state in the loop if more quit the loop
            System.out.println("Number of threads started = " + numThreadsStarted); // prints out the number of threads started
            SampleThread localThread = new SampleThread(); // creates a new thread
            SimpleServer simpleThreead = new SimpleServer();
            localThread.run(); // calls the run metod on local thread obeject
            Thread newThread = new Thread(localThread);
            newThread.start();
            numThreadsStarted++;
            if (numThreadsStarted > 10) {
                break;
            }
        }

        long endMillis = Instant.now().toEpochMilli();

        System.out.println("Ran in " + timerFormatter.format(endMillis - startMillis) + " ms");

        System.out.println("ThreadRunner done!");
    }
}

