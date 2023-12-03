
/**
 * Write a description of class Day1 here.
 *
 * @author (your name)
 * @version (a version number or a date)
 */

import java.io.*;
import java.util.*;

public class Day8
{
    public static void main (String[] args) throws Exception
    {
        Scanner scn = new Scanner(new File("input.txt"));
    
        
        int count = 0;
        int[][] trees = new int[99][99];
        
        int rowAt = -1;
        
            while (scn.hasNext())
            {
                String data = scn.nextLine();
                
                rowAt++;
                //System.out.println(data.length());
                            
                for (int colume = 0; colume < 99; colume++)
                {
                    trees[rowAt][colume] = data.charAt(colume) - 48;
                    //System.out.print(trees[rowAt][colume]);
                }
                //System.out.println(rowAt);
            }
            
            
            for (int row = 1; row < 98; row++)
            {
                for (int colume = 1; colume < 98; colume++)
                {
                    boolean visible1 = true;
                    boolean visible2 = true;
                    boolean visible3 = true;
                    boolean visible4 = true;
                    
                    int s1 = 1;
                    int s2 = 1;
                    int s3 = 1;
                    int s4 = 1;
                    
                    for (int x = colume + 1; x < 99; x++)
                    {

                        if(trees[row][colume] > trees[row][x] && visible1)
                        {
                            s1++;
                        }
                        else
                        {
                            visible1 = false;
                        }

                    }
                    
                    for (int x = colume - 1; x > -1; x--)
                    {
                        if(trees[row][colume] > trees[row][x] && visible3)
                        {
                            s3++;
                        }
                        else
                        {
                            visible3 = false;
                        }
                    }
                    
                    for (int x = row + 1; x < 99; x++)
                    {
                        if(trees[row][colume] > trees[x][colume] && visible2)
                        {
                            s2++;
                        }
                        else
                        {
                            visible2 = false;
                        }
                    }
                    
                    for (int x = row - 1; x > -1; x--)
                    {
                        if(trees[row][colume] > trees[x][colume] && visible4)
                        {
                            s4++;
                        }
                        else
                        {
                            visible4 = false;
                        }
                    }
                    
                    if (visible1)
                    {
                        s1--;
                    }
                    if (visible2)
                    {
                        s2--;
                    }
                    if (visible3)
                    {
                        s3--;
                    }
                    if (visible4)
                    {
                        s4--;
                    }
                    
                    if (s1 * s2 * s3 * s4 > count)
                    {
                        System.out.println(row + " " + colume);
                        System.out.println(s1 + " " + s2 + " " + s3 + " " + s4 + "=" + (s1 * s2 * s3 * s4));
                        count = s1 * s2 * s3 * s4;
                    }
                    else
                    {
                        //System.out.println(s1 + " " + s2 + " " + s3 + " " + s4 + "=" + (s1 * s2 * s3 * s4));
                    }
                }
            }
        
        
        System.out.println(count);
    }
}

