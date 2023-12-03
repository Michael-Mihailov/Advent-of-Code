
/**
 * Write a description of class Day12 here.
 *
 * @author (your name)
 * @version (a version number or a date)
 */

import java.io.*;
import java.util.*;

public class Day12
{
    public static HashMap<Integer, ArrayList<Integer>> pipes = new HashMap();
    
    public static void main (String[] args) throws IOException
    {
        File input = new File("input.txt");
        Scanner scn = new Scanner(input);
        
        
        int total = 0;
        int groups = 1; // for part 2
        
        
        while (scn.hasNext()) // read input data
        {
            String data = scn.nextLine();
            
            Integer key = new Integer(data.substring(0, data.indexOf(" ")));
            
            data = data.substring(data.indexOf(">") + 2);
                        
            Scanner dataScn = new Scanner(data);
            dataScn.useDelimiter(", ");
            
            ArrayList<Integer> values = new ArrayList();
            while (dataScn.hasNext())
            {
                values.add(dataScn.nextInt());
            }
            
            pipes.put(key, values);
        }
        
        
        total = explore(0);
        
        
        while (pipes.size() > 0) // part 2 stuff
        {
            Integer seed = pipes.keySet().iterator().next(); // seed for unconnected pipes
            
            explore(seed); // remove all pipes connected to the seed
            
            groups++; // add one to the number of groups found
        }
        
        
        System.out.println("Part 1 answer: " + total);
        System.out.println("Part 2 answer: " + groups);
    }
    
    public static int explore(int num)
    {
        int count = 1;
        
        ArrayList<Integer> connections = pipes.get(num);
        pipes.remove(num);
        
        for (Integer c : connections)
        {
            if (pipes.containsKey(c))
                count += explore(c);
        }
        
        return count;
    }
}
