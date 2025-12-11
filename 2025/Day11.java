import java.util.*;
import java.io.*;

public class Day11
{
    public static void main(String[] args) throws IOException
    {
        File file = new File("input.txt");
        Scanner scn = new Scanner(file);
        
        long ans = 0;
        
        HashMap<String, ArrayList<String>> pathMap = new HashMap();
        
        HashMap<String, long[]> positionMap = new HashMap(); // regular, 1 visted, 2 visited
        
        while (scn.hasNextLine())
        {
            String[] data = scn.nextLine().replace(":", "").split(" ");
            
            String start = data[0];
            ArrayList<String> paths = new ArrayList();
            for (int i = 1; i < data.length; i++) paths.add(data[i]);
            pathMap.put(start, paths);
        }
        
        positionMap.put("svr", new long[]{1,0,0});
        while (positionMap.size() > 0)
        {
            HashMap<String, long[]> newPositionMap = new HashMap();
            
            for (String position : positionMap.keySet())
            {
                long[] num = positionMap.get(position);
                if (pathMap.get(position) == null) continue;
                
                for (String nextPos : pathMap.get(position))
                {
                    long out[] = new long[3];
                    long[] current = newPositionMap.getOrDefault(nextPos, new long[3]);
                    for (int i = 0; i < num.length; i++)
                    {
                        if (nextPos.equals("dac") || nextPos.equals("fft"))
                        {
                            if (i == 0) out[i] = current[i];
                            else out[i] = current[i] + num[i - 1];
                        }
                        else
                        {
                            out[i] = current[i] + num[i];
                        }
                    }
                    newPositionMap.put(nextPos, out);
                }
            }
            
            ans += newPositionMap.getOrDefault("out", new long[3])[2];
            positionMap = newPositionMap;
        }
        
        System.out.println(ans);
    }
}
