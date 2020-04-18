import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;
import java.util.concurrent.Semaphore;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class Car implements Runnable {
    private static int id = 1;          // Поле для автоматической нумерации участников
    private int number;
    private int speed;
    Race race;
    Lock lock = new ReentrantLock();

    public Car(int speed, Race race) {
        this.number = id;
        this.speed = (int) (speed * (Math.random() + 0.5));  // Добавил рандомный разброс по скорости
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
            System.out.println("Speed # " + number + " = " + speed);

            for (int i = 0; i < race.getStages().size(); i++) {
                CyclicBarrier start = race.getStages().get(i).getStartPrepare();
                if (start != null) {
                    start.await();
                }
                Semaphore tunnel = race.getStages().get(i).getSemaphore();
                if (tunnel != null) {
                    System.out.println("Участник № " + number + " ожидает " + race.getStages().get(i).getDescription());
                    tunnel.acquire();
                }
                System.out.println("Участник № " + number + " начинает " + race.getStages().get(i).getDescription());
                Thread.sleep(1000 * race.getStages().get(0).getLength() / speed);
                System.out.println("          Участник № " + number + " завершил " + race.getStages().get(i).getDescription());
                if (tunnel != null) {
                    tunnel.release();     // Это место мне не нравится, но пока не придумал, как сделать по-другому
                }
            }
        }
        catch (InterruptedException | BrokenBarrierException e) {
            e.printStackTrace();
        }

        try {
             lock.tryLock(100, TimeUnit.MILLISECONDS);
             if (race.place == 1) {
                race.place++;                     // Сразу увеличиваем, чтобы застолбить место
                System.out.println("........Участник № " + number + " становится победителем!");
                }
             else {
                System.out.println("........Участник № " + number + " занимает " + race.place++ + " место!");
                }
                lock.unlock();
        }
        catch (InterruptedException ex) {
                ex.printStackTrace();
        }
    }

}