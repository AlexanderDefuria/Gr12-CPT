

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
    public static String output = "";
    public static final String[] digitName = {
        "", "one", "two", "three", "four", "five",
        "six", "seven", "eight", "nine", "ten",
        "eleven", "twelve", "thirteen", "fourteen",
        "fifteen", "sixteen", "eighteen", "nineteen" };
    public static final String[] tensName = {
        "", "twenty", "thirty", "forty", "fifty",
        "sixty", "seventy", "eighty", "ninety" };

    

    public static void main(String[] args) {
        // TODO code application logic here
        
        String input = String.format("%.2f", 123456.40);
        System.out.println("Input: " + input);
        System.out.println("Input length: " + input.length());
        
        for (int i = 0; i != input.length(); i++ ) {
            breakdown.add((input.charAt(i)));
        }
        
        cents = Byte.parseByte(String.valueOf(breakdown.get(breakdown.size() - 1)));
        cents += Byte.parseByte(String.valueOf(breakdown.get(breakdown.size() - 2))) * 10;
        
        System.out.println("cents: " + cents);
        
        for (int i = 0; i != 3; i++) breakdown.remove(breakdown.size() - 1);
        

        
        for (int i = 0; i != breakdown.size(); i++){
            int cur = Integer.parseInt(String.valueOf(breakdown.get(i)));
            int position = breakdown.size() - i;
            
            output += 
            
            
            
            
            
        }
                   
        if (breakdown.size() >= 3) {
            
//                output += digitName[Integer.parseInt(String.valueOf(breakdown.get(0)))];
//                output += tensName[Integer.parseInt(String.valueOf(breakdown.get(0)))];

            System.out.println(output);
        
        }
        
        
  
        
        
        
       
        
        System.out.println(breakdown);
        
        
        
    
    }
    

}
