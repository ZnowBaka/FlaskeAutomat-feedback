public class SmallConsumer implements Runnable {
    final SmallPantIntake smallPantIntake;
    final Wallet wallet;

    public SmallConsumer(Wallet wallet, SmallPantIntake smallPantIntake) {
        this.wallet = wallet;
        this.smallPantIntake = smallPantIntake;
    }


    @Override
    public void run() {
        synchronized (smallPantIntake) {
            while (true) {
                try {
                    if (smallPantIntake.getSize() == 10) {
                        for (int i = 0; i < smallPantIntake.getSize(); i++) {
                            smallPantIntake.getBottle();
                            wallet.setMoney(wallet.getMoney() + 2);
                            System.out.println("Bottle has been panted ");
                            System.out.print("receipt is now: " + wallet.getMoney() + "\n");
                            smallPantIntake.notify();
                            Thread.sleep(2000);
                        }
                    } else {
                        smallPantIntake.notify();
                        smallPantIntake.wait();
                    }
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }

    }
}