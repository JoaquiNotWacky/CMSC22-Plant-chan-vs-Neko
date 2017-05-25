import java.io.*;

public class Sun extends Sprite implements Runnable, Serializable{
	private int target;

	public Sun(int xPos, int yPos, int target){
		super(xPos, yPos, "sun.png");
		this.target = target;
	}

	public void move(){
		this.incY(STEPS);
	}

	public void start(){
		Thread thread = new Thread(this);
		thread.start();
	}

	public boolean toShoot(){
		return true;
	}

	public void run(){
		while(this.yPos < this.target && !Lawn.gameOver){
			this.move();
			try {
				Thread.sleep(100);
			} catch (Exception e){}
		}
	}
}
