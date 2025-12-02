import java.util.*;
import java.io.*;

public class Day2
{
    public static void main(String[] args) throws IOException
    {
        File file = new File("input.txt");
        Scanner scn = new Scanner(file);
        
        long ans = 0;
        String[] lines = scn.nextLine().split(",");
        
        for (String line : lines)
        {
            long num1 = Long.parseLong(line.substring(0, line.indexOf('-')));
            long num2 = Long.parseLong(line.substring(1 + line.indexOf('-')));
            
            for (long id = num1; id <= num2; id++)
            {
                boolean addId = false;
                String sid = id + "";
                for (int subLength = 1; subLength < sid.length(); subLength++)
                {
                    if (sid.length() % subLength != 0) continue;
                    boolean fail = false;
                    String subId = sid.substring(0, subLength);
                    
                    for (int i = 0; i < sid.length(); i++)
                    {
                        if (sid.charAt(i) != subId.charAt(i % subLength)) fail = true;
                    }
                    if (fail == false) addId = true;
                }
                
                if (addId) ans += id;
            }
        }
        
        System.out.println(ans);
    }
}