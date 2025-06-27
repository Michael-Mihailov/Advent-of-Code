import java.util.*;
import java.io.*;

public class Day13
{
    public static void main(String args[]) throws IOException
    {
        File file = new File("input.txt");
        Scanner scn = new Scanner(file);
        
        HashSet<String> names = new HashSet();
        HashMap<String, HashMap<String, Integer>> happyMap = new HashMap();
        
        while (scn.hasNextLine())
        {
            String line = scn.nextLine();
            line = line.substring(0, line.length() - 1);
            
            String[] data = line.split(" "); // String[11]
            
            names.add(data[0]);
            names.add(data[10]);
            
            happyMap.putIfAbsent(data[0], new HashMap());
            happyMap.putIfAbsent(data[10], new HashMap());
            
            happyMap.get(data[0]).put(data[10], (data[2].equals("gain") ? 1 : -1) * Integer.parseInt(data[3]) );            
        }
        
        names.add("Yourself");
        happyMap.put("Yourself", new HashMap());
        
        int ans = seat(names, new ArrayDeque(), happyMap);
        System.out.println(ans);
    }
    
    public static int seat (HashSet<String> remaining, ArrayDeque<String> order, HashMap<String, HashMap<String, Integer>> happyMap)
    {
        if (remaining.size() == 0) // "close" the seating loop
        {
            String head = order.peekFirst();
            String tail = order.peekLast();
            
            int res = 0;
            res += happyMap.get(head).getOrDefault(tail, 0) + happyMap.get(tail).getOrDefault(head, 0);
            
            while (order.size() > 0) 
            {
                String current = order.remove();
                String next = order.peek();
                
                int num = 0; 
                if (next != null) num = happyMap.get(current).getOrDefault(next, 0) + happyMap.get(next).getOrDefault(current, 0);
                                
                res += num;
            }
            
            return res;
        }
        
        int bestAnswer = Integer.MIN_VALUE;
        String head = order.peekFirst();
        for (String person : remaining)
        {
            HashSet<String> newRemaining = new HashSet(remaining);
            newRemaining.remove(person);
            
            ArrayDeque<String> newOrder = new ArrayDeque(order);
            newOrder.add(person);
            
            
            int pathAnswer = seat(newRemaining, newOrder, happyMap);
            
            if (pathAnswer > bestAnswer) bestAnswer = pathAnswer;
        }
        
        return bestAnswer;
    }
}