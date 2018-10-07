/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package charactermovement;

import javax.swing.ImageIcon;



/**
 *
 * @author Alexander
 */
public class Missile extends Sprite{
    
    public Missile () {
        ImageIcon ii = new ImageIcon("src/images/missile.png");
        appearance = ii.getImage();
    }
    
    public void update() {
        move();
    }
    
    private void move() {
        x++;
    }
}
