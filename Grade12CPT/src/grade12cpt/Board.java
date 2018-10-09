
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

    private final double aspectRatio = 1.77;
    private final int B_HEIGHT = 720;
    private final int B_WIDTH = (int)(720 * aspectRatio);
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

        drawStar(g);
    }

    // TODO update/remove drawStar
    private void drawStar(Graphics g) {
        //         Image, Coordinates, Pane
        //g.drawImage(star , x, y, this);
        Toolkit.getDefaultToolkit().sync();
    }
    
    private void drawBackground(Graphics g) {
        
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