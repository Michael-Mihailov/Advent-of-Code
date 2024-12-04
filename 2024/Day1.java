import java.util.*;
import java.io.*;

public class Day1
{
    public static void main(String[] args) throws IOException
    {
        File file = new File("input.txt");
        Scanner scn = new Scanner(file);
        
        long ans = 0;
        
        ArrayList<Integer> l1 = new ArrayList();
        ArrayList<Integer> l2 = new ArrayList();
        
        HashMap<String, Integer> numMap = new HashMap();
        
        while (scn.hasNextLine())
        {
            int s1 = scn.nextInt();
            int s2 = scn.nextInt();
            l1.add(s1);
            l2.add(s2);
            
            if (numMap.containsKey(s2 + "") == false)
            {
                numMap.put(s2 + "", 1);
            }
            else
            {
                numMap.put(s2 + "", 1 + numMap.get(s2 + ""));
            }
        }
        
        boolean done = false;
        while (done == false)
        {
            done = true;
            for (int i = 1; i < l1.size(); i++)
            {
                if (l1.get(i - 1) > l1.get(i))
                {
                    int temp = l1.get(i);
                    l1.set(i, l1.get(i-1));
                    l1.set(i - 1, temp);
                    
                    done = false;
                }
                
                if (l2.get(i - 1) > l2.get(i))
                {
                    int temp = l2.get(i);
                    l2.set(i, l2.get(i-1));
                    l2.set(i - 1, temp);
                    
                    done = false;
                }
            }
        }
        
        
        
        
        for (int i = 0; i < l1.size(); i++)
        {
            // ans += Math.abs(l1.get(i) - l2.get(i));

            if (numMap.containsKey(l1.get(i) + ""))
                ans += l1.get(i) * numMap.get(l1.get(i) + "");
        }
        
        System.out.println(ans);
    }
}
