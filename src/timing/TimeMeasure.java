package timing;

public interface TimeMeasure {
    void start(); // saves the current time (e.g., in a long variable).
    long stop(); //calculates how much time passed
    void resume();
    long pause(); //pause during non-important work (like loading data).
}
