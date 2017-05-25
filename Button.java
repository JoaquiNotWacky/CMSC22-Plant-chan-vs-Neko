import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.awt.Image;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Toolkit;
import javax.imageio.ImageIO;
import java.io.File;
import java.awt.image.BufferedImage;

public class Button extends JButton{
    BufferedImage img;
	
	public Button(String filename){
		this.loadImage(filename);
		this.setOpaque(false);
        this.setPreferredSize(new Dimension(img.getWidth(),img.getHeight()));
		this.setBorder(BorderFactory.createEmptyBorder());
		this.setContentAreaFilled(false);
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
	
	public void actionPerformed(ActionEvent e){
            System.out.println("Pressed ");
			
        }
}