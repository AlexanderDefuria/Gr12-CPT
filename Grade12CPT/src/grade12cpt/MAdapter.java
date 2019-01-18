
package grade12cpt;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

/**
 *
 * @author defur
 */

public class MAdapter extends MouseAdapter{

        public static UserInput input;
        
        // Passes all actions performed by the mouse to the UserInput Object Given
        
        public MAdapter(UserInput ui) {
            this.input = ui;
        }
    
        @Override
        public void mousePressed(MouseEvent e) {
            input.mousePressed(e);
        }
        
        @Override
        public void mouseReleased(MouseEvent e) {
            input.mouseReleased(e);
        }

}

