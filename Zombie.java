import java.awt.*;
import java.util.*;
import java.io.*;

public class Zombie extends Sprite implements Runnable,Serializable{
	private int hp;
	private int row;
	private String filename;
	private Lawnmower[] lawnmower;
	private Plant[][] plants;
	private boolean alive;
	private Lawn lawn;
	private boolean eating;

	public Zombie(int xPos, int yPos, String filename, int row, Lawn lawn){
		super(xPos, yPos, filename);
		this.eating = false;
		this.lawn = lawn;
		this.alive = true;
		this.hp = 10;
		this.row = row;
		this.filename = filename;
		this.lawnmower = lawn.getLawnmowers();
		this.plants = lawn.getPlants();
	}

	public void setImage(String filename){
		super.loadImage(filename);
	}

	public void move(){
		this.decX(STEPS);
	}

	public void start(){
		Thread thread = new Thread(this);
		thread.start();
	}

	public Rectangle getRect2(){
		return new Rectangle(this.getX()+50, this.getY()+10, this.getWdth()-70,this.getHght()-70);
	}

	public int getRow(){
		return this.row;
	}

	private boolean findPlant(){
		for(int i = 0; i < 9; i++){
			if(this.plants[row][i] != null){
				if(this.getRect2().intersects(this.plants[row][i].getRect()) && this.plants[row][i].isAlive()){
					this.eat(this.plants[row][i]);
					this.eating = true;
					return false;
				}
			}
		}
		this.eating = false;
		return true;
	}

	private void eat(Plant plant){
		plant.decHealth();
	}



	private void checker(){
		if(this.getRect().intersects(this.lawnmower[this.row].getRect()) && this.lawnmower[this.row].isAlive()){
			this.lawnmower[this.row].start();
		} else if(this.findPlant()){
			this.move();
		}
	}

	public synchronized void kill(){
		this.alive = false;
		this.hp = 0;
	}

	public void run(){
		while(this.xPos > 0 && this.alive && !Lawn.gameOver){
			checker();
			if(this.xPos <= 120) Lawn.setOver();
			try {
				if(!this.eating) Thread.sleep(350);
				else Thread.sleep(500);
			} catch (Exception e){}
		}
	}

	public synchronized void decHealth(Zombie z){
		this.hp -=1;
        if(this.hp == 0){
            this.lawn.removeZombie(z);
        }
	}
}
