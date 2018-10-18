
package grade12cpt;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Toolkit;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;
import javax.swing.JPanel;



public class Board extends JPanel implements Runnable {

    private static final double aspectRatio = 1.77;
    public static final int B_HEIGHT = 720;
    public static final int B_WIDTH = (int)(720 * aspectRatio);
    private final int DELAY = 50;
    private static int X_OFF = 0;

    public static Thread animator;
    public static Map map;
    public static UserInput input;
    public static List<Map> maps = new ArrayList<>();
    public static Player player;
    
    
    public Board() {
        initBoard();
    }


    private void initBoard() {

        setBackground(Color.BLACK);
        setPreferredSize(new Dimension(B_WIDTH, B_HEIGHT));
        setDoubleBuffered(true);
        
        map = new Map("src/maps/largemap.csv");
        input = new UserInput();
        map.publish();
        
        
        player = new Player(input);
        


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
        player.updatePlayer();
        g.drawImage(map.display(g, player), 0, 0, this);
        g.drawImage(player.appearance,500,500,this);
        Toolkit.getDefaultToolkit().sync();
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