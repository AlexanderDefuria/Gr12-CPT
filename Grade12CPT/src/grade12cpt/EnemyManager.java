
package grade12cpt;

import java.util.ArrayList;
import java.util.Iterator;

/**
 *
 * @author Alexander
 */

public abstract class EnemyManager {

    public static ArrayList<Enemy> allEnemies = new ArrayList<>();
    public static ArrayList<Enemy> visibleEnemies = new ArrayList<>();
    public static ArrayList<Enemy> toRemove = new ArrayList<>();
    private static int OLD_X = 0, OLD_Y = 0;
    private static int X_OFF = 0, Y_OFF = 0;
      
    
    public static void loadEnemies() {
        
    }
    
    public static void addEnemy(Enemy newEnemy) {
        allEnemies.add(newEnemy);
    }
        
    public static void removeEnemy(Enemy enemy) {
        allEnemies.remove(enemy);
    }
    
    public static ArrayList<Enemy> getEnemies() {
        return allEnemies;
    }
    
    public static void checkDamage() {
        for (Enemy x : allEnemies) {
            if (x.getHealth() < 0) toRemove.add(x);
        }
        
        for (Enemy removing : toRemove) {
            allEnemies.remove(removing);
        }
    }

    public static void setOffset(int x_off, int y_off) {
        X_OFF = x_off;
        Y_OFF = y_off;
    }

    // If the currecnt offset is different then update all enemies current offset
    public static void updateEnemies() {
        
        allEnemies.forEach((enemy) -> {
            enemy.update();
            
            if (X_OFF != OLD_X) {
                OLD_X = X_OFF;
                enemy.setMapX(X_OFF + enemy.Xorigin + (int)enemy.movedX);                
            } 
            
            if (Y_OFF != OLD_Y) {
                OLD_Y = Y_OFF;
                enemy.setMapY(Y_OFF + enemy.Yorigin + (int)enemy.movedY);
            }
            
            System.out.println(enemy.moveX);
        });
        
        for (Iterator<Enemy> enemyIter = allEnemies.iterator(); enemyIter.hasNext();) {
            Enemy enemy = enemyIter.next();
            for (Projectile projectile : ProjectileManager.allProjectiles) 
                if (projectile.intersects(enemy)) {
                    enemyIter.remove();
                    break;
                }

        }
        
        

    }
    
    public static Enemy getEnemy(String name) {
        for (Enemy enemy : allEnemies) 
            if (enemy.getName().equals(name)) 
                return enemy;
        
        throw new  IllegalArgumentException("That name does not exist as of now.... TIME TO CRASH!");
    }
}
