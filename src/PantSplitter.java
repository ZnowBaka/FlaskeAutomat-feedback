public class PantSplitter implements Runnable {
    final PantIntake ProducerPantIntake;
    final BigPantIntake BigPantIntake;
    final SmallPantIntake SmallPantIntake;

    public PantSplitter(PantIntake producerPantIntake, BigPantIntake bigPantIntake, SmallPantIntake smallPantIntake) {
        this.ProducerPantIntake = producerPantIntake;
        this.BigPantIntake = bigPantIntake;
        this.SmallPantIntake = smallPantIntake;
    }


    @Override
    public void run() {
        BigPantIntake bigPantIntake = BigPantIntake;
        SmallPantIntake smallPantIntake = SmallPantIntake;

        synchronized (ProducerPantIntake) {
            while (true) {
                if (ProducerPantIntake.getSize() == 10) {
                    for (int i = 0; i < ProducerPantIntake.getSize(); i++) {
                        try {
                            Bottle bottleToSort = ProducerPantIntake.getBottle();
                            System.out.println(bottleToSort.getSize() + " bottle added to splitter");

                            if (bottleToSort.getSize().equals("Big")) {
                                synchronized (BigPantIntake) {
                                    if (bigPantIntake.getSize() < 10) {
                                        System.out.println(bottleToSort.getSize() + " bottle sent to Big-Intake");
                                        BigPantIntake.addBottle(bottleToSort);
                                        BigPantIntake.notify();
                                        ProducerPantIntake.notify();
                                        Thread.sleep(1000);
                                    } else {
                                        BigPantIntake.notify();
                                        BigPantIntake.wait();
                                    }
                                }
                            } else if (bottleToSort.getSize().equals("Small")) {
                                synchronized (SmallPantIntake) {
                                    if (smallPantIntake.getSize() < 10) {
                                        System.out.println(bottleToSort.getSize() + " bottle sent to Small-Intake");
                                        SmallPantIntake.addBottle(bottleToSort);
                                        SmallPantIntake.notify();
                                        Thread.sleep(1000);
                                        ProducerPantIntake.notify();
                                    } else {
                                        SmallPantIntake.notify();
                                        SmallPantIntake.wait();
                                    }
                                }
                            }

                            Thread.sleep(1000);
                        } catch (InterruptedException e) {

                        }
                    }
                    try {
                        ProducerPantIntake.notify();
                        ProducerPantIntake.wait();
                    } catch (InterruptedException e) {
                    }
                } else {
                    try {
                        ProducerPantIntake.notify();
                        ProducerPantIntake.wait();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }

            }
        }
    }
}
