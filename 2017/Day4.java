
/**
 * Write a description of class Day4 here.
 *
 * @author (your name)
 * @version (a version number or a date)
 */

import java.util.*;
import java.io.*;

public class Day4
{
    public static void main(String args[]) throws IOException
    {
        File file = new File("input.txt");
        Scanner scn = new Scanner(file);
        
        int count = 0;
        
        while (scn.hasNextLine())
        {
            String data = scn.nextLine();
            Scanner dataScn1 = new Scanner(data);
            
            boolean good = true;
            
            while (dataScn1.hasNext())
            {
                String w1 = dataScn1.next();
                
                int num = 0;
                
                Scanner dataScn2 = new Scanner(data);
                while (dataScn2.hasNext())
                {
                    String w2 = dataScn2.next();
                    
                    if (StrValue(w1) == StrValue(w2))
                    {
                        num++;
                    }
                }
                
                if (num > 1) good = false;
            }
            
            
            if (good == true)
            {
                count++;
            }
        }
        
        System.out.println(count);
    }
    
    public static int StrValue(String s) // kinda like giveing each string a hashcode independent of char ordering in order to dettect annograms
    {
        int num = 1;
        
        for (int i = 0; i < s.length(); i++)
        {
            num *= s.charAt(i);
        }
        
        return num;
    }
}
