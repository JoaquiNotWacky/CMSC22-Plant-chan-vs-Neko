import java.util.*;
import java.awt.*;
import java.util.concurrent.CopyOnWriteArrayList;
import java.io.*;

public class Lawnmower extends Sprite implements Runnable, Serializable{
	public static final int LAWNMOWER_YPOS = 140;
	public static final int LAWNMOWER_HEIGHT = 83;
	public static final int EXTRA_SET = 95;
	public static final int LAWNMOWER_XPOS = 125;
	private static final int STEPS = 10;
	private int row;
	private boolean atEnd;
	private boolean alive;
	private Lawn lawn;
	private CopyOnWriteArrayList<Zombie> zombies;
	public Lawnmower(int xPos, int yPos, String filename, int row,  Lawn lawn){
		super(xPos, yPos, filename);
		this.lawn = lawn;
		this.zombies = lawn.getZombies();
		this.atEnd = false;
		this.row = row;
		this.alive = true;
		this.zombies = zombies;
	}

	private void move(){
		this.incX(STEPS);
	}

	public boolean atEnd(){
		return !this.atEnd;
	}
	public void start(){
		Thread t = new Thread(this);
		t.start();
	}

	public boolean isAlive(){
		return this.alive;
	}

	public Rectangle getRect(){
		return new Rectangle(this.getX(), this.getY(), this.getWdth(),this.getHght()-50);
	}

	private void checker(){
		for(Iterator<Zombie> iterator = this.zombies.iterator() ; iterator.hasNext();){
            Zombie z = iterator.next();
			if(this.getRect().intersects(z.getRect())){
				this.lawn.removeZombie(z);
			}
		}
	}

	public void run(){
		this.alive = false;
		while(this.xPos < 1020 && !lawn.gameOver){
			this.checker();
			this.move();
			try {
				Thread.sleep(100);
			} catch (Exception e){}
		}
		this.atEnd = true;
	}
}
