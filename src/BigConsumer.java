public class BigConsumer implements Runnable {
    final BigPantIntake bigPantIntake;
    final Wallet wallet;

    public BigConsumer(Wallet wallet, BigPantIntake bigPantIntake) {
        this.wallet = wallet;
        this.bigPantIntake = bigPantIntake;
    }


    @Override
    public void run() {
        synchronized (bigPantIntake) {
            while (true) {
                try {
                    if (bigPantIntake.getSize() == 10) {
                        for (int i = 0; i < bigPantIntake.getSize(); i++) {
                            bigPantIntake.getBottle();
                            wallet.setMoney(wallet.getMoney() + 5);
                            System.out.println("Bottle has been panted ");
                            System.out.print("receipt is now: " + wallet.getMoney() + "\n");
                            bigPantIntake.notify();
                            Thread.sleep(2000);
                        }
                    } else {
                        bigPantIntake.notify();
                        bigPantIntake.wait();
                    }
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }

    }
}// BigConsumer END
