
/**
 * Write a description of class Day18 here.
 *
 * @author (your name)
 * @version (a version number or a date)
 */

import java.io.*;
import java.util.*;

public class Day18 
{
    public static void main (String args[]) throws Exception
    {
        File file = new File("input.txt");
        Scanner scn = new Scanner(file);
 
        ArrayList equation = new ArrayList();
        
        
        while (scn.hasNextLine())
        {
            String s = scn.nextLine();
            ArrayList data = readString(s);
            
            /*
            equation = appendLists(equation, data);
            
            boolean simplified = false;
            while (!simplified)
            {
                if (!explode(equation) && !split(equation))
                    simplified = true;
            }
            */
        }
        
        
    }
    
    public static boolean explode (ArrayList equation)
    {
        boolean answer = false;
        
        int numIndex = -1;
        
        ArrayList<ArrayList> depthList = new ArrayList(); depthList.add(equation);
        int depthPos = 0; // 0 or 1
        while (!answer || depthList.size() == 0)
        {
            if (depthPos > 1)
            {
                depthPos = 0;
                depthList.remove(depthList.size() - 1);
            }
            else if (depthList.get(depthList.size() - 1).get(depthPos) instanceof ArrayList)
            {
                depthList.add((ArrayList) depthList.get(depthList.size() - 1).get(depthPos));
            }
            else
            {
                numIndex++;
            }
        }
        
        
        return answer;
    }
    
    public static boolean split (ArrayList equation)
    {
        boolean answer = false;
                
        ArrayList<ArrayList> depthList = new ArrayList(); depthList.add(equation);
        ArrayList<Integer> depthPos =  new ArrayList(); depthPos.add(0); // 0 or 1
        
        while (!answer && depthList.size() > 0)
        {
            /*
            if (depthPos.get(depthPos.size() - 1) <= 1)
            {
                System.out.println("Current thing : " + depthList.get(depthList.size() - 1).get(depthPos.get(depthPos.size() - 1)));
                System.out.println(depthList);
                System.out.println();
            }
            */
            
            if (depthPos.get(depthPos.size() - 1) > 1)
            {
                depthList.remove(depthList.size() - 1);
                depthPos.remove(depthPos.size() - 1);
            }
            else if (depthList.get(depthList.size() - 1).get(depthPos.get(depthPos.size() - 1)) instanceof ArrayList)
            {
                depthList.add((ArrayList) depthList.get(depthList.size() - 1).get(depthPos.get(depthPos.size() - 1)));  
                depthPos.set(depthPos.size() - 1, depthPos.get(depthPos.size() - 1) + 1);
                depthPos.add(0);
            }
            else
            {
                if ((int) depthList.get(depthList.size() - 1).get(depthPos.get(depthPos.size() - 1)) > 9)
                {
                    int left = (int) depthList.get(depthList.size() - 1).get(depthPos.get(depthPos.size() - 1)) / 2;
                    int right = left + ((int) depthList.get(depthList.size() - 1).get(depthPos.get(depthPos.size() - 1)) % 2);
                    
                    ArrayList insert = new ArrayList(); insert.add(left); insert.add(right);
                    
                    depthList.get(depthList.size() - 1).set(depthPos.get(depthPos.size() - 1), insert);
                    
                    answer = true;
                }
                
                depthPos.set(depthPos.size() - 1, depthPos.get(depthPos.size() - 1) + 1);
            }
        }
        
        return answer;
    }
    
    public static ArrayList appendLists(ArrayList l1, ArrayList l2)
    {
        ArrayList answer = new ArrayList();
        
        answer.add(l1);
        answer.add(l2);
        
        return answer;
    }
    
    public static ArrayList readString (String data)
    {
        data = data.substring(1, data.length() - 1);
        ArrayList answer = new ArrayList();
        
        ArrayList<ArrayList> depthList = new ArrayList(); depthList.add(answer);
        
        while (data.length() > 0)
        {
            char op = data.charAt(0);
            data = data.substring(1);
            if (op == ','); // do nothing
            else if (op == '[')
            {
                ArrayList newList = new ArrayList();
                depthList.get(depthList.size() - 1).add(newList);
                depthList.add(newList);
            }
            else if (op == ']')
            {
                depthList.remove(depthList.size() - 1);
            }
            else
            {
                depthList.get(depthList.size() - 1).add(new Integer("" + op));
            }
        }
        
        return answer;
    }
}
