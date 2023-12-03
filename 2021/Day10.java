
/**
 * Write a description of class Day1 here.
 *
 * @author (your name)
 * @version (a version number or a date)
 */

import java.io.*;
import java.util.*;

public class Day10 
{
    public static void main (String args[]) throws Exception
    {
        File file = new File("input.txt");
        Scanner scn = new Scanner(file);
        
        
        //int count = 0;
        ArrayList<Long> scores = new ArrayList();
        
        while (scn.hasNext())
        {
            String data = scn.nextLine();
            //System.out.println(data);
            ArrayList<String> past = new ArrayList();
            
            char error = 'X';
            boolean end = false;
            
            for (int i = 0; i < data.length() && end == false; i++)
            {

                if (data.charAt(i) == ']' || data.charAt(i) == '}' || data.charAt(i) == '>')
                {
                    if (past.get(past.size() - 1).equals("" + ((char)(data.charAt(i) - 2))))
                    {
                        past.remove(past.size() - 1);
                        //System.out.println("open left bracket");
                    }
                    else
                    {
                        error = data.charAt(i);
                        end = true;
                        //System.out.println("error found");
                        //System.out.println("     data: " + data.charAt(i));
                        //System.out.println("     past: " + past.get(past.size() - 1));
                    }
                }
                else if (data.charAt(i) == ')')
                {
                    if (past.get(past.size() - 1).equals("" + ((char)(data.charAt(i) - 1))))
                    {
                        past.remove(past.size() - 1);
                        //System.out.println("open left bracket");
                    }
                    else
                    {
                        error = data.charAt(i);
                        end = true;
                        //System.out.println("error found");
                        //System.out.println("     data: " + data.charAt(i));
                        //System.out.println("     past: " + past.get(past.size() - 1));
                    }
                }
                else
                {
                    past.add("" + data.charAt(i));
                    //System.out.println("open right bracket");
                }
            }
            
            long score = 0;
            
            if (end == false)
            {
                while (past.size() != 0)
                {
                    char bracket = past.get(past.size() - 1).charAt(0);
                    past.remove(past.size() - 1);
                    
                    score *= 5;
                    
                    if (bracket == '(')
                    {
                        score += 1;
                    }
                    else if (bracket == '[')
                    {
                        score += 2;
                    }
                    else if (bracket == '{')
                    {
                        score += 3;
                    }
                    else if (bracket == '<')
                    {
                        score += 4;
                    }
                    else
                    {
                        System.out.println("no error?");
                    }
                }
            }
            
            if (score != 0)
            {
                scores.add(score);
            }
        }
        
        scores.sort(Comparator.naturalOrder());
        System.out.println(scores.get(scores.size() / 2));
    }
}
