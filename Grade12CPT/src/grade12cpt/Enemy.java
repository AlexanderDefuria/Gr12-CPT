
package grade12cpt;

import java.awt.Image;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;

/**
 *
 * @author Alexander
 */

// TODO change to abstract class

public abstract class Enemy extends Sprite{
    
    
    public static String enemySheet = "";
    
    public Enemy(String name) {
        this.name = name;
        
        System.out.println("Loaded Enemy: " + this.name);
        Xorigin = x;
        Yorigin = y;
        distance = 0;
        
        
    }
    
    
    static final class Bat extends Enemy{

        public Bat(String name)  {
            super(name);
            enemySheet = "src/images/bat.png";
            loadBatSprites(this);
            speed = 4;
            this.updateHealth(20, true);
            this.setSize(getDimensions());
        }
        
        public void loadBatSprites(Bat bat) {
            String sheetName = bat.getSheet();
            
            
            try {
            spriteSheet = ImageIO.read(new File(sheetName));

            // Populate walking sprite image array
            walkingSprite = new Image[4][5];
            for (int i = 0; i != 5; i++){
                walkingSprite[0][i] = spriteSheet.getSubimage(32 * i, 0, 32, 32);
                walkingSprite[1][i] = Sprite.flipImage(spriteSheet.getSubimage(32 * i, 0, 32, 32));
                walkingSprite[2][i] = spriteSheet.getSubimage(32 * i, 32 * 3, 32, 32);
                walkingSprite[3][i] = spriteSheet.getSubimage(32 * i, 32 * 6, 32, 32);
            } 

        } catch (IOException ex) {}
            
        }
        
    }
    
    static class Ogre extends Enemy{
        public Ogre (String name) {
            super(name);
            enemySheet = "src/images/ogre.png";
            super.loadEnemySprites(this);
            speed = 1;
            this.updateHealth(40, true);
            this.setSize(getDimensions());
        }
    }
    

    
    
    public int movedX = 0;
    public int movedY = 0;
    
    private void init() {
        loadImage("src/images/enemy.png");
        this.setSize(getDimensions());
        
        Xorigin = x;
        Yorigin = y;
        distance = 0;
        speed = 2;
        this.updateHealth(10, true);


    }
    
    public String getSheet() {
        return enemySheet;
    }
    
    public void update() {
        
        if (movedX != moveX && moveX != 0) {
            if (moveX > 0) {
                x += speed;
                movedX += speed;
            }
            else if (moveX < 0) {
                x -= speed;
                movedX += speed;
            } 
        }
        
        if (movedY != moveY && moveY != 0 ) {
            if (moveY > 0) {
                y += speed;
                movedY += speed;
            }
            else if (moveY < 0) {
                y -= speed;
                movedY += speed;
            } 
        
        }   
    }
    
    
    public void walk(int x, int y) {
        moveX += x;
        moveY += y;
    }
    
    public void setStart(int x, int y) {
        Xorigin = x;
        Yorigin = y;
        this.x = x;
        this.y = y;
    }
    
    
    
    
    
    
    



}
