
/**
 * Write a description of class Day1 here.
 *
 * @author (your name)
 * @version (a version number or a date)
 */

import java.util.*;
import java.io.*;

public class Day1
{
    public static void main(String args[]) throws IOException
    {
        File file = new File("input.txt");
        Scanner scn = new Scanner(file);
        
        String data = scn.nextLine();
        
        int pos = 0;
        
        for (int i = 0; i < data.length(); i++)
        {
            if (pos < 0)
                System.out.println(i);
            
            if (data.charAt(i) == '(')
            {
                pos++;
            }
            else
            {
                pos--;
            }
        }
        
    }
}
