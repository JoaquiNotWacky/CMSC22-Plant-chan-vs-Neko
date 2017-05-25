import java.util.*;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import javax.imageio.ImageIO;
import java.io.File;
import java.awt.image.BufferedImage;

public class MenuPanel extends JPanel{
	private MainPanel mainPanel;
	private GameFrame gameFrame;
	//----------------
	private MyPanel middlePanel;
	private MyPanel middleDOWN;
	//----------------
	private Button start;
	private Button load;
	private Button credits;
	private BufferedImage img;
	private GamePanel game;

	public MenuPanel(MainPanel mainPanel){
		this.mainPanel = mainPanel;
		// this.gameFrame = gameFrame;


		this.start= new Button("start.png");
		this.load= new Button("load.png");
		this.credits= new Button("credit.png");
		this.loadImage("openscreen.png");

		this.setLayout(new GridLayout(0,3));

		this.add(new MyPanel());
		this.middlePanel = new MyPanel();
		this.add(middlePanel);
		this.add(new MyPanel());

		this.middlePanel.setLayout(new GridLayout(2,0));
		this.middlePanel.add(new MyPanel());
		this.middleDOWN = new MyPanel();
		this.middlePanel.add(middleDOWN);

		this.middleDOWN.add(start);
		this.middleDOWN.add(load);
		this.middleDOWN.add(credits);

		addListeners();
	}

	private void loadImage(String filename){
		try{
			img = ImageIO.read(new File(filename));
		} catch(Exception e){}
	}

	public void addListeners(){
		this.start.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
				System.out.println("START CLICKED");
				((CardLayout)mainPanel.getLayout()).show(mainPanel, "g");
				mainPanel.game.start();
				mainPanel.game.getSP().respawn();
				mainPanel.game.getLawn().startSpawners();
			}
		});

		this.load.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
				System.out.println("LOAD CLICKED");
				((CardLayout)mainPanel.getLayout()).show(mainPanel, "l");
				mainPanel.loadGame.start();
				mainPanel.loadGame.getSP().respawn();
			}
		});

		this.credits.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
				System.out.println("CREDITS CLICKED");
				((CardLayout)mainPanel.getLayout()).show(mainPanel, "c");
				//
			}
		});
	}


	@Override
    public void paintComponent(Graphics g){
        super.paintComponent(g);
        Graphics2D g2d = (Graphics2D) g;

        g2d.drawImage(this.img,0,0,null);
        Toolkit.getDefaultToolkit().sync();
    }



}
