package bench;

public interface IBenchmark {
    void run(); // just a basic run
    void run(Object... params); //for when you need to run the program with specific inputs
    void initialize(Object... params); //preparing before run. not supposed to measure this

//    void initialize(int n);

    void clean(); // operations after the run, also not supposed to be measured
    void cancel();

}
