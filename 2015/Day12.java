import java.util.*;
import java.io.*;

public class Day12
{
    public static void main(String args[]) throws IOException
    {
        File file = new File("input.txt");
        Scanner scn = new Scanner(file);
        
        int ans = 0;
        
        String data = scn.nextLine();
        
        
        for (int i = 0; i < data.length(); i++)
        {
            if (data.charAt(i) == '{')
            {
                int objectLength = subObjectLength(data.substring(i));
                String object = data.substring(i, i + objectLength);
                if (findRed(object))
                {
                    data = data.substring(0, i) + "[]" + data.substring(i + objectLength);
                }
            }
        }
        
                
        String currentNum = "";
        for (int i = 0; i < data.length(); i++)
        {
            if (data.charAt(i) >= '0' && data.charAt(i) <= '9')
            {
                currentNum = currentNum + data.charAt(i);
            }
            else if (currentNum.length() > 0)
            {
                if (currentNum.equals("-") == false)
                    ans += Integer.parseInt(currentNum);
                currentNum = "";
            }
            
            if (data.charAt(i) == '-')
            {
                currentNum = "-";
            }
        }
        
        System.out.println(ans);
    }
    
    public static boolean findRed (String object)
    {
        String newObj = "";
        int depth = 0;
        for (int i = 0; i < object.length(); i++)
        {
            if (object.charAt(i) == ']' || object.charAt(i) == '}')
            {
                depth--;
            }
            
            if (depth == 1) newObj = newObj + object.charAt(i);
            
            if (object.charAt(i) == '[' || object.charAt(i) == '{')
            {
                depth++;
            }
        }
        
        return newObj.contains( "\"red\"" );
    }
    
    public static int subObjectLength(String data)
    {
        int depth = 0;
        for (int i = 0; i < data.length(); i++)
        {
            if (data.charAt(i) == '{') depth++;
            else if (data.charAt(i) == '}') depth--;
            
            if (depth == 0) return i + 1;
        }
        
        System.out.println("ERROR");
        return -1;
    }
}