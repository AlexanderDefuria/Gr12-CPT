
package grade12cpt;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;

import java.awt.Graphics;
import java.awt.Image;
import java.awt.image.BufferedImage;
import static java.awt.image.BufferedImage.TYPE_3BYTE_BGR;
import java.io.File;
import javax.imageio.ImageIO;



public class Map {
    
    public ArrayList<ArrayList<Short>> map = new ArrayList<>();
    public final int B_WIDTH = Board.B_WIDTH;
    public final int B_HEIGHT = Board.B_HEIGHT;
    public final byte tile_size = 32;
    
    
    public Map(String file){
        init(file);
    }
    
    private void init(String file) {
        BufferedReader br = null;
        FileInputStream fis = null;
        String cvsSplitBy = ",";
        String line = "";


        try {
            
            fis = new FileInputStream(file);
            br = new BufferedReader(new InputStreamReader(fis));

            
            while ((line = br.readLine()) != null) {
                // use comma as separator
                String[] values = line.split(cvsSplitBy);
                ArrayList<Short> listValues= new ArrayList<>();
                
                //
                for (int i = 0; i != values.length; i++) {
                    listValues.add(Short.parseShort(values[i]));
                }
                
                map.add(listValues);
            }

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

    
    public Image display(Graphics g, int X_OFF) {
        if (X_OFF < 0) X_OFF *= -1;
        String imageFile = "src/images/desert_sprite.png";
        BufferedImage background = new BufferedImage(B_WIDTH + X_OFF, B_HEIGHT, TYPE_3BYTE_BGR);
        BufferedImage sprites = null;
        BufferedImage temp;
        try {
            sprites = ImageIO.read(new File(imageFile));
        } catch (Exception e) {
            System.out.println(e);
        }       

        int tile_row = sprites.getWidth()/(tile_size + 1);
        
        // TODO change short x = 0 to x = players current y; This should allow for moving about
        for (short x = 0;x < map.size(); x++) {
            for (short y = 0; y < map.get(0).size(); y++) {
                short tile_id = map.get(x).get(y);
                int tile_x;
                int tile_y;
                
                // Find the x and y of the tile id in the sprite sheet
                tile_x = (tile_id % tile_row);
                tile_y = (int)(tile_id / tile_row);
                
                //adjust sizing     //The + 1 is to account for initial margin offset
                tile_x = tile_x * (tile_size + 1) + 1;
                tile_y = tile_y * (tile_size + 1) + 1;
                
                //Get the sprite from the sprite sheet based off the id's x and y
                temp = sprites.getSubimage(tile_x, tile_y, tile_size, tile_size);
                
                // Create the tile image in the final background image with appropriate offset
                background.createGraphics().drawImage(temp, tile_size * y, tile_size * x, null);
            }
        }
        
        return background;
    }
            
            
            
            
    // TODO Remove after testing maps
    public void publish() {
        for (int x = 0; x != map.size(); x++){
            for (int y = 0; y != map.size(); y++){
                System.out.print(map.get(x).get(y) + " ");
            }
            System.out.println("");
        }
        
    }
}

