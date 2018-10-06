/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package charactermovement;

import java.awt.Image;
import javax.swing.ImageIcon;


public class Player{
    
    public Image appearance;
    public UserInput ui;
    public static int x;
    public static int y;
    
    public Player() {
        init();
    }
    
    public void init() {
        ImageIcon ii = new ImageIcon("src/images/playersprite.jpg");
        appearance = ii.getImage();
        x = 0;
        y = 0;
        ui = new UserInput();
    }
    
    public static int getX() {
        return x;
    }
    
    public static int getY() {
        return y;
    }
    
    public void updatePlayer() {
        if(this.ui.LEFT) x++;
        if(this.ui.RIGHT) x--;
        if(this.ui.UP) y--;
        if(this.ui.DOWN) y++;
    }


}
