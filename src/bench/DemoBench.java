package bench;


import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;

public class DemoBench implements IBenchmark{

    // select sort

    private ArrayList<Integer> array;

    private boolean running; // this is for the cancel method. cancel will set it to false which will stop any operation
    //every method needs to keep checking if running is true, when its false it stops operations
    Random random = new Random(); // a random number generator to generate the numbers in array
    private int arraySize;
    Scanner scan = new Scanner(System.in);
    public DemoBench(){}

    @Override
    public void run() {

        //null and empty check
        if (array == null){
            System.out.println("initialize() needs to be called first");
            return; //exit the program
        }


        //set running to true when starting
        running = true;

        //clear array before so it doesnt overflow if run() gets called multiple times
        array.clear();
        //populate array
        for ( int i = 0; i < arraySize && running; i++){
            array.add(random.nextInt(1000));
        }



        //the select sort algo
        for (int i = 0; i < array.size() - 1 && running; i++) {
            int minIndex = i;
            for (int j = i + 1; j < array.size() && running; j++) {
                if (array.get(j) < array.get(minIndex)) {
                    minIndex = j;
                }
            }
            // swap array[i] and array[minIndex]
            int temp = array.get(i);
            array.set(i, array.get(minIndex));
            array.set(minIndex, temp);
        }


    }

    @Override
    public void run(Object... params) {

    }

    @Override
    public void initialize(Object... params) {
        // only taking the size of the array and allocating space for it in arraylist
        System.out.println("Enter array size"); //
        arraySize = scan.nextInt();
        array = new ArrayList<Integer>(arraySize); // create arraylist with specified size
        running = true;
    }



    @Override
    public void clean() {
        if ( array != null){
            array.clear();
        }

    }

    @Override
    public void cancel() {
        running = false;

    }
}
