/*------------------------------------------------------------------------------
 *
 * Alexander De Furia
 * Mrs. Altobelli
 * ICS3U1b
 *  - insert date here - 
 *  - insert description here ie. program functions -
 *                              
*-----------------------------------------------------------------------------*/


package server;

/**
 *
 * @author Alexander
 */

import java.io.*;
import java.net.*;


public class AcceptClients {

    private final Socket socket   = null;
    private final ServerSocket server = null;
    private final DataInputStream in = null;
    private final DataOutputStream out = null;
    public static Socket s;
    public static ServerSocket ss;

    public AcceptClients(int port) {
        try {
            ss = new ServerSocket(port);
        }catch (IOException e){
            System.out.println(e);
        }
    }

    public static void accept() throws IOException{
        // TODO code application logic here
        try {
            s = ss.accept();

            Output.nextOut = ("A new client is connected: " + s.getRemoteSocketAddress());

            DataInputStream dis = new DataInputStream(s.getInputStream());
            DataOutputStream dos = new DataOutputStream(s.getOutputStream());

            Output.nextOut = ("Assigning new thread for this client.");

            Thread t = new ClientHandler(s, dis, dos);

            t.start();

            Output.nextOut = ("Started");

            
        } catch (Exception e) {
            System.out.println("Connection failed: ");
        }
    }

}



