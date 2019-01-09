
package maps;

import grade12cpt.Map;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;

/**
 *
 * @author defur
 */

public class FindIDs {

    private static BufferedReader br = null;
    private static FileInputStream fis;
    private static String cvsSplitBy = ",";
    private static String line = "";
    
    public static ArrayList<Integer> ID = new ArrayList<>(); 

    public static void main(String[] args) {
        // TODO code application logic here
        File dir = new File("src/unpassablemaps");
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
                            
                            int low  = 0;
                            int high = ID.size();
                            int mid = (low + high) / 2;
                            
                            boolean placeFound = false;

                            for (int i = 0; i != values.length; i++) {
                   
                                int key = Integer.parseInt(values[i]);
                                boolean toPass = true;
                                
                                if (key < 0) break;
                            
                                for (Integer item : ID){
                                    if (item == key) {
                                        toPass = false;
                                        break;
                                    }
                                }
                                
                                if (toPass) ID.add(key);
                                
                                
                            }

                        }

                    } catch (FileNotFoundException ex) {
                        Logger.getLogger(Map.class.getName()).log(Level.SEVERE, null, ex);
                    } catch (IOException ex) {
                        Logger.getLogger(Map.class.getName()).log(Level.SEVERE, null, ex);
                    }
                    
                    
                }
                

                    
                System.out.println(ID);
            }
        }

}
