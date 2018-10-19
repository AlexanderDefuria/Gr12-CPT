
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
        
        
        if(UserInput.LEFT) x = 2;
        else if(UserInput.RIGHT) x = -2; 
        else x = 0;
        
        if(UserInput.UP) y = 2;
        else if(UserInput.DOWN) y = -2;
        else y = 0;
        
    }

}
