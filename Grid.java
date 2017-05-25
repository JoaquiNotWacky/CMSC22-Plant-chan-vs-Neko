import java.util.*;
import javax.swing.*;
import java.awt.*;
import java.util.*;
import javax.swing.*;
import javax.imageio.*;
import java.io.*;
import java.awt.image.BufferedImage;

public class Grid  implements Serializable{
    private final static int START_X = 205;
    private final static int START_Y = 125;
    private final static int TILE_WIDTH = 80;
    private final static int TILE_HEIGHT = 95;
    public final static int ROWS = 5;
    public final static int COLS = 9;
    private Rectangle[][] tiles;
    private Plant[][] plants;

    public Grid(){
        this.tiles = new Rectangle[ROWS][COLS];
        this.plants = new Plant[ROWS][COLS];

        for(int i =0; i < ROWS; i++){
            for(int j = 0; j <COLS; j++){
                int x = START_X + (j*TILE_WIDTH);
                int y = START_Y + (i*TILE_HEIGHT);
                this.tiles[i][j] = new Rectangle(x,y,TILE_WIDTH, TILE_HEIGHT);
                this.plants[i][j] = null;
            }
        }
    }

    public void removePlant(int i, int j){
		this.plants[i][j] = null;
	}

    public Rectangle getRect(int i, int j){
        return this.tiles[i][j];
    }

    public boolean emptyTile(int i, int j){
        if(this.plants[i][j] == null) return true;
        else return false;
    }

    public Plant[][] getPlants(){
        return this.plants;
    }

    public synchronized void setPlant(Plant plant, int i, int j, Lawn lawn){
        this.plants[i][j] = plant;
        this.plants[i][j].setX(START_X+(j*TILE_WIDTH));
        this.plants[i][j].setY(START_Y+(i*TILE_HEIGHT)-(this.plants[i][j].img.getHeight()-TILE_HEIGHT));
        this.plants[i][j].setRow(i);
        this.plants[i][j].setCol(j);
        this.plants[i][j].setLawn(lawn);
        this.plants[i][j].shootingStart();
    }

    public void render(Graphics g){
        for(int i =0; i < ROWS; i++){
            for(int j = 0; j <COLS; j++){
                if(this.plants[i][j] == null) continue;
                else this.plants[i][j].render(g);
            }
        }
    }

    public void loadAll(){
        for(int i =0; i < ROWS; i++){
            for(int j = 0; j <COLS; j++){
                if(this.plants[i][j] == null) continue;
                else {
                    this.plants[i][j].loadImage(this.plants[i][j].filename);
                    this.plants[i][j].shootingStart();
                }
            }
        }
    }

}
