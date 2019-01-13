
package grade12cpt;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;
import javax.swing.JPanel;



public class Board extends JPanel implements Runnable, ActionListener {

    private static final double aspectRatio = 16.0/9.0;
    public static final int B_HEIGHT = 720;
    public static final int B_WIDTH = (int)(720 * aspectRatio);
    // TODO adjust delay for hardware capabilities 
    private final int DELAY = 40;
    public static int offset = 0;
    public static int overlayWidth = B_WIDTH / 2;
    public static int overlayHeight = B_HEIGHT / 2;

    public static Thread animator;
    public static Map map;
    private UserInput input;
    public static List<Map> maps = new ArrayList<>();
    public static Player player;
    public static int loopIteration;
    
    public static Enemy tom = new Enemy("Tom");
    
    public Board() {
        System.out.println();
        initBoard();
    }


    private void initBoard() {

        addKeyListener(new TAdapter());
        addMouseListener(new MAdapter());
        
        // TODO Fix bug where player keeps scrolling after window is deslected
        setFocusable(true);
        setBackground(Color.BLACK);
        setPreferredSize(new Dimension(B_WIDTH, B_HEIGHT));
        setDoubleBuffered(true);
        
        map = new Map();
        input = new UserInput();
        player = new Player(input, "Bill");
        
        
        
        EnemyManager.addEnemy(tom);
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
        drawOverlay(g);
        
        repaint();
    }

    private void drawBackground(Graphics g) {

        tom.walk(2,0);
        player.updatePlayer(map);
        EnemyManager.updateEnemies();
        ProjectileManager.updateProjectiles();
        
        g.drawImage(map.display(g, player), 0, 0, this);
        g.drawImage(player.appearance,B_WIDTH/2,B_HEIGHT/2,this);
        
        Toolkit.getDefaultToolkit().sync();
    }

    private void drawOverlay(Graphics g) {
        
        int percentHP = (int)(100 * player.getHealth() / player.getMaxHealth());
        
        g.setColor(Color.green);
        g.fillRect((int)B_WIDTH/4, (int)(B_HEIGHT * 0.9), (int)((B_WIDTH/2) * percentHP / 100), (int)(B_HEIGHT * 0.1));
        
        g.setColor(Color.red);
        g.fillRect(((int)B_WIDTH/4) + (int)((B_WIDTH/2) * percentHP / 100), (int)(B_HEIGHT * 0.9), 
                (int)(B_WIDTH/2) - (int)((B_WIDTH/2) * percentHP / 100), (int)(B_HEIGHT * 0.1));
        
        Toolkit.getDefaultToolkit().sync();
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
    
    private class MAdapter extends MouseAdapter {
        
        @Override
        public void mousePressed(MouseEvent e) {
            input.mousePressed(e);
        }
        
        @Override
        public void mouseReleased(MouseEvent e) {
            input.mouseReleased(e);
        }
    }
}

