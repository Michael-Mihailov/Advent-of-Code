
/**
 * Write a description of class Day2 here.
 *
 * @author (your name)
 * @version (a version number or a date)
 */

import java.io.*;
import java.util.*;

public class Day12 
{
    static HashMap<String, ArrayList<String>> caves = new HashMap();
    static long count = 0;
    
    public static void main (String args[]) throws Exception
    {
        Scanner scn = new Scanner(new File("input.txt"));
        
        
        while (scn.hasNext())
        {
            String data = scn.nextLine();
            Scanner splitScn = new Scanner(data);
            splitScn.useDelimiter("-");
            
            String first = splitScn.next();
            String last = splitScn.next();
            
            if (caves.containsKey(first) == false)
            {
                caves.put(first, new ArrayList<String>());
            }
            if (caves.containsKey(last) == false)
            {
                caves.put(last, new ArrayList<String>());
            }
            caves.get(first).add(last);
            caves.get(last).add(first);
        }
        
        System.out.println("hi");
        mapNext("start", caves, true, new ArrayList<String>());
        
        System.out.println();
        System.out.println(count);
    }
    
    public static void mapNext(String pos, HashMap<String, ArrayList<String>> m, boolean visit, ArrayList<String> v)
    {        
        HashMap<String, ArrayList<String>> map = new HashMap(m); 
        ArrayList<String> visited = new ArrayList(v);
        
        ArrayList<String> places = map.get(pos);
        boolean cont = true;
        
        //System.out.println("map size: " + m.size());
        
        if (places == null)
        {
            cont = false;
        }
                
        if (pos.equals("end"))
        {
            count++;
            //System.out.println("X\n");
        }
        else
        {
            if (pos.equals(pos.toUpperCase()) == false)
            {
                
                if (visit == false)
                {
                    map.remove(pos);
                }
                else if (visited.contains(pos) == true)
                {
                    visit = false;
                    
                    for (int i = 0; i < visited.size(); i++)
                    {
                        map.remove(visited.get(i));
                    }
                }
                else
                {
                    visited.add(pos);
                }
                
                //map.remove(pos);
            }
            
            if (cont == true)
            {
                map.remove("start");
                
                for (int i = 0; i < places.size(); i++)
                {
                    mapNext(places.get(i), map, visit, visited);
                    //System.out.println(i);
                }
            }
        }
    }
}
