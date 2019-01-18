
package grade12cpt;

import java.awt.Rectangle;


public class SolidTerrain extends Rectangle{
    
    // Simple rectangle that is used by the map class and only serves 
    // to represent a blocked tile and then detect collisions with. 

    public SolidTerrain(int posX, int posY) {
        setSize(Map.tile_size ,Map.tile_size);
        setLocation(posX, posY);
    }
       
}
