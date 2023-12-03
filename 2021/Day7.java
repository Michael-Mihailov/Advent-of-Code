
/**
 * Write a description of class Day1 here.
 *
 * @author (your name)
 * @version (a version number or a date)
 */

import java.io.*;
import java.util.*;

public class Day7
{
    public static void main (String args[]) throws Exception
    {
        File file = new File("input.txt");
        Scanner scn = new Scanner(file);
        scn.useDelimiter(",");
        ArrayList<Integer> crabs = new ArrayList<Integer>();
        
        // int fuelAvg = 0;
        int fuelSpent = 0;
        
        int avg = 0;
        int min = 0;
        int max = 1000;
        
        while (scn.hasNext())
        {
            int num = scn.nextInt();
            crabs.add(num);
            
        }
        
        
        crabs.sort(Comparator.naturalOrder());
        
        
        for (int i = 0; i < crabs.size(); i++)
        {
            fuelSpent += ((Math.abs(crabs.get(i) - 476) + 1) * (Math.abs(crabs.get(i) - 476))) / 2;
        }
        
        
        while (min != max)
        {
            int rnd1 = (int)(Math.random() * (max - min)) + 1 + min;
            int rnd2 = (int)(Math.random() * (max - min)) + 1 + min;
                        
            int fuelTest1 = 0;
            int fuelTest2 = 0;
            
            
            for (int i = 0; i < crabs.size(); i++)
            {
                fuelTest1 += ((Math.abs(crabs.get(i) - rnd1) + 1) * (Math.abs(crabs.get(i) - rnd1))) / 2;
            }
            
            for (int i = 0; i < crabs.size(); i++)
            {
                fuelTest2 += ((Math.abs(crabs.get(i) - rnd2) + 1) * (Math.abs(crabs.get(i) - rnd2))) / 2;
            }
            
            if (rnd1 < rnd2 && fuelTest1 < fuelTest2)
            {
                max = rnd2;
            }
            else if (rnd1 > rnd2 && fuelTest1 > fuelTest2)
            {
                max = rnd1;
            }
            else if(rnd1 < rnd2 && fuelTest1 > fuelTest2)
            {
                min = rnd1;
            }
            else if (rnd1 > rnd2 && fuelTest1 < fuelTest2)
            {
                min = rnd2;
            }
            
            System.out.println("Min: " + min + " Max: " + max);
        }
        
        System.out.println("Min: " + min + " Max: " + max + " FINAL");
        for (int i = 0; i < crabs.size(); i++)
        {
            for(i = 1; i <= Math.abs(crabs.get(i) - avg); i++)
            {
                fuelSpent += i;
            }
        }
        
        System.out.println(fuelSpent);
        
    }
}
