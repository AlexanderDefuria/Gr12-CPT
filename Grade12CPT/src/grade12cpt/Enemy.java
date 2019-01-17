
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
    private boolean bounce = true;
    
    public Enemy(String name) {
        this.name = name;
        
        System.out.println("Loaded Enemy: " + this.name);
        Xorigin = x;
        Yorigin = y;
        distance = 0;
        
        
    }
    
    
    static final class Bat extends Enemy{

        public Bat(String name, int x, int y)  {
            super(name);
            enemySheet = "src/images/bat.png";
            loadBatSprites(this);
            speed = 4;
            animate();
            walk(x,y,true);
            this.updateHealth(20, true);
            this.setSize(getDimensions());
        }
        
        public void loadBatSprites(Bat bat) {
            String sheetName = bat.getSheet();
            
            
            try {
            spriteSheet = ImageIO.read(new File(sheetName));

            // Populate walking sprite image array
            walkingSprite = new Image[5][5];
            for (int i = 0; i != 5; i++){
                walkingSprite[0][i] = spriteSheet.getSubimage(32 * i, 0, 32, 32);
                walkingSprite[1][i] = Sprite.flipImage(spriteSheet.getSubimage(32 * i, 0, 32, 32));
                walkingSprite[2][i] = spriteSheet.getSubimage(32 * i, 32 * 3, 32, 32);
                walkingSprite[3][i] = spriteSheet.getSubimage(32 * i, 32 * 6, 32, 32);
                walkingSprite[4][i] = walkingSprite[2][i];
            } 

        } catch (IOException ex) {}
            
        }
        
    }
    
    static class Ogre extends Enemy{
        public Ogre (String name, int x, int y) {
            super(name);
            enemySheet = "src/images/ogre.png";
            loadOgreSprites(this);
            speed = 1;
            animate();
            walk(x,y,true);
            this.updateHealth(40, true);
            this.setSize(getDimensions());
        }
        
        public void loadOgreSprites(Ogre ogre) {
            String sheetName = ogre.getSheet();
            
            try {
            spriteSheet = ImageIO.read(new File(sheetName));

            // Populate walking sprite image array
            walkingSprite = new Image[5][5];
            for (int i = 0; i != 5; i++){
                walkingSprite[0][i] = spriteSheet.getSubimage(32 * i, 32, 32, 32);
                walkingSprite[1][i] = Sprite.flipImage(spriteSheet.getSubimage(32 * i, 32, 32, 32));
                walkingSprite[2][i] = spriteSheet.getSubimage(32 * i, 32 * 4, 32, 32);
                walkingSprite[3][i] = spriteSheet.getSubimage(32 * i, 32 * 7, 32, 32);
                walkingSprite[4][i] = spriteSheet.getSubimage(0, 32 * 8, 32, 32);
            } 

        } catch (IOException ex) {}
            
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
        
        //if ()
        
        
        if (movedX != moveX && moveX != 0) {
            if (moveX > 0) {
                x += speed;
                movedX += speed;
            }
            else if (moveX < 0) {
                x -= speed;
                movedX -= speed;
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
            }
            else if (moveY < 0) {
                y -= speed;
                movedY += speed;
            } 
        
        } else if (movedX >= moveX && bounce) {
            moveX -= movedX * 2;
           
        } else if (movedX <= 0){
            moveX = EnemyManager.walkDist * 16;
        }
    }
    
    
    public void walk(int x, int y, boolean bouncy) {
        moveX += x;
        moveY += y;
        this.bounce = bouncy;
    }
    
    public void setStart(int x, int y) {
        Xorigin = x;
        Yorigin = y;
        this.x = x;
        this.y = y;
    }
    
    
    
    
    
    
    



}
