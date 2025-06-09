import java.util.*;
import java.io.*;

public class Day17
{
    public static void main(String args[]) throws IOException
    {
        File file = new File("input.txt");
        Scanner scn = new Scanner(file);
        
        int ans = 0;
        
        HashSet<String> spaceMap = new HashSet();
        
        for (int y = 0; scn.hasNextLine(); y++)
        {
            String line = scn.nextLine();
            for (int x = 0; x < line.length(); x++)
            {
                if (line.charAt(x) == '#')
                {
                    spaceMap.add(x + " " + y + " " + 0 + " " + 0);
                }
            }
        }
        
        for (int step = 0; step < 6; step++)
        {
            HashSet<String> candidates = new HashSet();
            
            for (String active : spaceMap)
            {
                int[] pos = parseString(active);
                
                for (int x = -1; x <= 1; x++)
                {
                    for (int y = -1; y <= 1; y++)
                    {
                        for (int z = -1; z <= 1; z++)
                        {
                            for (int w = -1; w <= 1; w++)
                            {
                                String candidate = (pos[0] + x) + " " + (pos[1] + y) + " " + (pos[2] + z) + " " + (pos[3] + w);
                                candidates.add(candidate);
                            }
                        }
                    }
                }
            }
            
            HashSet<String> newSpaceMap = new HashSet();
            
            for (String candidate : candidates)
            {
                int[] pos = parseString(candidate);
                
                int numActive = 0;
                
                for (int x = -1; x <= 1; x++)
                {
                    for (int y = -1; y <= 1; y++)
                    {
                        for (int z = -1; z <= 1; z++)
                        {
                            for (int w = -1; w <= 1; w++)
                            {
                                if (x == 0 && y == 0 && z == 0 && w == 0) continue;
                                
                                String check = (pos[0] + x) + " " + (pos[1] + y) + " " + (pos[2] + z) + " " + (pos[3] + w);
                                if (spaceMap.contains(check))
                                {
                                    numActive++;
                                }
                            }
                        }
                    }
                }
                
                if (numActive == 3 || (spaceMap.contains(candidate) && (numActive == 2)))
                {
                    newSpaceMap.add(candidate);
                }
            }
            
            spaceMap = newSpaceMap;
        }
        
        ans = spaceMap.size();
        System.out.println(ans);
    }
    
    public static int[] parseString(String posString)
    {
        String[] split = posString.split(" ");
        int[] res = new int[split.length];
        for (int i = 0; i < split.length; i++) res[i] = Integer.parseInt(split[i]);
        
        return res;
    }
}