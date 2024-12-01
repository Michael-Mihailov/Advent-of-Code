import java.util.*;
import java.io.*;

public class Day4
{
    public static void main(String args[]) throws IOException
    {
        File file = new File("input.txt");
        Scanner scn = new Scanner(file);
        
        int ans = 0;
        
        int min = 359282;
        int max = 820401;
        
        for (int num = min; num <= max; num++)
        {
            boolean incFlag = true;
            boolean twinFlag = false;
            
            String numStr = num + "";
            
            for (int i = 1; i < 6; i++)
            {
                if (numStr.charAt(i-1) > numStr.charAt(i)) incFlag = false;
                if (numStr.charAt(i-1) == numStr.charAt(i) && !(i != 1 && numStr.charAt(i-2) == numStr.charAt(i)) && !(i != 5 && numStr.charAt(i+1) == numStr.charAt(i))) twinFlag = true;

            }
            if (incFlag && twinFlag) ans++;
        }
        
        System.out.println(ans);
    }
}
