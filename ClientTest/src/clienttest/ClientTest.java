/*------------------------------------------------------------------------------
 *
 * Alexander De Furia
 * Mrs. Altobelli
 * ICS3U1b
 *  - insert date here - 
 *  - insert description here ie. program functions -
 *                              
*-----------------------------------------------------------------------------*/


package clienttest;


// A Java program for a Client
import java.net.*;
import java.io.*;
import java.util.Scanner;
 

public class ClientTest {
    
    private static Socket socket = null;
    private DataInputStream  input = null;
    private DataOutputStream out = null;
    private static String address;
    private static int port;
    
    public static String toSend = "";
    public static  String lastSent = "";


public ClientTest(String giveaddress, int giveport){
        address = giveaddress;
        port = giveport;   
    }
       

    public static void exchange(){
        try {
            Scanner scan = new Scanner(System.in);
            

            // establish the connection with server port 1111
            Socket s = new Socket(address, port);
     
            // obtaining input and out streams
            DataInputStream dis = new DataInputStream(s.getInputStream());
            DataOutputStream dos = new DataOutputStream(s.getOutputStream());
    
  
            while (true) {
                
                if(!toSend.equals(lastSent)){
                    System.out.println(toSend);
                    dos.writeUTF(toSend);
                    lastSent = toSend;
                    
                }
                if (toSend.equalsIgnoreCase("exit")) break;
            }
             
            // closing resources
            scan.close();
            dis.close();
            dos.close();
        }catch(Exception e){
            e.printStackTrace();
        }
    }
    

    public static void main(String args[])
    {
        
        try {
            InetAddress inetAddress = InetAddress.getLocalHost();
            ClientTest client = new ClientTest(inetAddress.toString().split("/")[1], 1111);
            client.exchange();
        } catch (Exception e) {
            System.out.println("Not on the proper network.");
            System.exit(0);
        }

    }

}
