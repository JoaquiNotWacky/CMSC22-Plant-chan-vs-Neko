public class Walnut extends Plant{
    public final static String WALNUT_75 = "nat_1.png";
    public final static String WALNUT_50 = "nat_2.png";
    public final static String WALNUT_25 = "nat_2.png";

    public Walnut(){
        super(75,50, WALNUT_75);
    }

    public synchronized void decHealth(){
        this.hp -=1;
        if(this.hp == 50) this.loadImage(WALNUT_50);
        if(this.hp == 25) this.loadImage(WALNUT_25);
        if(this.hp == 0){
            this.alive = false;
            this.lawn.removePlant(this.row,this.col);
        }
    }

}
