
package print.a.cheque;

import java.util.ArrayList;

/**
 *
 * @author Alexander
 */

public class PrintACheque {

    /**
     * @param args the command line arguments
     */
    public static ArrayList<Character> breakdown = new ArrayList<>();
    public static byte cents = 0;
    public static final String[] digitName = {
        "", "one", "two", "three", "four", "five",
        "six", "seven", "eight", "nine", "ten",
        "eleven", "twelve", "thirteen", "fourteen",
        "fifteen", "sixteen", "eighteen", "nineteen" };
    
    

    public static void main(String[] args) {
        // TODO code application logic here
        
        String input = String.format("%.2f", 13.40);
        System.out.println(input);
        System.out.println(input.length());
        
        for (int i = 0; i != input.length(); i++ ) {
            breakdown.add((input.charAt(i)));
        }
        
        cents = Byte.parseByte(String.valueOf(breakdown.get(breakdown.size() - 1)));
        cents += Byte.parseByte(String.valueOf(breakdown.get(breakdown.size() - 2))) * 10;
        
        System.out.println(cents);
        
        for (int i = 0; i != 3; i++) breakdown.remove(breakdown.size() - 1);
       
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        for (int i = 0; i != 3; i++) breakdown.get(i);
        
        
       
        
        System.out.println(breakdown);
        
        
        
    
    }

}
