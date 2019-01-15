
package grade12cpt;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

/**
 *
 * @author defur
 */

public class TAdapter extends KeyAdapter {
    
        public static UserInput input;
    
        public TAdapter(UserInput ui) {
            this.input = ui;
        }
        
        @Override
        public void keyReleased(KeyEvent e) {
            input.keyReleased(e);
        }

        @Override
        public void keyPressed(KeyEvent e) {
            input.keyPressed(e);
            
        }
}


