import java.util.*;
import java.io.*;

public class Spawner implements Runnable, Serializable{
	private Lawn lawn;
	private int maxdelay;
	private int initialDelay;
	private int id;
	private static int spawnercount;
	private boolean alive;
	// private int mode;
	private final static int SEC = 1000;

	public Spawner(int id, int maxdelay, int initialDelay, Lawn l){
		this.id = id;
		this.alive = false;
		this.maxdelay = maxdelay;
		this.initialDelay = initialDelay;
		this.lawn = l;
	}

	public void setDelay(int initDelay){
		this.initialDelay = initDelay;
	}

	public void decDelay(int delay){
		this.maxdelay -= delay;
	}

	public boolean isRunning(){
		return this.alive;
	}

	public void start(){
		Thread thread = new Thread(this);
		this.alive = true;
		thread.start();
	}

	public Zombie zSpawn(){
		Zombie z = new Zombie(1020, 119 + 88 * this.id, "tempzombie.png", this.id, this.lawn);
		return z;
	}

	public Sun sSpawn(){
		Random r = new Random();
		Sun s = new Sun((r.nextInt(10)) * 80 + 205,0, (r.nextInt(5)) * 90 + 119);
		return s;
	}

	public void perform(){
		if(this.id >= 0)this.lawn.addZombie(this.id);
		else this.lawn.addSun();
	}

	public void run(){
		try{Thread.sleep(this.initialDelay * SEC);}catch(Exception e){}
		while(this.lawn.minutes < 2){
			this.perform();
			try{Thread.sleep((this.maxdelay - ((this.id >= 0)? new Random().nextInt(5) : 0)) * SEC);}catch(Exception e){}
		}

	}



}
