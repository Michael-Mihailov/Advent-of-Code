import java.util.*;
import java.io.*;

public class Day13
{
    public static void main(String[] args) throws IOException
    {
        File file = new File("input.txt");
        Scanner scn = new Scanner(file);
        
        int answer = 0;
        
        
        ArrayList<String> rows = new ArrayList();
        ArrayList<String> cols = new ArrayList();
        while (scn.hasNextLine())
        {            
            String row = scn.nextLine();
            
            if (row.equals(""))
            {
                for (int i = 0; i < rows.size() - 1; i++)
                {                    
                    if (difference(rows.get(i), rows.get(i + 1)) <= 1) // check row symetry
                    {
                        int remainingSmudges = 1;
                        
                        for (int j = i, k = i + 1; j >= 0 && k < rows.size(); j--, k++)
                        {
                            remainingSmudges -= difference(rows.get(j), rows.get(k));
                        }
                        
                        if(remainingSmudges == 0)
                        {
                            System.out.println("Symetry: row " + (i + 1));
                            answer += 100 * (i + 1);
                        }
                    }
                }
                
                for (int i = 0; i < cols.size() - 1; i++)
                {                    
                    if (difference(cols.get(i), cols.get(i + 1)) <= 1) // check row symetry
                    {
                        int remainingSmudges = 1;
                        
                        for (int j = i, k = i + 1; j >= 0 && k < cols.size(); j--, k++)
                        {
                            remainingSmudges -= difference(cols.get(j), cols.get(k));
                        }
                        
                        if(remainingSmudges == 0)
                        {
                            System.out.println("Symetry: col " + (i + 1));
                            answer += (i + 1);
                        }
                    }
                }
                
                
                
                rows = new ArrayList();
                cols = new ArrayList();
                
                System.out.println();
                
                continue;
            }
            
            rows.add(row);
            
            for (int i = 0; i < row.length(); i++)
            {
                if (i == cols.size())
                {
                    cols.add("" + row.charAt(i));
                }
                else
                {
                    cols.set(i, cols.get(i) + row.charAt(i));
                }
            }
        }
        for (int i = 0; i < rows.size() - 1; i++)
        {                    
            if (difference(rows.get(i), rows.get(i + 1)) <= 1) // check row symetry
            {
                int remainingSmudges = 1;
                
                for (int j = i, k = i + 1; j >= 0 && k < rows.size(); j--, k++)
                {
                    remainingSmudges -= difference(rows.get(j), rows.get(k));
                }
                
                if(remainingSmudges == 0)
                {
                    System.out.println("Symetry: row " + (i + 1));
                    answer += 100 * (i + 1);
                }
            }
        }
        for (int i = 0; i < cols.size() - 1; i++)
        {                    
            if (difference(cols.get(i), cols.get(i + 1)) <= 1) // check row symetry
            {
                int remainingSmudges = 1;
                
                for (int j = i, k = i + 1; j >= 0 && k < cols.size(); j--, k++)
                {
                    remainingSmudges -= difference(cols.get(j), cols.get(k));
                }
                
                if(remainingSmudges == 0)
                {
                    System.out.println("Symetry: col " + (i + 1));
                    answer += (i + 1);
                }
            }
        }
        
        
        System.out.println("Answer: " + answer);
    }
    
    public static int difference(String s1, String s2)
    {
        int diff = 0;
        for (int i = 0; i < s1.length(); i++)
        {
            if (s1.charAt(i) != s2.charAt(i))
            {
                diff++;
            }
        }
        
        return diff;
    }
}
