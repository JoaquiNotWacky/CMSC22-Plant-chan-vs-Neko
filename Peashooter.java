import java.util.*;
import java.awt.*;
import java.util.concurrent.CopyOnWriteArrayList;
public class Peashooter extends Plant{
    public final static String PEASHOOTER_FILE = "pia_still.png";
    private CopyOnWriteArrayList<Pea> peas;
    private Shooter shooter;

    public Peashooter(){
        super(5,100,PEASHOOTER_FILE);
        this.peas = new CopyOnWriteArrayList<Pea>();
        this.shooter = new Shooter(3f, this);
        //this.shooter.start();
    }

    public void shootingStart(){
        this.shooter.start();
    }

    public void action(Pea p){
        this.lawn.addPeas(p);
        (this.lawn.getPeas().get(this.lawn.getPeas().size() - 1)).start();
    }

    public boolean toShoot(){
        for(Iterator<Zombie> iterator = this.lawn.getZombies().iterator() ; iterator.hasNext();){
            Zombie z = iterator.next();
            if(z == null)continue;
            if(z.getRow() == this.row){
                return true;
            }
        }return false;
    }

}
