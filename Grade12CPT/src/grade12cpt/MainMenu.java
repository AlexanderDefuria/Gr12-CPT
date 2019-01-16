
package grade12cpt;

import static grade12cpt.Board.animator;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;
import javax.swing.JButton;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

public class MainMenu extends JPanel implements Runnable{

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
        addKeyListener(new TAdapter(input));
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
        
        startBut = new JButton();
        
        add(startBut);
        
        
        
        
        
    }
    
    @Override
    public void addNotify() {
        super.addNotify();

        animator = new Thread(this);
        animator.start();
    }
    
    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        
        g.drawImage(menuImage,0,0,null );
        
        
        repaint();
    }
    
    public void cycle() {
        
    }
    
    
    @Override
    public void run() {
        long beforeTime, timeDiff, sleep;

        beforeTime = System.currentTimeMillis();

        while (true) {

            repaint();
            cycle();

            timeDiff = System.currentTimeMillis() - beforeTime;
            sleep = DELAY - timeDiff;
            
            // TODO the main loop counter is not updating smoothly when refrenced from other classes, find root of error
            //      if (loopIteration == 101) loopIteration = 0;
            //      else loopIteration++;
            

            if (sleep < 0) {
                sleep = 2;
            }

            try {
                Thread.sleep(sleep);
            } catch (InterruptedException e) {
                
                String msg = String.format("Thread interrupted: %s", e.getMessage());
                
                JOptionPane.showMessageDialog(this, msg, "Error", 
                    JOptionPane.ERROR_MESSAGE);
            }

            beforeTime = System.currentTimeMillis();
        }    }



    

}
