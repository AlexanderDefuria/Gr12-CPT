
package grade12cpt;

import static grade12cpt.Board.B_HEIGHT;
import static grade12cpt.Board.B_WIDTH;
import java.awt.Rectangle;


public class Player extends Sprite{

    public UserInput ui;
    public SolidTerrain walls;
    public static boolean checking = true;
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
        
        if (checking) {
            checkTerrain(map);
            checkEnemies(map);
            UserInput();
        } else {
                
            
        }
        
        
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
                ProjectileManager.addProjectile(new Projectile((RangedWeapon)weapon));
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
    
    
    
    public void checkTerrain(Map map) {
        canMove[0] = true;
        canMove[1] = true;
        canMove[2] = true;
        canMove[3] = true;
        
        
        for (Rectangle rect : map.getSolidTerrain()){
            int Xorigin = (int)rect.getX();
            int Yorigin = (int)rect.getY();
            
            
            
            rect.setLocation(Xorigin + speed, Yorigin);
            if(rect.intersects(this))
                canMove[0] = false;
            
            
            rect.setLocation(Xorigin - speed, Yorigin);
            if(rect.intersects(this)) 
                canMove[1] = false;
            
            
            rect.setLocation(Xorigin, Yorigin  + speed);
            if(rect.intersects(this)) 
                canMove[2] = false;
            
            
            rect.setLocation(Xorigin, Yorigin - speed);
            if(rect.intersects(this)) 
                canMove[3] = false;
            
            rect.setLocation(Xorigin, Yorigin);
            
            
                

        }
        
    }
    
    // Cehcks to see if any enemies
    public void checkEnemies(Map map) {
        for (Enemy enemy : EnemyManager.getEnemies()){
            if (this.intersects(enemy.getBounds())) {
                this.updateHealth(-1);
            }
            
            if (this.attackRange.intersects(enemy.getBounds())) {
                enemy.updateHealth(-20);
                
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

    //Returns the player hitbox as a rectangle
    public Rectangle getHitbox() {
        return new Rectangle(overallX, overallY, width, height);
    }

}


