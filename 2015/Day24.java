
/**
 * Write a description of class CopyOfDay_Default here.
 *
 * @author (your name)
 * @version (a version number or a date)
 */

import java.util.*;
import java.io.*;

public class Day24
{
    public static long min = Long.MAX_VALUE;
    
    public static void main(String args[]) throws IOException
    {
        File file = new File("input.txt");
        Scanner scn = new Scanner(file);
        
        int[] stuff = new int[28];
        int total = 0; // 1548
        
        for (int i = 0; i < 28; i++)
        {
            stuff[i] = scn.nextInt();
            total += stuff[i];
        }
        
        int idealSize = total / 4; // 516 for div 3, 387 for div 4
        
        findGroups(stuff , 0, 0, 0, 1);
        
        System.out.println(min);
    }
    
    public static void findGroups(int[] stuff, int pos, int w, int num, long qe)
    {        
        if (w == 387 && num < 6)
        {
            System.out.println(num + " " + qe);
            if (qe < min)
            {
                min = qe;
            }
        }
        else if (w > 387)
        {
            return;
        }
        
        if (pos < 28)
        {
            findGroups(stuff, pos + 1, w + stuff[pos], num + 1, qe * stuff[pos]);
        
            findGroups(stuff, pos + 1, w, num, qe);
        }
    }
}
