import java.util.*;
import java.io.*;

public class Day7
{
    public static void main(String[] args) throws IOException
    {
        File file = new File("input.txt");
        Scanner scn = new Scanner(file);
        
        long ans = 0;
        
        while (scn.hasNextLine())
        {
            String data = scn.nextLine();
            data = data.replace(":", "");
            
            //System.out.println(data);
            
            String[] str = data.split(" ");
            
            long calibration = Long.parseLong(str[0]);
            long[] nums = new long[str.length - 1];
            for (int i = 1; i < str.length; i++)
            {
                nums[i - 1] = Long.parseLong(str[i]);
                //System.out.println(Long.parseLong(str[i]));
            }
            
            //System.out.println(calibration);
            
            
            if (isGood(calibration, nums))
            {
                //System.out.println("hi");
                ans += calibration;
            }
            
            //System.out.println();
        }
        
        System.out.println(ans);
    }
    
    public static boolean isGood (long calibration, long[] nums)
    {
        if (nums.length == 1)
        {
            //System.out.println(nums[0] + " " + calibration + " " + (nums[0] == calibration));
            return nums[0] == calibration;
        }
        
        long[] newNums1 = new long[nums.length - 1];
        newNums1[0] = nums[0] * nums[1];
        
        long[] newNums2 = new long[nums.length - 1];
        newNums2[0] = nums[0] + nums[1];
        
        long[] newNums3 = new long[nums.length - 1];
        newNums3[0] = Long.parseLong(nums[0] + "" + nums[1]);
        
        for (int i = 1; i < newNums1.length; i++)
        {
            newNums1[i] = nums[i + 1];
            newNums2[i] = nums[i + 1];
            newNums3[i] = nums[i + 1];
            
            //System.out.println(nums[i+1]);
        }
        
        //System.out.println(newNums1[0] + " " + newNums2[0]);
        
        return isGood(calibration, newNums1) || isGood(calibration, newNums2) || isGood(calibration, newNums3);
    }
}