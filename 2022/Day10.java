
/**
 * Write a description of class Day1 here.
 *
 * @author (your name)
 * @version (a version number or a date)
 */

import java.io.*;
import java.util.*;

public class Day10
{
    public static void main (String[] args) throws Exception
    {
        File file = new File("input.txt");
        Scanner scn = new Scanner(file);
        
        int count = 0;
        
        int x = 1;
        int cycle = 0;
        
        int[] map = new int[240];
        
        while (scn.hasNext())
        {
            //cycle++;
            
            String data = scn.nextLine();
            Scanner splitScn = new Scanner(data);
            
            String left;
            int right;
            left = splitScn.next();
            
            /*
            if ((cycle % 40) - 20 == 0 & cycle < 222)
            {
                count += x * cycle;
            }
            if (cycle / 40 == 0)
            {
                //System.out.println();
            }
            */
            if (Math.abs(x - (cycle %  40)) <= 1)
            {
                map[cycle] = 1;
            }
            cycle++;
            
            if (left.equals("addx"))
            {
                right = splitScn.nextInt();
                //cycle++;
                
                /*
                if ((cycle % 40) - 20 == 0 & cycle < 222)
                {
                    count += x * cycle;
                }
                if (cycle / 40 == 0)
                {
                    //System.out.println();
                }
                */
                if (Math.abs(x - (cycle %  40)) <= 1)
                {
                    map[cycle] = 1;
                }
                cycle++;
                
                x += right;
            }
            //System.out.println(x);
        }
        
        //System.out.println(count);
        
        for (int i = 0; i < 240; i++)
        {
            if (map[i] == 1)
            {
                System.out.print("#");
            }
            else
            {
                System.out.print(".");
            }
            
            if (i % 40 == 0)
            {
                System.out.println();
            }
        }
    }
}
