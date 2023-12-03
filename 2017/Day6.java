
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
        
        int[] blocks = new int[16];
        
        ArrayList<int[]> states = new ArrayList();
        
        int cycle = 0;
        
        
        
        for (int i = 0; i < blocks.length; i++)
        {
            blocks[i] = scn.nextInt();
        }
        
        
        
        boolean go = true;
        while (go)
        {
            int index = -1;
            int num = -1;
            
            for (int i = 0; i < blocks.length; i++)
            {
                if (blocks[i] > num)
                {
                    index = i;
                    num = blocks[i];
                }
            }
            
            blocks[index] = 0;
            
            for (int i = index + 1; num > 0; i++)
            {
                blocks[i % blocks.length] += 1;
                num--;
            }
            
            cycle++;
            for (int[] state : states)
            {
                boolean eql = true;
                for (int i = 0; i < blocks.length; i++)
                {
                    if (state[i] != blocks[i])
                    {
                        eql = false;
                    }
                }
                
                if (eql == true)
                {
                    go = false;
                }
            }
            
            int[] copy = new int[blocks.length];
            for (int i = 0; i < blocks.length; i++)
            {
                copy[i] = blocks[i];
            }
            states.add(copy);
        }
        
        
        for (int[] state : states)
        {
            boolean eql = true;
            for (int i = 0; i < blocks.length; i++)
            {
                if (state[i] != states.get(states.size() - 1)[i])
                {
                    eql = false;
                }
            }
            
            if (eql == true)
            {
                System.out.println(states.indexOf(state));
            }
        }
        System.out.println(cycle);
    }
}
