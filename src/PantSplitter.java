public class PantSplitter implements Runnable {
    final PantIntake producerPantIntake;
    final BigPantIntake bigPantIntake;
    final SmallPantIntake SmallPantIntake;

    public PantSplitter(PantIntake producerPantIntake, BigPantIntake bigPantIntake, SmallPantIntake smallPantIntake) {
        this.producerPantIntake = producerPantIntake;
        this.bigPantIntake = bigPantIntake;
        this.SmallPantIntake = smallPantIntake;
    }

    /*
    Farlig synkroniserings logik!
    PantSplitter lavede farlige unlock/re-lock operationer på Big- og SmallPantIntake inde i samme metode.
    Dette kan føre til deadlocks, hvis en tråd venter på to låse samtidigt.
     */

    @Override
    public void run() {
        while (true) {

            synchronized (producerPantIntake) {
                while (producerPantIntake.getSize() < 10) { // Korrekt ventelogik: Denne løsning frem for "if statements", sikre at tråden ikke vågner unødvendigt.
                    try {
                        producerPantIntake.wait();
                    } catch (InterruptedException e) {
                        Thread.currentThread().interrupt();
                    }
                }

                for (int i = 0; i < 10; i++) {

                    try {
                        Thread.sleep(500); // Simuler sorteringstid
                    } catch (InterruptedException e) {
                        Thread.currentThread().interrupt();
                    }

                    Bottle bottleToSort = producerPantIntake.getBottle();
                    System.out.println(bottleToSort.getSize() + " bottle added to splitter");

                    if (bottleToSort.getSize().equals("Big")) {
                        synchronized (bigPantIntake) {
                            bigPantIntake.addBottle(bottleToSort);
                            bigPantIntake.notify();
                        }

                    } else {
                        synchronized (SmallPantIntake) {
                            SmallPantIntake.addBottle(bottleToSort);
                            SmallPantIntake.notify();
                        }
                    }

                }
                producerPantIntake.notifyAll(); // Bruger notifyAll() i stedet for notify(), så alle ventende tråde vækkes korrekt.
            }
        }
    }


} // PantSplitter END

