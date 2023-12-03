
/**
 * Write a description of class Day1 here.
 *
 * @author (your name)
 * @version (a version number or a date)
 */

import java.io.*;
import java.util.*;

public class Day14
{
    public static void main (String args[]) throws Exception
    {
        File file = new File("input.txt");
        Scanner scn = new Scanner(file);
        
        String polyStr = scn.nextLine();

        HashMap<String, String> keyMap = new HashMap();
        HashMap<String, Long> countMap = new HashMap();
        
        scn.nextLine();
        
        while (scn.hasNext())
        {
            String data = scn.nextLine();
            Scanner dataScn = new Scanner(data);
            
            String key = dataScn.next();
            dataScn.next();
            String out = dataScn.next();
            
            keyMap.put(key, out);
            countMap.put(key, new Long(0));
        }
        for (int i = 0; i < polyStr.length() - 1; i++)
        {
            String sub = polyStr.substring(i, i + 2);
            
            countMap.put(sub, countMap.get(sub) + 1);
        }
        
        
        
        for (int step = 0; step < 40; step++)
        {
            System.out.println(countMap.keySet());
            System.out.println(countMap.values());
            System.out.println();
        
            HashMap<String, Long> countMapCopy = (HashMap<String, Long>)countMap.clone();
            
            
            for (String key : countMapCopy.keySet())
            {
                String p1 = key.charAt(0) + keyMap.get(key);
                String p2 = keyMap.get(key) + key.charAt(1); 
                
                countMap.put(p1, countMap.get(p1) + countMapCopy.get(key));
                countMap.put(p2, countMap.get(p2) + countMapCopy.get(key));
                
                countMap.put(key, countMap.get(key) - countMapCopy.get(key));
            }
        }
        System.out.println(countMap.keySet());
        System.out.println(countMap.values());
        
        HashMap<String, Long> letters = new HashMap();
        
        for (String key : countMap.keySet())
        {
            if (letters.containsKey("" + key.charAt(0)) == false)
            {
                letters.put("" + key.charAt(0), new Long(countMap.get(key)));
            }
            else
            {
                Long tempInt = letters.get("" + key.charAt(0));
                tempInt += countMap.get(key);
                letters.put("" + key.charAt(0), new Long(tempInt));
            }
        }
        Long tempInt = letters.get("" + polyStr.charAt(polyStr.length() - 1));
        tempInt += 1;
        letters.put("" + polyStr.charAt(polyStr.length() - 1), new Long(tempInt));
        
        
        long least = Long.MAX_VALUE;
        long most = 0;
        
        System.out.println();
        System.out.println(letters.keySet());
        System.out.println(letters.values());
        
        for (String key : letters.keySet())
        {
            if (letters.get(key) < least)
            {
                least = letters.get(key);
            }
            if (letters.get(key) > most)
            {
                most = letters.get(key);
            }
        }
        
        System.out.println(most + " - " + least + " = " + (most - least));
    }
}
