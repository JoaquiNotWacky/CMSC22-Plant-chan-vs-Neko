import java.util.*;
import java.awt.*;

public class Sunflower extends Plant{
    public final static String SUNFLOWER_FILE = "sonia_still.png";
    private Shooter shooter;

    public Sunflower(){
        super(5,50,SUNFLOWER_FILE);
        this.shooter = new Shooter(15f,this);
        //this.shooter.start();
    }

    public void shootingStart(){
        this.shooter.start();
    }

    public void action(Sun s){
    	this.lawn.addSun(s);
    	(this.lawn.getSuns().get(this.lawn.getSuns().size() - 1)).start();
    }

    public boolean toShoot(){
    	return true;
    }

}
