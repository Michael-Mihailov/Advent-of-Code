
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
    public static void main(String args[]) throws IOException
    {
        File file = new File("input.txt");
        Scanner scn = new Scanner(file);
        
        boolean[][] map = new boolean[323][31];
        
        int row = 0;
        while (scn.hasNext())
        {
            String data = scn.nextLine();
            
            for (int i = 0; i < data.length(); i++)
            {
                if (data.charAt(i) == '#')
                {
                    map[row][i] = true;
                }
            }
            
            row++;
        }
        
        
        int count = 0;
        int xPos = 0;
        for (int i = 0; i < 323; i += 2) // "i" is the y-pos // increment changes
        {
            if (map[i][xPos % 31] == true)
            {
                count++;
            }
            
            xPos += 1; // increment changes
        }
        
        System.out.println(count);
    }
}
