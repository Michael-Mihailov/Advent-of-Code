
/**
 * Write a description of class Day1 here.
 *
 * @author (your name)
 * @version (a version number or a date)
 */

import java.io.*;
import java.util.*;

public class Day20
{
    public static void main (String args[]) throws Exception
    {
        File file = new File("input.txt");
        Scanner scn = new Scanner(file);
        
        int count = 0;
        
        String keyStr = scn.nextLine();
        boolean[] key = new boolean[512];
        
        for (int i = 0; i < 512; i++)
        {
            if (keyStr.charAt(i) == '#')
            {
                key[i] = true;
            }
            else
            {
                key[i] = false;
            }
        }
        
        scn.nextLine();
        
        boolean[][] pixels = new boolean[200][200]; 
        
        int rowAt = 49;
        

        while (scn.hasNext())
        {
            String data = scn.nextLine();
            
            rowAt++;
            
            for (int i = 50; i < 150; i++)
            {
                if (data.charAt(i - 50) == '#')
                {
                    pixels[rowAt][i] = true;
                }
                else
                {
                    pixels[rowAt][i] = false;
                }
            }
        }
        
        for (int go = 1; go <= 50; go++)
        {
            if (go % 2 == 1)
            {
                boolean[][] pixelsTemp1 = new boolean[200][200]; 
                
                for (int row = 0; row < 200; row++)
                {
                    for (int colume = 0; colume < 200; colume++)
                    {
                        String biCode = "";
                        
                        try
                        {
                            if (pixels[row - 1][colume - 1])
                            {
                                biCode += "1";
                            }
                            else
                            {
                                biCode += "0";
                            }
                        }
                        catch (Exception e)
                        {
                            biCode += "0";
                        }
                        
                        try
                        {
                            if (pixels[row - 1][colume])
                            {
                                biCode += "1";
                            }
                            else
                            {
                                biCode += "0";
                            }
                        }
                        catch (Exception e)
                        {
                            biCode += "0";
                        }
                        
                        try
                        {
                            if (pixels[row - 1][colume + 1])
                            {
                                biCode += "1";
                            }
                            else
                            {
                                biCode += "0";
                            }
                        }
                        catch (Exception e)
                        {
                            biCode += "0";
                        }
                        
                        try
                        {
                            if (pixels[row][colume - 1])
                            {
                                biCode += "1";
                            }
                            else
                            {
                                biCode += "0";
                            }
                        }
                        catch (Exception e)
                        {
                            biCode += "0";
                        }
                        
                        if (pixels[row][colume])
                        {
                            biCode += "1";
                        }
                        else
                        {
                            biCode += "0";
                        }
                        
                        try
                        {
                            if (pixels[row][colume + 1])
                            {
                                biCode += "1";
                            }
                            else
                            {
                                biCode += "0";
                            }
                        }
                        catch (Exception e)
                        {
                            biCode += "0";
                        }
                        
                        try
                        {
                            if (pixels[row + 1][colume - 1])
                            {
                                biCode += "1";
                            }
                            else
                            {
                                biCode += "0";
                            }
                        }
                        catch (Exception e)
                        {
                            biCode += "0";
                        }
                        
                        try
                        {
                            if (pixels[row + 1][colume])
                            {
                                biCode += "1";
                            }
                            else
                            {
                                biCode += "0";
                            }
                        }
                        catch (Exception e)
                        {
                            biCode += "0";
                        }
                        
                        try
                        {
                            if (pixels[row + 1][colume + 1])
                            {
                                biCode += "1";
                            }
                            else
                            {
                                biCode += "0";
                            }
                        }
                        catch (Exception e)
                        {
                            biCode += "0";
                        }
                        
                        pixelsTemp1[row][colume] = key[Integer.parseInt(biCode, 2)];
                    }
                }
                
                pixels = pixelsTemp1;
                }
            else
            {
                boolean[][] pixelsTemp2 = new boolean[200][200]; 
                
                for (int row = 0; row < 200; row++)
                {
                    for (int colume = 0; colume < 200; colume++)
                    {
                        String biCode = "";
                        
                        try
                        {
                            if (pixels[row - 1][colume - 1])
                            {
                                biCode += "1";
                            }
                            else
                            {
                                biCode += "0";
                            }
                        }
                        catch (Exception e)
                        {
                            biCode += "1";
                        }
                        
                        try
                        {
                            if (pixels[row - 1][colume])
                            {
                                biCode += "1";
                            }
                            else
                            {
                                biCode += "0";
                            }
                        }
                        catch (Exception e)
                        {
                            biCode += "1";
                        }
                        
                        try
                        {
                            if (pixels[row - 1][colume + 1])
                            {
                                biCode += "1";
                            }
                            else
                            {
                                biCode += "0";
                            }
                        }
                        catch (Exception e)
                        {
                            biCode += "1";
                        }
                        
                        try
                        {
                            if (pixels[row][colume - 1])
                            {
                                biCode += "1";
                            }
                            else
                            {
                                biCode += "0";
                            }
                        }
                        catch (Exception e)
                        {
                            biCode += "1";
                        }
                        
                        if (pixels[row][colume])
                        {
                            biCode += "1";
                        }
                        else
                        {
                            biCode += "0";
                        }
                        
                        try
                        {
                            if (pixels[row][colume + 1])
                            {
                                biCode += "1";
                            }
                            else
                            {
                                biCode += "0";
                            }
                        }
                        catch (Exception e)
                        {
                            biCode += "1";
                        }
                        
                        try
                        {
                            if (pixels[row + 1][colume - 1])
                            {
                                biCode += "1";
                            }
                            else
                            {
                                biCode += "0";
                            }
                        }
                        catch (Exception e)
                        {
                            biCode += "1";
                        }
                        
                        try
                        {
                            if (pixels[row + 1][colume])
                            {
                                biCode += "1";
                            }
                            else
                            {
                                biCode += "0";
                            }
                        }
                        catch (Exception e)
                        {
                            biCode += "1";
                        }
                        
                        try
                        {
                            if (pixels[row + 1][colume + 1])
                            {
                                biCode += "1";
                            }
                            else
                            {
                                biCode += "0";
                            }
                        }
                        catch (Exception e)
                        {
                            biCode += "1";
                        }
                        
                        pixelsTemp2[row][colume] = key[Integer.parseInt(biCode, 2)];
                    }
                }
                    
                pixels = pixelsTemp2;
                }
            }
        
        for (int row = 0; row < 200; row++)
        {
            for (int colume = 0; colume < 200; colume++)
            {
                if (pixels[row][colume])
                {
                    count++;
                    System.out.print("#");
                }
                else
                {
                    System.out.print(".");
                }
            }
            System.out.println();
        }
        
        System.out.println(count);
    } 
}
