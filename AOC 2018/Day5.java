import java.util.*;
import java.io.*;

public class Day5
{
    public static void main(String[] args) throws IOException
    {
        File file = new File("input.txt");
        Scanner scn = new Scanner(file);
        
        int answer = Integer.MAX_VALUE;
        
        String ogPoly = scn.nextLine();
        
        for (int c = 'a'; c <= 'z'; c++)
        {
            String poly = ogPoly.replaceAll(((char)c)+"", "");
            poly = poly.replaceAll(((char)(c - 32))+"", "");
            
            boolean flag = false;
            while (flag == false)
            {
                flag = true;
                
                for (int i = 0; i < poly.length() - 1; i++)
                {
                    if ( Math.abs(poly.charAt(i) - poly.charAt(i+1)) == 32)
                    {
                        poly = poly.substring(0, i) + poly.substring(i+2);
                        flag = false;
                    }
                }
            }
            
            if (poly.length() < answer)
                answer = poly.length();
        }
        
        
        System.out.println(answer);
    }
}
