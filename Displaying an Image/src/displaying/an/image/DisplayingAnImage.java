
package displaying.an.image;

/**
 *
 * @author Alexander
 */

import java.awt.Container;
import java.awt.EventQueue;
import javax.swing.GroupLayout;
import javax.swing.ImageIcon;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JLabel;

public class DisplayingAnImage extends JFrame{

    public DisplayingAnImage() {
        initUI();
    }
    
    private void initUI(){
        JLabel image = new JLabel(loadImage());
        createLayout(image);
        
    }
    
    private ImageIcon loadImage() {
        return new ImageIcon("src/Images/tiny.png");
    } 
    
    private void createLayout( JComponent... arg) {
        Container pane = getContentPane();
        GroupLayout gl = new GroupLayout(pane);
        pane.setLayout(gl);
        
        gl.setAutoCreateContainerGaps(true);
        
        gl.setHorizontalGroup(gl.createSequentialGroup()
            .addComponent(arg[0]));
        
        gl.setVerticalGroup(gl.createSequentialGroup()
            .addComponent(arg[0]));
        
        pack();
        
    }
    
    public static void main(String[] args) {

        EventQueue.invokeLater(() -> {
            DisplayingAnImage ex = new DisplayingAnImage();
            ex.setVisible(true);
        } );
            
    }
    
    

}
