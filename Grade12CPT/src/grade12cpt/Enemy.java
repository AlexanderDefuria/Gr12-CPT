
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
    
    private void init() {
        loadImage("src/images/enemy.png");
        this.setSize(getDimensions());
        this.setLocation(200, 200);
        x = 200;
        y = 100;
        Xorigin = x;
        Yorigin = y;
        distance = 0;
        speed = 2;
        this.updateHealth(10, true);
        walk(45,100);
        System.out.println("Move X: " + moveX + "     Move Y: " + moveY);

    }
    
    public void update() {
        distance += (int)Math.sqrt((moveX * moveX) + (moveY * moveY));
        if (distance > fullMove || distance < 0) {
            moveX = -moveX;
            moveY = -moveY;
        }
    }
    
    public void walk(double angle, int range) {
        moveX = 2; //;
        moveY = 2; //Math.cos(angle) * speed;
        fullMove = range;
        System.out.println("Move X: " + moveX + "     Move Y: " + moveY);
    }
    
    public void walk(int x, int y) {
        
    }
    
    
    
    
    



}
