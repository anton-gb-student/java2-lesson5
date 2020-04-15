import java.util.ArrayList;

public class Race {
    volatile int place = 1;           // Какое место займет участник
    private ArrayList<Stage> stages;  // Этапы гонки
    int carsCount;                    // Количество машин

    public Race(ArrayList<Stage> stages, int carsCount) {
        this.stages = stages;
        this.carsCount = carsCount;
    }

    public ArrayList<Stage> getStages() {
        return stages;
    }
}