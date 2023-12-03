
/**
 * Write a description of class Day1 here.
 *
 * @author (your name)
 * @version (a version number or a date)
 */

import java.io.*;
import java.util.Scanner;

public class Day1 
{
    public static void main (String args[]) throws Exception
    {
        File file = new File("input.txt");
        Scanner scn = new Scanner(file);
        
        
        int count = 0;
        
        int depth1 = scn.nextInt();
        int depth2 = scn.nextInt();
        int depth3 = scn.nextInt();
        int depth4 = scn.nextInt();
        if (depth4 > depth1)
        {
            count++;
        }
        while (scn.hasNextLine())
        {

                        
            depth1 = depth2;
            depth2 = depth3;
            depth3 = depth4;
            depth4 = scn.nextInt();
            if (depth4 > depth1)
            {
                count++;
            }
        }
        
        System.out.println(count);
        
    }
}
