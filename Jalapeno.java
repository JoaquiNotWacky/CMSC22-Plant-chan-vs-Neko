import java.util.*;
public class Jalapeno extends Plant{
    public final static String JALAPENO_START = "julie_1.png";
    public final static String JALAPENO_MIDDLE = "julie_3.png";
    public final static String JALAPENO_END = "jEnd.png";

    public Jalapeno(){
        super(10,150, JALAPENO_START);
    }

    public void action(){
        for(Iterator<Zombie> iterator = this.lawn.getZombies().iterator() ; iterator.hasNext();){
            Zombie z = iterator.next();
			if(this.getRow() == z.getRow()){
				this.lawn.removeZombie(z);
			}
		}
    }

    public void shootingStart(){
        this.start();
    }

    public void start(){
        Thread thread = new Thread(this);
        thread.start();
    }

    public void run(){
        int count = 0;
        while(count < 3){
            try{
                count++;
                if(count == 2) this.loadImage(JALAPENO_MIDDLE);
                Thread.sleep(800);
            }catch(Exception e){}
        }
        this.setX(205);
        this.loadImage(JALAPENO_END);
        this.action();

        try{
            Thread.sleep(2000);
        }catch(Exception e){}
        this.lawn.removePlant(this.getRow(), this.getCol());
    }

}
