
/**
 * Write a description of class Day13 here.
 *
 * @author (your name)
 * @version (a version number or a date)
 */

import java.io.*;
import java.util.*;

public class Day13
{
    public static void main (String[] args) throws IOException
    {
        File input = new File("input.txt");
        Scanner scn = new Scanner(input);
        
        
        int count = 0;
        
        while (scn.hasNext())
        {
            String data = scn.nextLine();
            Scanner dataScn = new Scanner(data);
            dataScn.useDelimiter(": ");
            
            
            int step = dataScn.nextInt();
            int range = dataScn.nextInt();
            
            if (step % ((range * 2) - 2) == 0)
            {
                count += step * range;
            }
        }
        
        System.out.println("Part 1 answer: " + count);
        System.out.println("Part 2 answer: " + p2());
    }
    
    public static int p2() throws IOException
    {
        File input = new File("input.txt");
        Scanner scn = new Scanner(input);
        
        HashMap<Integer, Integer> map = new HashMap();
        int last = 0;
        
                
        while (scn.hasNext())
        {
            String data = scn.nextLine();
            Scanner dataScn = new Scanner(data);
            dataScn.useDelimiter(": ");
            
            int step = dataScn.nextInt();
            int range = dataScn.nextInt();
            
            if (step > last) last = step;
            
            map.put(step, range);
        }
        
        boolean[] positions = new boolean[last + 2];
        for (int tick = 0; true; tick++)
        {
            if (positions[positions.length - 1] == true)
            {
                return tick - positions.length;
            }
            
            for (int p = positions.length - 1; p >= 0; p--)
            {
                if (positions[p] == true)
                {
                    positions[p] = false;
                    positions[p + 1] = true;
                }
            }
            
            positions[0] = true;
            
            for (Integer pos : map.keySet())
            {
                if (tick % ((map.get(pos) * 2) - 2) == 0)
                {
                    positions[pos] = false;
                }
            }
            
            // visualization commented out
            /*
            System.out.println(tick + ":");
            for (int i = 0; i < positions.length; i++)
            {
                if (positions[i] == true)
                {
                    System.out.print("#");
                }
                else
                {
                    System.out.print(".");
                }
            } System.out.println();
            */
        }
    }
}
