
package grade12cpt;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;
import javax.swing.JPanel;



public class Board extends JPanel implements Runnable, ActionListener {

    private static final double aspectRatio = 16.0/9.0;
    public static final int B_HEIGHT = 720;
    public static final int B_WIDTH = (int)(720 * aspectRatio);
    // TODO adjust delay for hardware capabilities 
    private final int DELAY = 20;
    public static int offset = 0;

    public static Thread animator;
    public static Map map;
    private UserInput input;
    public static List<Map> maps = new ArrayList<>();
    public static Player player;
    
    
    public Board() {
        System.out.println();
        initBoard();
    }


    private void initBoard() {

        addKeyListener(new TAdapter());
        
        // TODO Fix bug where player keeps scrolling after window is deslected
        setFocusable(true);
        setBackground(Color.BLACK);
        setPreferredSize(new Dimension(B_WIDTH, B_HEIGHT));
        setDoubleBuffered(true);
        
        map = new Map();
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

    private void drawBackground(Graphics g) {

        player.updatePlayer(map);
        
        g.drawImage(map.display(g, player), 0, 0, this);
        g.drawImage(player.appearance,B_WIDTH/2,B_HEIGHT/2,this);
        
        // TODO Remove hit boxes
        g.setColor(Color.red);
        g.drawRect((int)player.getHitbox().getX(), (int)player.getHitbox().getY(),  player.getHitbox().width  ,player.getHitbox().height);
        g.drawRect(map.getMapOutline().x,  map.getMapOutline().y,  map.getMapOutline().width,  map.getMapOutline().height);

        Toolkit.getDefaultToolkit().sync();
    }

    // TODO populate cycle() method 
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




    @Override
    public void actionPerformed(ActionEvent e) {
    }

    
    private class TAdapter extends KeyAdapter {

        @Override
        public void keyReleased(KeyEvent e) {
            input.keyReleased(e);
        }

        @Override
        public void keyPressed(KeyEvent e) {
            input.keyPressed(e);
        }
    }
}

