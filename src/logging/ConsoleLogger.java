package logging;
import java.util.concurrent.TimeUnit;


public class ConsoleLogger implements ILogger {

    @Override
    public void write(long value) {
        System.out.println(value);
    }

    @Override
    public void write(String value) {
        System.out.println(value);
    }

    @Override
    public void write(Object... values) {
        for (Object value : values) {
            System.out.print(value + " ");
        }
        System.out.println();
    }

    @Override
    public void close() {
        // No resources to close
    }

    @Override
    // Method to write time in multiple TimeUnit formats
    public void writeTime(long value, TimeUnit unit) {
        System.out.println("Finished in " + formatTime(value, unit));
    }

    public void writeTime(String message, long value, TimeUnit unit) {
        System.out.println(message + formatTime(value, unit));
    }

    // Utility method to format time based on the unit
    private String formatTime(long value, TimeUnit unit) {
        switch (unit) {
            case NANOSECONDS:
                return value + " ns";
            case MICROSECONDS:
                return (value / 1_000.0) + " µs";
            case MILLISECONDS:
                return (value / 1_000_000.0) + " ms";
            case SECONDS:
                return (value / 1_000_000_000.0) + " s";
            default:
                throw new IllegalArgumentException("Unknown TimeUnit: " + unit);
        }
    }
}