
/**
 * Write a description of class Day1 here.
 *
 * @author (your name)
 * @version (a version number or a date)
 */

import java.io.*;
import java.util.Scanner;

public class Day5 
{
    public static void main (String args[]) throws Exception
    {
        File file = new File("input.txt");
        Scanner scn = new Scanner(file);
        
        
        int[][] map = new int[1000][1000];  
        
        while (scn.hasNext())
        {
            String data = scn.nextLine();
            
            Scanner split = new Scanner(data);
            split.useDelimiter(" -> ");
            
            String left = split.next();
            String right = split.next();

            Scanner cut1 = new Scanner(left);
            Scanner cut2 = new Scanner(right);
            cut1.useDelimiter(",");
            cut2.useDelimiter(",");
            
            int x1 = cut1.nextInt();
            int y1 = cut1.nextInt();
            int x2 = cut2.nextInt();
            int y2 = cut2.nextInt();
            
            map = reDraw(map, x1, y1, x2, y2);
            
            /*
            for (int[] row : map)
            {
                System.out.println();
                for (int point : row)
                {
                    System.out.print(point);
                }
            }
            
            new Scanner(System.in).nextLine();
            */
        }
        
        int count = 0;
        
        for (int[] row : map)
        {
            System.out.println();
            for (int point : row)
            {
                System.out.print(point);
                if (point > 1)
                {
                    count++;
                }
            }
        }
        
        System.out.println(count);
    }
    
    public static int[][] reDraw(int[][] map, int x1, int y1, int x2, int y2)
    {
        if (x1 == x2 || y1 == y2 || Math.abs(x1 - x2) == Math.abs(y1 - y2))
        {
            for (int h = 0; h <= 1000; h++)
             {
                 for (int w = 0; w <= 1000; w++)
                 {
                     if (((h >= x1 && h <= x2) || (h <= x1 && h >= x2)) && ((w >= y1 && w <= y2) || (w <= y1 && w >= y2)))
                     {
                         if(Math.abs(x1 - x2) == Math.abs(y1 - y2))
                         {
                             if((Math.abs(x1 - h) == Math.abs(y1 - w)))
                             {
                                 map[h][w] += 1;
                             }
                         }
                         else
                         {
                             map[h][w] += 1;
                         }
                     }
                 }
             }
        }
        
        return map;
    }
}
