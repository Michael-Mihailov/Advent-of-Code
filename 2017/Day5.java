
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
        
        int[] map = new int[1070];
        
        for (int i = 0; i < map.length; i++)
        {
            map[i] = scn.nextInt();
        }
        
        int count = 0;
        int pos = 0;
        
        while (pos < 1070)
        {
            if (map[pos] >= 3)
            {    
                map[pos]--;
                pos += map[pos] + 1;
            }
            else
            {
                map[pos]++;
                pos += map[pos] - 1;
            }
            
            count++;
        }
        
        System.out.println(count);
    }
}
