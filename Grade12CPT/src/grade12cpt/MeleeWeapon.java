
package grade12cpt;

import java.awt.Image;


public class MeleeWeapon extends Weapon{

    public Image appearance;
    
    static class Sword extends MeleeWeapon {

        public Sword() {
            spriteSheet = "src/images/sword1.png";
            damage = 10;
        }
        
    }
    
    static class Axe extends MeleeWeapon {
        
        public Axe() {
            
        }
    }

    
    
    

}





