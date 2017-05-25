import java.awt.*;
import java.util.*;
public class PotatoMine extends Plant{
	private boolean isExplosive;

	public PotatoMine(){
		super(5, 25, "mina_1.png");
		this.isExplosive = false;
	}

	public void shootingStart(){
		this.start();
	}

	@Override
	public Rectangle getRect(){
		return new Rectangle(xPos, yPos + 30, width + 10, height - 60);
	}

	public void start(){
		Thread thread = new Thread(this);
		thread.start();
	}

	public void checker(){
		boolean bool = false;
		for(Iterator<Zombie> iterator = this.lawn.getZombies().iterator() ; iterator.hasNext();){
            Zombie z = iterator.next();
			if(this.getRect().intersects(z.getRect())){
				this.lawn.removeZombie(z);
				bool = true;
			}
		}if(bool){
			this.alive = false;
			this.hp = 0;
			this.lawn.removePlant(this.row,this.col);
		}
	}

	public void run(){
		int count = 0;
		while(count < 3)try{Thread.sleep(1000); count++;}catch(Exception e){}
		this.loadImage("mina_2.png");
		this.isExplosive = true;
		while(this.alive){
			this.checker();
		}
	}
}
