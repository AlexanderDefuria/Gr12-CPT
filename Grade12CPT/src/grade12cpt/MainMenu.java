
package grade12cpt;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

/**
 *
 * @author defur
 */

public class MainMenu extends JPanel implements Runnable, ActionListener {

    public static int DELAY = 0;
    public static int B_HEIGHT, B_WIDTH;
    
    public MainMenu(int width, int height) {
        B_HEIGHT = width;
        B_HEIGHT = height;
        init();
    }
    
    public void init() {
        UserInput input = new UserInput();
        addKeyListener(new TAdapter(input));
        addMouseListener(new MAdapter(input));
        
        setFocusable(true);
        setBackground(Color.BLACK);
        setPreferredSize(new Dimension(B_WIDTH, B_HEIGHT));
        setDoubleBuffered(true);
    }
    
    @Override
    public void run() {
        long beforeTime, timeDiff, sleep;

        beforeTime = System.currentTimeMillis();

        while (true) {

            repaint();

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

    @Override
    public void actionPerformed(ActionEvent e) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    

}
