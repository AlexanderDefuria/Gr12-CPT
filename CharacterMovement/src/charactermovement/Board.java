/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package charactermovement;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import javax.swing.ImageIcon;
import javax.swing.JOptionPane;
import javax.swing.JPanel;


public class Board extends JPanel implements Runnable, ActionListener {

    private final int HEIGHT = Main.HEIGHT;
    private final int WIDTH = Main.WIDTH;
    private final int DELAY = 25;

    private Player player1;
    private UserInput keyboard;
    private Image background;
    private Thread animator;
    private int x, y = 0;

    public Board() {

        initBoard();
    }

    // Loads required images from images package
    private void loadImages() {


    }

    private void initBoard() {

        addKeyListener(new TAdapter());
        setFocusable(true);
        setBackground(Color.BLACK);
        setPreferredSize(new Dimension(WIDTH, HEIGHT));
        setDoubleBuffered(true);
        
        player1 = new Player();
        keyboard = new UserInput();

        loadImages();

    }

    @Override
    // Runs when added to a JFrame, implements a new tthread for the animator 
    public void addNotify() {
        // Since the method is overridden it must be called again to perform proper task.
        super.addNotify();

        animator = new Thread(this);
        animator.start();
    }

    @Override
    // Overides the paint component to update with custom graphics
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        
        drawBackground(g);
        drawPlayer(g);
        
    }

    // Draw the background of the game in the center
    private void drawBackground(Graphics g) {

        g.drawImage(background, 0, 0, this);
        Toolkit.getDefaultToolkit().sync();
    }
    
    private void drawPlayer(Graphics g) {
        player1.updatePlayer();
        g.drawImage(player1.appearance, player1.getX(), player1.getY(), this);
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

    @Override
    public void actionPerformed(ActionEvent e) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
    private class TAdapter extends KeyAdapter {

        @Override
        public void keyReleased(KeyEvent e) {
            keyboard.keyReleased(e);
        }

        @Override
        public void keyPressed(KeyEvent e) {
            keyboard.keyPressed(e);
        }
    }
}
