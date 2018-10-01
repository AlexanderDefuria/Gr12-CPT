/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package server;

import java.io.IOException;

/**
 *
 * @author Alexander
 */
public class Output {
    
    public static String nextOut = "..";
    public static String lastOut = "";
    
    public static void main(String args[]) {
        
        AcceptClients accept = new AcceptClients(1111);
        
        try {
            accept.accept();
        } catch (IOException e) {
            System.out.println(e);
        }
        
        while(true) {

            
            if (!nextOut.equals(lastOut)) {
                System.out.println("good");
                System.out.println(nextOut);
                lastOut = nextOut;
            }
            
            if (nextOut.equals("exit")) break;
            
        }
        
    }
}
