
/**
 * Write a description of class Day11 here.
 *
 * @author (your name)
 * @version (a version number or a date)
 */

import java.util.*;
import java.io.*;

public class Day11
{
    public static void main(String[] args) throws IOException
    {
        int maxDist = 0; // for PART 2
        
        
        int d1 = 0; // north to south
        int d2 = 0; // NE
        int d3 = 0; // NW
        
        
        File file = new File("input.txt");
        Scanner scn = new Scanner(file);
        scn.useDelimiter(",");
        while (scn.hasNext()) // read all data into d1, d2, d3 (add for all 3 directions of a hexagon)
        {
            String data = scn.next();
            
            if (data.equals("n"))
            {
                d1++;
            }
            else if (data.equals("s"))
            {
                d1--;
            }
            else if (data.equals("ne"))
            {
                d2++;
            }
            else if (data.equals("sw"))
            {
                d2--;
            }
            else if (data.equals("nw"))
            {
                d3++;
            }
            else if (data.equals("se"))
            {
                d3--;
            }
            else System.out.println("This should not print: " + data);
            
            int num = distanceCheck(d1, d2, d3);
            if (num > maxDist)
            {
                maxDist = num;
            }
        }
        
        System.out.println("Part 1 Answer: " + distanceCheck(d1, d2, d3));
        System.out.println("Part 2 Answer: " + maxDist);
    }
    
    public static int distanceCheck(int d1, int d2, int d3)
    {
        
        int dir1 = 0;
        int dir2 = 0;
        int dir3 = 0;
        
        if (Math.signum(d2) == Math.signum(d3))
        {
            dir1 = d1;
            dir2 = d2;
            dir3 = d3;
        }
        else if (Math.signum(d1) != Math.signum(d2))
        {
            dir1 = d3;
            dir2 = d1;
            dir3 = d2;
            
            dir3 *= -1;
        }
        else if (Math.signum(d1) != Math.signum(d3))
        {
            dir1 = d2;
            dir2 = d1;
            dir3 = d3;
            
            dir3 *= -1;
        }
        else
        {
            System.out.println("problem center located here!");
        }
        
        
        
        if((dir2 > 0 && dir3 > 0) || (dir2 < 0 && dir3 < 0))
        {
            if (Math.abs(dir2) < Math.abs(dir3))
            {
                dir1 += dir2;
                dir3 -= dir2;
                dir2 = 0;
            }
            else
            {
                dir1 += dir3;
                dir2 -= dir3;
                dir3 = 0;
            }
        }
        else
        {
            System.out.println("This should not print");
        }
        
        
        int count = Math.abs(dir1) + Math.abs(dir2) + Math.abs(dir3);
        
        return count;
    }
}
