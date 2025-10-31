import java.util.*;
import java.io.*;

public class Day6
{
    public static void main(String[] args) throws IOException
    {
        File file = new File("input.txt");
        Scanner scn = new Scanner(file);
        
        String ans = "";
        
        HashMap<String, Integer>[] daCode = new HashMap[8];
        
        for (int i = 0; i < 8; i++)
        {
            daCode[i] = new HashMap();
        }
        
        while (scn.hasNextLine())
        {
            String line = scn.nextLine();
            
            for (int i = 0; i < 8; i++)
            {
                String character = line.charAt(i) + "";
                daCode[i].put(character, daCode[i].getOrDefault(character, 0) + 1);
            }
        }
        
        for (int i = 0; i < 8; i++)
        {
            String bestChar = "";
            int bestScore = Integer.MAX_VALUE;
            for (String c : daCode[i].keySet())
            {
                if (daCode[i].get(c) < bestScore)
                {
                    bestChar = c;
                    bestScore = daCode[i].get(c);
                }
            }
            
            ans = ans + bestChar;
        }
        
        System.out.println(ans);
    }
}
