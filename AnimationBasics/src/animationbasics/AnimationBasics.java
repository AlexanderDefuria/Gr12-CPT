
package animationbasics;

import java.awt.EventQueue;
import javax.swing.JFrame;


public class AnimationBasics extends JFrame{

    
    public AnimationBasics() {
        init();
    }
    
    private void init() {
        add(new Board());

        setSize(250, 200);

        setTitle("Application");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
    }

    public static void main(String[] args) {

        EventQueue.invokeLater(() -> {
            AnimationBasics ex = new AnimationBasics();
            ex.setVisible(true);
        });        

    }

}
