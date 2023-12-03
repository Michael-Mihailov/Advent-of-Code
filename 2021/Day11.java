
/**
 * Write a description of class Day1 here.
 *
 * @author (your name)
 * @version (a version number or a date)
 */

import java.io.*;
import java.util.*;

public class Day11 
{
    public static void main (String args[]) throws Exception
    {
        Scanner scn = new Scanner(new File("input.txt"));
        
        int count = 0;
        
        int[][] fish = new int[10][10];
        
        boolean done = false;
        for (int row = 0; row < 10; row++)
        {
            String line = scn.nextLine();
            for (int colume = 0; colume < 10; colume++)
            {
                fish[row][colume] = line.charAt(colume) - 48;
            }
        }
        
        while (done == false)
        {
            for (int row = 0; row < 10; row++)
            {
                for (int colume = 0; colume < 10; colume++)
                {
                    if(fish[row][colume] < 0)
                    {
                        fish[row][colume] = 0;
                    }
                }
            }
            count++;
            int flashes = 0;
            
            for (int row = 0; row < 10; row++)
            {
                for (int colume = 0; colume < 10; colume++)
                {
                    fish[row][colume]++;
                }
            }
            
            boolean end = false;
            while (end == false)
            {                
                end = true;
                
                for (int row = 0; row < 10; row++)
                {
                    for (int colume = 0; colume < 10; colume++)
                    {
                        if (fish[row][colume] >= 10)
                        {
                            fish[row][colume] = -999;
                            end = false;
                            
                            flashes++;
                            
                            if (row != 0 && colume != 0)
                            {
                                fish[row-1][colume-1]++;
                            }
                            if (row != 0 && colume != 9)
                            {
                                fish[row-1][colume+1]++;
                            }
                            if (row != 9 && colume != 0)
                            {
                                fish[row+1][colume-1]++;
                            }
                            if (row != 9 && colume != 9)
                            {
                                fish[row+1][colume+1]++;
                            }
                            if (row != 0)
                            {
                                fish[row-1][colume]++;
                            }
                            if (row != 9)
                            {
                                fish[row+1][colume]++;
                            }
                            if (colume != 0)
                            {
                                fish[row][colume-1]++;
                            }
                            if (colume != 9)
                            {
                                fish[row][colume+1]++;
                            }
                        }
                    }
                }
                
                if (flashes == 100)
                {
                    System.out.println(count);
                    done = true;
                }
            }
        }
        
        System.out.println(count);
    }
}
