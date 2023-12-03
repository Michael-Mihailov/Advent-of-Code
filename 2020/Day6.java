
/**
 * Write a description of class Day6 here.
 *
 * @author (your name)
 * @version (a version number or a date)
 */

import java.util.*;
import java.io.*;

public class Day6 // MODIFIED INPUT: 1 extra line at the end
{
    public static void main(String args[]) throws IOException
    {
        File file = new File("input.txt");
        Scanner scn = new Scanner(file);
        
        int count = 0;
        
        String group = "";
        while (scn.hasNextLine())
        {
            String data = scn.nextLine();
            
            String groupNew = "";
            
            if (data.equals("") == true)
            {
                count += group.length() - 1;
                group = "";
            }
            else
            {
                data += "?";
                for (int i = 0; i < data.length(); i++)
                {
                    if (group.contains(data.charAt(i) + "") == true)
                    {
                        groupNew += data.charAt(i);
                    }
                }
                
                if (group.length() == 0)
                {
                    group = data;
                }
                else
                {
                    group = groupNew;
                }
            }
        }
        
        System.out.println(count);
    }
}
