public class BottleProducer implements Runnable {
    private int rand;
    private PantIntake pantIntake;

    public BottleProducer(PantIntake pantIntake) {
        this.pantIntake = pantIntake;
    }

    @Override
    public void run() {
        synchronized (pantIntake) {
            while (true) {
                if (pantIntake.getSize() < 10) {

                    rand = (int) (Math.random() * 2 + 1);
                    System.out.println(rand);

                    if (rand == 1) {
                        Bottle bottle = new Bottle("Big");
                        System.out.println("Bottle size: " + bottle.getSize() + " was inserted into the machine");
                        pantIntake.addBottle(bottle);
                        try {
                            pantIntake.notify();
                            Thread.sleep(1000);
                        } catch (InterruptedException e) {
                        }
                    } else if (rand == 2) {
                        Bottle bottle = new Bottle("Small");
                        System.out.println("Bottle size: " + bottle.getSize() + " was inserted into the machine");
                        pantIntake.addBottle(bottle);
                        try {
                            pantIntake.notify();
                            Thread.sleep(1000);
                        } catch (InterruptedException e) {
                        }
                    }
                } else {
                    try {
                        pantIntake.notify();
                        pantIntake.wait();
                    } catch (InterruptedException e) {
                    }
                }
            }
        }
    }


}// BottleProducer END