
package grade12cpt;

/**
 *
 * @author Alexander
 */

// TODO change to abstract class

public class Enemy extends Sprite{
    
    public Enemy() {
        init();
    }
    
    private void init() {
        loadImage("src/images/enemy.png");
        this.setSize(getDimensions());
        this.setLocation(200, 200);
        x = 200;
        y = 200;
        this.updateHealth(10, true);
        
    }
    
    
    



}
