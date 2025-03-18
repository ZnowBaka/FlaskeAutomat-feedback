public class BottleProducer implements Runnable {
    private int rand;
    private PantIntake pantIntake;

    public BottleProducer(PantIntake pantIntake) {
        this.pantIntake = pantIntake;
    }

    /*
    Forkert brug af wait()
    Gammel BottleProducer kaldte wait() uden at tjekke, om listen faktisk var fuld.
     */

    @Override
    public void run() {
        synchronized (pantIntake) {
            while (true) {
                // Når vi har adgang...
                synchronized (pantIntake) {
                    // Hvis Intake er "fuldt"...
                    while (pantIntake.getSize() >= 10) { // Korrekt ventelogik: Bruger while (pantIntake.getSize() >= 10), så tråden ikke vågner unødvendigt.
                        try {
                            // Vent med at producer flere flasker...
                            pantIntake.wait();
                        } catch (InterruptedException e) {
                            Thread.currentThread().interrupt();
                        }
                    } // ...Ellers producer flasker

                    // Producer en flaske
                    rand = (int) (Math.random() * 2 + 1);
                    Bottle bottle = new Bottle(rand == 1 ? "Big" : "Small");
                    pantIntake.addBottle(bottle);
                    System.out.println("Bottle size: " + bottle.getSize() + " was inserted into the machine");
                    pantIntake.notifyAll(); // Bruger notifyAll(), så både PantSplitter og forbrugerne vækkes, når en ny flaske tilføjes.

                    try {
                        Thread.sleep(1000); // Simuler produktionstid
                    } catch (InterruptedException e) {
                        Thread.currentThread().interrupt();
                    }
                }
            }
        }
    }


}// BottleProducer END