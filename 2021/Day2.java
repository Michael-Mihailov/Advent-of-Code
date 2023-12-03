
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
    public static void main (String args[]) throws Exception
    {
        Scanner scn = new Scanner(new File("input.txt"));
        
        int horizontal = 0;
        int depth = 0;
        int aim = 0;
        
        while (scn.hasNext())
        {
            Scanner cut = new Scanner(scn.nextLine());
            cut.useDelimiter(" ");
            
            String direction = cut.next();            
            int value = cut.nextInt();
            
            if (direction.equals("forward"))
            {
                horizontal += value;
                depth += aim * value;
            }
            else if (direction.equals("down"))
            {
                aim += value;
            }
            else
            {
                aim -= value;
            }
        }
        
        System.out.println(depth * horizontal);
    }
}
