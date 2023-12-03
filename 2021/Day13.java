
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
    public static void main (String args[]) throws Exception
    {
        File file = new File("input.txt");
        Scanner scn = new Scanner(file);
        
        int count = 0;
        
        boolean[][] map = new boolean[2000][2000];
        
        boolean cont = true;
        while(cont)
        {
            String data = scn.nextLine();
            if (data.equals(""))
            {
                cont = false;
            }
            else
            {
                Scanner splitScn = new Scanner(data);
                splitScn.useDelimiter(",");
                
                int x = splitScn.nextInt();
                int y = splitScn.nextInt();
                
                map[y][x] = true; 
            }
        }
        /*
        for (int row = 0; row < 2000; row++)
        {
            for (int colume = 0; colume < 2000; colume++)
            {
                System.out.print(colume + "" + map[row][colume]);
            }
            System.out.println();
        }
        */
        while (scn.hasNext())
        {
            count = 0;
            String data = scn.nextLine();
            Scanner numScn = new Scanner(data);
            numScn.useDelimiter("=");
            numScn.next();
            
            int pos = numScn.nextInt();
            //System.out.println(pos);
            if (data.contains("x"))
            {
                for (int row = 0; row < 2000; row++)
                {
                    for (int colume = pos; colume < 2000; colume++)
                    {
                        if(map[row][colume])
                        {
                            map[row][colume] = false;
                            map[row][pos + pos - colume] = true;
                        }
                    }
                }
            }
            else
            {
                for (int row = pos; row < 2000; row++)
                {
                    for (int colume = 0; colume < 2000; colume++)
                    {
                        if(map[row][colume])
                        {
                            map[row][colume] = false;
                            map[pos + pos - row][colume] = true;
                        }
                    }
                }
            }
            /*
            for (int y = 0; y < 2000; y++)
            {
                for (int x = 0; x < 2000; x++)
                {
                    if (map[y][x])
                    {
                        count++;
                    }
                }
            }
            System.out.println(count);
            */
        }
        
        for (int row = 0; row < 10; row++)
        {
            for (int colume = 0; colume < 100; colume++)
            {
                if(map[row][colume] == true)
                {
                    System.out.print("X");
                }
                else
                {
                    System.out.print(" ");
                }
            }
            System.out.println();
        }
    }
}

