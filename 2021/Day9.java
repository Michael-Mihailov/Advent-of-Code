
/**
 * Write a description of class Day1 here.
 *
 * @author (your name)
 * @version (a version number or a date)
 */

import java.io.*;
import java.util.Scanner;

public class Day9
{
    public static void main (String args[]) throws Exception
    {
        File file = new File("input.txt");
        Scanner scn = new Scanner(file);
        
        int risk = 0;
        
        int height = 0;
        int width = 0;
        while (scn.hasNext())
        {
            String data = scn.next();
            width = data.length();
            height++;
        }
        
        scn = new Scanner(file);
        int[][] map = new int[height][width];
        
        int currentRow = 0;
        while (scn.hasNext())
        {
            String data = scn.next();
            
            for (int i = 0; i < width; i++)
            {
                map[currentRow][i] = data.charAt(i) - 48;
            }
            currentRow++;
        }
        
        int[][] basins = new int[height][width];
        
        for (int row = 0; row < height; row++)
        {
            for (int colume = 0; colume < width; colume++)
            {
                int point = map[row][colume];
                int posX = colume;
                int posY = row;
                
                boolean bottom = false;
                if (point == 9)
                {
                    bottom = true;
                }
                while (bottom == false)
                {
                    if ((posY == 0 || map[posY][posX] < map[posY - 1][posX]) && (posY == height - 1 || map[posY][posX] < map[posY + 1][posX]) && (posX == 0 || map[posY][posX] < map[posY][posX - 1]) && (posX == width - 1 || map[posY][posX] < map[posY][posX + 1]))
                    {
                        basins[posY][posX]++;
                        bottom = true;
                    }
                    else if (posY != 0 && point > map[posY - 1][posX])
                    {
                        point = map[posY - 1][posX];
                        posY--;
                    }
                    else if (posY != height - 1 && point > map[posY + 1][posX])
                    {
                        point = map[posY + 1][posX];
                        posY++;
                    }
                    else if (posX != 0 && point > map[posY][posX - 1])
                    {
                        point = map[posY][posX - 1];
                        posX--;
                    }
                    else if (posX != width - 1 && point > map[posY][posX + 1])
                    {
                        point = map[posY][posX + 1];
                        posX++;
                    }
                    else
                    {
                        System.out.println("Uh Oh");
                    }
                }
                //System.out.print(point);
            }
            //System.out.println();
        }
        
        System.out.println();
        
        int w1 = 0;
        int w2 = 0;
        int w3 = 0;
        for (int[] row : basins)
        {
            for (int point : row)
            {
                //System.out.print("x" + point + "x");
                if (point > w1)
                {
                    w3 = w2;
                    w2 = w1;
                    w1 = point;
                }
                else if (point > w2)
                {
                    w3 = w2;
                    w2 = point;
                }
                else if (point > w3)
                {
                    w3 = point;
                }
            }
            //System.out.println();
        }
        
        System.out.println(w1 * w2 * w3);
    }
}
