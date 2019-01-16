
package grade12cpt;


import java.awt.Color;
import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;

import java.awt.Graphics;
import java.awt.Image;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import static java.awt.image.BufferedImage.TYPE_3BYTE_BGR;
import java.io.File;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;

// TODO remove blockage from that one tree in the lava canyon that was removed in the image
// TODO add teleportation into the hosues
// TODO Create Enemy Movepaths

public class Map {
    
    public ArrayList<ArrayList<Integer>> map = new ArrayList<>();
    public ArrayList<ArrayList<Integer>> start = new ArrayList<>();
    public ArrayList<Rectangle> terrain = new ArrayList<>();
    public ArrayList<Integer> terrain_id = new ArrayList<>();
    public final int B_WIDTH = Board.B_WIDTH;
    public final int B_HEIGHT = Board.B_HEIGHT;
    public static final byte tile_size = 16;
    public static int MAP_Y = 0;
    public static int MAP_X = 0;
    public static int ENEMY_Y = 0; 
    public static int ENEMY_X = 0;
    public static int IMG_Y = -4300, IMG_X = 50; // Adjust This to fit different starting locations;
    public static int X_OFF = 0, Y_OFF = 0;
    public static int tiledWidth, tiledHeight = 0;
    public static int mapWidth, mapHeight = 0;
    public static Rectangle mapOutline;
    public static final String spriteFile = "src/images/desert_sprite.png";
    public static final String MapImageFile = "src/images/map.png";
    public static final String unpassableMap = "src/unpassable";
    public static final String enemyFile = "src/enemies";
    private static BufferedImage background;
    private static BufferedImage mapBack;
    
    public Map(){
        init();
    }
    
    private void init() {
        BufferedReader br = null;
        FileInputStream fis;
        String cvsSplitBy = ",";
        String line = "";
        tiledWidth = (int)(B_WIDTH / tile_size);
        tiledHeight = (int)(B_HEIGHT/ tile_size);
        
        
        
        File dir = new File(unpassableMap);
        File[] directoryListing = dir.listFiles();
            if (directoryListing != null) {                
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
                                map.get(lineNum);
                            } catch (IndexOutOfBoundsException e) {
                                map.add(new ArrayList<>());
                                
                            }
                            
                            for (int i = 0; i != values.length; i++) {
                                
                                int key = Integer.parseInt(values[i]);
                                
                                
                                try{
                                    if (map.get(lineNum).get(i) == -1 && key != -1)
                                        map.get(lineNum).set(i, 1);
                                } catch (IndexOutOfBoundsException e){
                                    map.get(lineNum).add(key);
                                    
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
       

        
        background = new BufferedImage(B_WIDTH, B_HEIGHT, TYPE_3BYTE_BGR);
        
        try {
            
            mapBack = ImageIO.read(new File(MapImageFile));

            // Read the tile id's into map ArrayList
            while ((line = br.readLine()) != null) {
                // use comma as separator
                String[] values = line.split(cvsSplitBy);
                ArrayList<Integer> listValues= new ArrayList<>();
                
                // Create a list of the tile id's 
                for (int i = 0; i != values.length; i++) {
                    listValues.add(Integer.parseInt(values[i]));
                }
                // Add the list created above to the map ArrayList
                map.add(listValues);
            }
            
            mapOutline = new Rectangle(0 , 0 , 
                    map.size() * tile_size , map.get(0).size() * tile_size );

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (br != null) {
                try {
                    br.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }    
    
    
    // TODO FIx bug where player keeps scrolling after window is deslected
    // TODO Use overallX and overallY of the player to determine where the Terrain is instead of the movements
    public Image display(Graphics g, Player player) {
        terrain.clear();
        X_OFF = player.getMoveX();
        Y_OFF = player.getMoveY();
        
        IMG_Y += Y_OFF;
        IMG_X += X_OFF;
        
        ENEMY_Y += Y_OFF;
        ENEMY_X += X_OFF;
        
        
        
        
        
        EnemyManager.setOffset( X_OFF, Y_OFF);
        ProjectileManager.setOffset( getMapXoffset(), getMapYoffset());
        

        mapOutline.setLocation((int)mapOutline.getX() + X_OFF , (int)mapOutline.getY() + Y_OFF );
        
        g = background.createGraphics();
        g.setColor(Color.BLACK);
        g.fillRect(0, 0, mapBack.getWidth() * 2, mapBack.getHeight() * 2);
        g.drawImage(mapBack, IMG_X + X_OFF, IMG_Y + Y_OFF, null);
        
        
        for (int x = 0; x != map.size(); x++) {
            for (int y = 0; y != map.get(x).size(); y++){
                if (map.get(x).get(y) != -1) {
                    
                    terrain.add(new SolidTerrain((tile_size * y) + IMG_X + X_OFF, Y_OFF + IMG_Y + (tile_size * x)));
                    
                }
                
            }
        } 
        
       
        
        // Display enemies in proper location
        for (Enemy enemy : EnemyManager.getEnemies()) {
           if (    enemy.getMapX() > -50 && enemy.getMapX() < B_WIDTH + 50
                && enemy.getMapY() > -50 && enemy.getMapY() < B_HEIGHT + 50  ) {
                        enemy.animate();
                        g.drawImage(enemy.appearance, enemy.getMapX(), enemy.getMapY(), null);
           }         
        }
        
        // Display projectile in proper location
        for (Projectile projectile : ProjectileManager.getProjectiles()) {
           if (    projectile.getMapX() > -50 && projectile.getMapX() < B_WIDTH + 50
                && projectile.getMapY() > -50 && projectile.getMapY() < B_HEIGHT + 50  ) {
                        g.drawImage(projectile.appearance, projectile.getMapX(), projectile.getMapY(), null);
           }       
        }

        
        
        return background;
    }
 

    public Rectangle getMapOutline() {
        return mapOutline;
    }
    
    public ArrayList<Rectangle> getSolidTerrain() {
        return terrain;
    }
    
    public static String getSpriteFile() {
        return spriteFile;
    }
    
    public static int getMapXoffset() {
        return IMG_X;
    }
    public static int getMapYoffset() {
        return IMG_Y;
    }
    
    
}

