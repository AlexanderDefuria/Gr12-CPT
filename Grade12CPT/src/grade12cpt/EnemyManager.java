
package grade12cpt;

import java.awt.Rectangle;
import java.util.ArrayList;

/**
 *
 * @author Alexander
 */

public abstract class EnemyManager {

    public static ArrayList<Enemy> allEnemies = new ArrayList<>();
    public static ArrayList<Enemy> visibleEnemies = new ArrayList<>();
    private static int OLD_X = 0, OLD_Y = 0;
    private static int X_OFF = 0, Y_OFF = 0;
      
    
    public static void addEnemy(Enemy newEnemy) {
        allEnemies.add(newEnemy);
    }
    
    public static ArrayList<Enemy> getEnemies() {
        return allEnemies;
    }
    
    public static void checkDamage(Rectangle range) {
        for (Enemy x : allEnemies) {
            //if (x.intersects(player.))
        }
    }

    public static void setOffset(int x_off, int y_off) {
        X_OFF = x_off;
        Y_OFF = y_off;
    }

    // If the currecnt offset is different then update all enemies current offset
    public static void updateEnemies() {
        if (X_OFF != OLD_X) {
            OLD_X = X_OFF;
            allEnemies.forEach((enemy) -> {
                enemy.setMapX(X_OFF);
            });
        }
        
        if (Y_OFF != OLD_Y) {
            OLD_Y = Y_OFF;
            allEnemies.forEach((enemy) -> {
                enemy.setMapY(Y_OFF);
            });
        }
        
        allEnemies.forEach((enemy) -> {
            //System.out.println(enemy.getLocation());
        });
    }
}
