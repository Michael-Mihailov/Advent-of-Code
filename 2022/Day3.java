/**
 * Write a description of class Day1 here.
 *
 * @author (your name)
 * @version (a version number or a date)
 */

import java.io.*;
import java.util.Scanner;

public class Day3
{
    public static void main (String[] args) throws Exception
    {
        Scanner scn = new Scanner(new File("input.txt"));


        int score = 0;
        
        
        
        
        while (scn.hasNext())
        {
            String data1 = scn.nextLine();
            String data2 = scn.nextLine();
            String data3 = scn.nextLine();
            
            //String sack1 = (data.substring(0, (data.length() / 2)));
            //String sack2 = (data.substring(data.length() / 2));
            
            char same = '1';
            
            
            for (int a = 0; a < data1.length(); a++)
            {
                for (int b = 0; b < data2.length(); b++)
                {
                    for (int c = 0; c < data3.length(); c++)
                    {
                        if (data1.charAt(a) == data2.charAt(b) && data3.charAt(c) == data1.charAt(a))
                        {
                            same = data1.charAt(a);
                        }
                    }
                }
            }
            
            if (Character.isUpperCase(same))
            {
                score += same - 38;
            }
            else
            {
                score += same - 96;
            }
                
        }
        
        System.out.println(score);
    }
}
