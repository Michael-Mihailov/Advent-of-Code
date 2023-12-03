
/**
 * Write a description of class Day1 here.
 *
 * @author (your name)
 * @version (a version number or a date)
 */

import java.io.*;
import java.util.*;

public class Day12
{
    static int[][] map = new int[41][83];
    
    static int rowFinal = 20;
    static int columeFinal = 58;
    
    static int minMoves = -1;  // NO MAX SET RIGHT NOW
    
    static boolean[][] bestPath = new boolean[41][83];
    
    public static void main (String[] args) throws Exception
    {
        File file = new File("input.txt");
        Scanner scn = new Scanner(file);
        
        
        int rowCurrent = 20;
        int columeCurrent = 0;
        
        
        for (int r = 0; r < 41; r++)
        {
            String data = scn.nextLine();
            
            for (int c = 0; c < 83; c++)
            {
                if (data.charAt(c) == 'S')
                {
                    map[r][c] = 0;
                    //System.out.println(r + " start " + c);
                }
                else if (data.charAt(c) == 'E')
                {
                    map[r][c] = 25;
                    //System.out.println(r + " end " + c);
                }
                else
                {
                    map[r][c] = data.charAt(c) - 97;
                    //System.out.println(data.charAt(c) - 97);
                }
            }
        }
        
        
        move(rowCurrent, columeCurrent, 0, new boolean[41][83]);
        System.out.println(minMoves);
        
        for (boolean[] y : bestPath)
        {
            System.out.println();
            for (boolean x : y)
            {
                if (x == true)
                {
                    System.out.print("#");
                }
                else
                {
                    System.out.print(".");
                }
            }
        }
    }
    
    public static void move(int rowPos, int colPos, int countCurrent, boolean[][] visitedMap)
    {
        boolean[][] visited = visitedMap.clone();
        
        if (visited[rowPos][colPos] == false)
        {
            visited[rowPos][colPos] = true;
            
            if (rowPos == rowFinal && colPos == columeFinal)
            {
                if (countCurrent < minMoves || minMoves == -1)
                {
                    minMoves = countCurrent;
                    bestPath = visited.clone();
                }
                else
                {
                    System.out.println("this should not print");
                }
            }
            else if (countCurrent < minMoves || minMoves == -1)
            {
                countCurrent++;
                
                for (int i = 0; i < 4; i++)
                {
                    if (i == 0 && colPos != 82 && map[rowPos][colPos] + 1 >= map[rowPos][colPos + 1]) // right
                    {
                        move(rowPos, colPos + 1, countCurrent, visited);
                    }
                    else if (i == 1 && rowPos != 40 && map[rowPos][colPos] + 1 >= map[rowPos + 1][colPos]) // down
                    {
                        move(rowPos + 1, colPos, countCurrent, visited);
                    }
                    else if (i == 2 && colPos != 0 && map[rowPos][colPos] + 1 >= map[rowPos][colPos - 1]) // left
                    {
                        move(rowPos, colPos - 1, countCurrent, visited);
                    }
                    else if (i == 3 && rowPos != 0 && map[rowPos][colPos] + 1 >= map[rowPos - 1][colPos]) // up
                    {
                        move(rowPos - 1, colPos, countCurrent, visited);
                    }
                    else
                    {
                        // System.out.println("hole");
                    }
                }
            }
        }
    }
}
