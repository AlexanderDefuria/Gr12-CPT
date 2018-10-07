/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package charactermovement;

import java.awt.event.KeyEvent;

/**
 *
 * @author Alexander
 */
public class UserInput {
    
    public static boolean LEFT;
    public static boolean RIGHT;
    public static boolean UP;
    public static boolean DOWN;
    public static boolean SPACE;
    
    public UserInput() {
        
    }

    
    void keyPressed(KeyEvent e) {
        
        int key = e.getKeyCode();

        if (key == KeyEvent.VK_LEFT) LEFT = true;
        if (key == KeyEvent.VK_RIGHT) RIGHT = true;
        if (key == KeyEvent.VK_UP) UP = true;
        if (key == KeyEvent.VK_DOWN) DOWN = true;
        if (key == KeyEvent.VK_SPACE) SPACE = true;
    }

    void keyReleased(KeyEvent e) {
         int key = e.getKeyCode();

        if (key == KeyEvent.VK_LEFT) LEFT = false;
        if (key == KeyEvent.VK_RIGHT) RIGHT = false;
        if (key == KeyEvent.VK_UP) UP = false;
        if (key == KeyEvent.VK_DOWN) DOWN = false;
    }
    
}
    
    

