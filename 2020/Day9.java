import java.util.*;
import java.io.*;

public class Day9
{
    public static void main(String args[]) throws IOException
    {
        File file = new File("input.txt");
        Scanner scn = new Scanner(file);
        
        long invalid = 0;
        long ans = 0;
        
        ArrayList<Long> preamble = new ArrayList();
        for (int i = 0; i < 25; i++) 
        {
            preamble.add(scn.nextLong());
        }
        
        while (scn.hasNextLine())
        {
            long num = scn.nextLong();
            
            boolean valid = false;
            for (int i = 0; i < 25; i++)
            {
                for (int j = i + 1; j < 25; j++)
                {
                    if (preamble.get(i) + preamble.get(j) == num) valid = true;
                }
            }
            
            if (valid == false)
            {
                invalid = num;
                break;
            }
            
            preamble.add(num);
            preamble.removeFirst();
        }
        
        ArrayList<Long> numbers = new ArrayList();
        scn = new Scanner(file);
        while (scn.hasNextLine()) numbers.add(scn.nextLong());
        
        int low = 0;
        int high = 1;
        long total = numbers.get(0) + numbers.get(1);
        
        while (total != invalid)
        {
            if (total < invalid)
            {
                high++;
                total += numbers.get(high);
            }
            else
            {
                total -= numbers.get(low);
                low++;
            }
        }
        
        long smallest = Long.MAX_VALUE;
        long largest = Long.MIN_VALUE;
        for (int i = low; i <= high; i++)
        {
            long num = numbers.get(i);
            if (num < smallest) smallest = num;
            if (num > largest) largest = num;
        }
        ans = smallest + largest;
        
        System.out.println(ans);
    }
}