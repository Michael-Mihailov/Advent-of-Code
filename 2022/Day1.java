
/**
 * Write a description of class Day1 here.
 *
 * @author (your name)
 * @version (a version number or a date)
 */

import java.io.*;
import java.util.*;

public class Day1
{
    public static void main (String[] args) throws Exception
    {
        File file = new File("input.txt");
        Scanner scn = new Scanner(file);
        
        int max1 = 0;
        int max2 = 0;
        int max3 = 0;
        int count = 0;
        
        while (scn.hasNext())
        {
            String data = scn.nextLine();
            if (data.equals(""))
            {
                if(count > max1)
                {
                    max3 = max2;
                    max2 = max1;
                    max1 = count;
                }
                else if(count > max2)
                {
                    max3 = max2;
                    max2 = count;
                }
                else if (count > max3)
                {
                    max3 = count;
                }
                count = 0;
            }
            else
            {
                count += Integer.parseInt(data);
            }
        }
        System.out.println(max1);
        System.out.println(max2);
        System.out.println(max3);
        System.out.println(max1 + max2 + max3);
    }
}
