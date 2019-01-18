
package grade12cpt;


import static grade12cpt.Map.tile_size;
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
    private static int startX = 0, startY = 0;
    public static int playerX = 0, playerY = 0;
    public static final int walkDist = 7;
    private static final String enemyFile = "src/enemies";
    private static Player player;
    
    
    //loads all enemies and sets them up
    public static void LoadEnemies() {
        
        startX = Map.getMapXoffset();
        startY = Map.getMapYoffset();        
        
        // Loads enemies from each csv file in the enemies folder into an arrayList
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
                           
                           // Splits line into multiple values string (each column) 
                            String[] values = line.split(cvsSplitBy); 
                            
                            // if there is no arraylist for this line add it
                            try {
                                start.get(lineNum);
                            } catch (IndexOutOfBoundsException e) {
                                start.add(new ArrayList<>());
                                
                            }
                            
                            // Put the values of the line into the arraylist completing the rows and columns setup.
                            for (int i = 0; i != values.length; i++) {
                                
                                int key = Integer.parseInt(values[i]);
                                
                                try{
                                    if (start.get(lineNum).get(i) == -1 && key != -1)
                                        start.get(lineNum).set(i, key);
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
            
            // Loads enemies from eneies starting postion 2D Arraylist and sets up their walk pattern
            int enemy_id = 0;
            for (int x = 0; x != start.size(); x++) {
                for (int y = 0; y != start.get(x).size(); y++){
                    if (start.get(x).get(y) != -1) {
                        enemy_id++;
                        int walkX = 0;
                        int walkY = 0;
                        Enemy enemy = null;
                        boolean Ogre = true;
                        switch (start.get(x).get(y)) {
                            case 0:
                                Ogre = false;
                            case 5:
                                walkX = tile_size * walkDist;
                                break;
                            case 1:
                                Ogre = false;
                            case 6:
                                walkX = tile_size * -walkDist;
                                break;
                            case 2:
                                Ogre = false;
                            case 4:
                                walkY = tile_size * walkDist;
                                break;
                            case 3: 
                                Ogre = false;
                            case 7:
                                walkY = tile_size * -walkDist;
                                break;
                            default:
                                break;                            
                        }
                        
                        if (Ogre) enemy = new Enemy.Ogre(Integer.toString(enemy_id), walkX, walkY);
                        else enemy = new Enemy.Bat(Integer.toString(enemy_id), walkX, walkY);
                        
                        enemy.setMapX((tile_size * y) + startX);
                        enemy.setMapY((tile_size * x) + startY);
                        enemy.setLocation((tile_size * y) + startX, startY + (tile_size * x));
                        System.out.println(enemy.getLocation());
                        
                            addEnemy(enemy);
                    }
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
    
    
    // Checks if all enemies are above 0hp and removes all that are set to remove.
    public static void checkDamage() {
        for (Enemy x : allEnemies) {
            if (x.getHealth() < 0) toRemove.add(x);
        }
        
        for (Enemy removing : toRemove) {
            allEnemies.remove(removing);
        }
    }

    
    // Sets the initial offset of the enemy from 0,0
    public static void setOffset(int x_off, int y_off) {
        X_OFF = x_off;
        Y_OFF = y_off;
        
        
        allEnemies.forEach((enemy) -> {
           enemy.setMapX(enemy.getMapX() + x_off);
           enemy.setMapY(enemy.getMapY() + y_off);
        });
    }
    
    
    // Set the offset based on the player and pass the other two to the aboe method
    public static void setOffset(int X_OFF, int Y_OFF, double x, double y) {
        setOffset(X_OFF, Y_OFF);
        playerX = (int)x;
        playerY = (int)y;
        
    }
    
    public static void clear() {
        allEnemies.clear();
    }
    // If the currecnt offset is different then update all enemies current offset
    public static void updateEnemies() {

        allEnemies.forEach((enemy) -> {

            
            if (X_OFF != OLD_X) {
                OLD_X = X_OFF;
                enemy.setMapX(X_OFF + enemy.Xorigin + (int)enemy.movedX);                
            } 
            
            if (Y_OFF != OLD_Y) {
                OLD_Y = Y_OFF;
                enemy.setMapY(Y_OFF  + enemy.Yorigin + (int)enemy.movedY);
            }
            
            if (enemy.getMapX() > playerX - (5 * tile_size) && enemy.getMapX() < playerX + (5 * tile_size)
                    && enemy.getMapY() > playerY - (5 * tile_size) && enemy.getMapY() < playerY + (5 * tile_size)
                    && enemy instanceof Enemy.Bat) {
                            enemy.walk(playerX - enemy.getMapX(), playerY - enemy.getMapY(), false);
                            enemy.moved = true;
                            
            } else if(enemy.moved){
                //enemy.walk(enemy.getMapX(),enemy.getMapY(),false);
            }

            enemy.update();
            
            
        });
        
        // Check each enemy for collisions with projectiles removes projectiles and updates health if thers a collision
        for (Iterator<Enemy> enemyIter = allEnemies.iterator(); enemyIter.hasNext();) {
            Enemy enemy = enemyIter.next();
            for (Projectile projectile : ProjectileManager.allProjectiles) 
                if (projectile.intersects(enemy)) {
                    if (enemy.getHealth() <= 0){
                        System.out.println("Was Killed At: " + enemy.getLocation());
                        enemyIter.remove();
                    } else {
                        enemy.updateHealth(-15);
                    }
                    ProjectileManager.removeProjectile(projectile);
                    break;
                }

        }
        
        

    }
    
    // Get the enemy with the provided name
    public static Enemy getEnemy(String name) {
        for (Enemy enemy : allEnemies) 
            if (enemy.getName().equals(name)) 
                return enemy;
        
        throw new  IllegalArgumentException("That name does not exist as of now.... TIME TO CRASH!");
    }


}
