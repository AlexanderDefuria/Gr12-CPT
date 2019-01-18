
package grade12cpt;

import java.awt.Image;
import java.io.File;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;

/**
 *
 * @author Alexander
 */

public class RangedWeapon extends Weapon{
    
    public Image projectileImage;
    
    // Loads iamge for the Orb weapon, and sets the damage of the weapon
    
    static class Orb extends RangedWeapon{

        public Orb()  {
            try {
                spriteSheet = "src/images/blue_orb.png";
                projectileImage = ImageIO.read(new File(spriteSheet));
                damage = 10;
            } catch (IOException ex) {
                Logger.getLogger(RangedWeapon.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        
    }

    public Image getProjectileImage() {
        return projectileImage;
    }
    



}
