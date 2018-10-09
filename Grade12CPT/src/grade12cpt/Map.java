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

public class Map {
    
    public ArrayList<ArrayList<Short>> map = new ArrayList<>();
    
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

