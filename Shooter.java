import java.util.*;
import java.io.*;
public class Shooter implements Runnable, Serializable{
	private int delay;
	private Plant owner;
	private int mode;

	public Shooter(float delay, Plant owner){
		this.delay = (int) delay;
		this.owner = owner;
		this.mode = (owner instanceof Peashooter)? 0 : 1;
	}

	public Pea shootPea(){
		Pea p = new Pea(this.owner.getX() + 25,this.owner.getY() + 25, this.owner.getRow(), this.owner.getLawn());
		return p;
	}

	public Sun shootSun(){
		Sun s = new Sun(this.owner.getX(), this.owner.getY(),this.owner.getX() - this.owner.getHght());
		return s;
	}

	private void perform(){
		switch(this.mode){
			case 0: Peashooter p = (Peashooter)this.owner;
				p.action(this.shootPea()); break;
			case 1: Sunflower s = (Sunflower)this.owner;
				s.action(this.shootSun()); break;
		}
	}

	public void run(){
		while(this.owner.isAlive() && !Lawn.gameOver){
			if(this.owner.toShoot()){
				try{Thread.sleep(this.delay * 1000);}catch(Exception e){}
				this.perform();
			}
		}
	}

	public void start(){
		Thread thread = new Thread(this);
		thread.start();
	}
}
