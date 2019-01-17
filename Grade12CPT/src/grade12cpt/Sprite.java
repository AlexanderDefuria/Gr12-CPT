    
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
    public double moveX;
    public double moveY;
    public int distance;
    public int fullMove;
    public int Xorigin;
    public int Yorigin;
    public int lastX;
    public int lastY;
    public int direction;
    public int attackDirection;
    public int step;
    public int actionStep;
    public int mapX;
    public int mapY;
    public int speed; 
    public int spriteLoop;
    public double curHP = 0;
    public double maxHP = 0;
    public boolean moves = false;
    public boolean attacking = false;
    public boolean canAttack = true;
    public Image appearance;
    public BufferedImage spriteSheet;
    public Rectangle bounds;
    public Image[][] walkingSprite;
    public Image[][] weaponSprite;
    public Image[][] actionSprite;
    public String armorSheet;
    public String weaponSheet;
    public String enemySheet;
    public String name;
    public Weapon weapon;  
    private boolean passable;
    
    
    protected final void loadImage(String imageName) {
        ImageIcon ii = new ImageIcon(imageName);
        appearance = ii.getImage();
        this.setSize(getDimensions());
        
        this.setLocation(x, y);
        System.out.println("Loaded Image: " + imageName);
        
    }
    
    protected final void loadSprites(String sheetName) {
        armorSheet = sheetName;
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

        } catch (IOException ex) {}
        
        System.out.println("Loaded Sprites: " + sheetName);

    }
    
    protected final void loadWeaponSprites(Weapon weapon) {
        weaponSheet = weapon.getSheet();
        try {
            spriteSheet = ImageIO.read(new File(weaponSheet));  
            weaponSprite = new Image[4][5];
            for (int i = 0; i != 5; i++){
                weaponSprite[0][i] = flipImage(spriteSheet.getSubimage(32 * i, 1, 32, 32));
                weaponSprite[1][i] = spriteSheet.getSubimage(32 * i, 1, 32, 32);
                weaponSprite[2][i] = spriteSheet.getSubimage(32 * i, 32 * 3, 32, 32);
                weaponSprite[3][i] = spriteSheet.getSubimage(32 * i, 32 * 6, 32, 32);
            }
            
            spriteSheet = ImageIO.read(new File(armorSheet));
            actionSprite = new Image[4][5];
            for (int i = 0; i != 5; i++){
                actionSprite[0][i] = flipImage(spriteSheet.getSubimage(32 * i, 1, 32, 32));
                actionSprite[1][i] = spriteSheet.getSubimage(32 * i, 1, 32, 32);
                actionSprite[2][i] = spriteSheet.getSubimage(32 * i, 32 * 3, 32, 32);
                actionSprite[3][i] = spriteSheet.getSubimage(32 * i, 32 * 6, 32, 32);
            }
            
        } catch (IOException ex) {}
        System.out.println("Loaded Sprites: " + weapon.getClass());
    }

    
    public void animate() {
        if      (mapX > lastX) direction = 0; // Right
        else if (mapX < lastX) direction = 1; // Left
        else if (mapY > lastY) direction = 2; // Down
        else if (mapY < lastY) direction = 3; // Up
        
        if (mapX == lastX && mapY == lastY) {
            direction = 4;
        } else if (spriteLoop % 20 == 0){
            step++;
        }
        
        if (spriteLoop % 5 == 0) {
            actionStep++;
        }
        
        if (spriteLoop % 50 == 0 && !canAttack) {
            canAttack = true;
        } 
             
        if (spriteLoop > 100) spriteLoop = 0;
            spriteLoop += 2;
        
        lastX = mapX;
        lastY = mapY;
        
        if (step == 4) step = 0;
        
        if (actionStep == 5) {
            actionStep = 0;
            canAttack = false;
        }
        
        if (direction == 4 ) 
            if (this instanceof Player) appearance = getIdle();
            else appearance = walkingSprite[2][0];
        else appearance = walkingSprite[direction][step];


        if (attacking && this instanceof Player) {
            
            if (UserInput.UP) direction = 2;
            else if (UserInput.LEFT) direction = 0;
            else if (UserInput.DOWN) direction = 3;
            else if (UserInput.RIGHT) direction = 1;
            
            if (weapon instanceof MeleeWeapon)
                if (direction == 4) {
                    appearance = actionSprite[3][actionStep];
                    appearance = addWeapon(appearance, weaponSprite[3][actionStep]);
                } else {
                    appearance = actionSprite[direction][actionStep];
                    appearance = addWeapon(appearance, weaponSprite[direction][actionStep]);
                }
            
            
        } else if (!attacking) actionStep = 0;
        
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
        this.setLocation(x, getMapY());
    }
    
    public void setMapY(int y) {
        this.y = y;
        this.setLocation(getMapX(), y);
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
    
    public String getName() {
        return name;
    }
    
    public void updateHealth(int change) {
        curHP += change;
        if (this instanceof Player) 
            if ( curHP < 0 ) curHP = maxHP;
    }
    
    public void updateHealth(int change, boolean complete) {
        if (complete) curHP = change;
    }

    public static Image flipImage(Image image) {
        BufferedImage flipped = new BufferedImage(image.getWidth(null), image.getHeight(null), BufferedImage.TYPE_INT_ARGB);
        int width = image.getWidth(null);
        int height = image.getHeight(null);
        
        Graphics2D g2 = flipped.createGraphics();
        g2.drawImage(image,  width + 1, 0, -width, height, null);
        
        return (Image) flipped;
    }
    

    
    private Image addWeapon(Image baseImage, Image weaponImage){
        BufferedImage weapon = (BufferedImage) weaponImage;
        
        
        Graphics2D g2 = weapon.createGraphics();
        g2.drawImage(baseImage, 0, 0, null);
        
        
        return (Image) weapon;
    }
    


    


}
