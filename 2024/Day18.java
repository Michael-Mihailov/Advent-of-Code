import java.util.*;
import java.io.*;

public class Day18
{
    public static void main(String[] args) throws IOException
    {
        File file = new File("input.txt");
        Scanner scn = new Scanner(file);
        
        String ans = "";
        final int SIZE = 71;
        
        boolean[][] walls = new boolean[SIZE][SIZE];
        
        boolean done = false;
        for (int b = 0; done == false; b++)
        {
            System.out.println("byte #" + b);
            
            String[] bStr = scn.nextLine().split(",");
            walls[Integer.parseInt(bStr[0])][Integer.parseInt(bStr[1])] = true;
            
            boolean[][] pathGrid = new boolean[SIZE][SIZE]; // x, y
            pathGrid[0][0] = true;
        
            int[][] dirs = new int[][]{{1,0},{0,1},{-1,0},{0,-1}};
            int step = 0;
            while (pathGrid[SIZE-1][SIZE-1] == false && step < 100000)
            {
                //if (step % 1000 == 0)display(pathGrid, walls);
                
                step++;
                boolean[][] newGrid = new boolean[SIZE][SIZE];
                for (int x = 0; x < SIZE; x++)
                {
                    for (int y = 0; y < SIZE; y++)
                    {
                        if (pathGrid[x][y] == false || walls[x][y] == true)
                        {
                            continue;
                        }
                        newGrid[x][y] = true;
                        for (int dir = 0; dir < 4; dir++)
                        {
                            try
                            {
                                if (walls[x+dirs[dir][0]][y+dirs[dir][1]] == false)
                                {
                                    newGrid[x+dirs[dir][0]][y+dirs[dir][1]] = true;
                                }
                            } catch (Exception e) {};
                        }
                    }
                }
                
                pathGrid = newGrid;
            }
            
            if (pathGrid[SIZE-1][SIZE-1] == false)
            {
                ans = bStr[0] + "," + bStr[1];
                done = true;
            }
        }
        
        
        
        System.out.println(ans);
    }
    
    public static void display(boolean[][] pathgrid, boolean[][] walls)
    {
        for (int y = 0; y < walls.length; y++)
        {
            for (int x = 0; x < walls.length; x++)
            {
                if (walls[x][y]) System.out.print("#");
                else if (pathgrid[x][y]) System.out.print("O");
                else System.out.print(".");
            }
            System.out.println();
        }
        System.out.println();
        System.out.println();
        System.out.println();
    }
}