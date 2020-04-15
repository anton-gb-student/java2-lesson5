import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;
import java.util.concurrent.Semaphore;

public class Car implements Runnable {
    private static int id = 1;
    private int number;
    private int speed;
    Race race;

    public Car(int speed, Race race) {
        this.number = id;
        this.speed = (int) (speed * (Math.random() + 0.5));
        this.race = race;
        id++;
    }

    public int getNumber() {
        return number;
    }

    public int getSpeed() {
        return speed;
    }

    @Override
    public void run() {
        System.out.println("В гонку вступает участник № " + number + ". Подготовка...");
        try {
            Thread.sleep(500 + (int)(Math.random() * 800));

            for (int i = 0; i < race.getStages().size(); i++) {
                CyclicBarrier start = race.getStages().get(i).getStartPrepare();
                if (start != null) {
                    start.await();
                }
                Semaphore tunnel = race.getStages().get(i).getSemaphore();

                System.out.println("Участник № " + number + " начинает " + race.getStages().get(i).getDescription());
                Thread.sleep(1000 * race.getStages().get(0).getLength() / speed);
                System.out.println("          Участник № " + number + " завершил " + race.getStages().get(i).getDescription());
                if (tunnel != null) {
                    tunnel.release();
                }
            }
            if (race.place == 1) {
                System.out.println("........Участник № " + number + " становится победителем!");
                race.place++;
            } else {
                System.out.println("........Участник № " + number + " занимает " + race.place + " место!");
                race.place++;
            }

        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (BrokenBarrierException e) {
            e.printStackTrace();
        }
    }
}