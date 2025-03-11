public class Main {
    public static void main(String[] args) {
        Thread bottleProducer = new Thread(new BottleProducer());
        Thread smallBottleIntake = new Thread(new SmallBottleIntake());
        bottleProducer.start();
        smallBottleIntake.start();


        System.out.println("Hello, World!");
    }
}