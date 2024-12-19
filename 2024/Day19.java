import java.util.*;
import java.io.*;

public class Day19
{
    public static void main(String[] args) throws IOException
    {
        File file = new File("input.txt");
        Scanner scn = new Scanner(file);
        
        long ans = 0;
        
        String[] towels = scn.nextLine().split(", ");
        scn.nextLine(); // blank line
        
        /*
        System.out.println("Old length: " + towels.length);
        
        int numRedundant = 0;
        for (int i = 0; i < towels.length; i++) // remove redundant towels
        {
            if (match(towels, towels[i], towels[i]) == true)
            {
                towels[i] = "null";
                numRedundant++;
            }
        }
        String[] newTowels = new String[towels.length - numRedundant];
        int newIndex = 0;
        for (int i = 0; i < towels.length; i++)
        {
            if (towels[i].equals("null") == false)
            {
                newTowels[newIndex] = towels[i];
                newIndex++;
            }
        }
        towels = newTowels;
        
        System.out.println("New length: " + towels.length);
        */
       
        while (scn.hasNextLine())
        {
            String pattern = scn.nextLine();
            
            /*
            if (match(towels, pattern, "") == true)
            {
                ans++;
            }
            */
           
            long[] occurences = new long[pattern.length() + 1];
            occurences[0] = 1;
            
            for (int i = 0; i < occurences.length - 1; i++)
            {
                for (String towel : towels)
                {
                    try
                    {
                        if (pattern.substring(i, towel.length() + i).equals(towel) == true)
                        {
                            occurences[i + towel.length()] += occurences[i];
                        }
                    } catch (Exception e) {}
                }
            }
            
            // System.out.println(occurences[occurences.length - 1]);
            
            ans += occurences[occurences.length - 1];
        }
        
        System.out.println("answer: " + ans);
    }
    
    public static boolean match (String[] towels, String pattern, String blacklist)
    {
        if (pattern.length() == 0) return true;
        
        for (String towel : towels)
        {
            try
            {
                if (towel.equals(blacklist) == false && pattern.substring(0, towel.length()).equals(towel) == true)
                {
                    if (match(towels, pattern.substring(towel.length()), blacklist) == true)
                    {
                        return true;
                    }
                }
            } catch (Exception e) {}
        }
        
        return false;
    }
}