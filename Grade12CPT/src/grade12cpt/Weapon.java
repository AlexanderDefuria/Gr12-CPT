
package grade12cpt;

/**
 *
 * @author Alexander
 */

public class Weapon {

    public String spriteSheet;
    public int damage = 0;

    // Evectively useless aside from combing two methods shown below, can be removed if necessary
    
    // Get sheet adress in project file system
    public String getSheet() {
        return spriteSheet;
    }
    
    // Get the damage this weapon does
    public int getDamage() {
        return damage;
    }

}
