import java.util.*;
import java.io.*;

public class Day25
{
    public static void main(String args[]) throws IOException
    {
        File file = new File("input.txt");
        Scanner scn = new Scanner(file);
        
        final long MAX_LOOPS = Long.MAX_VALUE;
        
        long ans = 0;
        
        long cardKey = scn.nextInt();
        long doorKey = scn.nextInt();
        long initialValue = 1;
        long subjectNum = 7;
        
                
        long cardLoops = numLoops(cardKey, initialValue, subjectNum, MAX_LOOPS);
        long doorLoops = numLoops(doorKey, initialValue, subjectNum, MAX_LOOPS);
                
        long path1 = initialValue;
        for (int loop = 0; loop < cardLoops; loop++)
        {
            path1 = loop(path1, doorKey);
        }
        long path2 = initialValue;
        for (int loop = 0; loop < doorLoops; loop++)
        {
            path2 = loop(path2, cardKey);
        }
        
        if (path1 != path2) System.out.println("Error");
        ans = path1;
        
        
        System.out.println("Answer: " + ans);
    }
    
    public static long numLoops (long endValue, long value, long subjectNum, long maxLoops)
    {
        for (int loop = 0; loop < maxLoops; loop++)
        {
            if (value == endValue) return loop;
            value = loop(value, subjectNum);
        }
        return -1;
    }
    
    public static long loop (long value, long subjectNum)
    {
        value *= subjectNum;
        value %= 20201227;
        return value;
    }
}