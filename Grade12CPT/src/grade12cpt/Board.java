
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

    public static int B_HEIGHT, B_WIDTH; 
    private final int DELAY = 40;
    public static int offset = 0;
    public static int overlayWidth, overlayHeight;

    public static Thread animator;
    public static Map map;
    private UserInput input;
    public static List<Map> maps = new ArrayList<>();
    public static Player player;
    public static int loopIteration;
    
    public static Enemy tom = new Enemy("Tom");
    
    public Board(int width, int height) {
        B_WIDTH = width;
        B_HEIGHT = height;
        overlayWidth = B_WIDTH / 2;
        overlayHeight = B_HEIGHT / 2;
        initBoard();
    }


    private void initBoard() {

        
        
        // TODO Fix bug where player keeps scrolling after window is deslected
        setFocusable(true);
        setBackground(Color.BLACK);
        setPreferredSize(new Dimension(B_WIDTH, B_HEIGHT));
        setDoubleBuffered(true);
        
        map = new Map();
        input = new UserInput();
        player = new Player(input, "Bill");
        
        addKeyListener(new TAdapter(input));
        addMouseListener(new MAdapter(input));
        
        
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


        player.updatePlayer(map);
        EnemyManager.updateEnemies();
        ProjectileManager.updateProjectiles();
        
        g.drawImage(map.display(g, player), 0, 0, this);
        g.drawImage(player.appearance,B_WIDTH/2,B_HEIGHT/2,this);
        
        g.setColor(Color.blue);
        //g.drawRect(640 + 32, 360 + 32, -50,-50);
        
        
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





}

