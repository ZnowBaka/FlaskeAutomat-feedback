public class Main {
    public static void main(String[] args) {
        PantIntake pantIntake = new PantIntake();
        BigPantIntake bigPantIntake = new BigPantIntake();
        SmallPantIntake smallPantIntake = new SmallPantIntake();
        Wallet wallet = new Wallet(0);

        Thread pantSplitter = new Thread(new PantSplitter(pantIntake,bigPantIntake,smallPantIntake));
        Thread bottleProducer = new Thread(new BottleProducer(pantIntake));
        Thread smallConsumer = new Thread(new SmallConsumer(wallet, smallPantIntake));
        Thread bigConsumer = new Thread(new BigConsumer(wallet, bigPantIntake));

        bottleProducer.start();
        pantSplitter.start();
        smallConsumer.start();
        bigConsumer.start();

        System.out.println("Hello, World!");
    }
}