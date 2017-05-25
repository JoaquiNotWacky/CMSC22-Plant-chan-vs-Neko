import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.awt.Image;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Toolkit;
import javax.imageio.ImageIO;
import java.io.File;

public class SeedPanel extends JPanel{
    private Seed sunflower;
    private Seed peashooter;
    private Seed walnut;
    private Seed jalapeno;
    private Seed potatoMine;

    public SeedPanel(Lawn lawn){
        super();
		this.setOpaque(false);
        this.sunflower = new Seed(lawn, 5, "sonia_seed.png","sonia_seed_dark.png", "sunflower");
        this.peashooter = new Seed(lawn, 8, "pia_seed.png","pia_seed_dark.png", "peashooter");
        this.walnut = new Seed(lawn, 25, "nat_seed.png","dark_nat_seed.png", "walnut");
        this.jalapeno = new Seed(lawn, 10, "julie_seed.png","dark_julie_seed.png", "jalapeno");
        this.potatoMine = new Seed(lawn, 10, "mina_seed.png","dark_mina_seed.png", "potatoMine");

        this.add(this.sunflower);
        this.add(this.peashooter);
        this.add(this.walnut);
        this.add(this.jalapeno);
        this.add(this.potatoMine);
    }


    public void respawn(){
        this.sunflower.respawn();
        this.peashooter.respawn();
        this.walnut.respawn();
        this.jalapeno.respawn();
        this.potatoMine.respawn();

    }
}
