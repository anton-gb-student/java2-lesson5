import java.util.concurrent.CyclicBarrier;

public class Road extends Stage {

    public Road(int length, String description) {
        super(length, description);
    }

    public Road(int length, String description, int carsCount) {
        super(length, description);
        this.startPrepare = new CyclicBarrier(carsCount);
    }
}
