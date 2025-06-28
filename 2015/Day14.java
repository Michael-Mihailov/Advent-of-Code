import java.util.*;
import java.io.*;

public class Day14
{
    public static void main(String args[]) throws IOException
    {
        // I really thought part-2 would have the time be way bigger :(
        
        File file = new File("input.txt");
        
        int ans = 0;
        
        int maxTime = 2503;
        
        HashMap<String, Integer> scoreMap = new HashMap();
        
        for (int time = 1; time <= maxTime; time++)
        {
            Scanner scn = new Scanner(file);
            
            String leader = null;
            int bestDist = 0;
            while (scn.hasNextLine())
            {
                String[] data = scn.nextLine().split(" ");
                String name = data[0];
                
                int speed = Integer.parseInt(data[3]);
                int endurance = Integer.parseInt(data[6]);
                int rest = Integer.parseInt(data[13]);
                
                int fullIntervals = time / (endurance + rest);
                int leftOverTime = time % (endurance + rest);
                int extraRunTime = (leftOverTime > endurance) ? endurance : leftOverTime;
                
                int runTime = (fullIntervals * endurance) + extraRunTime;
                
                int distance = runTime * speed;
                
                if (distance > bestDist) 
                {
                    bestDist = distance;
                    leader = name;
                }
            }
            scoreMap.put(leader, scoreMap.getOrDefault(leader, 0) + 1);
            scn.close();
        }
        
        for (int value : scoreMap.values())
        {
            if (value > ans) ans = value;
        }
        
        System.out.println(ans);
    }
}