public class SmallBottleIntake implements Runnable {
    private String bottleSize = "Small";

    @Override
    public void run() {
        PantIntake pantIntake = new PantIntake();
        Bottle bottle;
        while (true) {
            try {
                Thread.sleep(2000);
            } catch (InterruptedException e) {
            }
            try{
                bottle = pantIntake.takeBottle(bottleSize);
                System.out.println(bottle.getSize() + "test intake");
                if(pantIntake.getSize() < 10){
                }
            } catch (Exception e){
                e.printStackTrace();
            }
        }
    }

}
