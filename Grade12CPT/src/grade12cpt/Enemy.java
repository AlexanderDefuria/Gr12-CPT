
package grade12cpt;

/**
 *
 * @author Alexander
 */

// TODO change to abstract class

public class Enemy extends Sprite{
    
    public Enemy(String name) {
        this.name = name;
        init();
        System.out.println("Loaded Enemy: " + this.name);
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
    
    
    
    
    
    
    



}
