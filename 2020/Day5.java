
import java.util.*;
import java.io.*;

public class Day5
{
    public static void main(String args[]) throws IOException
    {
        File file = new File("input.txt");
        Scanner scn = new Scanner(file);

        int ans = 0;
        HashSet<Integer> seats = new HashSet();
        
        while (scn.hasNextLine())
        {
            String pass = scn.nextLine();
            
            int num = id(pass);
            
            seats.add(num);
        }
        
        for (int i = 0; i < id("BBBBBBBRRR"); i++)
        {
            if (seats.contains(i) == false && seats.contains(i - 1) == true && seats.contains(i + 1) == true)
            {
                ans = i;
            }
        }
        
        System.out.println(ans);
    }
    
    public static int id(String pass)
    {
        int res = 0;
        
        for (int i = 0; i < 10; i++)
        {
            if (pass.charAt(i) == 'B' || pass.charAt(i) == 'R')
            {
                res += Math.pow(2, 9 - i);
            }
        }
        
        return res;
    }
}
