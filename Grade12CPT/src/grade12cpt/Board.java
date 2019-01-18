
package grade12cpt;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Toolkit;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import javax.imageio.ImageIO;
import javax.swing.JOptionPane;
import javax.swing.JPanel;



public class Board extends JPanel implements Runnable {

    // Panel Variables
    public static int B_HEIGHT, B_WIDTH; 
    private final int DELAY = 40;
    public static int offset = 0;
    public static int overlayWidth, overlayHeight;

    // Game and animation variables
    public static Thread animator;
    public static Map map;
    private UserInput input;
    public static List<Map> maps = new ArrayList<>();
    public static Player player;
    public static int loopIteration;
    
    // End game variables
    public static BufferedImage endScreen; 
    public static String gameOver = "src/images/Game Over Screen.png";
    public static String gameWinner = "src/images/Winner.png";
    public static boolean end = false;
    
    // Starts the intialization of the panel with the given size
    public Board(int width, int height) {
        B_WIDTH = width;
        B_HEIGHT = height;
        overlayWidth = B_WIDTH / 2;
        overlayHeight = B_HEIGHT / 2;
        initBoard();
    }

    
    // Adds map, player and action lsiteners , as well as sets the appropriate settings
    private void initBoard() {
        
        // TODO Fix bug where player keeps scrolling after window is deslected
        setFocusable(true);
        setBackground(Color.BLACK);
        setPreferredSize(new Dimension(B_WIDTH, B_HEIGHT));
        setDoubleBuffered(true);
        
        map = new Map();
        input = new UserInput();
        player = new Player(input, "Bill");
        
        addKeyListener(new KAdapter(input));
        addMouseListener(new MAdapter(input));
        
        end = false;
        
        EnemyManager.LoadEnemies();

    }

    // Required to implement runnable
    @Override
    public void addNotify() {
        super.addNotify();

        animator = new Thread(this);
        animator.start();
    }
    
    
    // Controls the painting of overlay and map and panel
    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        
        drawBackground(g);
        drawOverlay(g);
        
        repaint();
    }

    
    // Draws the map and player unless the player has reached the endzone offsets
    private void drawBackground(Graphics g) {
        if (map.getMapYoffset() >= 20){
            if (map.getMapXoffset() >= -496 && map.getMapXoffset() <= -480){
                end = true;
                try {
                    g.drawImage(ImageIO.read(new File(gameWinner)), 0,0,null);
                } catch (IOException ex) {}
            }
        } else if (player.getHealth() <=0 ) {
            end = true;
                try {
                    g.drawImage(ImageIO.read(new File(gameOver)), 0,0,null);
                } catch (IOException ex) {}
        } else if (!end) {
            player.updatePlayer(map);
            EnemyManager.updateEnemies();
            ProjectileManager.updateProjectiles();

            g.drawImage(map.display(g, player), 0, 0, this);
            g.drawImage(player.appearance,B_WIDTH/2,B_HEIGHT/2,this);
        }
        
        
        Toolkit.getDefaultToolkit().sync();
    }

    
    // Creates the player health overlay on the display by creating two rectangles of the appropriate size and colour
    private void drawOverlay(Graphics g) {
        
        if (!end){
            int percentHP = (int)(100 * player.getHealth() / player.getMaxHealth());

            g.setColor(Color.green);
            g.fillRect((int)B_WIDTH/4, (int)(B_HEIGHT * 0.9), (int)((B_WIDTH/2) * percentHP / 100), (int)(B_HEIGHT * 0.1));

            g.setColor(Color.red);
            g.fillRect(((int)B_WIDTH/4) + (int)((B_WIDTH/2) * percentHP / 100), (int)(B_HEIGHT * 0.9), 
                    (int)(B_WIDTH/2) - (int)((B_WIDTH/2) * percentHP / 100), (int)(B_HEIGHT * 0.1));
        }
        
        Toolkit.getDefaultToolkit().sync();
    }
    

    // Takes care of the updating of the runnable and thread, keeps timing in check, should not be changed, and ends the program appropriately
    @Override
    public void run() {

        long beforeTime, timeDiff, sleep;

        beforeTime = System.currentTimeMillis();

        while (player.getHealth() >= 0) {

            if (!end) {
                repaint();
                
            } else 
                try {
                    Thread.sleep(2000);
                    System.exit(0);
                } catch (InterruptedException ex) {}
            
            
            
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

