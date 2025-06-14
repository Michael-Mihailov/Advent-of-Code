import java.util.*;
import java.io.*;

public class Day19
{
    public static void main(String args[]) throws IOException
    {
        File file = new File("input.txt");
        Scanner scn = new Scanner(file);
        
        int ans = 0;
        
        HashMap<Integer, int[][]> ruleMap = new HashMap(); // -1 --> "a" :: -2 --> "b"
        
        for (String line = scn.nextLine(); line.equals("") == false; line = scn.nextLine())
        {
            line = line.replace(" | ", "@");
            
            int rule = Integer.parseInt(line.substring(0, line.indexOf(":")));
            
            if (line.contains("a"))
            {
                ruleMap.put(rule, new int[][]{{-1}});
                continue;
            }
            else if (line.contains("b"))
            {
                ruleMap.put(rule, new int[][]{{-2}});
                continue;
            }
            
            line = line.substring(line.indexOf(":") + 2);
            String[] stringOptions = line.split("@");
            
            int[][] paths = new int[stringOptions.length][];
            for (int i = 0; i < paths.length; i++)
            {
                String[] stringOrder = stringOptions[i].split(" ");
                int[] order = new int[stringOrder.length];
                for (int j = 0; j < order.length; j++)
                {
                    order[j] = Integer.parseInt(stringOrder[j]);
                }
                paths[i] = order;
            }
            
            ruleMap.put(rule, paths);
        }
        
        HashSet<String> messages = new HashSet();
        while (scn.hasNextLine())
        {
            String line = scn.nextLine();
            messages.add(line);
        }
        
        HashSet<String> solutions = solve(0, ruleMap, messages, 0);
        
        for (String message : messages)
        {
            if (solutions.contains(message))
            {
                ans++;
            }
        }
        
        System.out.println(ans);
    }
    
    public static HashSet<String> solve(int rule, HashMap<Integer, int[][]> ruleMap, HashSet<String> messages, int depth)
    {
        HashSet<String> res = new HashSet();
        
        if (depth > 50)
        {
            res.add("x");
            return res;
        }
        if (ruleMap.get(rule)[0][0] == -1)
        {
            res.add("a");
            return res;
        }
        if (ruleMap.get(rule)[0][0] == -2)
        {
            res.add("b");
            return res;
        }
        
        for (int[] path : ruleMap.get(rule))
        {
            HashSet<String> pathSolution = new HashSet();
            pathSolution.add("");
            for (int order : path)
            {
                HashSet<String> orderSolution = solve(order, ruleMap, messages, depth + 1);
                HashSet<String> newPathSolution = new HashSet();
                
                for (String s : pathSolution)
                {
                    for (String t : orderSolution)
                    {
                        String temp = s + t;
                        if (temp.contains("x")) continue;
                        boolean pass = false;
                        for (String message : messages)
                        {
                            if (message.contains(temp) == true)
                            {
                                pass = true;
                                break;
                            }
                        }
                        if (pass) newPathSolution.add(temp);
                    }
                }
                
                pathSolution = newPathSolution;
            }
            
            res.addAll(pathSolution);
        }
        
        return res;
    }
}