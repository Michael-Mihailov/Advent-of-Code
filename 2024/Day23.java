import java.util.*;
import java.io.*;

public class Day23
{
    public static void main(String[] args) throws IOException
    {
        File file = new File("input.txt");
        Scanner scn = new Scanner(file);
        
        /*
        int oneGroup = 0;
        int twoGroup = 0;
        int threeGroup = 0;
        */
        int ans = 0;
        
        ArrayList<String> biggestGroup = new ArrayList();
        
        HashMap<String, HashSet<String>> connections = new HashMap();
        
        while (scn.hasNextLine())
        {
            String[] data = scn.nextLine().split("-");
            
            HashSet<String> set = connections.getOrDefault(data[0], new HashSet());
            set.add(data[1]);
            connections.put(data[0], set);
            
            set = connections.getOrDefault(data[1], new HashSet());
            set.add(data[0]);
            connections.put(data[1], set);
        }
        
        for (String key : connections.keySet())
        {
            ArrayList<String> members = new ArrayList();
            members.add(key);
            
            members = fillMembers(members, connections, new HashSet());
            
            if (members.size() > biggestGroup.size()) biggestGroup = members;
        }
        
        ans = biggestGroup.size();
        System.out.println("size: " + ans);
        
        Collections.sort(biggestGroup);
        for (int i = 0; i < ans; i++) System.out.print(biggestGroup.get(i) + ",");
        
        
        /*
        for (String key : connections.keySet())
        {
            if (key.charAt(0) != 't') continue;

            HashSet<String> con1 = connections.get(key);
            for (String c1 : con1)
            {
                HashSet<String> con2 = connections.get(c1);
                for (String c2 : con2)
                {
                    if (con1.equals(con2)) continue;
                    
                    if (con1.contains(c2))
                    {
                        int numGood = 1;
                        if (c1.charAt(0) == 't') numGood++;
                        if (c2.charAt(0) == 't') numGood++;
                        
                        if (numGood == 1) oneGroup++;
                        if (numGood == 2) twoGroup++;
                        if (numGood == 3) threeGroup++;
                    }
                }
            }
        }
        
        System.out.println(oneGroup);
        System.out.println(twoGroup);
        System.out.println(threeGroup);
        
        ans = (oneGroup / 2) + (twoGroup / 4) + (threeGroup / 6);
        System.out.println(ans);
        */
    }
    
    public static ArrayList<String> fillMembers(ArrayList<String> members, HashMap<String, HashSet<String>> connections, HashSet<String> blackList)
    {
        ArrayList<String> biggest = members;
        
        for (String member : members)
        {
            for (String reference : connections.get(member))
            {
                if (blackList.contains(reference)) continue;
                if (members.contains(reference)) continue;
                if (connections.get(reference).containsAll(members) == false) continue;
                
                ArrayList<String> temp = new ArrayList(members);
                temp.add(reference);
                temp = fillMembers(temp, connections, blackList);
                
                blackList.add(reference);
                
                if (temp.size() > biggest.size()) biggest = temp;
            }
        }
        
        return biggest;
    }
}