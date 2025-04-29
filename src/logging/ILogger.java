package logging;
//three classes should implement this interface: ConsoleLogger, FileLogger, DatabaseLogger
public interface ILogger {
    void write(long value);
    void write(String message);
    void write(Object... values);
    void close();
}
