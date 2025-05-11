package bench.cpu;

import bench.IBenchmark;
import java.math.BigDecimal;
import java.math.MathContext;
import java.math.RoundingMode;

// Benchmark class that calculates digits of Pi using the Chudnovsky algorithm
public class CPUDigitsOfPi implements IBenchmark {

    private int digits ;// Number of digits of Pi to compute
    private MathContext mc ;// Precision setting for BigDecimal operations

    // Set up the number of digits and MathContext precision
    public void initialize(Object... params) {
        if (params.length > 0 && params[0] instanceof Integer) {
            digits = (Integer) params[0];
        } else {
            digits = 1000 ;// Use 1000 if no value is given
        }

        // Set precision a bit higher than needed for accuracy
        mc = new MathContext(digits + 10, RoundingMode.HALF_UP)
    }

    // Short computations to prepare the JVM and stabilize timing
    public void warmup() {
        computePi(100);
        computePi(200);
    }

    // Run benchmark using the stored number of digits
    public void run() {
        computePi(digits);
    }

    // Run benchmark using digits passed in parameters
    public void run(Object... params) {
        if (params.length > 0 && params[0] instanceof Integer) {
            computePi((Integer) params[0]);
        } else {
            computePi(digits);
        }
    }

    // Computes Pi using the Chudnovsky algorithm
    private BigDecimal computePi(int digits) {
        mc = new MathContext(digits + 10, RoundingMode.HALF_UP);

        // Constant value C = 426880 * sqrt(10005)
        BigDecimal C = new BigDecimal("426880").multiply(sqrt(new BigDecimal("10005")));
        BigDecimal sum = BigDecimal.ZERO;

        // Compute 10 terms in the Chudnovsky series for high precision
        for (int k = 0; k < 10; k++) {
            BigDecimal numerator = factorial(6 * k).multiply(new BigDecimal(545140134L * k + 13591409));
            BigDecimal denominator = factorial(3 * k)
                    .multiply(factorial(k).pow(3))
                    .multiply(BigDecimal.valueOf(-262537412640768000L).pow(k));
            sum = sum.add(numerator.divide(denominator, mc));
        }

        // Final result of Pi = C / sum
        return C.divide(sum, mc).setScale(digits, RoundingMode.HALF_UP);
    }

    // Calculates factorial of n using BigDecimal
    private BigDecimal factorial(int n) {
        BigDecimal result = BigDecimal.ONE;
        for (int i = 2; i <= n; i++) {
            result = result.multiply(BigDecimal.valueOf(i));
        }
        return result;
    }

    // Calculates square root using Newton-Raphson method
    private BigDecimal sqrt(BigDecimal value) {
        BigDecimal x = new BigDecimal(Math.sqrt(value.doubleValue()), mc);
        BigDecimal two = BigDecimal.valueOf(2);

        // Repeat refinement steps to get more accurate sqrt
        for (int i = 0; i < 10; i++) {
            x = x.add(value.divide(x, mc)).divide(two, mc);
        }
        return x;
    }

    // No cleanup needed for this benchmark
    public void clean() {
    }

    // Optional cancel method (not used)
    public void cancel() {
    }

    @Override
    public void warmUp() {

    }

    @Override
    public String getResult() {
        return "";
    }
}

//package testbench;
//
//import bench.IBenchmark;
//import bench.cpu.CPUDigitsOfPi;
//import timing.TimeMeasure;
//import timing.Timer;
//import java.io.BufferedWriter;
//import java.io.FileWriter;
//import java.io.IOException;
//
// Class to run the Pi benchmark for increasing digit sizes and save runtimes to CSV
//public class PiBenchmarkDataCollector {
//    public static void main(String[] args) {
//        IBenchmark bench = new CPUDigitsOfPi() // Create the Pi benchmark
//        TimeMeasure timer = new Timer() // Used to measure time
//        String filename = "pi_runtimes.csv" // Output file
//
//        try (BufferedWriter writer = new BufferedWriter(new FileWriter(filename))) {
//            writer.write("Digits,Time(ms)\n") // CSV header
//
//            // Run the benchmark for increasing sizes: 50 → 100000
//            for (int digits = 50; digits <= 100000; digits *= 2) {
//                bench.initialize(digits) // Set number of digits
//                bench.warmup() // Warm up JVM
//
//                timer.start() // Start timer
//                bench.run() // Run Pi calculation
//                long timeNano = timer.stop() // Stop timer
//
//                long timeMillis = timeNano / 1_000_000 // Convert to milliseconds
//                writer.write(digits + "," + timeMillis + "\n") // Save result
//
//                bench.clean() // Optional cleanup
//            }
//
//            System.out.println("Benchmark completed. Results saved to: " + filename)
//        } catch (IOException e) {
//            System.err.println("Error writing to CSV: " + e.getMessage())
//        }
//    }
//}