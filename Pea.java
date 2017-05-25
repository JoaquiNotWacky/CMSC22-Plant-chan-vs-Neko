import java.util.*;
import java.util.concurrent.CopyOnWriteArrayList;
import java.io.*;

public class Pea extends Sprite implements Runnable, Serializable{
	private boolean alive;
	private CopyOnWriteArrayList<Zombie> zombies;
	private int row;
	private Lawn lawn;

	public Pea(int xPos, int yPos, int row, Lawn lawn){
		super(xPos, yPos, "pea.png");
		this.alive = true;
		this.row = row;
		this.lawn = lawn;
		this.zombies = lawn.getZombies();
	}

	public void move(){
		this.incX(STEPS);
	}

	public void start(){
		Thread thread = new Thread(this);
		thread.start();
	}


	private void checker(){
		for(Iterator<Zombie> iterator = this.zombies.iterator() ; iterator.hasNext();){
            Zombie z = iterator.next();
			if(this.getRect().intersects(z.getRect())){
				this.lawn.decZombieHealth(z);
				this.alive = false;

				return;
			}
		}
	}

	public void run(){
		while(this.getX() < 1020 && this.alive && !Lawn.gameOver){
			this.checker();
			if(this.alive) this.move();
			else this.lawn.removePea(this);
			try {
				Thread.sleep(100);
			} catch (Exception e){}
		}
	}
}
