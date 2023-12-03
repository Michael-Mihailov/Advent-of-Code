
/**
 * Write a description of class Day1 here.
 *
 * @author (your name)
 * @version (a version number or a date)
 */

import java.io.*;
import java.util.*;

public class Day6
{
    public static void main (String args[]) throws Exception
    {
        File file = new File("input.txt");
        Scanner scn = new Scanner(file);
        scn.useDelimiter(",");
        
        //ArrayList<Integer> fish = new ArrayList<Integer>();
        long[] fish = new long[11];
        
        while (scn.hasNext())
        {
            fish[scn.nextInt() + 1]++;
        }
        
        for (int day = 1; day <= 256; day++)
        {
            for (int i = 1; i <= 10; i++)
            {
                fish[i - 1] += fish[i];
                fish[i] = 0;
                
                fish[8] += fish[0];
                fish[10] += fish[0];
                fish[0] = 0;
            }
        }
        
        System.out.println(fish[0] + fish[1] + fish[2] + fish[3] + fish[4] + fish[5] + fish[6] + fish[7] + fish[8] + fish[9] + fish[10]);
    }
}
