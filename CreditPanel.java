import java.util.*;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import javax.imageio.ImageIO;
import java.io.File;
import java.awt.image.BufferedImage;

public class CreditPanel extends MyPanel{
	Image img;
	private MainPanel mainPanel;
	private MyPanel topPanel;
	private JButton back;

	public CreditPanel(MainPanel mainPanel){
		this.mainPanel = mainPanel;
		this.loadImage("credit_bg.png");
		this.setLayout(new BorderLayout());
		this.topPanel = new MyPanel();
		this.topPanel.setLayout(new BorderLayout());
		this.add(topPanel, BorderLayout.NORTH);
		this.back = new JButton("Back to Menu");
		this.topPanel.add(back, BorderLayout.WEST);

		this.addListeners();
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

	public void addListeners(){
		this.back.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
				System.out.println("START CLICKED");
				((CardLayout)mainPanel.getLayout()).show(mainPanel, "m");
			}
		});

	}

}
