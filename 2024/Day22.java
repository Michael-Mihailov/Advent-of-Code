import java.util.*;
import java.io.*;

public class Day22
{
    public static void main(String[] args) throws IOException
    {
        File file = new File("input.txt");
        Scanner scn = new Scanner(file);
        
        long ans = 0;
        HashMap<String, Long> sequenceScoreMap = new HashMap();
        
        while (scn.hasNextLine())
        {
            long secret = Integer.parseInt(scn.nextLine());
            HashSet<String> seen = new HashSet();
            
            long[] changes = new long[4];
            
            for (int step = 0; step < 2000; step++)
            {
                long old = secret % 10;
                
                long temp = secret * 64;
                secret  = mix(secret, temp);
                secret = prune(secret);
                
                temp = secret / 32;
                secret  = mix(secret, temp);
                secret = prune(secret);
                
                temp = secret * 2048;
                secret  = mix(secret, temp);
                secret = prune(secret);
                
                long current = secret % 10;
                
                long change = current - old;
                
                for (int i = 1; i < 4; i++)
                {
                    changes[i-1] = changes[i];
                }
                changes[3] = change;
                
                
                String changeString = "";
                for (int i = 0; i < 4; i++)
                {
                    changeString += "(" + changes[i] + ")";
                }
                
                if (step >= 3 && seen.contains(changeString) == false)
                {
                    seen.add(changeString);
                    
                    sequenceScoreMap.put(changeString, current + sequenceScoreMap.getOrDefault(changeString, 0l));
                }
            }
            
            //ans += secret;
        }
        
        for (String key : sequenceScoreMap.keySet())
        {
            long value = sequenceScoreMap.get(key);
            if (value > ans)
            {
                System.out.println(key + ": " + value);
                ans = value;
            }
        }
        
        System.out.println(ans);
    }
    
    public static long mix (long secret, long num)
    {
        return secret ^ num;
    }
    
    public static long prune (long secret)
    {
        return secret % 16777216;
    }
}