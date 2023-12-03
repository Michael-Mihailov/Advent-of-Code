
/**
 * Write a description of class Day3 here.
 *
 * @author (your name)
 * @version (a version number or a date)
 */

import java.util.*;
import java.io.*;

public class Day3
{
    public static void main(String args[]) throws IOException
    {
        File file = new File("input.txt");
        Scanner scn = new Scanner(file);
        
        String data = scn.nextLine();
        
        int xPos1 = 0;
        int yPos1 = 0;
        int xPos2 = 0;
        int yPos2 = 0;
        
        HashMap<String, Boolean> map = new HashMap();
        
        map.put(0 + "-" + 0, true);
        
        for (int i = 0; i < data.length(); i++)
        {
            if (i % 2 == 0)
            {
                if (data.charAt(i) == '<')
                {
                    xPos1--;
                }
                else if (data.charAt(i) == '>')
                {
                    xPos1++;
                }
                else if (data.charAt(i) == '^')
                {
                    yPos1++;
                }
                else
                {
                    yPos1--;
                }
                
                map.put(xPos1 + "-" + yPos1, true);
            }
            else
            {
                if (data.charAt(i) == '<')
                {
                    xPos2--;
                }
                else if (data.charAt(i) == '>')
                {
                    xPos2++;
                }
                else if (data.charAt(i) == '^')
                {
                    yPos2++;
                }
                else
                {
                    yPos2--;
                }
                
                map.put(xPos2 + "-" + yPos2, true);
            }
        }
        
        System.out.println(map.size());
    }
}
