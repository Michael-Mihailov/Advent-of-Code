import java.util.*;
import java.io.*;

public class Day16
{
    public static void main(String[] args) throws IOException
    {
        File file = new File("input.txt");
        Scanner scn = new Scanner(file);
        
        int finalAnswer = -1;
        int answer = 0;
        
        final int SIZE = 110;
        
        char[][] grid = new char[SIZE][SIZE];
        boolean[][] energized = new boolean[SIZE][SIZE];
        
        for (int row = 0; row < grid.length; row++)
        {
            String data = scn.nextLine();
            for (int col = 0; col < grid[row].length; col++)
            {
                grid[col][row] = data.charAt(col);
            }
        }
        
        
        
        for (int xStart = 0; xStart < SIZE; xStart++)
        {
            ArrayList<int[]> lights = new ArrayList<int[]>(); // xPos, yPos, dir
            
            int xPosTemp = xStart;
            int yPosTemp = -1;
            int dirTemp = 1; // 0 == right, 1 == down, 2 == left, 3 == up
            int[] lightTemp = new int[3];
            lightTemp[0] = xPosTemp;
            lightTemp[1] = yPosTemp;
            lightTemp[2] = dirTemp;
            lights.add(lightTemp);
            
            
            HashMap<String, Boolean> stepMap = new HashMap();
            
            while (lights.size() > 0)
            {            
                int[] light = lights.remove(0);
                int xPos = light[0];
                int yPos = light[1];
                int dir = light[2];
                
        
                if ((xPos < 0 || xPos >= SIZE || yPos < 0 || yPos >= SIZE) == false)
                    energized[xPos][yPos] = true;
                
                
                
                if (dir == 0)// move based on direction
                {
                    xPos++;
                }
                else if (dir == 1)
                {
                    yPos++;
                }
                else if (dir == 2)
                {
                    xPos--;
                }
                else if (dir == 3)
                {
                    yPos--;
                }
                else System.out.println("Error dir" + dir);
                
                String lightString = "" + light[0] + " " + light[1] + " " + light[2]; 
                if (stepMap.get(lightString) != null || xPos < 0 || xPos >= SIZE || yPos < 0 || yPos >= SIZE) // check if outside grid bounds
                {
                    continue;
                }
                stepMap.put(lightString, true);
                
                if (grid[xPos][yPos] == '-' && (dir == 1 || dir == 3)) // set new dir(s)
                {
                    int[] l1 = new int[3];
                    int[] l2 = new int[3];
                    l1[0] = xPos; l2[0] = xPos;
                    l1[1] = yPos; l2[1] = yPos;
                    
                    l1[2] = 0;
                    l2[2] = 2;
                    
                    lights.add(l1);
                    lights.add(l2);
                }
                else if (grid[xPos][yPos] == '|' && (dir == 0 || dir == 2))
                {
                    int[] l1 = new int[3];
                    int[] l2 = new int[3];
                    l1[0] = xPos; l2[0] = xPos;
                    l1[1] = yPos; l2[1] = yPos;
                    
                    l1[2] = 1;
                    l2[2] = 3;
                    
                    lights.add(l1);
                    lights.add(l2);
                }
                else if (grid[xPos][yPos] == '/')
                {
                    if (dir == 0)
                    {
                        dir = 3;
                    }
                    else if (dir == 1)
                    {
                        dir = 2;
                    }
                    else if (dir == 2)
                    {
                        dir = 1;
                    }
                    else if (dir == 3)
                    {
                        dir = 0;
                    }
                    
                    int[] l = new int[3];
                    l[0] = xPos;
                    l[1] = yPos;
                    l[2] = dir;
                    
                    lights.add(l);
                }
                else if (grid[xPos][yPos] == '\\')
                {
                    if (dir == 0)
                    {
                        dir = 1;
                    }
                    else if (dir == 1)
                    {
                        dir = 0;
                    }
                    else if (dir == 2)
                    {
                        dir = 3;
                    }
                    else if (dir == 3)
                    {
                        dir = 2;
                    }
                    
                    int[] l = new int[3];
                    l[0] = xPos;
                    l[1] = yPos;
                    l[2] = dir;
                    
                    lights.add(l);
                }
                else
                {
                    int[] l = new int[3];
                    l[0] = xPos;
                    l[1] = yPos;
                    l[2] = dir;
                    
                    lights.add(l);
                }
            }
            
            
            for (int row = 0; row < grid.length; row++)
            {
                for (int col = 0; col < grid[row].length; col++)
                {
                    if (energized[col][row] == true)
                    {
                        answer++;
                    }
                }
            } 
            
            if (answer > finalAnswer)
            {
                finalAnswer = answer;
            }
            answer = 0;
            energized = new boolean[SIZE][SIZE];
        }
        for (int xStart = 0; xStart < SIZE; xStart++)
        {
            ArrayList<int[]> lights = new ArrayList<int[]>(); // xPos, yPos, dir
            
            int xPosTemp = xStart;
            int yPosTemp = SIZE;
            int dirTemp = 3; // 0 == right, 1 == down, 2 == left, 3 == up
            int[] lightTemp = new int[3];
            lightTemp[0] = xPosTemp;
            lightTemp[1] = yPosTemp;
            lightTemp[2] = dirTemp;
            lights.add(lightTemp);
            
            
            HashMap<String, Boolean> stepMap = new HashMap();
            
            while (lights.size() > 0)
            {            
                int[] light = lights.remove(0);
                int xPos = light[0];
                int yPos = light[1];
                int dir = light[2];
                
        
                if ((xPos < 0 || xPos >= SIZE || yPos < 0 || yPos >= SIZE) == false)
                    energized[xPos][yPos] = true;
                
                
                
                if (dir == 0)// move based on direction
                {
                    xPos++;
                }
                else if (dir == 1)
                {
                    yPos++;
                }
                else if (dir == 2)
                {
                    xPos--;
                }
                else if (dir == 3)
                {
                    yPos--;
                }
                else System.out.println("Error dir" + dir);
                
                String lightString = "" + light[0] + " " + light[1] + " " + light[2]; 
                if (stepMap.get(lightString) != null || xPos < 0 || xPos >= SIZE || yPos < 0 || yPos >= SIZE) // check if outside grid bounds
                {
                    continue;
                }
                stepMap.put(lightString, true);
                
                if (grid[xPos][yPos] == '-' && (dir == 1 || dir == 3)) // set new dir(s)
                {
                    int[] l1 = new int[3];
                    int[] l2 = new int[3];
                    l1[0] = xPos; l2[0] = xPos;
                    l1[1] = yPos; l2[1] = yPos;
                    
                    l1[2] = 0;
                    l2[2] = 2;
                    
                    lights.add(l1);
                    lights.add(l2);
                }
                else if (grid[xPos][yPos] == '|' && (dir == 0 || dir == 2))
                {
                    int[] l1 = new int[3];
                    int[] l2 = new int[3];
                    l1[0] = xPos; l2[0] = xPos;
                    l1[1] = yPos; l2[1] = yPos;
                    
                    l1[2] = 1;
                    l2[2] = 3;
                    
                    lights.add(l1);
                    lights.add(l2);
                }
                else if (grid[xPos][yPos] == '/')
                {
                    if (dir == 0)
                    {
                        dir = 3;
                    }
                    else if (dir == 1)
                    {
                        dir = 2;
                    }
                    else if (dir == 2)
                    {
                        dir = 1;
                    }
                    else if (dir == 3)
                    {
                        dir = 0;
                    }
                    
                    int[] l = new int[3];
                    l[0] = xPos;
                    l[1] = yPos;
                    l[2] = dir;
                    
                    lights.add(l);
                }
                else if (grid[xPos][yPos] == '\\')
                {
                    if (dir == 0)
                    {
                        dir = 1;
                    }
                    else if (dir == 1)
                    {
                        dir = 0;
                    }
                    else if (dir == 2)
                    {
                        dir = 3;
                    }
                    else if (dir == 3)
                    {
                        dir = 2;
                    }
                    
                    int[] l = new int[3];
                    l[0] = xPos;
                    l[1] = yPos;
                    l[2] = dir;
                    
                    lights.add(l);
                }
                else
                {
                    int[] l = new int[3];
                    l[0] = xPos;
                    l[1] = yPos;
                    l[2] = dir;
                    
                    lights.add(l);
                }
            }
            
            
            for (int row = 0; row < grid.length; row++)
            {
                for (int col = 0; col < grid[row].length; col++)
                {
                    if (energized[col][row] == true)
                    {
                        answer++;
                    }
                }
            }
            
            if (answer > finalAnswer)
            {
                finalAnswer = answer;
            }
            answer = 0;
            energized = new boolean[SIZE][SIZE];
        }
        for (int yStart = 0; yStart < SIZE; yStart++)
        {
            ArrayList<int[]> lights = new ArrayList<int[]>(); // xPos, yPos, dir
            
            int xPosTemp = -1;
            int yPosTemp = yStart;
            int dirTemp = 0; // 0 == right, 1 == down, 2 == left, 3 == up
            int[] lightTemp = new int[3];
            lightTemp[0] = xPosTemp;
            lightTemp[1] = yPosTemp;
            lightTemp[2] = dirTemp;
            lights.add(lightTemp);
            
            
            HashMap<String, Boolean> stepMap = new HashMap();
            
            while (lights.size() > 0)
            {            
                int[] light = lights.remove(0);
                int xPos = light[0];
                int yPos = light[1];
                int dir = light[2];
                
        
                if ((xPos < 0 || xPos >= SIZE || yPos < 0 || yPos >= SIZE) == false)
                    energized[xPos][yPos] = true;
                
                
                
                if (dir == 0)// move based on direction
                {
                    xPos++;
                }
                else if (dir == 1)
                {
                    yPos++;
                }
                else if (dir == 2)
                {
                    xPos--;
                }
                else if (dir == 3)
                {
                    yPos--;
                }
                else System.out.println("Error dir" + dir);
                
                String lightString = "" + light[0] + " " + light[1] + " " + light[2]; 
                if (stepMap.get(lightString) != null || xPos < 0 || xPos >= SIZE || yPos < 0 || yPos >= SIZE) // check if outside grid bounds
                {
                    continue;
                }
                stepMap.put(lightString, true);
                
                if (grid[xPos][yPos] == '-' && (dir == 1 || dir == 3)) // set new dir(s)
                {
                    int[] l1 = new int[3];
                    int[] l2 = new int[3];
                    l1[0] = xPos; l2[0] = xPos;
                    l1[1] = yPos; l2[1] = yPos;
                    
                    l1[2] = 0;
                    l2[2] = 2;
                    
                    lights.add(l1);
                    lights.add(l2);
                }
                else if (grid[xPos][yPos] == '|' && (dir == 0 || dir == 2))
                {
                    int[] l1 = new int[3];
                    int[] l2 = new int[3];
                    l1[0] = xPos; l2[0] = xPos;
                    l1[1] = yPos; l2[1] = yPos;
                    
                    l1[2] = 1;
                    l2[2] = 3;
                    
                    lights.add(l1);
                    lights.add(l2);
                }
                else if (grid[xPos][yPos] == '/')
                {
                    if (dir == 0)
                    {
                        dir = 3;
                    }
                    else if (dir == 1)
                    {
                        dir = 2;
                    }
                    else if (dir == 2)
                    {
                        dir = 1;
                    }
                    else if (dir == 3)
                    {
                        dir = 0;
                    }
                    
                    int[] l = new int[3];
                    l[0] = xPos;
                    l[1] = yPos;
                    l[2] = dir;
                    
                    lights.add(l);
                }
                else if (grid[xPos][yPos] == '\\')
                {
                    if (dir == 0)
                    {
                        dir = 1;
                    }
                    else if (dir == 1)
                    {
                        dir = 0;
                    }
                    else if (dir == 2)
                    {
                        dir = 3;
                    }
                    else if (dir == 3)
                    {
                        dir = 2;
                    }
                    
                    int[] l = new int[3];
                    l[0] = xPos;
                    l[1] = yPos;
                    l[2] = dir;
                    
                    lights.add(l);
                }
                else
                {
                    int[] l = new int[3];
                    l[0] = xPos;
                    l[1] = yPos;
                    l[2] = dir;
                    
                    lights.add(l);
                }
            }
            
            
            for (int row = 0; row < grid.length; row++)
            {
                for (int col = 0; col < grid[row].length; col++)
                {
                    if (energized[col][row] == true)
                    {
                        answer++;
                    }
                }
            }
            
            if (answer > finalAnswer)
            {
                finalAnswer = answer;
            }
            answer = 0;
            energized = new boolean[SIZE][SIZE];
        }
        for (int yStart = 0; yStart < SIZE; yStart++)
        {
            ArrayList<int[]> lights = new ArrayList<int[]>(); // xPos, yPos, dir
            
            int xPosTemp = SIZE;
            int yPosTemp = yStart;
            int dirTemp = 2; // 0 == right, 1 == down, 2 == left, 3 == up
            int[] lightTemp = new int[3];
            lightTemp[0] = xPosTemp;
            lightTemp[1] = yPosTemp;
            lightTemp[2] = dirTemp;
            lights.add(lightTemp);
            
            
            HashMap<String, Boolean> stepMap = new HashMap();
            
            while (lights.size() > 0)
            {            
                int[] light = lights.remove(0);
                int xPos = light[0];
                int yPos = light[1];
                int dir = light[2];
                
        
                if ((xPos < 0 || xPos >= SIZE || yPos < 0 || yPos >= SIZE) == false)
                    energized[xPos][yPos] = true;
                
                
                
                if (dir == 0)// move based on direction
                {
                    xPos++;
                }
                else if (dir == 1)
                {
                    yPos++;
                }
                else if (dir == 2)
                {
                    xPos--;
                }
                else if (dir == 3)
                {
                    yPos--;
                }
                else System.out.println("Error dir" + dir);
                
                String lightString = "" + light[0] + " " + light[1] + " " + light[2]; 
                if (stepMap.get(lightString) != null || xPos < 0 || xPos >= SIZE || yPos < 0 || yPos >= SIZE) // check if outside grid bounds
                {
                    continue;
                }
                stepMap.put(lightString, true);
                
                if (grid[xPos][yPos] == '-' && (dir == 1 || dir == 3)) // set new dir(s)
                {
                    int[] l1 = new int[3];
                    int[] l2 = new int[3];
                    l1[0] = xPos; l2[0] = xPos;
                    l1[1] = yPos; l2[1] = yPos;
                    
                    l1[2] = 0;
                    l2[2] = 2;
                    
                    lights.add(l1);
                    lights.add(l2);
                }
                else if (grid[xPos][yPos] == '|' && (dir == 0 || dir == 2))
                {
                    int[] l1 = new int[3];
                    int[] l2 = new int[3];
                    l1[0] = xPos; l2[0] = xPos;
                    l1[1] = yPos; l2[1] = yPos;
                    
                    l1[2] = 1;
                    l2[2] = 3;
                    
                    lights.add(l1);
                    lights.add(l2);
                }
                else if (grid[xPos][yPos] == '/')
                {
                    if (dir == 0)
                    {
                        dir = 3;
                    }
                    else if (dir == 1)
                    {
                        dir = 2;
                    }
                    else if (dir == 2)
                    {
                        dir = 1;
                    }
                    else if (dir == 3)
                    {
                        dir = 0;
                    }
                    
                    int[] l = new int[3];
                    l[0] = xPos;
                    l[1] = yPos;
                    l[2] = dir;
                    
                    lights.add(l);
                }
                else if (grid[xPos][yPos] == '\\')
                {
                    if (dir == 0)
                    {
                        dir = 1;
                    }
                    else if (dir == 1)
                    {
                        dir = 0;
                    }
                    else if (dir == 2)
                    {
                        dir = 3;
                    }
                    else if (dir == 3)
                    {
                        dir = 2;
                    }
                    
                    int[] l = new int[3];
                    l[0] = xPos;
                    l[1] = yPos;
                    l[2] = dir;
                    
                    lights.add(l);
                }
                else
                {
                    int[] l = new int[3];
                    l[0] = xPos;
                    l[1] = yPos;
                    l[2] = dir;
                    
                    lights.add(l);
                }
            }
            
            
            for (int row = 0; row < grid.length; row++)
            {
                for (int col = 0; col < grid[row].length; col++)
                {
                    if (energized[col][row] == true)
                    {
                        answer++;
                    }
                }
            }
            
            if (answer > finalAnswer)
            {
                finalAnswer = answer;
            }
            answer = 0;
            energized = new boolean[SIZE][SIZE];
        }
        
        System.out.println(finalAnswer);
        
    }
}
