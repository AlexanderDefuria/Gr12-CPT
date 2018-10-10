
package grade12cpt;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Toolkit;
import java.util.ArrayList;
import java.util.List;
import javax.swing.ImageIcon;
import javax.swing.JOptionPane;
import javax.swing.JPanel;


/**
 *
 * @author Alexander
 */
public class Board extends JPanel implements Runnable {

    private static final double aspect_ratio = 1.77;
    public static final int B_HEIGHT = 720;
    public static final int B_WIDTH = (int)(720 * aspect_ratio);
    private final int DELAY = 25;

    public static Map map;
    public static List<Map> maps = new ArrayList<>();
    
    private Thread animator;

    public Board() {

        initBoard();
    }


    private void initBoard() {

        setBackground(Color.BLACK);
        setPreferredSize(new Dimension(B_WIDTH, B_HEIGHT));
        setDoubleBuffered(true);
        
        map = new Map("src/maps/mainmap.cvs");


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

        drawBackground(g);
    }

    // TODO update/remove example
    private void drawExample(Graphics g) {
        // Called by paint component method above
        // param -- Image, Start Coordinates, Pane
        // g.drawImage(example , x, y, this);
        // Toolkit.getDefaultToolkit().sync();
    }
    
    private void drawBackground(Graphics g) {
        Image background = map.display(g);
        g.drawImage(background, 0, 0, this);
    }

    private void cycle() {

        
    }

    @Override
    public void run() {

        long beforeTime, timeDiff, sleep;

        beforeTime = System.currentTimeMillis();

        while (true) {

            cycle();
            repaint();

            timeDiff = System.currentTimeMillis() - beforeTime;
            sleep = DELAY - timeDiff;

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
        }
    }
}