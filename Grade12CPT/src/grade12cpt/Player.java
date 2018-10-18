
package grade12cpt;

import javax.swing.ImageIcon;



public class Player extends Sprite{

    public UserInput ui;
    
    public Player(UserInput x) {
        ui = x;
        init();
    }
    
        public void init() {
        ImageIcon ii = new ImageIcon("src/images/guard.png");
        appearance = ii.getImage();
        x = 0;
        y = 0;
        
    }
    
    
    public void updatePlayer() {
        
        
        if(UserInput.LEFT) x--;
        if(UserInput.RIGHT) x++;
        if(UserInput.UP) y--;
        if(UserInput.DOWN) y++;
        
        if (x != 0) System.out.println(x);
        if (y != 0) System.out.println(y);
    }
    
        
    public static int moveX() {
        int toPass = x;
        x = 0;
        return toPass; 
    }
        

}


