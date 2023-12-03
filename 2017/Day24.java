
/**
 * Write a description of class Day24 here.
 *
 * @author (your name)
 * @version (a version number or a date)
 */

import java.io.*;
import java.util.*;

public class Day24
{
    public static int max = 0;
    
    public static int max2 = 0; // for PART 2
    public static int length = 0; // ***
    
    public static void main (String[] args) throws IOException
    {
        File input = new File("input.txt");
        Scanner scn = new Scanner(input);
        
        
        
        
        
        ArrayList<int[]> dominos = new ArrayList(); 
        
        
        while (scn.hasNextLine()) // read data
        {
            String data = scn.nextLine();
            Scanner dataScn = new Scanner(data);
            dataScn.useDelimiter("/");
            
            int n1 = dataScn.nextInt();
            int n2 = dataScn.nextInt();
            
            int[] domino = new int[2];
            domino[0] = n1; domino[1] = n2;
            dominos.add(domino);
        }
        
        
        for (int[] domino : dominos)
        {
            ArrayList<int[]> copy = copyList(dominos);
            copy.remove(domino);
            
            if (domino[1] == 0)
                connect(domino[0], copy, domino[0] + domino[1], 1);
            
            if (domino[0] == 0)
                connect(domino[1], copy, domino[0] + domino[1], 1);
        }
        
        System.out.println("Part 1 answer: " + max);
        
        
        max2 = 0; // P2 below
        for (int[] domino : dominos)
        {
            ArrayList<int[]> copy = copyList(dominos);
            copy.remove(domino);
            
            if (domino[1] == 0)
                connect(domino[0], copy, domino[0] + domino[1], 1);
            
            if (domino[0] == 0)
                connect(domino[1], copy, domino[0] + domino[1], 1);
        }
        
        System.out.println("Part 2 answer: " + max2);
    }
    
    public static void connect(int face, ArrayList<int[]> remaining, int count, int len)
    {
        for (int[] d : remaining)
        {
            if (d[0] == face)
            {
                ArrayList<int[]> copy = copyList(remaining);
                copy.remove(d);
                                
                connect(d[1], copy, count + d[0] + d[1], len + 1);
            }
            else if (d[1] == face)
            {
                ArrayList<int[]> copy = copyList(remaining);
                copy.remove(d);
                
                connect(d[0], copy, count + d[0] + d[1], len + 1);
            }
        }
        
        if (count > max)
        {
            max = count;
        }
        
        if (count > max2 && len == length) // P2
        {
            max2 = count;
        }
        
        if (len > length) // P2
        {
            length = len;
        }
    }
    
    public static ArrayList copyList(ArrayList<int[]> subject)
    {
        ArrayList<int[]> copy = new ArrayList();
        
        for(int[] v : subject)
        {
            copy.add(v);
        }
        
        return copy;
    }
}
