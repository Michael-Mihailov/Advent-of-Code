import java.util.*;
import java.io.*;

public class Day2
{
    public static void main(String[] args) throws IOException
    {
        File file = new File("input.txt");
        Scanner scn = new Scanner(file);
        
        int ans = 0;
        
        while (scn.hasNextLine())
        {
            String[] dataFull = scn.nextLine().split(" ");
            
            boolean found = false;
            
            for (int j = 0; j < dataFull.length; j++)
            {
                String[] data = new String[dataFull.length - 1];
                for (int i = 0; i < data.length; i++)
                {
                    if (i < j)
                    {
                        data[i] = dataFull[i];
                    }
                    else if (i >= j)
                    {
                        data[i] = dataFull[i + 1];
                    }
                }
                
                
                boolean up = true;
                boolean down = true;
                int num = Integer.parseInt(data[0]);
                for (int i = 1; i < data.length; i++)
                {
                    
                    if ((Integer.parseInt(data[i]) > num && Integer.parseInt(data[i]) <= num + 3) == false)
                    {
                        up = false;
                    }
                    if ((Integer.parseInt(data[i]) < num && Integer.parseInt(data[i]) >= num - 3) == false)
                    {
                        down = false;
                    }
                    num = Integer.parseInt(data[i]);
                }
                
                if (up || down) found = true;
            }
            if (found) ans++;
        }
        
        System.out.println(ans);
    }
}