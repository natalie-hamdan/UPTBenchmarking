package testbench;

import bench.DemoBench;
import bench.IBenchmark;
import logging.ConsoleLogger;
import logging.ILogger;
import timing.TimeMeasure;
import timing.Timer;

import java.util.concurrent.TimeUnit;

//a default testbench code that you can use for every new test
//this code Initializes DemoBench, measures how long run() takes (in nanoseconds), logs that time, and then cleans up.
public class DemoBenchTestbench {
    public static void main(String[] args){
        IBenchmark bench1 = new DemoBench();
        TimeMeasure timer1 = new Timer();
        ILogger log = new ConsoleLogger();

        bench1.initialize(); // asks for array size and setting up the data
        timer1.start(); //begins the timer using the method in timer class
        bench1.run(); // runs the benchmark class
        long elapsedTime = timer1.stop();
        log.writeTime("Finished in (ns) : ", elapsedTime, TimeUnit.NANOSECONDS); // ses the printing in console logger

        bench1.clean(); // clear array
//        log.close(); //  this is for file logger to close the files
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
////a default testbench code that you can use for every new test
////this code Initializes DemoBench, measures how long run() takes (in nanoseconds), logs that time, and then cleans up.
//public class DemoBenchTestbench {
//    public static void main(String[] args){
//        IBenchmark bench1 = new DemoBench();
//        TimeMeasure timer1 = new Timer();
//        ILogger log = new ConsoleLogger();
//
//        bench1.initialize(); // asks for array size and setting up the data
//        timer1.start(); //begins the timer using the method in timer class
//        bench1.run(); // runs the benchmark class
//        long elapsedTime = timer1.stop();
//        log.writeTime("Finished in (ns) : ", elapsedTime, TimeUnit.NANOSECONDS); // ses the printing in console logger
//
//        bench1.clean(); // clear array
////        log.close(); //  this is for file logger to close the files
//    }
//}
