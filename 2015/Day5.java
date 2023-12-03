
/**
 * Write a description of class Day5 here.
 *
 * @author (your name)
 * @version (a version number or a date)
 */

import java.util.*;
import java.io.*;

public class Day5
{
    public static void main(String args[]) throws IOException
    {
        File file = new File("input.txt");
        Scanner scn = new Scanner(file);
        
        int count = 0;
        
        while (scn.hasNext())
        {
            String data = scn.nextLine();
                        
            boolean twiceRow = false;
            boolean triple = false;
            
            for (int i = 0; i < data.length(); i++)
            {
                if (i < data.length() - 2)
                {
                    if (data.substring(i + 2).contains(data.substring(i, i + 2)))
                    {
                        twiceRow = true;
                    }
                    
                    if (data.charAt(i) == data.charAt(i + 2))
                    {
                        triple = true;
                    }
                }
            }
            
            if (twiceRow && triple)
            {
                count++;
                
                System.out.println(data);
            }
        }
        
        System.out.println(count);
    }
}
