
package grade12cpt;

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
import java.util.Hashtable;
import java.util.Set;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;



public class Map {
    
    public ArrayList<ArrayList<ArrayList<Integer>>> map = new ArrayList<>();
    public ArrayList<ArrayList<Integer>> maplayer = new ArrayList<>();
    public ArrayList<ArrayList<Integer>> finalmap = new ArrayList<>();
    public ArrayList<ArrayList<Boolean>> passable = new ArrayList<>();
    public ArrayList<BufferedImage> tile_image = new ArrayList<>();
    public ArrayList<Rectangle> terrain = new ArrayList<>();
    public ArrayList<Integer> terrain_id = new ArrayList<>();
    public Hashtable<Integer, BufferedImage> mapsprite = new Hashtable<Integer, BufferedImage>();
    
    public final int B_WIDTH = Board.B_WIDTH;
    public final int B_HEIGHT = Board.B_HEIGHT;
    public static final byte tile_size = 16;
    public static int MAP_Y, MAP_X = 0;
    public static int PIC_Y, PIC_X = 0;
    public static int tiledWidth, tiledHeight = 0;
    public static int mapWidth, mapHeight = 0;
    public static int mapCSVwidth, mapCSVheight = 0;
    public static Rectangle mapOutline;
    public static String mapFile = "src/maps/singleterraintest.csv";
    public static String goodmaps = "src/goodmaps";
    public static String unpassableMap = "src/unpassablemaps";
    public static String spriteFile = "src/maps/tilesheet.png";
    
    private static BufferedImage sprites;
    private static BufferedImage background;
    private static BufferedImage temp;

    private static BufferedReader br = null;
    private static FileInputStream fis;
    private static String cvsSplitBy = ",";
    private static String line = "";

    
    public Map(){
        init();
    }
    
    private void solveUnpassable() {
        File dir = new File(unpassableMap);
        File[] directoryListing = dir.listFiles();
            if (directoryListing != null) {                
                for (File child : directoryListing) {
                    try {
                        System.out.println(child);
                        fis = new FileInputStream(child);
                        br = new BufferedReader(new InputStreamReader(fis));
                        
                        while ((line = br.readLine()) != null) {
                            // use comma as separator
                            String[] values = line.split(cvsSplitBy); 
                            
                            for (int i = 0; i != values.length; i++) {
                                
                                int key = Integer.parseInt(values[i]);
                                boolean toPass = true;
                                
                                if (key < 0) break;
                            
                                for (Integer item : terrain_id){
                                    if (item == key) {
                                        toPass = false;
                                        break;
                                    }
                                }
                                
                                if (toPass) terrain_id.add(key);
                            }
                        }
                    } catch (FileNotFoundException ex) {
                        Logger.getLogger(Map.class.getName()).log(Level.SEVERE, null, ex);
                    } catch (IOException ex) {
                        Logger.getLogger(Map.class.getName()).log(Level.SEVERE, null, ex);
                    }
                    
                    
                }
            }   
    }
    
    private void init() {
        
        solveUnpassable();
        
        tiledWidth = (int)(B_WIDTH / tile_size);
        tiledHeight = (int)(B_HEIGHT/ tile_size);      

        background = new BufferedImage(B_WIDTH, B_HEIGHT, TYPE_3BYTE_BGR);
        
        File dir = new File(goodmaps);
        File[] directoryListing = dir.listFiles();
        if (directoryListing != null)   
        for (File child : directoryListing) {
            try {
                fis = new FileInputStream(child);
                br = new BufferedReader(new InputStreamReader(fis));
                sprites = ImageIO.read(new File(spriteFile));
                 
                int fileHeight = 0, fileWidth = 0; 
                
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
                    maplayer.add(listValues);
                    
                    fileWidth++;
                    fileHeight++;   
                    
                }
                
                mapCSVwidth = fileWidth;
                mapCSVheight = fileHeight;

                map.add(maplayer);

                mapOutline = new Rectangle(0 - tile_size , 0 - tile_size, 
                        map.get(0).size() * tile_size , map.size() * tile_size );
                
                
                
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
            
            
        }
        
        int newID = 0;
        int tile_row = sprites.getWidth()/(tile_size);

        for (int x = 0; x != mapCSVwidth + 1; x++) {
            finalmap.add(new ArrayList<>());
            for (int y = 0; y != mapCSVheight + 1; y++) {
                finalmap.get(x).add(0);
            }
        }
                
        for (ArrayList<ArrayList<Integer>> layer : map){
            int row = 0;
            for (ArrayList<Integer> x : layer){
                
                
                int index = 0;
                for (Integer y : x) {
                    
                    if (y != -1) {
                        int tile_id = y;
                        System.out.println("Index: " + index);
                        newID++;
                        

                        finalmap.get(row).set(index, newID);
          
                        
                        
                        int tile_x;
                        int tile_y;

                        // Find the x and y of the tile id in the sprite sheet
                        tile_y = (tile_id % tile_row);
                        tile_x = (int)(tile_id / tile_row);

                        //adjust sizing     //The + 1 is to account for initial margin offset
                        tile_x = tile_x * (tile_size);
                        tile_y = tile_y * (tile_size);
                        
                        
                        //Get the sprite from the sprite sheet based off the id's x and y
                        try {
                            temp = sprites.getSubimage(tile_y, tile_x, tile_size, tile_size);
                        } catch (Exception e) {
                            System.out.println("Y: " + y + "tile_Y: " + tile_y + "  Size: " + tile_size);
                            System.exit(0);
                        }

                        // Cycle Through existing images in the hashtable and if there is no identical create new tile ID
                        boolean createNew = true ;
                        int overWriteKey = 0;
                        Set<Integer> keys = mapsprite.keySet();
                        for (Integer key: keys){
                            if (temp.equals(mapsprite.get(key))){
                                finalmap.get(row).set(y, key);
                                createNew = true;
                                overWriteKey = key;
                                break;
                            }
                                
                        }
                        
                        
                        if (createNew) {
                            Graphics g = new BufferedImage(temp.getWidth(),temp.getHeight(), BufferedImage.TYPE_INT_ARGB).getGraphics();
                            g.drawImage(mapsprite.get(overWriteKey),0,0,null);
                            g.drawImage(temp,0,0,null);
                                
                            newID++;
                            finalmap.get(row).set(index, newID);
                            mapsprite.put(newID, background);
                        }
                    }
                    index++;
                }
                
                
                row++;
            }
        }
        
    }    
    
