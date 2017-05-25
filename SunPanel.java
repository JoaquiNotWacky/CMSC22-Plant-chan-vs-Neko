import javax.swing.*;
import java.awt.*;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Toolkit;
import javax.imageio.ImageIO;
import java.io.File;
import java.awt.image.BufferedImage;

public class SunPanel extends JPanel{
    private Lawn lawn;
    BufferedImage img;
    private JLabel sunCount;
    private Font font;

    public SunPanel(Lawn lawn){
        this.sunCount = new JLabel(Integer.toString(lawn.getSun()));
        this.lawn = lawn;
        this.font = new Font("Arial", Font.BOLD, 20);
        this.loadImage("suncntr.png");
        this.setOpaque(false);
        this.setPreferredSize(new Dimension(img.getWidth(),img.getHeight()));
        this.setLayout(new BorderLayout());
        this.sunCount.setFont(this.font);
        this.sunCount.setForeground(Color.WHITE);
        this.sunCount.setMaximumSize(new Dimension(20,20));
        this.sunCount.setHorizontalAlignment(SwingConstants.CENTER);
        this.sunCount.setVerticalAlignment(SwingConstants.CENTER);
        this.add(this.sunCount, BorderLayout.CENTER);
    }

    private void loadImage(String filename){
        try{
            img = ImageIO.read(new File(filename));
        } catch(Exception e){}
    }

    public void changeSunCount(int x){
        this.sunCount.setText(Integer.toString(x));
    }

    @Override
    public void paintComponent(Graphics g){
        super.paintComponent(g);
        Graphics2D g2d = (Graphics2D) g;

        g2d.drawImage(this.img,0,0,null);
        Toolkit.getDefaultToolkit().sync();
    }
}
