
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
        
        while (scn.hasNext())
        {
            String data = scn.nextLine();
            Scanner dataScn = new Scanner(data);
            dataScn.useDelimiter("x");
            
            int x = dataScn.nextInt();
            int y = dataScn.nextInt();
            int z = dataScn.nextInt();
            
            
            int extra = x + y + z;
            
            count += x * y * z;
            
            if (z > x && z > y)
            {
                extra -= z;
            }
            else
            {
                if (x > y)
                {
                    extra -= x;
                }
                else
                {
                    extra -= y;
                }
            }
            
            count += 2 * extra;
        }
        
        System.out.println(count);
    }
}
