
/**
 * Write a description of class Day3 here.
 *
 * @author (your name)
 * @version (a version number or a date)
 */

import java.util.*;
import java.io.*;

public class Day3
{
    public static void main(String args[])
    {
        int[][] map = new int[100][100];
        
        int mid = map.length / 2;
        
        
        map[mid][mid] = 1;
        
        int len = 1;
        
        int dir = 0;
        
        int x = mid;
        int y = mid;
        
        int count = 0;
        
        
        int value = 1;
        
        while (value < 312051) // 312051 is puzzel input
        {
            int xAdj = 0;
            int yAdj = 0;
            
            if (dir == 0)
            {
                xAdj = 1;
            }
            else if (dir == 1)
            {
                yAdj = 1;
            }
            else if (dir == 2)
            {
                xAdj = -1;
            }
            else if (dir == 3)
            {
                yAdj = -1;
            }
            
            x += xAdj;
            y += yAdj;
            
            count++;
            if (count == len)
            {
                dir = (dir + 1) % 4;
                if (yAdj != 0) len++;
                count = 0;
            }
            
            map[x][y] = map[x + 1][y] + map[x + 1][y + 1] + map[x][y + 1] + map[x - 1][y + 1] + map[x - 1][y] + map[x - 1][y - 1] + map[x][y - 1] + map[x + 1][y - 1];
            
            value = map[x][y];
            
            
            
            //System.out.println(map[x][y]);
            //System.out.println(x - mid + " " + (y - mid));
            //System.out.println();
        }
        
        System.out.println(value);
    }
}
