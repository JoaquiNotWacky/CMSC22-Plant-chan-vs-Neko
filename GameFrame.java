import javax.swing.JFrame;
import java.awt.Dimension;
import java.awt.Container;
import java.awt.BorderLayout;

public class GameFrame extends JFrame{
	public static final int DIMENSION_X = 1020;
	public static final int DIMENSION_Y = 720;
	MainPanel main;

	public GameFrame (String title){
		super(title);
		this.setPreferredSize(new Dimension(DIMENSION_X,DIMENSION_Y));
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setResizable(false);


		Container container = this.getContentPane();
		this.main = new MainPanel();
		container.add(main);

		this.pack();
        this.setVisible(true);
	}

}