    // TODO FIx bug where player keeps scrolling after window is deslected
    // TODO Use overallX and overallY of the player to determine where the Terrain is instead of the movements
    public Image display(Graphics g, Player player) {
        terrain.clear();
        int X_OFF = player.getMoveX();
        switch (X_OFF + PIC_X) {
            case tile_size:
                PIC_X = 0;
                MAP_X--;
                break;
            case -tile_size:
                PIC_X = 0;
                MAP_X++;
                break;
            default:
                PIC_X += X_OFF;
                break;
        }
        
        int Y_OFF = player.getMoveY();
        switch (Y_OFF + PIC_Y) {
            case tile_size:
                PIC_Y = 0;
                MAP_Y--;
                break;
            case -tile_size:
                PIC_Y = 0;
                MAP_Y++;
                break;
            default:
                PIC_Y += Y_OFF;
                break;
        }
        
        EnemyManager.setOffset( getMapXoffset(), getMapYoffset());
        ProjectileManager.setOffset( getMapXoffset(), getMapYoffset());
        
        mapOutline.setLocation((int)mapOutline.getX() + X_OFF , (int)mapOutline.getY() + Y_OFF );
               

        for (int y = MAP_Y; y != tiledHeight + MAP_Y + 3; y++ ){
            for (int x = MAP_X; x != tiledWidth + MAP_X + 3; x++) {
                int tile_id = 0;
                try {

                    if (tile_id < 0) tile_id = 38;
                } catch (Exception e) {
                    tile_id = 38;
                }

                //Get the sprite from the sprite sheet based off the id's x and y
                temp = mapsprite.get(tile_id);

                // Create the tile image in the final background image with appropriate offset
                background.createGraphics().drawImage(temp, tile_size * (x - MAP_X - 1) + PIC_X, 
                       tile_size * (y - MAP_Y - 1) + PIC_Y, null);


                if (terrain_id.contains(tile_id)) {

                    terrain.add(new SolidTerrain(tile_id, tile_size * (x - MAP_X - 1) + PIC_X, 
                        tile_size * (y - MAP_Y - 1) + PIC_Y));

                }
            }

        }
        
        // Display enemies in proper location
        for (Enemy enemy : EnemyManager.getEnemies()) {
           if (    enemy.getMapX() > -50 && enemy.getMapX() < B_WIDTH + 50
                && enemy.getMapY() > -50 && enemy.getMapY() < B_HEIGHT + 50  ) {
                        background.createGraphics().drawImage(enemy.appearance, enemy.getMapX(), enemy.getMapY(), null);
           }         
        }
        
        // Display projectile in proper location
        for (Projectile projectile : ProjectileManager.getProjectiles()) {
           if (    projectile.getMapX() > -50 && projectile.getMapX() < B_WIDTH + 50
                && projectile.getMapY() > -50 && projectile.getMapY() < B_HEIGHT + 50  ) {
                        background.createGraphics().drawImage(projectile.appearance, projectile.getMapX(), projectile.getMapY(), null);
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
    
    public static String getMapFile() {
        return mapFile;
    }
    
    public static String getSpriteFile() {
        return spriteFile;
    }
    
    public static int getMapXoffset() {
        return (-MAP_X * 32) + PIC_X;
    }
    public static int getMapYoffset() {
        return (-MAP_Y * 32) + PIC_Y;
    }
    

    
}

