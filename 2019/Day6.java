import java.util.*;
import java.io.*;

public class Day6
{
    public static void main(String args[]) throws IOException
    {
        File file = new File("input.txt");
        Scanner scn = new Scanner(file);
        
        HashMap<String, ArrayList<String>> orbitMap = new HashMap<>();
        HashMap<String, String> parentMap = new HashMap();
        
        
        while(scn.hasNextLine())
        {
            String line = scn.nextLine();
            String[] planets = new String[]{ line.substring(0, 3), line.substring(4) };
            
            if (orbitMap.containsKey(planets[0]) == false)
            {
                orbitMap.put(planets[0], new ArrayList<String>());
            }
            orbitMap.get(planets[0]).add(planets[1]);
                        
            parentMap.put(planets[1], planets[0]);
        }
        
        // System.out.println(totalOrbits(orbitMap, "COM", 0)); // part 1 ans
        
        int ans = 0;
        String pos = parentMap.get("YOU");
        String dest = parentMap.get("SAN");
        
        boolean done = false;
        while (!done)
        {
            if (distToChild(orbitMap, pos, dest) >= 0)
            {
                ans += distToChild(orbitMap, pos, dest);
                done = true;
            }
            else
            {
                ans++;
                pos = parentMap.get(pos);
            }
        }
        
        if (pos.equals(dest)) ans--; // special case
        System.out.println(ans);
    }
    
    public static int distToChild(HashMap<String, ArrayList<String>> orbitMap, String pos, String dest)
    {        
        // System.out.println(pos + " " + dest + " " + orbitMap.containsKey(pos) + " " + pos.equals(dest));
        
        if (pos.equals(dest)) return 0;
        
        if (orbitMap.containsKey(pos))
        {
            for (String planet : orbitMap.get(pos))
            {
                int temp = distToChild(orbitMap, planet, dest);
                if (temp >= 0) return 1 + temp;
            }
        }

        return Integer.MIN_VALUE;
    }
    
    public static int totalOrbits(HashMap<String, ArrayList<String>> orbitMap, String pos, int depth)
    {
        int ans = depth;
        depth++;
        
        if (orbitMap.containsKey(pos))
        {
            for (String planet : orbitMap.get(pos))
            {
                ans += totalOrbits(orbitMap, planet, depth);
            }
        }
        
        return ans;
    }
}
