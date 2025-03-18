public class SmallConsumer implements Runnable {
    final SmallPantIntake smallPantIntake;
    final Wallet wallet;

    public SmallConsumer(Wallet wallet, SmallPantIntake smallPantIntake) {
        this.wallet = wallet;
        this.smallPantIntake = smallPantIntake;
    }


    @Override
    public void run() {
        while (true) {
            synchronized (smallPantIntake) {
                try {
                    while (smallPantIntake.getSize() < 10) { // Korrekt ventelogik: Denne løsning frem for "if statements", sikre at tråden ikke vågner unødvendigt.
                        smallPantIntake.wait();
                    }

                    for (int i = 0; i < 10; i++) {
                        smallPantIntake.getBottle();
                        wallet.setMoney(wallet.getMoney() + 3);
                        System.out.println("Bottle has been panted ");
                        System.out.print("receipt is now: " + wallet.getMoney() + "\n");
                    }

                    smallPantIntake.notifyAll(); // Bruger notifyAll() i stedet for notify(), så alle ventende tråde vækkes korrekt.
                    Thread.sleep(2000);
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
                }
            }
        }
    }


}