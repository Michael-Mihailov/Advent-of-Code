import java.util.*;
import java.io.*;


public class Day3
{
    public static void main(String[] args) throws IOException
    {
        File file = new File("input.txt");
        Scanner scn = new Scanner(file);
        
        long ans = 0;
        
        while (scn.hasNextLine())
        {
            String line = scn.nextLine();
            
            for (int digit = 11; digit >= 0; digit--)
            {
                int d = findLarge(line.substring(0, line.length() - digit));
                line = line.substring( line.indexOf(d + "") + 1 );
                ans += d * Math.pow(10, digit);
            }
        }
        
        System.out.println(ans);
    }
    
    public static int findLarge(String s)
    {
        int temp = 0;
        for (int i = 0; i < s.length(); i++)
        {
            if (s.charAt(i) - '0' > temp) temp = s.charAt(i) - '0';
        }
        return temp;
    }
}
