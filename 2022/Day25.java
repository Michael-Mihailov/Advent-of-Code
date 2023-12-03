
import java.io.*;
import java.util.*;

public class Day25
{
    public static void main (String[] args) throws Exception
    {
        File file = new File("input.txt");
        Scanner scn = new Scanner(file);
        
        long count = 0;
        
        long pos = 0;
        long neg = 0;
        
        while (scn.hasNext())
        {
            String data = scn.nextLine();
            
            String posString = "";
            String negString = "";
            
            for (int i = 1; i <= data.length(); i++)
            {
                if (data.charAt(data.length() - i) == '2')
                {
                    posString = 2 + posString;
                    negString = 0 + negString;
                }
                else if (data.charAt(data.length() - i) == '1')
                {
                    posString = 1 + posString;
                    negString = 0 + negString;
                }
                else if (data.charAt(data.length() - i) == '0')
                {
                    posString = 0 + posString;
                    negString = 0 + negString;
                }
                else if (data.charAt(data.length() - i) == '-')
                {
                    posString = 0 + posString;
                    negString = 1 + negString;
                }
                else if (data.charAt(data.length() - i) == '=')
                {
                    posString = 0 + posString;
                    negString = 2 + negString;
                }
                else
                {
                    System.out.println("uh oh");
                }
            }
            
            count += Long.parseLong(posString, 5);
            count -= Long.parseLong(negString, 5);
        }
        
        System.out.println(count);
        System.out.println(Long.parseLong("20000020002000001002", 5) - Long.parseLong("00222100100020000100", 5));
    }
}

