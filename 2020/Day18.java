import java.util.*;
import java.io.*;

public class Day18
{
    public static void main(String args[]) throws IOException
    {
        File file = new File("input.txt");
        Scanner scn = new Scanner(file);
        
        long ans = 0;
        
        while (scn.hasNextLine())
        {
            String line = scn.nextLine();
            ans += solve(line);
        }
        
        System.out.println(ans);
    }
    
    public static long solve(String equation)
    {
        long res = 0;
        
        // boolean operator = true; // true ? add : multiply
        
        String innerEquation = "";
        int depth = 0;
        
        for (int i = 0; i < equation.length(); i++)
        {
            if (depth > 0)
            {
                if (equation.charAt(i) == ')')
                {
                    depth--;
                }
                else if (equation.charAt(i) == '(')
                {
                    depth++;
                }
                
                if (depth == 0)
                {
                    res = res + solve(innerEquation);
                    innerEquation = "";
                }
                else
                {
                    innerEquation += equation.charAt(i);
                }
            }
            else
            {
                if (equation.charAt(i) == ' ') continue;
                
                if (equation.charAt(i) == '+') continue; // operator = true;
                else if (equation.charAt(i) == '*') 
                {
                    // operator = false;
                    res = res * solve(equation.substring(i+1));
                    break;
                }
                else if (equation.charAt(i) == '(') depth++;
                else
                {
                    int num = equation.charAt(i) - 48;
                    res = res + num;
                }
            }
        }
        
        return res;
    }
}
