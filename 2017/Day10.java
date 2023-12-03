
/**
 * Write a description of class Day10 here.
 *
 * @author (your name)
 * @version (a version number or a date)
 */

import java.util.*;
import java.io.*;

public class Day10
{
    public static void main(String args[]) throws IOException
    {
        File file = new File("input.txt");
        
        
        int[] hash = new int[256];
        
        for (int i = 0; i < hash.length; i++)
        {
            hash[i] = i;
        }
        
        
        int pos = 0;
        int skip = 0;
        
        for (int r = 0; r < 64; r++)
        {
            Scanner scn = new Scanner(file);
            scn.useDelimiter(",");
            while (scn.hasNext())
            {
                int len = scn.nextInt() + 48;
                
                for(int i = 0; i < len / 2; i++)
                {
                    int temp = hash[(pos + i) % 256];
                    hash[(pos + i) % 256] = hash[(pos + len - 1 - i) % 256];
                    hash[(pos + len - 1 - i) % 256] = temp;
                }
                
                pos += len + skip;
                
                skip++;
            }
        }
        
        // stuff that works ends here... i think
        
        int[] split = new int[16];
        int[] dense = new int[16];
        for (int i = 0; i < hash.length; i++)
        {
            split[i % 16] = hash[i];
            
            if (i % 16 == 15)
            {
                int d = split[0];
                for (int s = 1; s < split.length; s++)
                {
                    d = d ^ split[s];
                }
                
                dense[i / 16] = d;
            }
        }
        
        
        for (int n : dense)
        {
            System.out.println(n);
        }
        
        //System.out.println(hash[0] + " " + hash[1]);
    }
}
