
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
        
        String data = scn.next();
        
        int count = 0;
        
        for (int i = 0; i < data.length(); i++)
        {
            if (data.charAt(i) == data.charAt((i + (data.length() / 2)) % data.length()))
            {
                count += Integer.valueOf(data.charAt(i) + "");
            }
        }
        
        System.out.println(count);
    }
}
