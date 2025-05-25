import java.util.*;
import java.io.*;

public class Day14
{
    public static void main(String args[]) throws IOException
    {
        File file = new File("input.txt");
        Scanner scn = new Scanner(file);
        
        long ans = 0;
        
        HashMap<Long, Long> memory = new HashMap();
        
        String mask = "";
        
        while (scn.hasNextLine())
        {
            String line = scn.nextLine();
            
            if (line.contains("mask"))
            {
                mask = line.substring(line.indexOf('=') + 2);
            }
            else
            {
                long input = Long.parseLong( line.substring(line.indexOf('[')+1, line.indexOf(']')) );
                long value = Long.parseLong( line.substring(line.indexOf('=') + 2) );
                
                ArrayList<Long> addresses = applyMask(input, mask);
                for (long address : addresses)
                {
                    memory.put(address, value);    
                }
            }
        }
        
        for (long address : memory.keySet())
        {
            ans += memory.get(address);
        }
        
        System.out.println(ans);
    }
    
    public static ArrayList<Long> applyMask(long value, String mask)
    {
        long base = 0;
        
        ArrayList<Long> res = new ArrayList();
        
        for (int i = 0; i < 36; i++)
        {
            if (mask.charAt(35 - i) == '1')
            {                
                base += Math.pow(2, i);
            }
            else if (mask.charAt(35 - i) == '0')
            {
                base += ((value % Math.pow(2, i + 1)) >= Math.pow(2, i)) ? Math.pow(2, i) : 0; 
            }
        }
        res.add(base);
        
        for (int i = 0; i < 36; i++)
        {
            if (mask.charAt(35 - i) == 'X')
            {
                for (int j = res.size() - 1; j >= 0; j--)
                {
                    res.add( res.get(j) + (long)Math.pow(2,i) );
                }
            }
        }
        
        return res;
    }
}