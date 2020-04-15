import java.util.ArrayList;
import java.util.concurrent.CyclicBarrier;

public class Race {
    volatile int place = 1;
    private ArrayList<Stage> stages;
    int carsCount;

    public Race(ArrayList<Stage> stages, int carsCount) {
        this.stages = stages;
        this.carsCount = carsCount;
    }

    public ArrayList<Stage> getStages() {
        return stages;
    }
}