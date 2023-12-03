
/**
 * Write a description of class Day2 here.
 *
 * @author (your name)
 * @version (a version number or a date)
 */

import java.util.*;
import java.io.*;

public class Day2
{
    public static void main(String args[]) throws IOException
    {
        File file = new File("input.txt");
        Scanner scn = new Scanner(file);
        int count = 0;
        while (scn.hasNextLine())
        {
            int[] nums = new int[16];
            
            Scanner dataScn = new Scanner(scn.nextLine());
            for (int i = 0; dataScn.hasNext(); i++)
            {
                nums[i] = dataScn.nextInt();
            }
            
            
            
            for (int i = 0; i < 15; i ++)
            {
                for (int j = i + 1; j < 16; j++)
                {
                    if (nums[i] % nums[j] == 0)
                    {
                        count += nums[i] / nums[j];
                        
                        System.out.println(nums[i] + " " + nums[j]);
                    }
                    else if (nums[j] % nums[i] == 0)
                    {
                        count += nums[j] / nums[i];
                        
                        System.out.println(nums[j] + " " + nums[i]);
                    }
                }
            }
        }
        System.out.println(count);
    }
}
