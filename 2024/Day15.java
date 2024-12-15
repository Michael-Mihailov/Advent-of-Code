import java.util.*;
import java.io.*;

public class Day15
{
    public static void main(String[] args) throws IOException
    {
        File file = new File("input.txt");
        Scanner scn = new Scanner(file);
        
        int ans = 0;
        
        final int SIZE = 50;
        
        String ops = "";
        boolean[][] walls = new boolean[SIZE][2*SIZE];
        boolean[][] leftBoxes = new boolean[SIZE][2*SIZE];
        boolean[][] rightBoxes = new boolean[SIZE][2*SIZE];
        
        int xPos = -1;
        int yPos = -1;
        
        for (int y = 0; y < SIZE; y++)
        {
            String line = scn.nextLine();
            for (int x = 0; x < SIZE; x++)
            {
                if (line.charAt(x) == '#')
                {
                    walls[y][x*2] = true;
                    walls[y][x*2 + 1] = true;
                }
                if (line.charAt(x) == 'O')
                {
                    leftBoxes[y][x*2] = true;
                    rightBoxes[y][x*2 + 1] = true;
                }
                if (line.charAt(x) == '@')
                {
                    xPos = x * 2;
                    yPos = y;
                }
            }
        }
        scn.nextLine();
        while (scn.hasNextLine())
        {
            ops += scn.nextLine();
        }
        
        for (int operation = 0; operation < ops.length(); operation++)
        {
            //display(xPos, yPos, walls, leftBoxes, rightBoxes);
            //System.out.println(xPos + "," + yPos);
            
            char c = ops.charAt(operation);
            
            int[] dir = new int[2];
            if (c == '^')
            {
                dir = new int[]{-1,0};
            }
            else if (c == '>')
            {
                dir = new int[]{0,1};
            }
            else if (c == 'v')
            {
                dir = new int[]{1,0};
            }
            else if (c == '<')
            {
                dir = new int[]{0,-1};
            }
            else {System.out.println("Error 1");}
            
            //System.out.println("dir: " + dir[1] + ", " + dir[0]);
            
            if (canMove(xPos + dir[1], yPos + dir[0], walls, leftBoxes, rightBoxes, dir) == true)
            {
                move(xPos + dir[1], yPos + dir[0], walls, leftBoxes, rightBoxes, dir);
                yPos += dir[0];
                xPos += dir[1];
            }
        }
        //display(xPos, yPos, walls, leftBoxes, rightBoxes);
        
        for (int y = 0; y < SIZE; y++)
        {
            for (int x = 0; x < SIZE * 2; x++)
            {
                if (leftBoxes[y][x])
                {
                    ans += (100 * y) + x;
                }
            }
        }
        
        System.out.println(ans);
    }
    
    public static boolean canMove(int xPos, int yPos, boolean[][] walls, boolean[][] leftBoxes, boolean[][] rightBoxes, int[] dir)
    {
        if (walls[yPos][xPos] == true) 
        {
            //System.out.println("wall");
            return false;
        }
        if (leftBoxes[yPos][xPos] == false && rightBoxes[yPos][xPos] == false) return true;
        
        if (dir[1] != 0) //horizontal
        {
            return canMove(xPos + dir[1], yPos, walls, leftBoxes, rightBoxes, dir);
        }
        
        // vertical  
        if (rightBoxes[yPos][xPos] == true)
        {
            xPos--;
        }
        
        if (canMove(xPos, yPos + dir[0], walls, leftBoxes, rightBoxes, dir) == false) return false;
        if (canMove(xPos + 1, yPos + dir[0], walls, leftBoxes, rightBoxes, dir) == false) return false;
        
        return true;
    }
    
    public static void move(int xPos, int yPos, boolean[][] walls, boolean[][] leftBoxes, boolean[][] rightBoxes, int[] dir)
    {        
        if (canMove(xPos, yPos, walls, leftBoxes, rightBoxes, dir) == false) return;
        if (leftBoxes[yPos][xPos] == false && rightBoxes[yPos][xPos] == false) return;
        
        if (dir[1] == 1) // move right
        {
            move(xPos + 2, yPos, walls, leftBoxes, rightBoxes, dir);
            
            leftBoxes[yPos][xPos] = false;
            leftBoxes[yPos][xPos + 1] = true;
            
            rightBoxes[yPos][xPos + 1] = false;
            rightBoxes[yPos][xPos + 2] = true;
        }
        else if (dir[1] == -1) // move left
        {
            move(xPos - 2, yPos, walls, leftBoxes, rightBoxes, dir);
            
            rightBoxes[yPos][xPos] = false;
            rightBoxes[yPos][xPos - 1] = true;
            
            leftBoxes[yPos][xPos - 1] = false;
            leftBoxes[yPos][xPos - 2] = true;
        }
        else // vertical movement
        {
            if (rightBoxes[yPos][xPos] == true)
            {
                xPos--;
            }
            
            move(xPos, yPos + dir[0], walls, leftBoxes, rightBoxes, dir);
            move(xPos + 1, yPos + dir[0], walls, leftBoxes, rightBoxes, dir);
            
            leftBoxes[yPos][xPos] = false;
            rightBoxes[yPos][xPos + 1] = false;
            
            leftBoxes[yPos + dir[0]][xPos] = true;
            rightBoxes[yPos + dir[0]][xPos + 1] = true;
        }
    }
    
    public static void display(int xPos, int yPos, boolean[][] walls, boolean[][] leftBoxes, boolean[][] rightBoxes)
    {
        for (int y = 0; y < walls.length; y++)
        {
            for (int x = 0; x < walls[y].length; x++)
            {
                if (walls[y][x])
                {
                    System.out.print("#");
                }
                else if (leftBoxes[y][x] && rightBoxes[y][x])
                {
                    System.out.print("X");
                }
                else if (leftBoxes[y][x])
                {
                    System.out.print("[");
                }
                else if (rightBoxes[y][x])
                {
                    System.out.print("]");
                }
                else if (x == xPos && y == yPos)
                {
                    System.out.print("@");
                }
                else
                {
                    System.out.print(".");
                }
            }
            System.out.println();
        }
        System.out.println();
    }
}