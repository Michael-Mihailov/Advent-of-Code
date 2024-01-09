import java.util.*;
import java.io.*;

public class Day21
{
    // To do part 2:
    
    /*
     * figure out how long it takes to get to each point from:
     *     the starting position (start)
     *     itself with 1 boundry crossing (loop)
     *     
     * The number of times that point gets visited is (visits = ((steps - start) / loop) + 1) 
     */
    
    
    public static void main(String[] args) throws IOException
    {
        File file = new File("input.txt");
        Scanner scn = new Scanner(file);
        
        int answer = 0;
        
        final int SIZE = 11;
        
        boolean[][] garden = new boolean[SIZE][SIZE];
        
        int[][] start = new int[SIZE][SIZE];
        int[][] loop = new int[SIZE][SIZE];
        
        int xStart = -1;
        int yStart = -1;
        
        for (int y = 0; y < SIZE; y++)
        {
            String data = scn.nextLine();
            for (int x = 0; x < SIZE; x++)
            {
                start[x][y] = -1; // designates unexplored locations
                
                if (data.charAt(x) == '#')
                {
                    garden[x][y] = true;
                }
                else if (data.charAt(x) == 'S')
                {
                    xStart = x;
                    yStart = y;
                }
            }
        }
        start[xStart][yStart] = 0; // "seed" value
        
        
        boolean done = false;
        while (!done)
        {
            done = true;
            
            int[][] temp = new int[SIZE][SIZE];
            
            for (int y = 0; y < SIZE; y++)
            {
                for (int x = 0; x < SIZE; x++)
                {
                    if (start[x][y] != -1)
                    {
                        int prev = start[x][y];
                        
                        
                        if (isValid(garden, x + 1, y) && temp[x + 1][y] == 0)
                        {
                            temp[x + 1][y] = prev + 1;
                        }
                        if (isValid(garden, x - 1, y) && temp[x - 1][y] == 0)
                        {
                            temp[x - 1][y] = prev + 1;
                        }
                        if (isValid(garden, x, y + 1) && temp[x][y + 1] == 0)
                        {
                            temp[x][y + 1] = prev + 1;
                        }
                        if (isValid(garden, x, y - 1) && temp[x][y - 1] == 0)
                        {
                            temp[x][y - 1] = prev + 1;
                        }
                    }
                }
            }
            
            for (int y = 0; y < SIZE; y++)
            {
                for (int x = 0; x < SIZE; x++)
                {
                    if (start[x][y] == -1 && temp[x][y] != 0)
                    {
                         start[x][y] = temp[x][y];
                         done = false;
                    }
                }
            }
        }
        
        int gridColor = (xStart + yStart) % 2; // imagine a checkers board with black and white squares
        for (int y = 0; y < SIZE; y++)
        {
            for (int x = 0; x < SIZE; x++)
            {
                if (start[x][y] == -1)
                {
                    System.out.print("#");
                }
                else if (start[x][y] == 0)
                {
                    System.out.print("S");
                }
                else
                {
                    System.out.print(start[x][y] % 10);
                }
                
                /*
                if ((x + y) % 2 == gridColor && positions[x][y])
                {
                    answer++;
                }
                */
            }
            System.out.println();
        }
        
        System.out.println(answer);
    }
    
    public static boolean isValid(boolean[][] positions, int x, int y) // valid spot on square array
    {
        if (x < 0 || y < 0) return false;
        
        if (x >= positions.length || y >= positions.length) return false;
                
        if (positions[x][y]) return false; // hit rock
        
        return true;
    }
}
