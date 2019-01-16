
package grade12cpt;


import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Alexander
 */

public abstract class EnemyManager {

    public static ArrayList<Enemy> allEnemies = new ArrayList<>();
    public static ArrayList<ArrayList<Integer>> start = new ArrayList<>();
    public static ArrayList<Enemy> visibleEnemies = new ArrayList<>();
    public static ArrayList<Enemy> toRemove = new ArrayList<>();
    private static int OLD_X = 0, OLD_Y = 0;
    private static int X_OFF = 0, Y_OFF = 0;
    private static final String enemyFile = "src/enemies";
    
    
    public static void loadEnemies() {
        BufferedReader br;
        FileInputStream fis;
        String cvsSplitBy = ",";
        String line;
        File dir = new File(enemyFile);
        File[] directoryListing = dir.listFiles();
            if (directoryListing != null)                 
                for (File child : directoryListing) {
                    try {
                        
                        System.out.println(child);
                        fis = new FileInputStream(child);
                        br = new BufferedReader(new InputStreamReader(fis));
                        
                        int lineNum = 0; 
                        
                        while ((line = br.readLine()) != null) {
                            // use comma as separator\
                            
                            String[] values = line.split(cvsSplitBy); 
                            
                            try {
                                start.get(lineNum);
                            } catch (IndexOutOfBoundsException e) {
                                start.add(new ArrayList<>());
                                
                            }
                            
                            for (int i = 0; i != values.length; i++) {
                                
                                int key = Integer.parseInt(values[i]);
                                
                                
                                try{
                                    if (start.get(lineNum).get(i) == -1 && key != -1)
                                        start.get(lineNum).set(i, 1);
                                } catch (IndexOutOfBoundsException e){
                                    start.get(lineNum).add(key);
                                    
                                }
   
                            }
                            
                            lineNum++;
                        }
                    } catch (FileNotFoundException ex) {
                        Logger.getLogger(Map.class.getName()).log(Level.SEVERE, null, ex);
                    } catch (IOException ex) {
                        Logger.getLogger(Map.class.getName()).log(Level.SEVERE, null, ex);
                    }
                    
                    
                }
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
    
    public static void clear() {
        allEnemies.clear();
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
            
            
        });
        
        for (Iterator<Enemy> enemyIter = allEnemies.iterator(); enemyIter.hasNext();) {
            Enemy enemy = enemyIter.next();
            for (Projectile projectile : ProjectileManager.allProjectiles) 
                if (projectile.intersects(enemy)) {
                    enemyIter.remove();
                    ProjectileManager.removeProjectile(projectile);
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
