
import java.io.*;
import java.util.*;

public class Day21
{
    public static void main (String[] args) throws Exception
    {
        File file = new File("input.txt");
        Scanner scn;
        
        HashMap<String, Long> map = new HashMap();
        
        while (map.containsKey("root") == false)
        {
            scn = new Scanner(file);
            while (scn.hasNext())
            {
                String data = scn.nextLine();
                
                Scanner splitScn = new Scanner(data);
                splitScn.useDelimiter(": ");
                
                String left = splitScn.next();
                String right = splitScn.next();
                
                if (map.containsKey(left) == false)
                {
                    if (right.length() < 10 || left.equals("humn")) // if it is an int
                    {
                        map.put(left, new Long(Long.parseLong(right)));
                    }
                    else // if it is a calculation
                    {
                        Scanner calcScn = new Scanner(right);
                        
                        String leftMonkey = calcScn.next();
                        String operation = calcScn.next();
                        String rightMonkey = calcScn.next();
                        
                        if (map.containsKey(leftMonkey) && map.containsKey(rightMonkey))
                        {
                            if (operation.equals("+"))
                            {
                                map.put(left, map.get(leftMonkey) + map.get(rightMonkey)); // might not be allowed to add objects ?????
                            }
                            else if (operation.equals("-"))
                            {
                                map.put(left, map.get(leftMonkey) - map.get(rightMonkey));
                            }
                            else if (operation.equals("*"))
                            {
                                map.put(left, map.get(leftMonkey) * map.get(rightMonkey));
                            }
                            else if (operation.equals("/"))
                            {
                                map.put(left, map.get(leftMonkey) / map.get(rightMonkey)); // possible might want to check if there is a remainder for part 2 ?????
                            }
                            else if (operation.equals("="))
                            {
                               System.out.println(map.get(leftMonkey));
                               System.out.println(map.get(rightMonkey));
                               map.put("root", new Long(0));
                            }
                            else
                            {
                                System.out.println("uh oh");
                            }
                        }
                    }
                }
            }
        }
        
        System.out.println(map.get("root"));
    }
}