
/**
 * Write a description of class Day7 here.
 *
 * @author (your name)
 * @version (a version number or a date)
 */

import java.util.*;
import java.io.*;

public class Day7
{
    public static HashMap<String, ArrayList<String>> tower;
    
    public static HashMap<String, Integer> weights;
    
    public static void main(String args[]) throws IOException
    {
        File file = new File("input.txt");
        Scanner scn = new Scanner(file);
        
        tower = new HashMap();
        weights = new HashMap();
        
        
        while (scn.hasNext())
        {
            String data = scn.nextLine();
            Scanner dataScn = new Scanner(data);
            
            
            String key = dataScn.next();
            
            String weight = dataScn.next();
            weights.put(key, Integer.valueOf(weight.substring(1, weight.length()-1)));
            
            ArrayList<String> values = new ArrayList();
            if (dataScn.hasNext())
            {
                String dataRef = data.substring(data.indexOf(" -> ") + 4);
                
                Scanner dataRefScn = new Scanner(dataRef);
                dataRefScn.useDelimiter(", ");
                
                
                
                while (dataRefScn.hasNext())
                {
                    values.add(dataRefScn.next());
                }
            }
            
            tower.put(key, values);
        }
        
        
        
        System.out.println(totalWeight("arqoys", true));
    }
    
    public static int totalWeight(String name, boolean first)
    {
        int total = 0;
        for (String value : tower.get(name))
        {
            total += totalWeight(value, false);
            if (first) System.out.println(value + " " + totalWeight(value, false));
        }
        
        total += weights.get(name);
        
        return total;
    }
    
    public static String findBase(String name)
    {
        String upper = null;
        
        for (String key : tower.keySet())
        {
            for (String value : tower.get(key))
            {
                if (value.equals(name))
                {
                    upper = key;
                }
            }
        }
        
        
        if (upper == null)
        {
            return name;
        }
        else
        {
            return findBase(upper);
        }
    }
}
