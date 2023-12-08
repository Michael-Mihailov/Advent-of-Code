import java.util.*;
import java.io.*;

public class Day8
{
    static HashMap<String, String[]> map;
    static String steps;
    
    public static void main(String[] args) throws IOException
    {
        File file = new File("input.txt");
        Scanner scn = new Scanner(file);
        
        
        map = new HashMap();
        steps = scn.nextLine(); // left/right steps
        
        
        while (scn.hasNextLine())
        {
            String pos = scn.next();
            
            scn.next(); // remove'='
            
            String left = scn.next().replace("(", "").replace(",", "");
            String right = scn.next().replace(")", "");
            
            String[] data = new String[2];
            data[0] = left; data[1] = right;
            
            //System.out.println(pos + " " + left + " " + right);
            
            map.put(pos, data);
        }
        
        
        ArrayList<String> startPositions = new ArrayList();
        for (String key : map.keySet())
        {
            if (key.charAt(2) == 'A')
            {
                startPositions.add(key);
            }
        } 
        
        
        ArrayList<Long> values = new ArrayList();
        ArrayList<Long> loopValues = new ArrayList();
        for (int i = 0; i < startPositions.size(); i++)
        {
            int[] t = loopTimes(startPositions.get(i));
            values.add((long)t[0]);
            loopValues.add((long)t[1]);
        }
        
        
        while (isDone(values) == false)
        {
            int minIndex = 0;
            long minValue = values.get(minIndex);
            
            for (int i = 0; i < values.size(); i++)
            {
                if (values.get(i) < minValue)
                {
                    minIndex = i;
                    minValue = values.get(i);
                }
            }
            
            values.set(minIndex, minValue + loopValues.get(minIndex));
        }
        
        
        System.out.println(values.get(0));
    }
    
    public static int[] loopTimes (String pos) // thank you Evan Gorrell! (graphs are confusing)
    {
        int step = 0;
        
        boolean swap = false;
        int num1 = 0;
        int num2 = 0;
        
        while (pos.charAt(2) != 'Z' || !swap)
        {            
            if (pos.charAt(2) == 'Z')
            {
                swap = true;
            }
            
            char instruction = steps.charAt(step);
                
            if (instruction == 'L')
            {
                pos = map.get(pos)[0];
            }
            else if (instruction == 'R')
            {
                pos = map.get(pos)[1];
            }  
            else
            {
                System.out.println("ERROR: " + instruction);
            }
            
            if (!swap)
                num1++;
            else
                num2++;
            
            step++;
            step %= steps.length();
        }
        
        int[] ans = {num1, num2}; 
        return ans;
    }
    
    public static boolean isDone(ArrayList<Long> values)
    {
        long base = values.get(0);
        
        for (long v : values)
        {
            if (v != base)
            {
                return false;
            }
        }
        
        return true;
    }
}
