
package mapinterpretation;

import java.awt.EventQueue;

/**
 *
 * @author Alexander
 */

public class MapInterpretation {

    public MapInterpretation() {        
        Map map = new Map("src/Map/map.csv");
        map.publish();
    }

    public static void main(String[] args) {
        // TODO code application logic here
        EventQueue.invokeLater(() -> {
            MapInterpretation mi = new MapInterpretation();
            
        });
    }

}
