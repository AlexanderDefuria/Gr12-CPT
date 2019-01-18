
package grade12cpt;



import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;



public class UserInput {
    
    public static boolean LEFT;
    public static boolean RIGHT;
    public static boolean UP;
    public static boolean DOWN;
    public static boolean SPACE;
    public static boolean W_KEY;
    public static boolean A_KEY;
    public static boolean S_KEY;
    public static boolean D_KEY;
    public static boolean E_KEY;
    public static boolean Q_KEY;
    public static int mouse_X;
    public static int mouse_Y;
    public static boolean CLICKED;
    
    // Takes actions from TAdapter and MAdapter Classes and packages them nicely
    // with distinct variables for each relevant action or piece of data.
    
    void keyPressed(KeyEvent e) {
        int key = e.getKeyCode();
        

        if (key == KeyEvent.VK_LEFT) LEFT = true;
        if (key == KeyEvent.VK_RIGHT) RIGHT = true;
        if (key == KeyEvent.VK_UP) UP = true;
        if (key == KeyEvent.VK_DOWN) DOWN = true;
        if (key == KeyEvent.VK_SPACE) SPACE = true;
        if (key == KeyEvent.VK_W) W_KEY = true;
        if (key == KeyEvent.VK_A) A_KEY = true;
        if (key == KeyEvent.VK_S) S_KEY = true;
        if (key == KeyEvent.VK_D) D_KEY = true;
        if (key == KeyEvent.VK_E) E_KEY = true;
        if (key == KeyEvent.VK_Q) Q_KEY = true;
    }

    void keyReleased(KeyEvent e) {
        int key = e.getKeyCode();
        

        if (key == KeyEvent.VK_LEFT) LEFT = false;
        if (key == KeyEvent.VK_RIGHT) RIGHT = false;
        if (key == KeyEvent.VK_UP) UP = false;
        if (key == KeyEvent.VK_DOWN) DOWN = false;
        if (key == KeyEvent.VK_SPACE) SPACE = false;
        if (key == KeyEvent.VK_W) W_KEY = false;
        if (key == KeyEvent.VK_A) A_KEY = false;
        if (key == KeyEvent.VK_S) S_KEY = false;
        if (key == KeyEvent.VK_D) D_KEY = false;
        if (key == KeyEvent.VK_E) E_KEY = false;
        if (key == KeyEvent.VK_Q) Q_KEY = false;
    }


    void mousePressed(MouseEvent e) {
        CLICKED = true;
        mouse_X = e.getX();
        mouse_Y = e.getY();
        
    }
    
    void mouseReleased(MouseEvent e) {
        CLICKED = false;
        mouse_X = e.getX();
        mouse_Y = e.getY();
    }
    
}
