/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package charactermovement;

import javax.swing.ImageIcon;


public class Player extends Sprite{
   
    public UserInput ui;

    public Player() {
        init();
    }
    

    
    public void init() {
        ImageIcon ii = new ImageIcon("src/images/craft.png");
        appearance = ii.getImage();
        x = 0;
        y = 0;
        ui = new UserInput();
    }
    
    
    public void updatePlayer() {
        if(this.ui.LEFT) x--;
        if(this.ui.RIGHT) x++;
        if(this.ui.UP) y--;
        if(this.ui.DOWN) y++;
        if(this.ui.SPACE) {
            
        }
        

    }


}
