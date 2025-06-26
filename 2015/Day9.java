import java.util.*;
import java.io.*;

public class Day9
{
    public static void main(String args[]) throws IOException
    {
        File file = new File("input.txt");
        Scanner scn = new Scanner(file);
        
        HashSet<String> locations = new HashSet();
        HashMap<String, HashMap<String, Integer>> travelMap = new HashMap();
        
        while (scn.hasNextLine())
        {
            String line = scn.nextLine();
            String[] data = line.split(" "); // String[5]
            
            travelMap.putIfAbsent(data[0], new HashMap());
            travelMap.putIfAbsent(data[2], new HashMap());
            
            travelMap.get(data[0]).put(data[2], new Integer(data[4]));
            travelMap.get(data[2]).put(data[0], new Integer(data[4]));
            
            locations.add(data[0]);
            locations.add(data[2]);
        }
        
        int ans = 0;
        for (String start : locations)
        {
            int cost = distance(start, new HashSet(), locations, travelMap);
            if (cost > ans) ans = cost;
        }
        
        System.out.println(ans);
    }
    
    public static int distance (String location, HashSet<String> visited, HashSet<String> locations, HashMap<String, HashMap<String, Integer>> travelMap)
    {
        visited = new HashSet(visited);
        visited.add(location);
        if (visited.size() == locations.size()) return 0;
        
        HashMap<String, Integer> paths = travelMap.get(location);
        int bestCost = 0;
        for (String option : paths.keySet())
        {
            if (visited.contains(option)) continue;
            int cost = paths.get(option) + distance(option, visited, locations, travelMap);
            if (cost > bestCost) bestCost = cost;
        }
        
        if (bestCost == 0) return -1000000;
        return bestCost;
    }
}