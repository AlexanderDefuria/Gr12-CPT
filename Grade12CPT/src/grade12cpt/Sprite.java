
package grade12cpt;

import java.awt.Dimension;
import java.awt.Image;
import java.awt.Rectangle;
import javax.swing.ImageIcon;



public abstract class Sprite extends Rectangle{

    public static int x;
    public static int y;
    public Image appearance;
    public static Rectangle bounds;
    protected int width = 0, height = 0;
    private static boolean passable;

    protected void loadImage(String imageName) {
        ImageIcon ii = new ImageIcon(imageName);
        appearance = ii.getImage();
        getDimensions();
        this.setSize(width, height);
        this.setLocation(x, y);
        
    }
    
    protected void updateDimensions() {
        height = appearance.getHeight(null);
        width = appearance.getWidth(null);
    }
    
    protected Dimension getDimensions() {
        updateDimensions();
        return new Dimension(width, height);
    }
    
    public Image getImage() {
        return appearance;
    }    
    
    public static int getMapX() {
        return x;
    }
    
    public static int getMapY() {
        return y;
    }
    
    public boolean getPassable() {
        return passable;
    }

    


}
