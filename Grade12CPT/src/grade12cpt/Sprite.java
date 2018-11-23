
package grade12cpt;

import java.awt.Dimension;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
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
    public int spriteLoop;
    public double curHP = 0;
    public double maxHP = 0;
    public Image appearance;
    public BufferedImage spriteSheet;
    public Rectangle bounds;
    public Image[][] walkingSprite;
    
    private boolean animate = true; // TODO have different sprites not be animated, add method to set
    
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
            
            // Populate walking sprite image array
            walkingSprite = new Image[4][4];
            for (int i = 0; i != 4; i++){
                walkingSprite[0][i] = flipImage(spriteSheet.getSubimage(32 * i, 32, 32, 32));
                walkingSprite[1][i] = spriteSheet.getSubimage(32 * i, 32, 32, 32);
                walkingSprite[2][i] = spriteSheet.getSubimage(32 * i, 32 * 4, 32, 32);
                walkingSprite[3][i] = spriteSheet.getSubimage(32 * i, 32 * 7, 32, 32);
            }
            
            // TODO Populate combat sprite image array 

        } catch (IOException ex) {}
    }
    
    public void animate() {
        if      (mapX > lastX) direction = 0;
        else if (mapX < lastX) direction = 1;
        else if (mapY > lastY) direction = 2; 
        else if (mapY < lastY) direction = 3;
        
        if (mapX == lastX && mapY == lastY) {
            
            direction = 4;
        } else if (spriteLoop % 20 == 0)
             step++;
        if (spriteLoop > 100) spriteLoop = 0;
            spriteLoop++;
        
        lastX = mapX;
        lastY = mapY;
        
        if (step == 4) step = 0;
        
        switch (direction) {
            case 0: 
                appearance = walkingSprite[0][step];
                break;
            case 1:
                appearance = walkingSprite[1][step];
                break;
            case 2:
                appearance = walkingSprite[2][step];
                break;
            case 3: 
                appearance = walkingSprite[3][step];
                break;
            case 4:
                appearance = getIdle();
                break;
        }
        

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

    private Image flipImage(Image image) {
        BufferedImage flipped = new BufferedImage(image.getWidth(null), image.getHeight(null), BufferedImage.TYPE_INT_ARGB);
        
        Graphics2D g2 = flipped.createGraphics();
        g2.drawImage(image,  width + 1, 0, - width, height, null);
        
        return (Image)flipped;
    }
    


    


}
