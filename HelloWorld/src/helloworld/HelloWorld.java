
package helloworld;

/**
 *
 * @author Alexander
 */
import java.util.Scanner;

public class HelloWorld {

    /**
     * @param args the command line arguments
     */

    public static void main(String[] args) {
        // TODO code application logic here
        Scanner scan = new Scanner(System.in);
        System.out.print("Your Name Is: ");
        String name = scan.nextLine();
        System.out.println("Hello, " + name);
    }

}
