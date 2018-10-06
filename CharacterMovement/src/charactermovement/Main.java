
package charactermovement;

import java.awt.EventQueue;
import javax.swing.JFrame;

/**
 *
 * @author Alexander
 */

public class Main extends JFrame{

    public static final int WIDTH = 500;
    public static final int HEIGHT = 500;
    
    public Main() {
        init();
    }
    
    private void init(){
        add(new Board());

        setResizable(false);
        pack();
        
        setTitle("Main");    
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); 
    }

    public static void main(String[] args) {
        // TODO code application logic here
        EventQueue.invokeLater(() -> {
            Main main = new Main();
            main.setVisible(true);
        });
    }

}
