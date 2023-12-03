import java.util.*;
import java.io.*;

public class Day2
{
    public static void main(String[] args) throws IOException
    {
        File file = new File("input.txt");
        Scanner scn = new Scanner(file);
        
        int answer = 0;

        /*
        HashMap<String, Integer> maxMap = new HashMap();
        maxMap.put("red", 12);
        maxMap.put("green", 13);
        maxMap.put("blue", 14);
        */
        
        while (scn.hasNextLine())
        {
            String data = scn.nextLine();
            
            
            //boolean possible = true;
            
            
            data = data.replace("Game ", "");
            data = data.replace(":", "");
            data = data.replace(",", "");
            data = data.replace("; ", " ; "); // may need to change for part 2;
            
            Scanner dataScn = new Scanner(data);
            
            
            int id = dataScn.nextInt();
            
            HashMap<String, Integer> map1 = new HashMap();
            map1.put("red", 0);
            map1.put("green", 0);
            map1.put("blue", 0);
            HashMap<String, Integer> map2 = new HashMap();
            map2.put("red", 0);
            map2.put("green", 0);
            map2.put("blue", 0);
            HashMap<String, Integer> currentMap = map1;
            
            
            while (dataScn.hasNext())
            {
                String next = dataScn.next();
                
                //System.out.println(next);
                
                if (next.equals(";"))
                {
                    map1.put("red", (map1.get("red") > map2.get("red")) ? map1.get("red") : map2.get("red"));
                    map1.put("green", (map1.get("green") > map2.get("green")) ? map1.get("green") : map2.get("green"));
                    map1.put("blue", (map1.get("blue") > map2.get("blue")) ? map1.get("blue") : map2.get("blue"));
                    
                    currentMap = map2;
                    map2.put("red", 0);
                    map2.put("green", 0);
                    map2.put("blue", 0);
                }
                else
                {
                    int num = Integer.parseInt(next);
                    String color = dataScn.next();
                    
                    currentMap.put(color, num);
                }
                
                map1.put("red", (map1.get("red") > map2.get("red")) ? map1.get("red") : map2.get("red"));
                map1.put("green", (map1.get("green") > map2.get("green")) ? map1.get("green") : map2.get("green"));
                map1.put("blue", (map1.get("blue") > map2.get("blue")) ? map1.get("blue") : map2.get("blue"));
            }

            
            int power = 1;
            power *= map1.get("red");
            power *= map1.get("green");
            power *= map1.get("blue");
            //System.out.println("power: " + power);
            
            
            /*
            if (possible)
            {
                answer += id;
            }
            */
           
            answer += power;
        }
        
        System.out.println(answer);
    }
}
