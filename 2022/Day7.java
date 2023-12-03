
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
    static HashMap<String, ArrayList<String>> directories = new HashMap();
    
    static String dirCurrent = "/";
    static String dirPast = "";
    
    public static void main (String[] args) throws Exception
    {
        directories.put("/", new ArrayList<String>());
        
        Scanner scn = new Scanner(new File("input.txt"));
        
        int count = 0;
        
        while (scn.hasNext())
        {
            String data = scn.nextLine();
            
            if (data.contains("$ cd"))
            {
                String dir = data.substring(5);
                
                //System.out.println(dir);
                
                dirCurrent = dir;
                if (dir.equals("/"))
                {
                    dirCurrent = "/";
                }
                else if (dir.equals(".."))
                {
                    dirCurrent = findOuter();
                }
                else
                {
                    directories.put(dirPast + "@" + dir, new ArrayList<String>());
                    dirCurrent = dirPast + "@" + dir;
                }
                
                dirPast = dirCurrent;
            }
            else
            {
                if (data.equals("$ ls") == false)
                {
                    Scanner splitScn = new Scanner(data);
                    String left = splitScn.next();
                    String right = splitScn.next();
                    
                    if (left.equals("dir"))
                    {
                        directories.get(dirCurrent).add("dir " + dirCurrent + "@" + right);
                    }
                    else
                    {
                        directories.get(dirCurrent).add(data);
                    }
                }
            }
        }
        
        ArrayList<Integer> nums = new ArrayList();
        
        for (String dir : directories.keySet())
        {

            //System.out.println("NEW DIRECTORY STARTING NOW: " + count);
            int num = getCount(dir);
            if (num >= 7048086) // maybe just <                 ???????
            {
                nums.add(num);
            }
        }
        //count = getCount("/");
        Collections.sort(nums);  
        System.out.println(nums.get(0));
    }
    
    public static String findOuter()
    {
        for (String dir : directories.keySet())
        {            
            if (directories.get(dir).contains("dir " + dirPast))
            {
                return dir;
            }
        }
        
        System.out.println(dirPast);
        
        System.out.println("Outer directory not found");
        //System.out.println(dirCurrent);
        //System.out.println(dirPast);
        return "/";
    }
    
    
    public static int getCount(String dirKey)
    {
        int numSoFar = 0;
        
        for (int i = 0; directories.containsKey(dirKey) && i < directories.get(dirKey).size(); i++)
        {
            String dataStr = directories.get(dirKey).get(i);
            
            Scanner splitScn = new Scanner(dataStr);
            String left = splitScn.next();
            String right = splitScn.next();
            
            //System.out.println(dataStr);
           // System.out.println(numSoFar);
            //System.out.println(dirKey);
            
            /*
            if (numSoFar > 100000) // prevent stack overflow ???
            {
                return numSoFar;
            }
            */
           
            if(left.equals("dir"))
            {
                numSoFar += getCount(right);
            }
            else
            {
                numSoFar += Integer.valueOf(left);
            }
        }
        
        return numSoFar;
    }
}
