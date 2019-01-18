
package grade12cpt;

import static grade12cpt.Board.B_HEIGHT;
import static grade12cpt.Board.B_WIDTH;

public class Projectile extends Sprite{

    public double angle;
    public double totalSpeed = 5;
    public double Xspeed;
    public double Yspeed;
    public int loop = 0;
    public int damage;
    
    // Loads a ranged weapon image as it’s appearance, and sets the XY origin from the player’s. 
    // calculats angles and the rate of change in each axis when moving from mouse.
    
    public Projectile(RangedWeapon weapon) {
        loadImage(weapon.getSheet());
        
        x = B_WIDTH / 2;
        y = B_HEIGHT / 2;
        Xorigin = x - Map.getMapXoffset();
        Yorigin = y - Map.getMapYoffset();
        mapX = B_WIDTH / 2;
        mapY = B_HEIGHT / 2;
        angle = 1.5 * Math.PI - Math.atan2(UserInput.mouse_Y - ((B_HEIGHT ) / 2), UserInput.mouse_X - ((B_WIDTH ) / 2));
        Xspeed = Math.sin(angle) * totalSpeed;
        Yspeed = Math.cos(angle) * totalSpeed;
    
    }



}
