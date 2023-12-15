import java.util.*;
import java.io.*;

public class Day15
{
    public static void main(String[] args) throws IOException
    {
        File file = new File("input.txt");
        Scanner scn = new Scanner(file);
        
        int answer = 0;
        
        String[] hashs = scn.nextLine().split(",");
        
        ArrayList<Integer>[] focals = new ArrayList[256];
        ArrayList<String>[] labels = new ArrayList[256];
        
        for (int i = 0; i < 256; i++)
        {
            focals[i] = new ArrayList();
            labels[i] = new ArrayList();
        }
        
        for (String s : hashs)
        {
            if (s.contains("-"))
            {
                s = s.substring(0, s.length() - 1);
                
                int h = hash(s);
                
                
                int index = labels[h].indexOf(s);
                
                if (index != -1)
                {
                    focals[h].remove(index);
                    labels[h].remove(index);
                }
            }
            else if (s.contains("="))
            {
                String index = s.substring(0, s.indexOf("="));
                
                int h = hash(index);
                
                int value = Integer.parseInt(s.substring(s.indexOf("=") + 1));
                
                
                int check = labels[h].indexOf(index);
                if (check == -1)
                {
                    focals[h].add(value);
                    labels[h].add(index);
                }
                else
                {
                    focals[h].set(check, value);
                    labels[h].set(check, index);
                }
            }
            else System.out.println("error");
        }
        
        
        for (int i = 0; i < 256; i++)
        {
            for (int slot = 0; slot < focals[i].size(); slot++)
            {
                int num = (i + 1) * (slot + 1) * focals[i].get(slot);
                answer += num;
            }
        }
        
        System.out.println(answer);
    }
    
    public static int hash(String s)
    {
        int num = 0; 
            
        for (int i = 0; i < s.length(); i++)
        {
            num += s.charAt(i);
            num *= 17;
            num %= 256;
        }
        
        return num;
    }
}
