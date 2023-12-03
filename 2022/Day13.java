
/**
 * Write a description of class Day1 here.
 *
 * @author (your name)
 * @version (a version number or a date)
 */

import java.io.*;
import java.util.*;

public class Day13
{
    public static String packetStr;
    
    public static void main (String[] args) throws Exception
    {
        int count = 0; // answer
        
        
        File file = new File("input.txt");
        Scanner scn = new Scanner(file);
        
        Tuple[] packets = new Tuple[302]; // an array of 300 different packets
        
        int listCurrent = 0;
        while (scn.hasNext())
        {
            String data = scn.nextLine();
            
            if (data.equals("") == false)
            {                
                packetStr = data.substring(1); // remove first bracket start
                packets[listCurrent] = new Tuple();
                
                listCurrent++;
            }
        }
        
        Tuple index1 = packets[300];
        Tuple index2 = packets[301];
        
        //System.out.println(packets[44].compareTo(packets[45]));
        
        boolean go = true;
        while (go == true)
        {
            go = false;
            for (int i = 0; i < 301; i++)
            {
                if (packets[i].compareTo(packets[i + 1]) == 1)
                {
                    //System.out.println(i + " oops");
                    //count += (i / 2) + 1;
                    
                    Tuple temp = packets[i];
                    packets[i] = packets[i + 1];
                    packets[i + 1] = temp;
                    
                    go = true;
                }
                //System.out.println();
            }
        }
        
        
        for (int i = 0; i < 302; i++)
        {
            if (packets[i] == index1)
            {
                System.out.println("Index 1 at: " + i);
            }
            else if (packets[i] == index2)
            {
                System.out.println("Index 2 at: " + i);
            }
        }
        
        //System.out.println(count);
    }
}
