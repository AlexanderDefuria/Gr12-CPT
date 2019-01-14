
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
    }
    
    public int movedX = 0;
    public int movedY = 0;
    
    private void init() {
        loadImage("src/images/enemy.png");
        this.setSize(getDimensions());
        this.setLocation(200, 200);
        x = 200;
        y = 100;
        Xorigin = x;
        Yorigin = y;
        distance = 0;
        speed = 0;
        this.updateHealth(10, true);
        walk(45,100);

    }
    
    public void update() {
        if (movedX != moveX) {
            if (moveX > 0) {
                x += speed;
                movedX += speed;
            }
            else if (moveX < 0) {
                x -= speed;
                movedX += speed;
            } 
        } else if (movedY != moveY) {
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
