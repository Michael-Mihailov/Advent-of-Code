import java.util.*;
import java.io.*;

public class Day2
{
    public static void main(String[] args) throws IOException
    {
        File file = new File("input.txt");
        Scanner scn1 = new Scanner(file);
        
        
        // part 1 stuff
        /*
        int answer = 0;
        
        int n2 = 0; 
        int n3 = 0;
        */
        
        while (scn1.hasNext())
        {
            String data = scn1.next();
            Scanner scn2 = new Scanner(file);
            
            while(scn2.hasNext())
            {
                String data2 = scn2.next();
                
                int diff = 0;
                
                for (int i = 0; i < data.length(); i++)
                {
                    if (data.charAt(i) != data2.charAt(i))
                    {
                        diff++;
                    }
                }
                
                if (diff == 1)
                {
                    System.out.println();
                    System.out.println(data);
                    System.out.println(data2);
                }
            }
            
            // part 1 stuff
            /*
            String data = scn.next();
            
            boolean got2 = false;
            boolean got3 = false;
            
            int count = 0;
            
            for (int i = 0; i < data.length(); i++)
            {
                count = 0;
                
                for (int j = 0; j < data.length(); j++)
                {
                    if (data.charAt(i) == data.charAt(j))
                    {
                        count++;
                    }
                }
                
                if (count == 2)
                    got2 = true;
                if (count == 3)
                    got3 = true;
            }
            
            if (got2)
            {
                n2++;
                //System.out.println(data);
            }
            if (got3)
            {
                n3++;
                //System.out.println(data);
            }
            */
        }
        
        //System.out.println(n2 + "*" + n3 + "=" + (n2 * n3));
    }
}
