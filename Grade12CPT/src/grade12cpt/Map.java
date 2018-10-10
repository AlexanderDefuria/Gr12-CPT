/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
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
import javax.swing.ImageIcon;



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
        String line = "";
        String cvsSplitBy = ",";

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

    public BufferedImage display(Graphics g) {
        String imageFile = "src/images/desert_sprite.png";
        BufferedImage sprites = null;
        BufferedImage background = null;
        try {
            sprites = ImageIO.read(new File(imageFile));
        } catch (Exception e) {
            System.out.println(e);
        }
        
        
        for (short x = 0; (x * tile_size != B_WIDTH) && (x < map.size()); x++) {
            for (short y = 0; (y * tile_size != B_HEIGHT) && (y < map.get(0).size()); y++) {
                short tile_id = map.get(x).get(y);

                // background = sprites.getSubimage(x * (tile_size + 1), y * (tile_size + 1), tile_size, tile_size);
                 background = sprites.getSubimage(x , y , tile_size, tile_size);
                
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

