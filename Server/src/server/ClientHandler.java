

package server;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.text.SimpleDateFormat;
import java.text.DateFormat;

 
// ClientHandler class
class ClientHandler extends Thread 
{
    DateFormat fordate = new SimpleDateFormat("yyyy/MM/dd");
    DateFormat fortime = new SimpleDateFormat("hh:mm:ss");
    static DataInputStream dis;
    static DataOutputStream dos;
    final Socket s;
    public static int x = 5;
     
 
    // Constructor
    public ClientHandler(Socket s, DataInputStream dis, DataOutputStream dos) 
    {
        this.s = s;
        this.dis = dis;
        this.dos = dos;
    }
 

    public void actions() {
        
        String received;
        
        while (true) {
            try {
                  
                // receive the answer from client
                received = dis.readUTF();
                 
                if(received.equalsIgnoreCase("exit")) { 
                    System.out.println("Client " + this.s + " sends exit...");
                    System.out.println("Closing this connection.");
                    this.s.close();
                    System.out.println("Connection closed");
                    break;
                } else
                
                Output.nextOut = received;
                 
            } catch (IOException e) {
                received = "exit";
                System.out.println(e);
            }
        }
         
        try {
            // closing resources
            this.dis.close();
            this.dos.close();
             
        }catch(IOException e){
            e.printStackTrace();
        }
    }
    
    
    public static void read() {
        String input;
        
        try {
            input = dis.readUTF();
        } catch (Exception e){
            System.out.println(e);
        }
    }
    
    
    public void alternate(int iterations) {
        
    }

}
