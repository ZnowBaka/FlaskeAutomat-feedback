public class BottleProducer implements Runnable {
    private int rand;
    private PantIntake pantIntake = new PantIntake();
    @Override
    public void run() {
        PantIntake pantIntake = new PantIntake();
        while (true) {
            rand = (int)(Math.random()*2 + 1);
            System.out.println(rand);
            if (rand == 1) {
                Bottle bottle = new Bottle("Big");
                System.out.println("Bottle " + bottle.getSize());
                pantIntake.add(bottle);
                try {
                    Thread.sleep(2000);
                } catch (InterruptedException e) {
                }
            } else if (rand == 2) {
                Bottle bottle = new Bottle("Small");
                System.out.println("Bottle " + bottle.getSize());
                pantIntake.add(bottle);
                try {
                    Thread.sleep(2000);
                } catch (InterruptedException e) {
                }
            }
            try {
                Thread.sleep(2000);
            } catch (InterruptedException e) {
            }
        }
    }
}