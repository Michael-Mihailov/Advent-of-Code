import java.util.*;
import java.io.*;

public class Day17
{
    public static void main(String[] args) throws IOException
    {
        File file = new File("input.txt");
        Scanner scn = new Scanner(file);
        
        int answer = Integer.MAX_VALUE;
        
        final int SIZE = 141;
        int[][] grid = new int[SIZE][SIZE];
        
        // [xPos] [yPos] [direction] [steps remaining]
        int[][][][] valueMap = new int[SIZE][SIZE][4][10]; // stores the minumum value of heatloss
        // direction: 0 == right, 1 == down, 2 == left, 3 == up
        // steps remaining: up to 9
        
        
        
        // get the grid of heatloss values
        for (int y = 0; y < SIZE; y++)
        {
            String data = scn.nextLine();
            for (int x = 0; x < SIZE; x++)
            {
                grid[x][y] = data.charAt(x) - 48; // 48 is the ASCII value of '0'
            }
        }
        // seed values
        valueMap[0][0][0][0] = grid[0][0];
        valueMap[0][0][1][0] = grid[0][0];
        
        
        // loop to calculate the best possible heat at all positions
        boolean changed = true;
        while (changed == true)
        {
            changed = false;
            
            for (int xPos = 0; xPos < SIZE; xPos++)
            {
                for (int yPos = 0; yPos < SIZE; yPos++)
                {
                    for (int direction = 0; direction < valueMap[xPos][yPos].length; direction++)
                    {
                        for (int step = 0; step < valueMap[xPos][yPos][direction].length; step++)
                        {
                            int currentValue = valueMap[xPos][yPos][direction][step];
                            
                            if (currentValue == 0) continue;
                            
                            for (int dirChange = -1; dirChange <= 1; dirChange++) // how much to turn
                            {
                                int x = xPos;
                                int y = yPos; 
                                int dir = direction + dirChange; dir %= 4; if(dir == -1) dir = 3;
                                int s = step; if (dirChange == 0) s--; else s = 9;
                                int newValue = currentValue;
                                
                                if (dirChange != 0 && step >= 7) continue; // 4 steps have not been taken
                                
                                if (dir == 0) // right
                                {
                                    x++;
                                }
                                else if (dir == 1) // down
                                {
                                    y++;
                                }
                                else if (dir == 2) // left
                                {
                                    x--;
                                }
                                else if (dir == 3) // up
                                {
                                    y--;
                                }
                                
                                if (isValid(valueMap, x, y, dir, s) == false) continue;
                                
                                newValue += grid[x][y];
                                
                                
                                if (newValue < valueMap[x][y][dir][s] || valueMap[x][y][dir][s] == 0)
                                {
                                    valueMap[x][y][dir][s] = newValue;
                                                                        
                                    changed = true;
                                }
                            }
                        }
                    }
                }
            }
        }
        
        
        for (int d = 0; d < 4; d++) // find the lowest heatloss at the final position
        {
            for (int st = 0; st < 10; st++)
            {
                int check = valueMap[SIZE - 1][SIZE - 1][d][st];
                
                answer = (check != 0 && check < answer) ? check : answer;
            }
        }
        answer -= grid[0][0]; // you don't incur starting block heat
        
        System.out.println(answer);
    }
    
    // Method to check if a certain "position" in the valueMap is valid
    public static boolean isValid(int[][][][] valueMap, int xPos, int yPos, int direction, int step)
    { 
        if (xPos < 0) return false;
        
        if (yPos < 0) return false;
        
        
        if (xPos >= valueMap.length) return false;
        
        if (yPos >= valueMap[xPos].length) return false;
        
        
        if (direction < 0) return false;
        
        if (direction >= 4) return false;
        
        
        if (step < 0) return false;
        
        if (step > 9) return false;
        
        
        return true;
    }
}