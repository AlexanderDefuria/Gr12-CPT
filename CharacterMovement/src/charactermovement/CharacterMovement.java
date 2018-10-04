
package charactermovement;

import java.awt.EventQueue;
import javax.swing.JFrame;

/**
 *
 * @author Alexander
 */

public class CharacterMovement extends JFrame{

    int WIDTH = 500;
    int HEIGHT = 500;
    
    private Player;
    
    public CharacterMovement() {
        init();
    }
    
    private void init(){
        add(new Board());

        setResizable(false);
        pack();
        
        setTitle("Star");    
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); 
    }

    public static void main(String[] args) {
        // TODO code application logic here
        EventQueue.invokeLater(() -> {
            CharacterMovement main = new CharacterMovement();
            main.setVisible(true);
        });
    }

}
