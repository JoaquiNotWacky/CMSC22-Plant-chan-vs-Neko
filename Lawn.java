import java.util.*;
import javax.swing.*;
import java.awt.*;
import java.util.*;
import javax.swing.*;
import javax.imageio.*;
import java.io.*;
import java.awt.image.BufferedImage;
import java.util.concurrent.CopyOnWriteArrayList;

public class Lawn implements Serializable{
	private static final int xPos = 205;
	private static final int yPos = 130;
	private transient BufferedImage img;
	private Seed seed;
	private Grid grid;
	private boolean canPlant;
	private Plant whatPlant;
	private int sunCount;
	public static boolean gameOver;
	private boolean isWin;
	private Lawnmower[] lawnmowers;
	private CopyOnWriteArrayList<Zombie> zombies;
	private CopyOnWriteArrayList<Sun> suns;
	private CopyOnWriteArrayList<Pea> peas;
	private Spawner[] zSpawner;
	private Spawner sSpawner;
	private Timer timer;
	public static int minutes;
	private static final long serialVersionUID = -2228939238203302363L;

	public Lawn(){
		this.gameOver = false;
		this.sunCount = 150;
		this.grid = new Grid();
		this.seed = null;
		this.canPlant = false;
		this.whatPlant = null;
		this.loadImage("lawn.png");
		this.lawnmowers = new Lawnmower[5];
		this.zombies = new CopyOnWriteArrayList<Zombie>();
		this.peas = new CopyOnWriteArrayList<Pea>();
		this.suns = new CopyOnWriteArrayList<Sun>();
		this.zSpawner = new Spawner[5];
		this.sSpawner = new Spawner(-1, 10, 10, this);
		for(int z = 0; z < 5; z++)this.zSpawner[z] = new Spawner(z, 30, 0, this);
		this.timer = new Timer(this);
		// this.startSpawners();
		this.makeLawnmowers();
	}

