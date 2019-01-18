
package grade12cpt;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Toolkit;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;
import javax.swing.JButton;
import javax.swing.JPanel;

public class MainMenu extends JPanel {

    public static int DELAY = 0;
    public static int B_HEIGHT, B_WIDTH;
    public static BufferedImage menuImage;
    public static String file = "src/images/Title Screen CPT Game.png";
    public static JButton startBut;
    
    public MainMenu(int width, int height) {
        B_WIDTH = width;
        B_HEIGHT = height;
        init();
    }
    
    public void init() {
        UserInput input = new UserInput();
        addKeyListener(new KAdapter(input));
        addMouseListener(new MAdapter(input));
        
        setFocusable(true);
        setBackground(Color.white);
        setPreferredSize(new Dimension(B_WIDTH, B_HEIGHT));
        setDoubleBuffered(true);
        
        try {
            menuImage = ImageIO.read(new File(file));
        } catch (IOException ex) {
            System.out.println("Missing Menu Image...");
            System.exit(0);
        }

        
    }
    

    
    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        
        g.drawImage(menuImage,0,0,null );
        
        
        Toolkit.getDefaultToolkit().sync();
        repaint();
    }
    
    
   
    

}
