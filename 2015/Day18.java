import java.util.*;
import java.io.*;

public class Day18
{
    public static void main(String args[]) throws IOException
    {
        File file = new File("input.txt");
        Scanner scn = new Scanner(file);
        
        int ans = 0;
        
        boolean[][] grid = new boolean[102][102]; // extra buffer space for edges
        
        for (int y = 0; scn.hasNextLine(); y++)
        {
            String line = scn.nextLine();
            for (int x = 0; x < line.length(); x++)
            {
                grid[y+1][x+1] = (line.charAt(x) == '#');
            }
        }
        
        grid[1][1] = true;
        grid[grid.length - 2][1] = true;
        grid[1][grid.length - 2] = true;
        grid[grid.length - 2][grid.length - 2] = true;
        
        for (int step = 0; step < 100; step++)
        {
            boolean[][] tempGrid = new boolean[102][102];
            
            for (int y = 1; y < grid.length - 1; y++)
            {
                for (int x = 1; x < grid[y].length - 1; x++)
                {
                    int neighbors = 0;
                    for (int i = -1; i <= 1; i++)
                    {
                        for (int j = -1; j <= 1; j++)
                        {
                            if (i == 0 && j == 0) continue;
                            if (grid[y+i][x+j] == true) neighbors++;
                        }
                    }
                    
                    if (neighbors == 3) tempGrid[y][x] = true;
                    else if (neighbors == 2 && grid[y][x] == true) tempGrid[y][x] = true;
                }
            }
            grid = tempGrid;
            
            grid[1][1] = true;
            grid[grid.length - 2][1] = true;
            grid[1][grid.length - 2] = true;
            grid[grid.length - 2][grid.length - 2] = true;
        }
        
        for (int y = 1; y < grid.length - 1; y++)
        {
            for (int x = 1; x < grid[y].length - 1; x++)
            {
                ans += grid[y][x] ? 1 : 0;
            }
        }
        
        System.out.println(ans);
    }
}