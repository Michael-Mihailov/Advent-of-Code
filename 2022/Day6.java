
/**
 * Write a description of class Day1 here.
 *
 * @author (your name)
 * @version (a version number or a date)
 */

import java.io.*;
import java.util.*;

public class Day6
{
    public static void main (String[] args) throws Exception
    {
        Scanner scn = new Scanner(new File("input.txt"));
        String data = scn.nextLine();
        
        //char c1 = 'd', c2 = 'd', c3 = 'd', c4 = 'd';
        char[] c = new char[14];
        c[0] = 'd';
        c[1] = 'd';
        c[2] = 'd';
        c[3] = 'd';
        c[4] = 'd';
        c[5] = 'd';
        c[6] = 'd';
        c[7] = 'd';
        c[8] = 'd';
        c[9] = 'd';
        c[10] = 'd';
        c[11] = 'd';
        c[12] = 'd';
        c[13] = 'd';
        
        int count = 0;
        
        boolean end = false;
        for(int i = 0; i < data.length() && end == false; i++)
        {
            count++; 
            end = true;
            
            for (int t = 13; t > 0; t--)
            {
                c[t] = c[t - 1];
            }
            c[0] = data.charAt(i);
            
            
            for (int s = 0; s < 14; s++)
            {
                for (int d = 0; d < 14; d++)
                {
                    if(c[s] == c[d] && s != d)
                    {
                        end = false;
                    }
                }
            }
            
            if (end == true)
            {
                for (int s = 0; s < 14; s++)
                {
                    System.out.print(c[s] + " ");
                }
            }
            
            //if (c1 != c2 && c1 != c3 && c1 != c4 && c2 != c3 && c2 != c4 && c3 != c4)
            //{
            //    end = true;
            //}
        }
        System.out.println(count);
    }
}
