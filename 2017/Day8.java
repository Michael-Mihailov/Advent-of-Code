
/**
 * Write a description of class Day8 here.
 *
 * @author (your name)
 * @version (a version number or a date)
 */

import java.util.*;
import java.io.*;

public class Day8
{
    public static void main(String args[]) throws IOException
    {
        File file = new File("input.txt");
        Scanner scn = new Scanner(file);
        
        HashMap<String, Integer> values = new HashMap();
        
        
        int max = Integer.MIN_VALUE;
        
        
        
        while (scn.hasNext())
        {
            String data = scn.nextLine();
            Scanner dataScn = new Scanner(data);
            
            String v1 = dataScn.next();
            String op1 = dataScn.next();
            int n1 = dataScn.nextInt();
            dataScn.next();
            String v2 = dataScn.next();
            String op2 = dataScn.next();
            int n2 = dataScn.nextInt();
            
            if (values.containsKey(v1) == false)
            {
                values.put(v1, 0);
            }
            if (values.containsKey(v2) == false)
            {
                values.put(v2, 0);
            }
            
            
            boolean go = false;
            
            if (op2.equals("<") && values.get(v2) < n2) go = true;
            if (op2.equals("<=") && values.get(v2) <= n2) go = true;
            if (op2.equals(">") && values.get(v2) > n2) go = true;
            if (op2.equals(">=") && values.get(v2) >= n2) go = true;
            if (op2.equals("==") && values.get(v2) == n2) go = true;
            if (op2.equals("!=") && values.get(v2) != n2) go = true;
            
            if (go)
            {
                if (op1.equals("inc"))
                {
                    values.put(v1, values.get(v1) + n1);
                }
                else
                {
                    values.put(v1, values.get(v1) - n1);
                }
            }
            
            for (String key : values.keySet())
            {
                if (values.get(key) > max)
                {
                    max = values.get(key);
                }
            }
        }
        
        
        
        
        System.out.println(max);
    }
}
