import java.util.*;
import java.io.*;

public class Day3
{
    public static void main(String[] args) throws IOException
    {
        File file = new File("input.txt");
        Scanner scn = new Scanner(file);
        
        // size 140 by 140
        
        char[][] grid = new char[140][140];
        String[][] trueGridInit = new String[140][140];
        String[][] trueGrid = new String[140][140];
        
        HashMap<String, Integer[]> stars = new HashMap(); //coords, [power, num mult]
        ArrayList<Integer> nums = new ArrayList();
        
        
        int answer = 0;
        
        int row = 0;
        while (scn.hasNextLine())
        {
            String data = scn.nextLine();
            
            for (int i = 0; i < data.length(); i++)
            {
                grid[i][row] = data.charAt(i);
            }
            
            row++;
        }
        
        
        
        for (int y = 0; y < grid.length; y++)
        {
            for (int x = 0; x < grid[y].length; x++)
            {
                if (grid[x][y] == 42)
                {
                    trueGridInit[x][y] = (x + " " + y);
                    Integer[] temp = new Integer[2];
                    temp[0] = 1;
                    temp[1] = 0;
                    stars.put(trueGridInit[x][y], temp);
                }
            }
        }
        
        for (int y = 0; y < grid.length; y++)
        {
            for (int x = 0; x < grid[y].length; x++)
            {
                if (trueGridInit[x][y] != null)
                {
                    int num = 0;
                    
                    if (x < grid[y].length - 1 && y < grid.length - 1) // bottom right
                    {
                        if (grid[x + 1][y + 1] >= 48 && grid[x + 1][y + 1] <= 57){ trueGrid[x + 1][y + 1] = trueGridInit[x][y];}
                    }
                    if (x > 0 && y < grid.length - 1) // bottom left
                    {
                        if (grid[x - 1][y + 1] >= 48 && grid[x - 1][y + 1] <= 57){ trueGrid[x - 1][y + 1] = trueGridInit[x][y];}
                    }
                    if (x > 0 && y > 0) // top left
                    {
                        if (grid[x - 1][y - 1] >= 48 && grid[x - 1][y - 1] <= 57){ trueGrid[x - 1][y - 1] = trueGridInit[x][y];}
                    }
                    if (x < grid[y].length - 1 && y > 0) // top right
                    {
                        if (grid[x + 1][y - 1] >= 48 && grid[x + 1][y - 1] <= 57){ trueGrid[x + 1][y - 1] = trueGridInit[x][y];}
                    }
                }
            }
        }
        
        for (int y = 0; y < grid.length; y++)
        {
            for (int x = 0; x < grid[y].length; x++)
            {
                if (trueGridInit[x][y] != null)
                {
                    trueGrid[x][y] = trueGridInit[x][y];
                }
            }
        }
        
        for (int step = 0; step < 10; step++) // 10 steps should be enough
        {
            for (int y = 0; y < grid.length; y++)
            {
                for (int x = 0; x < grid[y].length; x++)
                {
                    if (trueGrid[x][y] != null)
                    {
                        if (x < grid[y].length - 1) // right
                        {
                            if (grid[x + 1][y] != 46)
                            {
                                trueGrid[x + 1][y] = trueGrid[x][y];
                            }
                        }
                        if (y < grid.length - 1) // down
                        {
                            if (grid[x][y + 1] != 46)
                            {
                                trueGrid[x][y + 1] = trueGrid[x][y];
                            }
                        }
                        if (x > 0) // left
                        {
                            if (grid[x - 1][y] != 46)
                            {
                                trueGrid[x - 1][y] = trueGrid[x][y];
                            }
                        }
                        if (y > 0) // up
                        {
                            if (grid[x][y - 1] != 46)
                            {
                                trueGrid[x][y - 1] = trueGrid[x][y];
                            }
                        }
                    }
                }
            }
        }
        
        for (int y = 0; y < grid.length; y++)
        {
            for (int x = 0; x < grid[y].length; x++)
            {
                if (trueGridInit[x][y] != null || grid[x][y] == 46)
                {
                    trueGrid[x][y] = null;
                }
            }
        }
        /*
        for (int y = 0; y < grid.length; y++) // test
        {
            for (int x = 0; x < grid[y].length; x++)
            {
                if (trueGridInit[x][y] != null)
                {
                    System.out.print("*");
                }
                else if (trueGrid[x][y] != null)
                {
                    System.out.print(grid[x][y]);
                }
                else
                {
                    System.out.print(".");
                }
            }
            System.out.println();
        }
        System.out.println(); */
        // true grid should now only highlight the numbers adjacent to parts
        
        int mult = 0;
        int number = 0;
        String numStar = null;
        for (int y = grid.length - 1; y >= 0; y--)
        {
            for (int x = grid[y].length - 1; x >= 0; x--)
            {
                if (trueGrid[x][y] != null)
                {
                    number += Math.pow(10, mult) * (grid[x][y] - 48);
                    mult++;
                    
                    numStar = trueGrid[x][y];
                }
                else if (numStar != null)
                {
                    //System.out.println(numStar);
                    
                    Integer[] toAdd = new Integer[2];
                    toAdd[0] = stars.get(numStar)[0] * number;
                    toAdd[1] = stars.get(numStar)[1] + 1;
                    
                    stars.put(numStar, toAdd);
                    
                    mult = 0;
                    number = 0;
                    numStar = null;
                }
            }
        }
        if (numStar != null)
        {
            Integer[] toAdd = new Integer[2];
            toAdd[0] = stars.get(numStar)[0] * number;
            toAdd[1] = stars.get(numStar)[1] + 1;
            
            stars.put(numStar, toAdd);
        }

        
        // Now add the count
        for (String key : stars.keySet())
        {
            if (stars.get(key)[1] == 2)
            {
                answer += stars.get(key)[0];
            }
        }
        
        System.out.println(answer);
    }
}
