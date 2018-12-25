
package grade12cpt;

import static grade12cpt.Board.B_HEIGHT;
import static grade12cpt.Board.B_WIDTH;
import java.util.ArrayList;
import java.util.Iterator;

/**
 *
 * @author Alexander
 */

public abstract class ProjectileManager {

    public static ArrayList<Projectile> allProjectiles = new ArrayList<>();
    private static int X_OFF = 0, Y_OFF = 0;
      
    
    public static void addProjectile(Projectile newProjectile) {
        allProjectiles.add(newProjectile);
        if (allProjectiles.size() > 10) allProjectiles.remove(0);
    }
        
    public static void removeProjectile(Projectile projectile) {
        allProjectiles.remove(projectile);
    }
    
    public static ArrayList<Projectile> getProjectiles() {
        return allProjectiles;
    }
    

    public static void setOffset(int x_off, int y_off) {
        X_OFF = x_off;
        Y_OFF = y_off;
    }
    
    

    // If the currecnt offset is different then update all Projectiles current offset
    public static void updateProjectiles() {

        allProjectiles.forEach((projectile) -> {
            projectile.setMapX((int)(projectile.Xorigin + X_OFF - (projectile.Xspeed * projectile.loop)));
            projectile.setMapY((int)(projectile.Yorigin + Y_OFF - (projectile.Yspeed * projectile.loop)));
            projectile.loop++;
        });

        if (!allProjectiles.isEmpty()) {
            Iterator<Projectile> iterator = allProjectiles.iterator();
            while ( iterator.hasNext() ){
                Projectile projectile = iterator.next();
                if (projectile.getMapX() < -32 || projectile.getMapX() > B_WIDTH + 32  ||
                    projectile.getMapY() < -32 || projectile.getMapY() > B_HEIGHT + 32 )
                        iterator.remove();
            }
        }
        
                
    }
    


}
