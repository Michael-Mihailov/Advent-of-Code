import java.util.*;
import java.io.*;

public class Day7
{
    public static void main(String args[]) throws IOException
    {
        File file = new File("input.txt");
        Scanner scn = new Scanner(file);
        
        HashMap<String, HashMap<String, Integer>> bagMap = new HashMap();
        
        while (scn.hasNextLine())
        {
            String line = scn.nextLine();
            
            if (line.contains("no other bags.")) continue;
            
            line = line.replace(".", "");
            line = line.replace(" bags", "");
            line = line.replace(" bag", "");
            line = line.replace(" contain", ",");
            
            String[] data = line.split(", ");
            
            HashMap<String, Integer> bag = new HashMap();
            for (int i = 1; i < data.length; i++)
            {
                int num = Integer.parseInt( data[i].substring(0, data[i].indexOf(" ")) );
                String color = data[i].substring(data[i].indexOf(" ") + 1);
                
                bag.put(color, num);
            }
            
            String color = data[0];
            bagMap.putIfAbsent(color, bag);
        }
        
        HashSet<String> validBags = new HashSet();
        validBags.add("shiny gold");
        
        for (int step = 0; step < 1000; step++)
        {
            for (String bag : bagMap.keySet())
            {
                if (validBags.contains(bag)) continue;
                for (String innerBag : bagMap.get(bag).keySet())
                {
                    if (validBags.contains(innerBag))
                    {
                        validBags.add(bag);
                        break;
                    }
                }
            }
        }
        
        // int ans = validBags.size() - 1; // Part 1 Answer
        int ans = totalSize("shiny gold", bagMap) - 1; // Part 2 Answer
        
        System.out.println(ans);
    }
    
    public static int totalSize(String bag, HashMap<String, HashMap<String, Integer>> bagMap)
    {
        int res = 1;
        
        if (bagMap.containsKey(bag))
        {
            for (String innerBag : bagMap.get(bag).keySet())
            {
                res += bagMap.get(bag).get(innerBag) * totalSize(innerBag, bagMap);
            }
        }
        
        return res;
    }
}