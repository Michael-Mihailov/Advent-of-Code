
/**
 * Write a description of class Day9 here.
 *
 * @author (your name)
 * @version (a version number or a date)
 */

import java.util.*;
import java.io.*;

public class Day9
{
    public static void main(String args[]) throws IOException
    {
        File file = new File("input.txt");
        Scanner scn = new Scanner(file);
        
        String data = scn.nextLine();
        
        for (int i = 0; i < data.length(); i++) // use '!' to remove garbage
        {
            if (data.charAt(i) == '!')
            {
                data = data.substring(0, i) + data.substring(i + 2);
                i--;
            }
        }
        
        
        int trash = 0;
        
        boolean type = false;
        int index = -1;
        for (int i = 0; i < data.length(); i++) // destroy all garbage
        {
            if (data.charAt(i) == '<' && type == false)
            {
                type = true;
                index = i;
            }
            
            if (data.charAt(i) == '>' && type == true)
            {
                type = false;
                data = data.substring(0, index) + data.substring(i + 1);
                
                trash += i - index - 1;
                
                i = 0;
                index = -1;
            }
        }
        System.out.println(data);
       
        System.out.println(trash);
        
        
        
        
        
        
        
        /*
        for (int i = 0; i < data.length(); i++) // remove all non curly bracket chars
        {
            if (data.charAt(i) != '{' && data.charAt(i) != '}')
            {
                data = data.substring(0, i) + data.substring(i + 1);
                i--;
            }
        }
        
        
        
        int count = 0;
        int depth = 0;
        
        for (int i = 0; i < data.length(); i++) // part 1
        {
            if (data.charAt(i) == '{')
            {
                depth++;
            }
            else if (data.charAt(i) == '}')
            {
                count += depth;
                depth--;
            }
            else
            {
                System.out.println("this should not print");
            }
        }
        
        
        System.out.println(count);
        */
    }
}






