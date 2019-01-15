
package grade12cpt;

import java.awt.Rectangle;

/**
 *
 * @author Alexander
 */

public abstract class Terrain extends Rectangle{

    public Terrain(int posX, int posY) {
        //setSize(Map.tile_size ,Map.tile_size);
        init(posX, posY);
        
    }
    
    public void init(int posX, int posY) {
        setSize(Map.tile_size ,Map.tile_size);
        setLocation(posX, posY);
    }
    
    

}
