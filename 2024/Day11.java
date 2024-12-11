import java.util.*;
import java.io.*;

public class Day11
{
    public static void main(String[] args) throws IOException
    {
        File file = new File("input.txt");
        Scanner scn = new Scanner(file);
        
        long ans = 0;
        
        //ArrayList<String> stones = new ArrayList();
        HashMap<String, Long> stones = new HashMap();
        
        while (scn.hasNextLine())
        {
            stones.put(scn.next(), 1l);
        }
        
        for (int step = 0; step < 75; step++)
        {
            HashMap<String, Long> newStones = new HashMap();
            
            // System.out.println(step);
            
            for (String s : stones.keySet())
            {
                long num = Long.parseLong(s);
                long temp = stones.get(s);
                
                //System.out.println(num + ", " + temp);
                
                if (num == 0)
                {
                    long extra = newStones.get("1") == null ? 0 : newStones.get("1");
                    newStones.put("1", temp + extra);
                }
                else if (s.length() % 2 == 0)
                {
                    long extra = newStones.get("" + Integer.parseInt(s.substring(0, s.length() / 2))) == null ? 0 : newStones.get("" + Integer.parseInt(s.substring(0, s.length() / 2)));
                    newStones.put("" + Integer.parseInt(s.substring(0, s.length() / 2)), temp + extra);
                    
                    extra = newStones.get("" + Integer.parseInt(s.substring(s.length() / 2))) == null ? 0 : newStones.get("" + Integer.parseInt(s.substring(s.length() / 2)));
                    newStones.put("" + Integer.parseInt(s.substring(s.length() / 2)), temp + extra);
                }
                else
                {
                    long extra = newStones.get("" + (num * 2024)) == null ? 0 : newStones.get("" + (num * 2024));
                    newStones.put("" + (num * 2024), temp + extra);
                }
            }
            
            stones = newStones;
        }
        
        for (String s : stones.keySet())
        {
            ans += stones.get(s);
        }
        
        System.out.println(ans);
    }
}