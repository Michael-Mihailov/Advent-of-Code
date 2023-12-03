
/**
 * Write a description of class Day1 here.
 *
 * @author (your name)
 * @version (a version number or a date)
 */

import java.io.*;
import java.util.*;

public class Day8
{
    public static void main (String args[]) throws Exception
    {
        File file = new File("input.txt");
        Scanner scn = new Scanner(file);
        
        int count = 0;
        
        while(scn.hasNext())
        {
            String line = scn.nextLine();
            Scanner splitScn = new Scanner(line);
            splitScn.useDelimiter(" X ");
                            
            String left = splitScn.next();
            String right = splitScn.next();
    
            Scanner scnLeft = new Scanner(left);
            Scanner scnRight = new Scanner(right);
            
            
            String[] inpts = new String[10];
            String[] nums = new String[10];
            String[] wires = new String[7];
            
            String inpt0 = scnLeft.next(); inpt0 = sortString(inpt0);
            inpts[0] = inpt0;
            String inpt1 = scnLeft.next(); inpt1 = sortString(inpt1);
            inpts[1] = inpt1;
            String inpt2 = scnLeft.next(); inpt2 = sortString(inpt2);
            inpts[2] = inpt2;
            String inpt3 = scnLeft.next(); inpt3 = sortString(inpt3);
            inpts[3] = inpt3;
            String inpt4 = scnLeft.next(); inpt4 = sortString(inpt4);
            inpts[4] = inpt4;
            String inpt5 = scnLeft.next(); inpt5 = sortString(inpt5);
            inpts[5] = inpt5;
            String inpt6 = scnLeft.next(); inpt6 = sortString(inpt6);
            inpts[6] = inpt6;
            String inpt7 = scnLeft.next(); inpt7 = sortString(inpt7);
            inpts[7] = inpt7;
            String inpt8 = scnLeft.next(); inpt8 = sortString(inpt8);
            inpts[8] = inpt8;
            String inpt9 = scnLeft.next(); inpt9 = sortString(inpt9);
            inpts[9] = inpt9;
            
            for (int i = 0; i < 10; i++)
            {
                //System.out.println(inpts[i]);
                if (inpts[i].length() == 2)
                {
                    nums[1] = inpts[i];
                }
                else if (inpts[i].length() == 3)
                {
                    nums[7] = inpts[i];
                }
                else if (inpts[i].length() == 4)
                {
                    nums[4] = inpts[i];
                }
                else if (inpts[i].length() == 7)
                {
                    nums[8] = inpts[i];
                }                
            }
            
            wires[0] = difference(nums[1], nums[7]);
            /*
            System.out.println(nums[7]);
            System.out.println(nums[1]);
            System.out.println(wires[0]);
            System.out.println();
            */
            for (int i = 0; i < 10; i++)
            {
                if (inpts[i].length() == 6)
                {
                    if (difference(inpts[i], nums[7]).length() == 1)
                    {
                        wires[2] = difference(inpts[i], nums[1]);
                        nums[6] = inpts[i];
                    }
                    else if (difference(inpts[i], nums[4]).length() == 1)
                    {
                        wires[3] = difference(inpts[i], nums[8]);
                        nums[0] = inpts[i];
                    }
                    else
                    {
                        nums[9] = inpts[i];
                    }
                }
            }
            
            wires[1] = difference(nums[1] + wires[3], nums[4]);
            wires[5] = difference(wires[0] + wires[2], nums[7]);
            
            for (int i = 0; i < 10; i++)
            {
                if (inpts[i].length() == 5) 
                {
                    if (difference(inpts[i], wires[2] + wires[5]).length() == 0)
                    {
                        nums[3] = inpts[i];
                    }
                    else if (difference(inpts[i], wires[5]).length() == 0)
                    {
                        nums[5] = inpts[i];
                    }
                    else
                    {
                        nums[2] = inpts[i];
                    }
                }
            }
            
            
            
            String out1 = scnRight.next(); out1 = sortString(out1);
            String out2 = scnRight.next(); out2 = sortString(out2);
            String out3 = scnRight.next(); out3 = sortString(out3);
            String out4 = scnRight.next(); out4 = sortString(out4);
            
            int d1 = -999, d2 = -999, d3 = -999, d4 = -999;
            
            for (int i = 0; i < 10; i++)
            {
                //System.out.println(nums[i]);
                if (out1.equals(nums[i]))
                {
                    d1 = i;
                }
                if (out2.equals(nums[i]))
                {
                    d2 = i;
                }
                if (out3.equals(nums[i]))
                {
                    d3 = i;
                }
                if (out4.equals(nums[i]))
                {
                    d4 = i;
                }
            }
            
            count += d1 * 1000;
            count += d2 * 100;
            count += d3 * 10;
            count += d4;
            
            System.out.println("" + d1 + d2 + d3 + d4);
            
            System.out.println(out1 + " " + out2 + " " + out3 + " " + out4); 
            for(String num : nums)
            {
                System.out.println(num);
            } 
            System.out.println();
        }
        
        System.out.println(count);
    }
    
    public static String sortString(String inputString) // borrowed from "geeks for geeks"
    {
        // Converting input string to character array
        char tempArray[] = inputString.toCharArray();
 
        // Sorting temp array using
        Arrays.sort(tempArray);
 
        // Returning new sorted string
        return new String(tempArray);
    }
    
    public static String difference(String s1, String s2)
    {
        for(int i = 0; i < s1.length(); i++)
        {
            s2 = s2.replace("" + s1.charAt(i), "");
        }
        return s2;
    }
}
        