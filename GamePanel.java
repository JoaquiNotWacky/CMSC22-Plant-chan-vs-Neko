import java.util.*;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import javax.imageio.ImageIO;
import java.io.*;

public class GamePanel extends JPanel implements Runnable{
	//----
	private Image img;
	private JPanel topPanel;
	private JPanel sunAndSeedPanel;
	private JButton save;
	private SeedPanel sp;
	private SunPanel sunP;
	private boolean newGame;
	Lawn lawn;
	private End end;

	public GamePanel(boolean newGame){
		this.newGame = newGame;
		this.setLayout(new BorderLayout());
		//------
		this.topPanel = new JPanel();
		this.sunAndSeedPanel = new JPanel();
		this.sunAndSeedPanel.setLayout(new BorderLayout());
		this.save = new JButton("SAVE");
		this.sunAndSeedPanel.setPreferredSize(new Dimension(600,100));
		this.sunAndSeedPanel.setOpaque(false);

		this.topPanel.setOpaque(false);
		this.topPanel.setLayout(new BorderLayout());
		//-------
		if(this.newGame) this.lawn = new Lawn(); //newgame
		else{ //loadgame
			try{
				ObjectInputStream in = new ObjectInputStream(new FileInputStream("Lawn.txt"));
				this.lawn = (Lawn)in.readObject();
				in.close();
			}catch(Exception e){
				e.printStackTrace();
				System.out.println("unsuccessful");
			}
		}

		this.sp = new SeedPanel(this.lawn);
		this.sunP = new SunPanel(this.lawn);

		//-------
		this.sunAndSeedPanel.add(sunP, BorderLayout.WEST);
		this.sunAndSeedPanel.add(sp, BorderLayout.CENTER);
		this.topPanel.add(save, BorderLayout.EAST);
		this.topPanel.add(sunAndSeedPanel, BorderLayout.WEST);
		this.add(topPanel, BorderLayout.NORTH);
		//-------
		this.end = new End(this.lawn);

		save.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
				lawn.save();
			}
		});

		this.addMouseListener(new MouseListener() {
			public void mouseEntered(MouseEvent me){}
			public void mouseExited(MouseEvent me){}
			public void mouseReleased(MouseEvent me){}
			public void mousePressed(MouseEvent me){}
			public void mouseClicked(MouseEvent me){
				Point p = me.getPoint();
				if (SwingUtilities.isLeftMouseButton(me)) {
					if (lawn.hasPlant() != null) lawn.placePlant(p, sunP, lawn);
					else lawn.collectSun(p, sunP);
				} else if (SwingUtilities.isRightMouseButton(me)) {
					if (lawn.hasPlant() != null) lawn.undoValidToPlant();
				}
			}
		});
		// repaint();
}

	private void loadImage(String filename){
		try{
			img = ImageIO.read(new File(filename));
		} catch(Exception e){}
	}

	public SeedPanel getSP(){
		return this.sp;
	}

	public void run(){
		while(!Lawn.gameOver){
			this.repaint();
		}
	}

	public Lawn getLawn(){
		return this.lawn;
	}

	public void start(){
		Thread thread = new Thread(this);
		this.lawn.loadImage("lawn.png");
		if(!this.newGame)this.lawn.loadAllImages();
		thread.start();
	}

	@Override
	public void paintComponent(Graphics g){
		Graphics2D g2d = (Graphics2D) g;
		super.paintComponent(g);
		lawn.render(g);
		if(lawn.getState()){
			end.show();
			end.render(g);
		}
		Point p = this.getMousePosition();
		if (p != null && lawn.hasPlant() != null) {
			highlightTile(p, g2d);
		}
	}

	private void highlightTile(Point p, Graphics2D g2d){
		Color highlight = new Color(255, 255, 102,70);
		for (int i = 0; i < Grid.ROWS; ++i) {
            for (int j = 0; j < Grid.COLS; ++j) {
                Rectangle rect = lawn.getGrid().getRect(i, j);
                if (rect.contains(p)) {
                    if (lawn.getGrid().emptyTile(i,j)) {
						g2d.setColor(highlight);
						g2d.fill(rect);
                    }
                }
            }
		}
	}
}
