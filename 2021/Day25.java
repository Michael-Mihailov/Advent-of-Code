
/**
 * Write a description of class Day1 here.
 *
 * @author (your name)
 * @version (a version number or a date)
 */

// Answer data
// low: 485
// high: 500

import java.io.*;
import java.util.Scanner;

public class Day25
{
    public static void main (String args[]) throws Exception
    {
        File file = new File("input.txt");
        Scanner scn = new Scanner(file);
        
        
        boolean[][] hor = new boolean[137][139];
        boolean[][] ver = new boolean[137][139];
        
        for (int y = 0; y < 137; y++)
        {
            String data = scn.nextLine();
            for (int x = 0; x < 139; x++)
            {
                if (data.charAt(x) == '>')
                {
                    hor[y][x] = true;
                }
                else if (data.charAt(x) == 'v')
                {
                    ver[y][x] = true;
                }
            }
        }
                
        boolean go = true;
        int step = 0;
        while (go == true)
        {
            step++;
            go = false;
            
            boolean[][] horCopy = makeCopy(hor);
            boolean[][] verCopy = makeCopy(ver);
            
            
            
            
            
            
            for (int y = 0; y < 137; y++)
            {
                for (int x = 0; x < 139; x++)
                {
                    if (horCopy[y][x] == true)
                    {
                        if (x == 138 && horCopy[y][0] == false && verCopy[y][0] == false)
                        {
                            hor[y][x] = false;
                            hor[y][0] = true;
                            
                            go = true;
                        }
                        else if (x != 138 && horCopy[y][x + 1] == false && verCopy[y][x + 1] == false)
                        {
                            hor[y][x] = false;
                            hor[y][x + 1] = true;
                            
                            go = true;
                        }
                    }
                }
            }
            
            
            horCopy = makeCopy(hor);
            verCopy = makeCopy(ver);
            
            for (int y = 0; y < 137; y++)
            {
                for (int x = 0; x < 139; x++)
                {
                    if (verCopy[y][x] == true)
                    {
                        if (y == 136 && horCopy[0][x] == false && verCopy[0][x] == false)
                        {
                            ver[y][x] = false;
                            ver[0][x] = true;
                                       
                            go = true;
                        }
                        else if (y != 136 && horCopy[y + 1][x] == false && verCopy[y + 1][x] == false)
                        {
                            ver[y][x] = false;
                            ver[y + 1][x] = true;
                            
                            go = true;
                        }
                    }
                }
            }
            
            //display(hor, ver);
            //System.out.println(step);
            //new Scanner(System.in).nextLine();
        }
        
        System.out.println(step);
    }
    
    public static void display(boolean[][] a1, boolean[][] a2)
    {
        for (int y = 0; y < 137; y++)
        {
            for (int x = 0; x < 139; x++)
            {
                if (a1[y][x] == true)
                {
                    System.out.print(">");
                }
                else if (a2[y][x] == true)
                {
                    System.out.print("v");
                }
                else
                {
                    System.out.print(".");
                }
            }
            System.out.println();
        }
    }
    
    public static boolean[][] makeCopy(boolean[][] a)
    {
        boolean[][] c = new boolean[137][139];
        for (int y = 0; y < 137; y++)
        {
            for (int x = 0; x < 139; x++)
            {
                c[y][x] = a[y][x];
            }
        }
        
        return c;
    }

}
