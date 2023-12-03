
/**
 * Write a description of class Day2 here.
 *
 * @author (your name)
 * @version (a version number or a date)
 */

import java.io.*;
import java.util.Scanner;

public class Day3 
{
    public static void main (String args[]) throws Exception
    {
        int length = (new Scanner(new File("input.txt"))).nextLine().length();
        
        String common = "";
                
        for (int i = 0; i < length; i++)
        {
            Scanner scn = new Scanner(new File("input.txt"));
            System.out.println("xxx");
            int b0 = 0;
            int b1 = 0;
            
            int count = 0;
            
            while (scn.hasNext())
            {
                String line = scn.nextLine();           
                

                
                boolean clean = true;
                for (int check = 0; check < i; check++)
                {
                    if(line.charAt(check) != common.charAt(check))
                    {
                        clean = false;
                    }
                }
                
                if (clean)
                {
                    count++;
                    System.out.println(line);
                    if (line.charAt(i) == '0')
                    {
                        b0++;
                    }
                    else
                    {
                        b1++;
                    }
                }
                /*
                if (countPrev == 1)
                {
                    System.out.println(line);
                    System.out.println(Integer.parseInt(line, 2));
                }
                */
            }
            
            System.out.println("count: " + count);

            
            if (b0 > b1)
            {
                common += "0";
            }
            else
            {
                common += "1";
            }
        }
        System.out.println(common);
        System.out.println(Integer.parseInt(common, 2));
    }
}
