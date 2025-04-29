package logging;

import java.util.concurrent.TimeUnit;

//three classes should implement this interface: ConsoleLogger, FileLogger, DatabaseLogger
public interface ILogger {
    void write(long value);
    void write(String message);
    void write(Object... values);
    void close();
    void writeTime(long value, TimeUnit unit);
    void writeTime(String message, long value, TimeUnit unit);
}
