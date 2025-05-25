import java.util.*;
import java.io.*;

public class Day15
{
    public static void main(String args[]) throws IOException
    {
        File file = new File("input.txt");
        Scanner scn = new Scanner(file);
        scn.useDelimiter(",");
        
        HashMap<Integer, Integer> ageMap = new HashMap();
        int time = 0;
        
        int num = -1;
        
        while (time < 30000000 - 1)
        {
            time++;
            
            if (scn.hasNextInt())
            {
                num = scn.nextInt();
            }
            int age = time - ageMap.getOrDefault(num, time);
            
            ageMap.put(num, time);
            num = age;            
        }
        
        System.out.println(num);
    }
}
