package timing;

public class Timer implements TimeMeasure{

    private long totalTime, start, end;
    @Override
    public void start() {
        totalTime = 0;
        start = System.nanoTime();
    }

    @Override
    public long stop() {
        end  = System.nanoTime();
        totalTime += end - start;
        return totalTime;
    }

    @Override
    public void resume() {
        start = System.nanoTime();


    }

    @Override
    public long pause() {
        end =  System.nanoTime();
        totalTime += end - start;
        return end - start;
    }
}
