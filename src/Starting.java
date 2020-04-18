import java.util.ArrayList;

public class Starting {
    public static void main(String[] args) {
        // Готовим трассу
        ArrayList<Stage> stages = new ArrayList<>();
        Race myRace = new Race (stages, 7);
        Stage level1 = new Road(80,"стартовый этап", myRace.carsCount);
        Stage level2 = new Tunnel(10, "10-километровый туннель", 2);
        Stage level3 = new Road(40, "второй участок");
        Stage level4 = new Tunnel(15, "15-километровый туннель", 3);
        Stage level5 = new Road(60, "третий участок");
        stages.add(level1);
        stages.add(level2);
        stages.add(level3);
        stages.add(level4);
        stages.add(level5);

        // Добавляем участников и стартуем
        for (int i = 0; i < myRace.carsCount; i++) {
            new Thread(new Car(20,myRace)).start();
        }
    }
}
