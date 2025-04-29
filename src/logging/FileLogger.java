//package logging;
//
//import java.io.BufferedWriter;
//import java.io.FileWriter;
//import java.io.IOException;
//import java.util.concurrent.TimeUnit;
//
//public class FileLogger implements ILogger {
//    private BufferedWriter writer;
//
//    /**
//     * Constructor - opens a file for writing.
//     * @param filePath The path of the file to write to.
//     */
//    public FileLogger(String filePath) {
//        try {
//            writer = new BufferedWriter(new FileWriter(filePath));
//        } catch (IOException e) {
//            System.err.println("Error opening file: " + e.getMessage());
//        }
//    }
//
//    /**
//     * Writes a long value to the file.
//     */
//    @Override
//    public void write(long value) {
//        try {
//            writer.write(Long.toString(value));
//            writer.newLine();
//        } catch (IOException e) {
//            System.err.println("Error writing long: " + e.getMessage());
//        }
//    }
//
//    /**
//     * Writes a string message to the file.
//     */
//    @Override
//    public void write(String message) {
//        try {
//            writer.write(message);
//            writer.newLine();
//        } catch (IOException e) {
//            System.err.println("Error writing string: " + e.getMessage());
//        }
//    }
//
//    /**
//     * Writes multiple values to the file, separated by space.
//     */
//    @Override
//    public void write(Object... values) {
//        try {
//            for (Object value : values) {
//                writer.write(value.toString() + " ");
//            }
//            writer.newLine();
//        } catch (IOException e) {
//            System.err.println("Error writing values: " + e.getMessage());
//        }
//    }
//
//    /**
//     * Closes the file writer.
//     */
//    @Override
//    public void close() {
//        try {
//            if (writer != null) {
//                writer.close();
//            }
//        } catch (IOException e) {
//            System.err.println("Error closing file: " + e.getMessage());
//        }
//    }
//
//    @Override
//    public void writeTime(long value, TimeUnit unit) {
//
//    }
//}