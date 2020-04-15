import java.util.concurrent.Semaphore;

public class Tunnel extends Stage {

    public Tunnel(int length, String description, int maxCarsInTunnel) {
        super(length, description);
        semaphore = new Semaphore(maxCarsInTunnel);
    }
}
