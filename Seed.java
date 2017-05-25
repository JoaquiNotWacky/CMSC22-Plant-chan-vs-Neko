import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.awt.Image;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Toolkit;
import javax.imageio.ImageIO;
import java.io.*;
import java.awt.image.BufferedImage;

public class Seed extends JButton implements Runnable, Serializable{
    private boolean canClick;
    private int progress;
    private final static int SEC = 1000;
    private int cooldown;
    BufferedImage img;
    private Lawn lawn;
    private String plant;
    private String can;
    private String cannot;

    public Seed(Lawn lawn, int cooldown, String can, String cannot, String plant){
        this.lawn = lawn;
        this.plant = plant;
        this.canClick = false;
        this.progress = 0;
        this.cooldown = cooldown*SEC;
        this.can = can;
        this.cannot = cannot;
        this.loadImage(can);
        this.addActionListener(new Picker(this));

        this.setOpaque(false);
        this.setPreferredSize(new Dimension(img.getWidth(),img.getHeight()));
    }


    public void run(){
        this.loadImage(this.cannot);
        repaint();
        this.progress = 0;
        this.canClick = true;
        while(this.progress < this.cooldown){
            this.progress += 1000;
            try{
                Thread.sleep(1000);
            } catch(Exception e){}
        }
        this.loadImage(this.can);
        repaint();
        this.canClick = false;;
    }

    public void respawn(){
        Thread thread = new Thread(this);
        thread.start();
    }

    private void loadImage(String filename){
		try{
			img = ImageIO.read(new File(filename));
		} catch(Exception e){}
	}

    @Override
    public void paintComponent(Graphics g){
        super.paintComponent(g);
        Graphics2D g2d = (Graphics2D) g;

        g2d.drawImage(this.img,0,0,null);
        Toolkit.getDefaultToolkit().sync();
    }

    private class Picker implements ActionListener{
        private Seed seed;

        public Picker(Seed seed){
            this.seed = seed;
        }

        public void actionPerformed(ActionEvent e){
            PlantMaker make = new PlantMaker(seed.plant);
            Plant plant = make.makePlant();
            if(!seed.canClick && seed.lawn.getSun()>= plant.getCost()){
                plant.setLawn(seed.lawn);
                seed.lawn.validToPlant(plant,this.seed);
            }
        }
    }
}
