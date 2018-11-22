
package grade12cpt;

import java.awt.Dimension;
import java.awt.Image;
import java.awt.Rectangle;
import javax.swing.ImageIcon;



public abstract class Sprite extends Rectangle{

    public int x;
    public int y;
    public int mapX;
    public int mapY;
    public Image appearance;
    public Rectangle bounds;
    
    private boolean passable;
    
    public static byte health = 0;

    protected final void loadImage(String imageName) {
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
    
    public int getMapX() {
        return x;
    }
    
    public int getMapY() {
        return y;
    }
    
    public void setMapX(int x) {
        this.x = x;
    }
    
    public void setMapY(int y) {
        this.y = y;
    }
    
    public boolean getPassable() {
        return passable;
    }
    
    public int getHealth() {
        return health;
    }
    
    public void updateHealth(byte change) {
        health += change;
    }
    
    public void updateHealth(byte change, boolean complete) {
        if (complete) health = change;
    }

    


}
