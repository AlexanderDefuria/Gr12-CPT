
package grade12cpt;

import static grade12cpt.Board.B_HEIGHT;
import static grade12cpt.Board.B_WIDTH;
import java.awt.Rectangle;


public class Player extends Sprite{

    public UserInput ui;
    public Terrain walls;
    public static int overallX = B_WIDTH / 2;
    public static int overallY = B_HEIGHT / 2;
    public int moveX, moveY = 0;
    public static Rectangle attackRange;
    private static boolean canMove[] = new boolean[5];
    
    public Player(UserInput ui, String name) {
        this.ui = ui;
        this.name = name;
        init(); 
    }
    

    public void init() {
        this.weapon = new RangedWeapon.Orb();
        
        loadImage("src/images/enemy.png");
        loadSprites("src/images/clotharmor.png");
            
        if (weapon instanceof MeleeWeapon) loadWeaponSprites(weapon);
        else if (weapon instanceof RangedWeapon)
        
        moveX = 0;
        moveY = 0;
        speed = 2;
        this.maxHP = 100;
        this.curHP = maxHP;
        this.setLocation(overallX, overallY);
        this.setSize(getDimensions());
        
        attackRange = new Rectangle(mapX, mapY,0,0);
                
        for (int i = 0; i != canMove.length; i++) canMove[i] = true;

    }

    
    public void updatePlayer(Map map) {
        
        checkMapEdge(map);
        checkTerrain(map);
        checkEnemies(map);
        
        UserInput();
        animate();
        
        mapX += moveX;
        mapY += moveY;

    }
    
    public void UserInput() {
        if(UserInput.A_KEY && canMove[0]) moveX = speed;
        else if(UserInput.D_KEY && canMove[1]) moveX = -speed;
        else moveX = 0;

        if(UserInput.W_KEY && canMove[2]) moveY = speed;
        else if(UserInput.S_KEY && canMove[3]) moveY = -speed;
        else moveY = 0;
        
        if(UserInput.CLICKED && canAttack) {
            attacking = true;
            
            if (weapon instanceof RangedWeapon) {
                ProjectileManager.addProjectile(new Projectile(weapon));
                canAttack = false;
                return;
            }

            switch(direction) {
                case 0:
                    attackRange = new Rectangle(overallX - 32, overallY - (32 / 2), 32, 64);
                    break;
                case 1: 
                    attackRange = new Rectangle(overallX + 32, overallY - (32 / 2), 32, 64);
                    break;
                case 2: 
                    attackRange = new Rectangle(overallX - (32 / 2), overallY - 32, 64, 32);
                    break;
                default:
                    attackRange = new Rectangle(overallX - (32 / 2), overallY + 32, 64, 32);
                    break;
            }
            
        } else {
            attacking = false;
            attackRange = new Rectangle();
        }
    }
    
    // <editor-fold defaultstate="collapsed">
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
                

        }
        
    }
    
    public void checkEnemies(Map map) {
        for (Enemy enemy : EnemyManager.getEnemies()){
            if (this.intersects(enemy.getBounds())) {
                this.updateHealth(-1);
            }
            
            if (this.attackRange.intersects(enemy.getBounds())) {
                enemy.updateHealth(-1);
                
            }

        }
        
        EnemyManager.checkDamage();

    }
    
    public int getMoveX() {
        return moveX;
    }
    
    public int getMoveY() {
        return moveY;
    }
    // </editor-fold>
    

    public Rectangle getHitbox() {
        return new Rectangle(overallX, overallY, width, height);
    }

}


