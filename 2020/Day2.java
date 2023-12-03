
/**
 * Write a description of class Day2 here.
 *
 * @author (your name)
 * @version (a version number or a date)
 */

import java.util.*;
import java.io.*;

public class Day2
{
    public static void main(String args[]) throws IOException
    {
        File file = new File("input.txt");
        Scanner scn = new Scanner(file);
        
        int count = 0;
        while (scn.hasNextLine())
        {
            String data = scn.nextLine();
            Scanner splitScn = new Scanner(data);
            
            String numStr = splitScn.next();
            String charStr = splitScn.next();
            String dataStr = splitScn.next();
            
            int n1 = Integer.valueOf(numStr.substring(0, numStr.indexOf("-"))); n1--;
            int n2 = Integer.valueOf(numStr.substring(numStr.indexOf("-") + 1)); n2--;
                        
            char c = charStr.charAt(0);
                        
            
            if ((dataStr.charAt(n1) == c || dataStr.charAt(n2) == c) && dataStr.charAt(n1) != dataStr.charAt(n2))
            {
                count++;
            }
        }
        
        System.out.println(count);
    }
}
