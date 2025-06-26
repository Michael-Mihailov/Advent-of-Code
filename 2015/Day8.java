import java.util.*;
import java.io.*;

public class Day8
{
    public static void main(String args[]) throws IOException
    {
        File file = new File("input.txt");
        Scanner scn = new Scanner(file);
        
        int ans = 0;
        
        while (scn.hasNextLine())
        {
            String line = scn.nextLine();
                        
            int lineLength = line.length();
            int numCharacters = 0;
            
            //line = line.substring(1, line.length() - 1);
            line = line.replace("\\", "@@");
            line = line.replace("\"", "@@");
            
            /*
            for (int i = line.length() - 4; i >= 0; i--)
            {
                if (line.charAt(i) == '\\' && line.charAt(i+1) == 'x')
                {
                    line = line.substring(0, i) + "@" + line.substring(i+4);
                }
            }
            */
            
            line = "\"" + line + "\"";
            
            numCharacters = line.length();
            
            ans += numCharacters - lineLength;
        }
        
        System.out.println(ans);
    }
}
