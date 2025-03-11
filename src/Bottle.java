public class Bottle {
    private boolean pant;
    private String size;
    public Bottle(boolean pant, String size){
        this.pant = pant;
        this.size = size;
    }
    public Bottle(String size){
        this.size = size;
    }

    public boolean isPant() {
        return pant;
    }

    public String getSize() {
        return size;
    }
}