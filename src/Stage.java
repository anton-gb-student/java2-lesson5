import java.util.concurrent.CyclicBarrier;
import java.util.concurrent.Semaphore;

public class Stage {
    private int length;
    private String description;
    Semaphore semaphore = null;          // Семафор для туннелей
    CyclicBarrier startPrepare = null;   // Барьер для стартового этапа

    public Stage(int length, String description) {
        this.length = length;
        this.description = description;
    }

    public int getLength() {
        return length;
    }

    public String getDescription() {
        return description;
    }

    public Semaphore getSemaphore() {
        return semaphore;
    }

    public CyclicBarrier getStartPrepare() {
        return startPrepare;
    }
}
