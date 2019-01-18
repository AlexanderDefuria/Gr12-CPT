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
    
    
    public static String enemySheet;
    private boolean bounce = true;
    public static boolean moved = false;
    public int movedX = 0;
    public int movedY = 0;
    
    
    // Each enemy has a name, generally an ID, but there is also Tom
    // There is the generic enemy which can be used for testing,
    // but there are static subclasses used for unique enemies.
    public Enemy(String name) {
        this.name = name;
        
        System.out.println("Loaded Enemy: " + this.name);
        
        // Set origin
        Xorigin = x;
        Yorigin = y;
        distance = 0;
    }
    
    //Bat enemy class
    static final class Bat extends Enemy{

        // Bat has name and x,y coordinate to walk back and forth from
        // Has a health of 15, and takes care of standard setting of variables
        public Bat(String name, int x, int y)  {
            super(name);
            enemySheet = "src/images/bat.png";
            loadBatSprites(this);
            speed = 4;
            animate();
            walk(x,y,true);
            this.updateHealth(15, true);
            this.setSize(getDimensions());
        }
        
        // Not Standard Loading of sprites for animations, should only run once
        public void loadBatSprites(Bat bat) {
            String sheetName = bat.getSheet();

            try {
            spriteSheet = ImageIO.read(new File(sheetName));

            // Populate walking sprite image array
            walkingSprite = new Image[5][5];
            for (int i = 0; i != 5; i++){
                walkingSprite[0][i] = spriteSheet.getSubimage(32 * i, 0, 32, 48);
                walkingSprite[1][i] = Sprite.flipImage(spriteSheet.getSubimage(32 * i, 0, 32, 48));
                walkingSprite[3][i] = spriteSheet.getSubimage(32 * i, 48 * 4, 32, 48);
                walkingSprite[2][i] = spriteSheet.getSubimage(32 * i, 48 * 7, 32, 48);
                walkingSprite[4][i] = walkingSprite[2][i];
            } 

        } catch (IOException ex) {}
            
        }
        
    }
    
    // Ogre enemy class
    static class Ogre extends Enemy{
        public Ogre (String name, int x, int y) {
            super(name);
            enemySheet = "src/images/ogre.png";
            loadOgreSprites(this);
            speed = 1;
            animate();
            walk(x,y,true);
            this.updateHealth(60, true);
            this.setSize(getDimensions());
        }
        
        // Not Standard Loading of sprites for animations, should only run once
        public void loadOgreSprites(Ogre ogre) {
            String sheetName = ogre.getSheet();
            
            try {
            spriteSheet = ImageIO.read(new File(sheetName));

            // Populate walking sprite image array
            walkingSprite = new Image[5][5];
            for (int i = 0; i != 5; i++){
                walkingSprite[0][i] = spriteSheet.getSubimage(48 * i, 48, 48, 48);
                walkingSprite[1][i] = Sprite.flipImage(spriteSheet.getSubimage(48 * i, 48, 48, 48));
                walkingSprite[2][i] = spriteSheet.getSubimage(48 * i, 48 * 7, 48, 48);
                walkingSprite[3][i] = spriteSheet.getSubimage(48 * i, 48 * 4, 48, 48);
                walkingSprite[4][i] = spriteSheet.getSubimage(0, 48 * 8, 32, 48);
            } 

        } catch (IOException ex) {}
            
        }
    }
    

    
    // Returns the name of the enemy sheet being used by this enemy
    public String getSheet() {
        return enemySheet;
    }
    
    // Update the position of the enemy based on the movement path and last position and speed
    // Should switch direction at limit of desired movement
    public void update() {

        if (movedX != moveX && moveX != 0) {
            if (moveX > 0) {
                x += speed;
                movedX += speed;
                mapX += speed;
            }
            else if (moveX < 0) {
                x -= speed;
                movedX -= speed;
                mapX -= speed;
            } 
            
        } else if (movedX >= moveX && bounce) {
            moveX -= movedX * 2;
           
        } else if (movedX <= 0 && bounce){
            moveX = EnemyManager.walkDist * 16;
        }
        
        if (movedY != moveY && moveY != 0 ) {
            if (moveY > 0) {
                y += speed;
                movedY += speed;
                mapY += speed;
            }
            else if (moveY < 0) {
                y -= speed;
                movedY -= speed;
                mapY -= speed;
            } 
        
        } else if (movedY >= moveY && bounce) {
            moveY -= movedY * 2;
           
        } else if (movedY <= 0){
            moveY = EnemyManager.walkDist * 16;
        }
    }
    
    
    // Set the next movement and whether it will move back and forth
    public void walk(int x, int y, boolean bouncy) {
        moveX += x;
        moveY += y;
        this.bounce = bouncy;
    }
    
    // Should be self explanatory
    public void setStart(int x, int y) {
        Xorigin = x;
        Yorigin = y;
        this.x = x;
        this.y = y;
    }
    
    
    
    
    
    
    



}
