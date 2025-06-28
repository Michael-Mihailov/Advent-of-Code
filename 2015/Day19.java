import java.util.*;
import java.io.*;

public class Day19
{
    public static void main(String args[]) throws IOException
    {
        File file = new File("input.txt");
        Scanner scn = new Scanner(file);
        
        int ans = 0;
        
        HashMap<String, HashSet<String>> replaceMap = new HashMap();
        HashSet<String> possibilities = new HashSet();
        
        
        for (String line = scn.nextLine(); line.equals("") == false; line = scn.nextLine())
        {
            String[] data = line.split(" ");
            replaceMap.putIfAbsent(data[2], new HashSet());
            replaceMap.get(data[2]).add(data[0]);
        }
        possibilities.add(scn.nextLine());
        String goal = "e";
        
        while (possibilities.contains(goal) == false)
        {
            ans++;            
            HashSet<String> updatedPossibilities = new HashSet();
            
            System.out.println("Step: " + ans + " :: Possibilities: " + possibilities.size());
            
            for (String original : possibilities)
            {
                if (updatedPossibilities.size() > 1000000) break; // with on million paths, one should be optimal
                
                for (String key : replaceMap.keySet())
                {
                    if (original.contains(key) == false) continue;
                    for (int i = 0; i <= original.length() - key.length(); i++)
                    {
                        boolean found = true;
                        for (int j = 0; j < key.length(); j++)
                        {
                            if (original.charAt(i + j) != key.charAt(j)) found = false;
                        }
                        if (found)
                        {
                            String startPart = original.substring(0, i);
                            String endPart = original.substring(i + key.length());
                            for (String middlePart : replaceMap.get(key))
                            {
                                String temp = startPart + middlePart + endPart;
                                if (possibilities.contains(temp)) continue;
                                if (temp.contains("e") && temp.length() > 1) continue;
                                updatedPossibilities.add(temp);
                            }
                        }
                    }
                }
            }
            
            possibilities = updatedPossibilities;
        }
        
        System.out.println("Answer: " + ans);
    }
}