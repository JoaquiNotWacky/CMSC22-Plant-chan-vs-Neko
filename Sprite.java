import java.util.*;
import javax.swing.*;
import java.awt.*;
import java.util.*;
import javax.swing.*;
import javax.imageio.*;
import java.io.*;
import java.awt.image.BufferedImage;


public class Sprite implements Serializable{
	public static final int STEPS = 7;
	protected int xPos, yPos, height, width;
	protected transient BufferedImage img;
	protected Rectangle rect;
	protected String filename;

	public Sprite(int xPos, int yPos, String filename){
		this.filename = filename;
		this.xPos = xPos;
		this.yPos = yPos;
		this.loadImage(filename);
		this.width = img.getWidth();
		this.height = img.getHeight();
	}

	public void loadImage(String filename){
		try{
			img = ImageIO.read(new File(filename));
			this.filename = filename;
		} catch(Exception e){}
	}

	public void render(Graphics g){
		g.drawImage(this.img,this.xPos,this.yPos,null);
	}

	public Rectangle getRect(){
		 return new Rectangle(xPos, yPos, width,height);
	}
	public void decX(int distance){
		this.xPos -= distance;
	}
	public void incX(int distance){
		this.xPos += distance;
	}

	public void incY(int distance){
		this.yPos += distance;
	}
	public int getX(){
		return this.xPos;
	}
	public int getY(){
		return this.yPos;
	}

	public void setX(int x){
		this.xPos = x;
	}

	public void setY(int y){
		this.yPos = y;
	}
	public Image getImg(){
		return this.img;
	}
	public int getWdth(){
		return this.width;
	}
	public int getHght(){
		return this.height;
	}


	/*@Override
	public void paintComponent(Graphics g){
		super.paintComponent(g);
		g.drawImage(this.img,this.xPos,this.yPos,null);
	}*/
}
