import java.io.*;

public class Plant extends Sprite implements Runnable, Serializable{
    protected int hp;
    protected int cost;
    protected int row;
    protected int col;
    protected boolean alive;
    protected Lawn lawn;
    public String currImage;

    public Plant(int hp, int cost, String filename){
        super(0,0,filename);
        this.hp = hp;
        this.cost = cost;
        this.alive = true;
    }

    protected void action(){
        System.out.println();
    }

    public void shootingStart(){
        System.out.println();
    }

    public void run(){
        System.out.println();
    }

    public int getCost(){
        return this.cost;
    }

    public boolean isAlive(){
        return this.alive;
    }

    public int getRow(){
        return this.row;
    }

    public int getCol(){
        return this.col;
    }

    public void setRow(int row){
        this.row = row;
    }

    public void setCol(int col){
        this.col =col;
    }

    public boolean toShoot(){
        return true;
    }

    public synchronized void decHealth(){
        this.hp -=1;
        if(this.hp == 0){
            this.alive = false;
            this.lawn.removePlant(this.row,this.col);
        }
    }

    public void setLawn(Lawn lawn){
        this.lawn = lawn;
    }

    public Lawn getLawn(){
        return this.lawn;
    }

    public void start(){
        Thread thread = new Thread(this);
        thread.start();
    }
}
