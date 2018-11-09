
package grade12cpt;

import static grade12cpt.Board.B_HEIGHT;
import static grade12cpt.Board.B_WIDTH;
import java.awt.Rectangle;


public class Player extends Sprite{

    public UserInput ui;
    public Terrain walls;
    public static int overallX = B_WIDTH / 2;
    public static int overallY = B_HEIGHT / 2;
    public static int moveX, moveY = 0;
    private static boolean canMove[] = new boolean[5];
    // Speed work with 1,2,4,8 etc      TODO No fucking clue why but that's how it is!
    public static int speed = 2;
    
    
    public Player(UserInput ui) {
        this.ui = ui;
        init();
    }
      
    
    public void init() {
        loadImage("src/images/guard.png");
        moveX = 0;
        moveY = 0;
        this.setLocation(overallX, overallY);
        this.setSize(getDimensions());
        
        for (int i = 0; i != canMove.length; i++) canMove[i] = true;


    }
    
    public int getMoveX() {
        return moveX;
    }
    
    public int getMoveY() {
        return moveY;
    }
    
    
    public void updatePlayer(Map map) {
        
        checkMapEdge(map);
        checkTerrain(map);
           
        if(UserInput.LEFT && canMove[0]) moveX = speed;
        else if(UserInput.RIGHT && canMove[1]) moveX = -speed;
        else moveX = 0;

        if(UserInput.UP && canMove[2]) moveY = speed;
        else if(UserInput.DOWN && canMove[3]) moveY = -speed;
        else moveY = 0;

    }
    

    public void checkMapEdge(Map map) {

        Rectangle hit = new Rectangle(map.getMapOutline());
        
        if (!hit.contains(this.getHitbox())){
            if (overallX < hit.getMinX()) {
                canMove[0] = false;
                canMove[1] = true;
            }
            else if (overallX + width > hit.getMaxX()){
                canMove[1] = false;
                canMove[0] = true;
            }
            if (overallY - height < hit.getMinY()) {
                canMove[2] = false;
                canMove[3] = true;
            }
            else if (overallY + height> hit.getMaxY()) {
                canMove[3] = false;
                canMove[2] = true;
            }
        }
        else 
            for (int i = 0; i != canMove.length; i++) {
                canMove[i] = true;
            }
    }
    
    public void checkTerrain(Map map) {
        for (Rectangle rect : map.getSolidTerrain()){
            int Xorigin = (int)this.getX();
            int Yorigin = (int)this.getY();
            
            this.setLocation(Xorigin - speed, Yorigin);
            if(rect.intersects(this)) 
                canMove[0] = false;
            
            this.setLocation(Xorigin + speed, Yorigin);
            if(rect.intersects(this)) 
                canMove[1] = false;
            
            this.setLocation(Xorigin, Yorigin  - speed);
            if(rect.intersects(this)) 
                canMove[2] = false;
            
            this.setLocation(Xorigin, Yorigin + speed);
            if(rect.intersects(this)) 
                canMove[3] = false;
            
            this.setLocation(Xorigin, Yorigin);
                
                
//                if ((this.getMinY() < rect.getMaxY() && this.getMinY() > rect.getMinY())  ||
//                    (this.getMaxY() < rect.getMinY() && this.getMaxY() > rect.getMaxY()))
//                    {
//                    if  (this.getX() > rect.getX()) {
//                        canMove[1] = true;
//                        canMove[0] = false;
//                    }
//                    else if (this.getX() < rect.getX()) {
//                        canMove[1] = false;
//                        canMove[0] = true;
//                    }
//                }
//                
//                if ((this.getMinX() < rect.getMaxX() && this.getMinX() > rect.getMinX()) ||
//                    (this.getMaxX() < rect.getMinX() && this.getMaxX() > rect.getMaxX())) 
//                    {
//                    if  (this.getY() > rect.getY()) {
//                        canMove[3] = true;
//                        canMove[2] = false;
//                    }
//                    else if (this.getY() < rect.getY()) {
//                        canMove[3] = false;
//                        canMove[2] = true;
//                    }
//                    
//                }
            

        }
        
    }
       
    public Rectangle getHitbox() {
        return new Rectangle(overallX, overallY, width, height);
    }

}
