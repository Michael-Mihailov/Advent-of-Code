
/**
 * Write a description of class Day6 here.
 *
 * @author (your name)
 * @version (a version number or a date)
 */

import java.util.*;
import java.io.*;

public class Day6
{
    public static void main(String args[]) throws IOException
    {
        File file = new File("input.txt");
        Scanner scn = new Scanner(file);
        
        int[][] map = new int[1000][1000];
        
        while (scn.hasNext())
        {
            String data = scn.nextLine();
            Scanner dataScn = new Scanner(data);
            
            
            int x1;
            int y1;
            
            int x2;
            int y2;
            
            if (dataScn.next().equals("turn"))
                dataScn.next();
            
            String c1 = dataScn.next();
            
            dataScn.next();
            
            String c2 = dataScn.next();
            
            Scanner splitScn = new Scanner(c1);
            splitScn.useDelimiter(",");
            
            x1 = splitScn.nextInt();
            y1 = splitScn.nextInt();
            
            splitScn = new Scanner(c2);
            splitScn.useDelimiter(",");
            
            x2 = splitScn.nextInt();
            y2 = splitScn.nextInt();
            
            if (data.charAt(6) == ' ')
            {                
                for (int x = 0; x < 1000; x++)
                {
                    for (int y = 0; y < 1000; y++)
                    {
                        if (x >= x1 && x <= x2 && y >= y1 && y <= y2)
                        {
                            map[x][y] += 2;
                        }
                    }
                }
            }
            else
            {
                boolean value = data.charAt(6) == 'n';
                
                for (int x = 0; x < 1000; x++)
                {
                    for (int y = 0; y < 1000; y++)
                    {
                        if (x >= x1 && x <= x2 && y >= y1 && y <= y2)
                        {
                            if (value)
                                map[x][y]++;
                            else if (map[x][y] > 0)
                                map[x][y]--;
                        }
                    }
                }
            }
        }
        
        int count = 0;
        
        for (int x = 0; x < 1000; x++)
        {
            for (int y = 0; y < 1000; y++)
            {
                if (map[x][y] > 0)
                {
                    count += map[x][y];
                }
            }
        }
        
        System.out.println(count);
    }
}
