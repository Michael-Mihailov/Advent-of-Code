import java.util.*;
import java.io.*;

public class Day14
{
    // How to optimize part 2
    /*
     * Have a hashMap that stores every grid state that has been encountered so far and the output grid state.
     * If the grid state has been encountered before, then there is a "loop" of grid states.
     * This loop can be found and used to efficently figure out the gride state at One Billion steps.
     */
    
    public static void main(String[] args) throws IOException
    {
        File file = new File("input.txt");
        Scanner scn = new Scanner(file);
        
        int answer = 0;
        
        final int SIZE = 100; // the input grid width
        
        char[][] grid = new char[SIZE][SIZE];
        String prevGrid = "";
        
        HashMap<String, String> states = new HashMap();
        
        for (int row = 0; row < SIZE; row++)
        {
            String data = scn.nextLine();
            
            for (int col = 0; col < data.length(); col++)
            {
                grid[row][col] = data.charAt(col);
            }
        }
        
        boolean loopFound = false;
        int step = 0;
        while (step < 1000000000 && !loopFound)
        {
            step++;
            
            //System.out.println(states.size());
            
            boolean changed = true;
            while (changed) // move to top
            {
                changed = false;
                
                for (int row = 1; row < grid.length; row++)
                {                
                    for (int col = 0; col < grid[row].length; col++)
                    {
                        if (grid[row][col] == 'O' && grid[row - 1][col] == '.')
                        {
                            changed = true;
                            
                            grid[row - 1][col] = 'O';
                            grid[row][col] = '.';
                        }
                    }
                }
            }
            changed = true;
            while (changed) // move to left
            {
                changed = false;
                
                for (int row = 0; row < grid.length; row++)
                {                
                    for (int col = 1; col < grid[row].length; col++)
                    {
                        if (grid[row][col] == 'O' && grid[row][col - 1] == '.')
                        {
                            changed = true;
                            
                            grid[row][col - 1] = 'O';
                            grid[row][col] = '.';
                        }
                    }
                }
            }
            changed = true;
            while (changed) // move to bottom
            {
                changed = false;
                
                for (int row = 0; row < grid.length - 1; row++)
                {                
                    for (int col = 0; col < grid[row].length; col++)
                    {
                        if (grid[row][col] == 'O' && grid[row + 1][col] == '.')
                        {
                            changed = true;
                            
                            grid[row + 1][col] = 'O';
                            grid[row][col] = '.';
                        }
                    }
                }
            }
            changed = true;
            while (changed) // move to right
            {
                changed = false;
                
                for (int row = 0; row < grid.length; row++)
                {                
                    for (int col = 0; col < grid[row].length - 1; col++)
                    {
                        if (grid[row][col] == 'O' && grid[row][col + 1] == '.')
                        {
                            changed = true;
                            
                            grid[row][col + 1] = 'O';
                            grid[row][col] = '.';
                        }
                    }
                }
            }
            
            
            
            //System.out.println(states.get(gridID(grid)));
            if (states.get(gridID(grid)) != null)
            {
                loopFound = true;
                System.out.println("loop found!");
            }
            states.put(prevGrid, gridID(grid));
            prevGrid = gridID(grid);
        }
        
        
        String loopPos = prevGrid;        
        int loopSize = 1;
        String initialPos = loopPos;
        while (states.get(loopPos).equals(initialPos) == false)
        {
            loopSize++;
            loopPos = states.get(loopPos);
        }
        int remainingSteps = (1000000000 - step) % loopSize;
        
        for (int i = 0; i < remainingSteps; i++)
        {
            prevGrid = states.get(prevGrid);
        }
        
        
        int row = 0;
        for (int i = 0; i < prevGrid.length(); i++)
        {
            if (prevGrid.charAt(i) == '?')
            {
                row++;
            }
            else if (prevGrid.charAt(i) == '1')
            {
                answer += grid.length - row;
            }
        }
        
        System.out.println("answer: " + answer);
    }
    
    public static String gridID(char[][] grid)
    {
        String id = "";
        for (int row = 0; row < grid.length; row++)
        {                
            for (int col = 0; col < grid[row].length; col++)
            {
                if (grid[row][col] == 'O') id += "1";
                else if (grid[row][col] == '.') id += "0";
            }
            id += "?";
        }
        
        return id;
    }
}
