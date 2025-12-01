import java.util.*;
import java.io.*;

public class Day1
{
    public static void main(String[] args) throws IOException
    {
        File file = new File("input.txt");
        Scanner scn = new Scanner(file);
        
        int ans = 0;
        
        int num = 50;
        
        while (scn.hasNextLine())
        {
            String line = scn.nextLine();
            char c = line.charAt(0);
            int dist = Integer.parseInt(line.substring(1));
            
            if (c == 'L')
            {
                for (int i = 0; i < dist; i++)
                {
                    num--;
                    num = (num + 100) % 100;
                    if (num == 0) ans++;
                }
            }
            else
            {
                for (int i = 0; i < dist; i++)
                {
                    num++;
                    num = (num + 100) % 100;
                    if (num == 0) ans++;
                }
            }
        }
        
        System.out.println(ans);
    }
}