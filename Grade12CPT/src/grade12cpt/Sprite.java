
package grade12cpt;

import static grade12cpt.Board.loopIteration;
import java.awt.Dimension;
import java.awt.Image;
import java.awt.Rectangle;
import java.awt.geom.AffineTransform;
import java.awt.image.AffineTransformOp;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;



public abstract class Sprite extends Rectangle{

    public int x;
    public int y;
    public int lastX;
    public int lastY;
    public int direction;
    public int step;
    public int mapX;
    public int mapY;
    public double curHP = 0;
    public double maxHP = 0;
    public Image appearance;
    public BufferedImage spriteSheet;
    public Rectangle bounds;
    
    private boolean animate = true; // TODO have different sprites not be anuimated, add method to set
    
    private boolean passable;
    
    
    protected final void loadImage(String imageName) {
        ImageIcon ii = new ImageIcon(imageName);
        appearance = ii.getImage();
        getDimensions();
        
        this.setLocation(x, y);
        
    }
    
    protected final void loadSprites(String sheetName) {
        try {
            spriteSheet = ImageIO.read(new File(sheetName));
        } catch (IOException ex) {
            Logger.getLogger(Sprite.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public void animate() {
        if      (mapX < lastX) direction = 0;
        else if (mapX > lastX) direction = 1;
        else if (mapY < lastY) direction = 2;
        else if (mapY > lastY) direction = 3;
        else direction = 4;
        
        lastX = mapX;
        lastY = mapY;
        
        
        
        
        if (loopIteration % 10 == 0) 
             step++;
        
        if (step > 3) step = 0;
        
        switch (direction) {
            case 0: 
            case 1:
                appearance = spriteSheet.getSubimage(32 * step, 32, 32,32);
                return;
            case 2:
                appearance = spriteSheet.getSubimage(32 * step, 32 * 7, 32,32);
                
                return;
            case 3: 
                appearance = spriteSheet.getSubimage(32 * step, 32 * 4, 32,32);
                return;
            case 4:
                appearance = getIdle();
                return;
        }
        
        //appearance = spriteSheet.getSubimage((32 * step) , 32 * (direction), 32, 32);
        
        //appearance = getIdle();
        updateDimensions();
    }
    
    private Image getIdle() {
        return spriteSheet.getSubimage(32 , 32 * 8, 32, 32);
    }
    
    protected void updateDimensions() {
        height = appearance.getHeight(null);
        width = appearance.getWidth(null);
        this.setSize(width, height);
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
        setLocation(x,y);
    }
    
    public void setMapY(int y) {
        this.y = y;
        setLocation(x,y);
    }
    
    public boolean getPassable() {
        return passable;
    }
    
    public double getHealth() {
        return curHP;
    }
    
    public double getMaxHealth() {
        return maxHP;
    }
    
    public void updateHealth(int change) {
        curHP += change;
        updateHealth();
    }
    
    public void updateHealth() {
        if (curHP > maxHP) curHP = maxHP;
        else if (curHP < 0) curHP = maxHP;
    }
    
    public void updateHealth(byte change, boolean complete) {
        if (complete) curHP = change;
    }

    


}
