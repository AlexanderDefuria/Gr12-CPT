
package animationbasics;

import java.awt.EventQueue;
import javax.swing.JFrame;


public class BasicShape extends JFrame{

    
    public BasicShape() {
        init();
    }
    
    private void init() {
        
        add(new Board());

        setSize(250, 250);

        setTitle("Application");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
    }

    public static void main(String[] args) {

        EventQueue.invokeLater(() -> {
            BasicShape ex = new BasicShape();
            ex.setVisible(true);
        });        

    }

}
