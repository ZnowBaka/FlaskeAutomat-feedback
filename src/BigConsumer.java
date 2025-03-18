public class BigConsumer implements Runnable {
    final BigPantIntake bigPantIntake;
    final Wallet wallet;

    public BigConsumer(Wallet wallet, BigPantIntake bigPantIntake) {
        this.wallet = wallet;
        this.bigPantIntake = bigPantIntake;
    }


    @Override
    public void run() {
        while (true) {
            synchronized (bigPantIntake) {
                try {
                    while (bigPantIntake.getSize() < 10) { // Korrekt ventelogik: Denne løsning frem for "if statements", sikre at tråden ikke vågner unødvendigt.
                        bigPantIntake.wait();
                    }

                    for (int i = 0; i < 10; i++) {
                        bigPantIntake.getBottle();
                        wallet.setMoney(wallet.getMoney() + 5);
                        System.out.println("Bottle has been panted ");
                        System.out.print("receipt is now: " + wallet.getMoney() + "\n");
                    }

                    bigPantIntake.notifyAll(); // Bruger notifyAll() i stedet for notify(), så alle ventende tråde vækkes korrekt.
                    Thread.sleep(2000);
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
                }
            }
        }
    }


}// BigConsumer END
