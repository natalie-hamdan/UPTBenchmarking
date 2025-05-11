package testbench;

import logging.ConsoleLogger;
import logging.ILogger;
import java.util.concurrent.TimeUnit;
import timing.Timer;
import bench.IBenchmark;
import bench.cpu.CPUFixedVsFloatingPoint;
import bench.cpu.NumberRepresentation;

public class TestCPUFixedVsFloatingPoint {
	public static void main(String[] args) {
		Timer timer      = new Timer();
		ILogger log      = new ConsoleLogger();
		TimeUnit unit    = TimeUnit.MILLISECONDS;

		IBenchmark bench = new CPUFixedVsFloatingPoint();
		bench.initialize(10_000_000);
		bench.warmUp();

		timer.start();
		bench.run(NumberRepresentation.FIXED); //with right shift
//		 bench.run(NumberRepresentation.FLOATING);
		long elapsed = timer.stop();

		log.writeTime("Finished in ", elapsed, unit);
		log.write("Result is", bench.getResult());

		bench.clean();
		log.close();
	}
}
