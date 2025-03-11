import java.util.ArrayList;

public class PantIntake {

    private ArrayList<Bottle> intakeList = new ArrayList<>();
    public void add(Bottle bottle) {
                System.out.println(bottle.getSize() + " adding");
                intakeList.add(bottle);
                System.out.println(intakeList.getLast().getSize());
    }
    public synchronized Bottle takeBottle(String size) throws Exception {
        int index;
        if(intakeList.size() > 0 || intakeList.size() < 10) {
            Bottle bottle = null;
            if(size == "Big"){

                if(intakeList.contains(bottle.getSize()=="Big")) {

                    index = intakeList.indexOf(bottle.getSize() == "Big");
                    bottle = intakeList.get(index);
                    intakeList.remove(index);
                    notify();
                    return bottle;
                } else {
                    wait();
                }
            } else if (size == "Small"){

                    bottle = intakeList.getFirst();
                    System.out.println(bottle.getSize());
                    intakeList.remove(intakeList.getFirst());
                    return bottle;

            } else{
                throw new Exception();
            }
        } else {
            notify();
        }
return null;
    }
    public int getSize() {
        return intakeList.size();
    }
}
