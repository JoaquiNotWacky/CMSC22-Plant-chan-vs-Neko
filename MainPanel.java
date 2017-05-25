import java.awt.*;
import java.awt.event.*;
import java.awt.Image;
import java.awt.Graphics;
import javax.swing.*;
import java.awt.Graphics2D;
import java.awt.Toolkit;

public class MainPanel extends JPanel{
	private MenuPanel menu;
	GamePanel game;
	GamePanel loadGame;
	private CreditPanel credits;
    private Image img;
	/************************************************************
	Constructor
	*************************************************************/
    public MainPanel(){
        this.setLayout(new CardLayout());
        this.menu = new MenuPanel(this);
		this.game = new GamePanel(true);
		this.loadGame = new GamePanel(false);
		this.credits = new CreditPanel(this);
		this.add(menu, "m");
		this.add(game, "g");
		this.add(loadGame, "l");
		this.add(credits, "c");


	}
}
