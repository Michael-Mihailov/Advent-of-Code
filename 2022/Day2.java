
/**
 * Write a description of class Day2 here.
 *
 * @author (your name)
 * @version (a version number or a date)
 */

import java.io.*;
import java.util.Scanner;

public class Day2
{
    public static void main (String[] args) throws Exception
    {
        Scanner scn = new Scanner(new File("input.txt"));
        
        int score = 0;
        
        while (scn.hasNext())
        {
            String data = scn.nextLine();
            
            int temp;
            
            if (data.charAt(2) == 'X')
            {
                score += 0;
                temp = (data.charAt(0) - 64) - 1;
            }
            else if (data.charAt(2) == 'Y')
            {
                score += 3;
                temp = (data.charAt(0) - 64);
            }
            else
            {
                score += 6;
                temp = (data.charAt(0) - 64) + 1;
            }
            
            if (temp == 4)
            {
                temp = 1;
            }
            else if (temp == 0)
            {
                temp = 3;
            }
            
            score += temp;
            
            /*
            if (data.charAt(0) + 23 == data.charAt(2))
            {
                score += 3;
            }
            else if (data.charAt(2) == 'Z')
            {
                if (data.charAt(0) == 'B')
                {
                    score += 6;
                }
            }
            else if (data.charAt(2) == 'Y')
            {
                if (data.charAt(0) == 'A')
                {
                    score += 6;
                }
            }
            else
            {
                if (data.charAt(0) == 'C')
                {
                    score += 6;
                }
            }
            */
            
        }
        System.out.println(score);
    }
}
