import java.util.ArrayList;

public class SmallPantIntake {
    private ArrayList<Bottle> bottles = new ArrayList<>();

    public void addBottle(Bottle bottle) {
        if(bottles.size() < 10) {
            bottles.add(bottle);
            System.out.println(bottles.size());
            System.out.println("Small intake received: " + bottles.getLast().getSize() + " bottle");
            System.out.println("There is: " + bottles.size() + " in Small intake");
        }
    }

    public Bottle getBottle() {
        Bottle sortedBottle = null;
        sortedBottle = bottles.get(0);
        bottles.remove(bottles.get(0));

        return sortedBottle;
    }

    public int getSize() {
        return bottles.size();
    }



}