	public void save(){
		try{
			ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream("Lawn.txt"));
			out.writeObject(this);
			out.close();
			System.out.println("saved");
		}catch(Exception e){
			e.printStackTrace();
			System.out.println("unsuccessful");}
	}

	public void startSpawners(){
		int started = 0, index, y = 25;
		Random r = new Random();
		for(index = r.nextInt(5); started < 5; index = r.nextInt(5)){
			if(!this.zSpawner[index].isRunning()){
				this.zSpawner[index].setDelay(y);
				this.zSpawner[index].start();
				y = y += 20;
				started++;
			}
		}
		this.sSpawner.start();
		this.timer.start();
	}

	public void loadSpawners(){
		for(int i = 0; i < 5; i++){
			this.zSpawner[i].setDelay(i * 20);
			this.zSpawner[i].start();
		}
		this.sSpawner.start();
		this.timer.start();
	}

	public boolean noZombies(){
		return (this.zombies.size() > 0)? false : true;
	}

	public void minutePassed(){
		this.minutes += 1;
		for(int index = 0; index < 5; index++){
			System.out.println("minutepassed = "+this.minutes);
			this.zSpawner[index].decDelay(5 - this.minutes);
		}
	}

	public void setWin(){
		this.isWin = true;
	}

	public boolean hasZombie(){
		return (this.zombies.size() > 0)? true : false;
	}

	public synchronized boolean getIsWin(){
		return this.isWin;
	}

	public synchronized void addPeas(Pea p){
		this.peas.add(p);
	}

	public CopyOnWriteArrayList<Pea> getPeas(){
		return this.peas;
	}

	public synchronized void addSun(Sun s){
		this.suns.add(s);
	}

	public CopyOnWriteArrayList<Sun> getSuns(){
		return this.suns;
	}

	public synchronized void addZombie(int id){
		this.zombies.add(this.zSpawner[id].zSpawn());
		this.zombies.get(this.zombies.size() - 1).start();
	}

	public synchronized void addSun(){
		this.suns.add(this.sSpawner.sSpawn());
		this.suns.get(this.suns.size() - 1).start();
	}


	public void loadImage(String filename){
		try{
			img = ImageIO.read(new File(filename));
		} catch(Exception e){}
	}

	public void loadAllImages(){
		for(int i = 0; i < 5; i++){
			if(this.lawnmowers[i]!=null) {
				this.lawnmowers[i].loadImage("lawnmoer.png");
				if(!this.lawnmowers[i].isAlive()) this.lawnmowers[i].start();
			}
		}

		for(Iterator<Zombie> iterator = this.zombies.iterator(); iterator.hasNext();){
			Zombie z = iterator.next();
			z.loadImage("tempzombie.png");
			z.start();
		}

			for(Iterator<Sun> iterator = this.suns.iterator(); iterator.hasNext();){
				Sun s = iterator.next();
				s.loadImage("sun.png");
				s.start();
			}

			for(Iterator<Pea> iterator = this.peas.iterator(); iterator.hasNext();){
				Pea p = iterator.next();
				p.loadImage("pea.png");
				p.start();
			}

			grid.loadAll();
			this.loadSpawners();
	}

	private void makeLawnmowers(){
		for(int i=0; i<5;i++){
			this.lawnmowers[i] = new Lawnmower(Lawnmower.LAWNMOWER_XPOS, Lawnmower.LAWNMOWER_YPOS+(Lawnmower.EXTRA_SET*i), "lawnmoer.png", i, this);
		}
	}

	private void renderLawnmowers(Graphics g){
		for(int i=0; i<5; i++){
			if(this.lawnmowers[i].atEnd()) this.lawnmowers[i].render(g);
		}
	}

	public Lawnmower[] getLawnmowers(){
		return this.lawnmowers;
	}

	public synchronized void removeZombie(Zombie z){
		z.kill();
		this.zombies.remove(z);
	}



	public void render(Graphics g){
		g.drawImage(this.img,0,0,null);
		grid.render(g);
		this.renderLawnmowers(g);
		this.renderZombies(g);
		this.renderSuns(g);
		this.renderPeas(g);
	}

	private synchronized void renderZombies(Graphics g){
		for(Iterator<Zombie> iterator = this.zombies.iterator(); iterator.hasNext();){
			Zombie z = iterator.next();
			z.render(g);
		}
	}

	private void renderSuns(Graphics g){
		for(Iterator<Sun> iterator = this.suns.iterator(); iterator.hasNext();){
			Sun s = iterator.next();
			s.render(g);
		}
	}

	private void renderPeas(Graphics g){
		for(Iterator<Pea> iterator = this.peas.iterator(); iterator.hasNext();){
			Pea p = iterator.next();
			p.render(g);
		}
	}


	public void collectSun(Point p, SunPanel sp){
		for(int i =0; i < this.suns.size(); i++){
			if(this.suns.get(i).getRect().contains(p)){
				this.sunCount+=25;
				this.suns.remove(i);
				sp.changeSunCount(this.sunCount);
			}
		}

	}

	public int getSun(){
		return this.sunCount;
	}

	private void decSun(int value){
		this.sunCount -= value;
	}

	public void validToPlant(Plant plant, Seed seed){
		this.canPlant = true;
		this.whatPlant = plant;
		this.seed = seed;
	}

	public void undoValidToPlant(){
		this.canPlant = false;
		this.whatPlant = null;
		this.seed = null;
	}

	public Plant hasPlant(){
		return this.whatPlant;
	}

	public Plant[][] getPlants(){
		return this.grid.getPlants();
	}

	public void removePea(Pea pea){
		pea.loadImage(null);
		this.peas.remove(pea);
	}

	public void removePlant(int i, int j){
		this.grid.removePlant(i,j);
	}

	public CopyOnWriteArrayList<Zombie> getZombies(){
		return this.zombies;
	}

	public void placePlant(Point p, SunPanel sp, Lawn lawn){
		for (int i = 0; i < Grid.ROWS; ++i) {
            for (int j = 0; j < Grid.COLS; ++j) {
                Rectangle rect = grid.getRect(i, j);
                if (rect.contains(p)) {
                    if (this.grid.emptyTile(i,j)) {
						this.grid.setPlant(this.whatPlant,i,j, lawn);
						this.seed.respawn();
						this.decSun(this.whatPlant.getCost());
						this.undoValidToPlant();
						sp.changeSunCount(this.sunCount);
                    }
                }
            }
		}
	}

	public Grid getGrid(){
		return this.grid;
	}

	public void decZombieHealth(Zombie z){
		z.decHealth(z);
	}

	public synchronized static void setOver(){
		Lawn.gameOver = true;
	}

	public static boolean getState(){
		return Lawn.gameOver;
	}

}
