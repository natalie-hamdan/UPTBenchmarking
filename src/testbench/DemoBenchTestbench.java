

package testbench;

import bench.DemoBench;
import bench.IBenchmark;
import logging.ConsoleLogger;
import logging.ILogger;
import timing.TimeMeasure;
import timing.Timer;

import java.util.concurrent.TimeUnit;

public class DemoBenchTestbench {
    public static void main(String[] args) {
        IBenchmark bench = new DemoBench();
        TimeMeasure timer = new Timer();
        ILogger log = new ConsoleLogger();

        int loopCount = 3; // how many times to run pause/resume
        long segmentTime;

        timer.start(); // Start the timer once, cumulates all segment times

        for (int i = 1; i <= loopCount; i++) {
            bench.initialize(); // Prepare data before resume
            timer.resume();      // Resume the timer for a segment
            bench.run();         // Run the benchmark code
            segmentTime = timer.pause(); // Pause and get time for this segment
            log.write("Segment " + i + " duration (ns): " + segmentTime);
            bench.clean(); // Clean data after pause
        }

        long totalTime = timer.stop(); // Stop timer and get total time
        log.write("Total time (ns): " + totalTime);
        log.writeTime("Total time in ms:", totalTime, TimeUnit.MILLISECONDS);

        // log.close(); // If using FileLogger
    }
}

//package testbench;
//
//import bench.DemoBench;
//import bench.IBenchmark;
//import logging.ConsoleLogger;
//import logging.ILogger;
//import timing.TimeMeasure;
//import timing.Timer;
//
//import java.util.concurrent.TimeUnit;
//
//public class DemoBenchTestbench {
//    public static void main(String[] args) {
//        IBenchmark bench = new DemoBench();
//        TimeMeasure timer = new Timer();
//        ILogger log = new ConsoleLogger();
//
//        int loopCount = 3; // how many times to run pause/resume
//        long segmentTime;
//
//        timer.start(); // Start the timer once, cumulates all segment times
//
//        for (int i = 1; i <= loopCount; i++) {
//            bench.initialize(); // Prepare data before resume
//            timer.resume();      // Resume the timer for a segment
//            bench.run();         // Run the benchmark code
//            segmentTime = timer.pause(); // Pause and get time for this segment
//            log.write("Segment " + i + " duration (ns): " + segmentTime);
//            bench.clean(); // Clean data after pause
//        }
//
//        long totalTime = timer.stop(); // Stop timer and get total time
//        log.write("Total time (ns): " + totalTime);
//        log.writeTime("Total time in ms:", totalTime, TimeUnit.MILLISECONDS);
//
//        // log.close(); // If using FileLogger
//    }
//}





