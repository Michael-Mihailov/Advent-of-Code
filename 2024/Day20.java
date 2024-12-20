import java.util.*;
import java.io.*;

public class Day20
{
    public static void main(String[] args) throws IOException
    {
        File file = new File("input.txt");
        Scanner scn = new Scanner(file);
        
        int ans = 0;
        
        final int SIZE = 141;
        int[][] dir = new int[][]{{0,1},{1,0},{0,-1},{-1,0}};
        
        boolean[][] walls = new boolean[SIZE][SIZE];
        int[][] noCheat = new int[SIZE][SIZE];
        int[] start = new int[2];
        int[] end = new int[2];
        
        for (int y = 0; y < SIZE; y++)
        {
            String line = scn.nextLine();
            for (int x = 0; x < SIZE; x++)
            {
                if (line.charAt(x) == '#')
                {
                    walls[y][x] = true;
                }
                else if (line.charAt(x) == 'S')
                {
                    start = new int[]{y,x};
                }
                else if (line.charAt(x) == 'E')
                {
                    end = new int[]{y,x};
                }
            }
        }
        noCheat[start[0]][start[1]] = 1;
        
        int step = 0;
        while (noCheat[end[0]][end[1]] == 0)
        {
            int[][] newNoCheat = new int[SIZE][SIZE];
            
            for (int y = 0; y < SIZE; y++)
            {
                for (int x = 0; x < SIZE; x++)
                {
                    for (int d = 0; d < dir.length; d++)
                    {
                        try
                        {
                            if (noCheat[y][x] != 0 && walls[y+dir[d][0]][x+dir[d][1]] == false)
                            {
                                newNoCheat[y+dir[d][0]][x+dir[d][1]] = (newNoCheat[y+dir[d][0]][x+dir[d][1]] == 0 || newNoCheat[y+dir[d][0]][x+dir[d][1]] > noCheat[y][x] + 1) ? noCheat[y][x] + 1 : newNoCheat[y+dir[d][0]][x+dir[d][1]];
                            }
                        } catch (Exception e) {}
                    }
                }
            }
            
            for (int y = 0; y < SIZE; y++)
            {
                for (int x = 0; x < SIZE; x++)
                {
                    if (newNoCheat[y][x] != 0 && (newNoCheat[y][x] < noCheat[y][x] || noCheat[y][x] == 0))
                    {
                        noCheat[y][x] = newNoCheat[y][x];
                    }
                }
            }
        }
        
        int noCheatTime = noCheat[end[0]][end[1]];
        System.out.println("noCheatTime: " + noCheatTime);
        
        for (int y = 0; y < SIZE; y++)
        {
            for (int x = 0; x < SIZE; x++)
            {
                if (noCheat[y][x] != 0)
                {
                    int[][] cheat = new int[SIZE][SIZE];
                    cheat[y][x] = noCheat[y][x];
                    /*
                    for (int d = 0; d < dir.length; d++)
                    {
                        if (walls[y+dir[d][0]][x+dir[d][1]] == true) cheat[y+dir[d][0]][x+dir[d][1]] = noCheat[y][x] + 1;
                    }
                    */
                        
                    for (int second = 0; second < 20; second++) // I am dumb. VERY DUMB. I did not relize that you could still go over regular tiles while "cheating". I would have just calculated manhattan distance instead if I had relized sooner.
                    {
                        int[][] newCheat = new int[SIZE][SIZE];
                        for (int j = 0; j < SIZE; j++)
                        {
                            for (int i = 0; i < SIZE; i++)
                            {
                                if (cheat[j][i] != 0) // && walls[j][i] == true)
                                {
                                    for (int d = 0; d < dir.length; d++)
                                    {
                                        try
                                        {                                                                                   
                                            newCheat[j+dir[d][0]][i+dir[d][1]] = (newCheat[j+dir[d][0]][i+dir[d][1]] == 0 || newCheat[j+dir[d][0]][i+dir[d][1]] > cheat[j][i] + 1) ? cheat[j][i] + 1 : newCheat[j+dir[d][0]][i+dir[d][1]];
                                        } catch (Exception e) {}
                                    }
                                }
                            }
                        }
                        for (int j = 0; j < SIZE; j++)
                        {
                            for (int i = 0; i < SIZE; i++)
                            {
                                if (newCheat[j][i] != 0 && (newCheat[j][i] < cheat[j][i] || cheat[j][i] == 0))
                                {
                                    cheat[j][i] = newCheat[j][i];
                                }
                            }
                        }
                    }
                    
                    for (int j = 0; j < SIZE; j++)
                    {
                        for (int i = 0; i < SIZE; i++)
                        {
                            if (walls[j][i] == false && cheat[j][i] != 0)
                            {
                                int diff = noCheat[j][i] - cheat[j][i];
                                if (diff >= 100) ans++;
                            }
                        }
                    }
                }
            }
        }
        
        System.out.println("ans: " + ans);
    }
}