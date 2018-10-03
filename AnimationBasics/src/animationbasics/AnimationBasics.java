
package animationbasics;

import java.awt.Dimension;
import java.awt.EventQueue;
import javax.swing.JFrame;

public class AnimationBasics extends JFrame{

    public int width = 350;
    public int height = 250;
    
    public AnimationBasics() {
        init();
    
    }
    
    private void init() {
        add(new Board());

        setResizable(false);
        pack();
        
        setTitle("Star");    
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); 
    }

    public static void main(String[] args) {
        EventQueue.invokeLater(() -> {
            AnimationBasics ex = new AnimationBasics();
            ex.setVisible(true);
        });

    }

}
