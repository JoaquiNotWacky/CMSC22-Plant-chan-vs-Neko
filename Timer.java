import java.io.*;

public class Timer implements Runnable, Serializable{
    private int count;
    private Lawn lawn;
    private boolean paused;
    private static final long serialVersionUID = 1542754695000814348L;
    // private boolean sunflower; //this is like a mode
    public static final int ONE_SECOND = 1000;

	public Timer(Lawn lawn){ //edited
		this.count = 0;
        this.lawn = lawn;
        this.paused = false;
        // this.sunflower = mode;
	}

    public void start(){
        Thread thread = new Thread(this);
        thread.start();
    }

    public void pause(){
        int temp = this.count;
        this.paused = true;
        while(this.paused)this.count = temp;
    }

    public void play(){
        this.paused = false;
    }

	public int getTime(){
		return this.count;
	}

    @Override
	public void run(){ //the timer will just run time, will not initalize in spawning anything
        while(!Lawn.gameOver){
            System.out.println("started");
            if(this.count > 60 && !this.lawn.hasZombie()){
                    this.lawn.setOver();
                    this.lawn.setWin();
            }
                    //call other shit here
            try{
                Thread.sleep(ONE_SECOND);
                if(this.count % 60 == 0)this.lawn.minutePassed();
                System.out.println(this.count);
                this.count++;
            }catch(Exception e){}
        }
    }
}
