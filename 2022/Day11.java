
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
    public static void main (String[] args) throws Exception
    {
        File file = new File("input.txt");
        Scanner scn = new Scanner(file);
        
        ArrayList<Long>[] monkeyStuff = new ArrayList[8];
        long[] monkeyThrows = new long[8];
        int[] throwTest = {13, 7, 19, 2, 5, 3, 11, 17};
        int[] throwTrue = {6, 0, 5, 4, 1, 3, 5, 6};
        int[] throwFalse = {7, 7, 3, 1, 0, 4, 2, 2};
        
        int mon = 0;
        
        
        while (scn.hasNext())
        {
            String data = scn.nextLine();
            
            if (data.contains("Starting items:"))
            {
                Scanner stuffScn = new Scanner(data);
                stuffScn.useDelimiter(": ");
                //stuffScn.next();

                stuffScn.useDelimiter(", ");
                stuffScn.next();
                
                //System.out.println(stuffScn.nextLine());
                
                monkeyStuff[mon] = new ArrayList<Long>();
                while (stuffScn.hasNext())
                {
                    monkeyStuff[mon].add(Long.parseLong(stuffScn.next()));
                    //System.out.println("Thing added");
                }
                //System.out.println("searching stuff");
                mon++;   
            }
        }
        
        for (int round = 1; round <= 10000; round++)
        {
            for (int monkey = 0; monkey < 8; monkey++)
            {
                for (int i = 0; i < monkeyStuff[monkey].size(); i++)
                {
                    if (monkey == 0)
                    {
                        monkeyStuff[monkey].set(i, monkeyStuff[monkey].get(i) * 17);
                        
                        //monkeyStuff[monkey].set(i, monkeyStuff[monkey].get(i) / 3);
                    }
                    else if (monkey == 1)
                    {
                        monkeyStuff[monkey].set(i, monkeyStuff[monkey].get(i) + 8);
                        
                        //monkeyStuff[monkey].set(i, monkeyStuff[monkey].get(i) / 3);
                    }
                    else if (monkey == 2)
                    {
                        monkeyStuff[monkey].set(i, monkeyStuff[monkey].get(i) + 6);
                        
                        //monkeyStuff[monkey].set(i, monkeyStuff[monkey].get(i) / 3);
                    }
                    else if (monkey == 3)
                    {
                        monkeyStuff[monkey].set(i, monkeyStuff[monkey].get(i) * 19);
                        
                        //monkeyStuff[monkey].set(i, monkeyStuff[monkey].get(i) / 3);
                    }
                    else if (monkey == 4)
                    {
                        monkeyStuff[monkey].set(i, monkeyStuff[monkey].get(i) + 7);
                        
                        //monkeyStuff[monkey].set(i, monkeyStuff[monkey].get(i) / 3);
                    }
                    else if (monkey == 5)
                    {
                        monkeyStuff[monkey].set(i, monkeyStuff[monkey].get(i) * monkeyStuff[monkey].get(i));
                        
                        //monkeyStuff[monkey].set(i, monkeyStuff[monkey].get(i) / 3);
                    }
                    else if (monkey == 6)
                    {
                        monkeyStuff[monkey].set(i, monkeyStuff[monkey].get(i) + 1);
                        
                        //monkeyStuff[monkey].set(i, monkeyStuff[monkey].get(i) / 3);
                    }
                    else if (monkey == 7)
                    {
                        monkeyStuff[monkey].set(i, monkeyStuff[monkey].get(i) + 2);
                        
                        //monkeyStuff[monkey].set(i, monkeyStuff[monkey].get(i) / 3);
                    }
                    else
                    {
                        System.out.println("monkey not found");
                    }
                    
                    if (monkeyStuff[monkey].get(i) % throwTest[monkey] == 0)
                    {
                        Long thing = new Long(monkeyStuff[monkey].get(i));
                        
                        if (thing > 9699690)
                        {
                            thing %= 9699690;
                            System.out.println("done1");
                        }
                        
                        monkeyStuff[monkey].remove(i);
                        monkeyStuff[throwTrue[monkey]].add(thing);
                        
                        monkeyThrows[monkey]++;
                        
                        i--;
                    }
                    else
                    {
                        Long thing = new Long(monkeyStuff[monkey].get(i));
                        
                        if (thing > 9699690)
                        {
                            thing %= 9699690;
                            System.out.println("done2");
                        }
                        
                        monkeyStuff[monkey].remove(i);
                        monkeyStuff[throwFalse[monkey]].add(thing);
                        
                        monkeyThrows[monkey]++;
                        
                        i--;
                    }
                    //System.out.println("Hi");
                    //System.out.println(monkeyStuff[monkey].size());
                }
                
            }
            System.out.println(round);
        }
        
        long l1 = 0;
        long l2 = 0;
        
        for (long numThrows : monkeyThrows)
        {
            if (numThrows > l1)
            {
                l2 = l1;
                l1 = numThrows;
            }
            else if (numThrows > l2)
            {
                l2 = numThrows;
            }
        }
        
        System.out.println(l1 + " " + l2 + " " + (l1 * l2));
    }
}

