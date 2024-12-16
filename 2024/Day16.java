import java.util.*;
import java.io.*;

public class Day16
{
    public static void main(String[] args) throws IOException
    {
        File file = new File("input.txt");
        Scanner scn = new Scanner(file);
        
        int part1Answer = Integer.MAX_VALUE;
        int part2Answer = 0;
        
        final int SIZE = 141;
        
        boolean[][] walls = new boolean[SIZE][SIZE];
        
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
                    start = new int[]{y, x};
                }
                else if (line.charAt(x) == 'E')
                {
                    end = new int[]{y, x};
                }
            }
        }
        
        for (int dir = 0; dir < 4; dir++)
        {
            int temp = calculateScore(new int[]{start[0], start[1], 0}, new int[]{end[0], end[1], dir}, walls);
            if (temp < part1Answer)
            {
                part1Answer = temp;
            }
        }
        
        System.out.println("part 1: " + part1Answer);
        
        
        boolean[][] validMap = new boolean[SIZE][SIZE];
        
        int[][][] startPaths = idealPaths(new int[]{start[0], start[1], 0}, walls);
        for (int dir = 0; dir < 4; dir++)
        {
            if (startPaths[end[0]][end[1]][dir] != part1Answer) continue;
            
            int[][][] endPaths = idealPaths(new int[]{end[0], end[1], (dir + 4 - 2) % 4}, walls);
            for (int y = 0; y < SIZE; y++)
            {
                for (int x = 0; x < SIZE; x++)
                {   
                    if (walls[y][x] == true) continue;
                    
                    for (int d = 0; d < 4; d++)
                    {
                        int seg1 = startPaths[y][x][d];
                        int seg2 = endPaths[y][x][(d + 4 - 2) % 4];
                        if (seg1 + seg2 == part1Answer)
                        {
                            validMap[y][x] = true;
                        }    
                    }
                }
            }
        }
        
        for (int y = 0; y < SIZE; y++)
        {
            for (int x = 0; x < SIZE; x++)
            {   
                if (validMap[y][x] == true)
                {
                    part2Answer++;
                }
            }
        }
        
        
        System.out.println("part 2: " + part2Answer);
    }
    
    public static int[][][] idealPaths(int[] start, boolean walls[][])
    {
        final int SIZE = walls.length;
        
        int[][][] scores = new int[SIZE][SIZE][4]; // 0 == east, 1 == south, etc.
        
        scores[start[0]][start[1]][start[2]] = 1; // ans has 1 extra point
        
        for (int step = 0; step < 3000; step++)
        {
            int[][][] newScores = new int[SIZE][SIZE][4];
            
            for (int y = 0; y < SIZE; y++)
            {
                for (int x = 0; x < SIZE; x++)
                {
                    if (walls[y][x] == true) continue;
                    
                    int[][] dirMap = new int[][]{{0,1},{1,0},{0,-1},{-1,0}};
                    for (int dir = 0; dir < 4; dir++)
                    {
                        int temp = scores[y][x][dir];
                        if (temp == 0) continue;
                        
                        if (newScores[y][x][(dir + 4 - 1) % 4] == 0 || newScores[y][x][(dir + 4 - 1) % 4] > temp + 1000) // turn counterclockwise
                        {
                            newScores[y][x][(dir + 4 - 1) % 4] = temp + 1000;
                            //System.out.println("turn left");
                        }
                        if (newScores[y][x][(dir + 1) % 4] == 0 || newScores[y][x][(dir + 1) % 4] > temp + 1000) // turn clockwise
                        {
                            newScores[y][x][(dir + 1) % 4] = temp + 1000;
                            //System.out.println("turn right");
                        }
                        
                        if (walls[y + dirMap[dir][0]][x + dirMap[dir][1]] == false)
                        {
                            if (newScores[y + dirMap[dir][0]][x + dirMap[dir][1]][dir] == 0 || newScores[y + dirMap[dir][0]][x + dirMap[dir][1]][dir] > temp + 1)
                            {
                                newScores[y + dirMap[dir][0]][x + dirMap[dir][1]][dir] = temp + 1;
                                //System.out.println("move forward");
                            }
                        }
                    }
                }
            }
            
            for (int y = 0; y < SIZE; y++)
            {
                for (int x = 0; x < SIZE; x++)
                {
                    for (int dir = 0; dir < 4; dir++)
                    {
                        if (scores[y][x][dir] == 0 || (scores[y][x][dir] > newScores[y][x][dir] && newScores[y][x][dir] != 0))
                        {
                            scores[y][x][dir] = newScores[y][x][dir];
                        }
                    }
                }
            }
        }
        
        for (int y = 0; y < SIZE; y++) // remove the extra point
        {
            for (int x = 0; x < SIZE; x++)
            {
                for (int dir = 0; dir < 4; dir++)
                {
                    scores[y][x][dir]--;
                }
            }
        }
        
        return scores;
    }
    
    public static int calculateScore(int[] start, int[] end, boolean walls[][])
    {
        int ans = Integer.MAX_VALUE;
        final int SIZE = walls.length;
        
        int[][][] scores = new int[SIZE][SIZE][4]; // 0 == east, 1 == south, etc.
        
        scores[start[0]][start[1]][start[2]] = 1; // ans has 1 extra point
        
        for (int step = 0; step < 1000; step++)
        {
            int[][][] newScores = new int[SIZE][SIZE][4];
            
            for (int y = 0; y < SIZE; y++)
            {
                for (int x = 0; x < SIZE; x++)
                {
                    if (walls[y][x] == true) continue;
                    
                    int[][] dirMap = new int[][]{{0,1},{1,0},{0,-1},{-1,0}};
                    for (int dir = 0; dir < 4; dir++)
                    {
                        int temp = scores[y][x][dir];
                        if (temp == 0) continue;
                        
                        if (newScores[y][x][(dir + 4 - 1) % 4] == 0 || newScores[y][x][(dir + 4 - 1) % 4] > temp + 1000) // turn counterclockwise
                        {
                            newScores[y][x][(dir + 4 - 1) % 4] = temp + 1000;
                            //System.out.println("turn left");
                        }
                        if (newScores[y][x][(dir + 1) % 4] == 0 || newScores[y][x][(dir + 1) % 4] > temp + 1000) // turn clockwise
                        {
                            newScores[y][x][(dir + 1) % 4] = temp + 1000;
                            //System.out.println("turn right");
                        }
                        
                        if (walls[y + dirMap[dir][0]][x + dirMap[dir][1]] == false)
                        {
                            if (newScores[y + dirMap[dir][0]][x + dirMap[dir][1]][dir] == 0 || newScores[y + dirMap[dir][0]][x + dirMap[dir][1]][dir] > temp + 1)
                            {
                                newScores[y + dirMap[dir][0]][x + dirMap[dir][1]][dir] = temp + 1;
                                //System.out.println("move forward");
                            }
                        }
                    }
                }
            }
            
            for (int y = 0; y < SIZE; y++)
            {
                for (int x = 0; x < SIZE; x++)
                {
                    for (int dir = 0; dir < 4; dir++)
                    {
                        if (scores[y][x][dir] == 0 || (scores[y][x][dir] > newScores[y][x][dir] && newScores[y][x][dir] != 0))
                        {
                            scores[y][x][dir] = newScores[y][x][dir];
                        }
                    }
                }
            }
        }
        
        for (int dir = 0; dir < 4; dir++)
        {
            if (scores[end[0]][end[1]][dir] < ans)
            {
                ans = scores[end[0]][end[1]][dir];
            }
        }
        
        ans--; // remove the extra point
        
        return ans;
    }
    
    public static void display(int[][][] scores, boolean walls[][])
    {
        int SIZE = scores.length;
        
        for (int y = 0; y < SIZE; y++)
        {
            for (int x = 0; x < SIZE; x++)
            {
                int occupied = 0;
                int best = Integer.MAX_VALUE;
                for (int dir = 0; dir < 4; dir++)
                {
                    if (scores[y][x][dir] > 0 && scores[y][x][dir] < best)
                    {
                        occupied = dir + 1;
                        best = scores[y][x][dir];
                    }
                }
                
                if (walls[y][x] && occupied != 0)
                {
                    System.out.print("X");
                }
                else if (walls[y][x])
                {
                    System.out.print("#");
                }
                else if (occupied == 0)
                {
                    System.out.print(".");
                }
                else
                {
                    System.out.print(occupied);
                }
            }
            System.out.println();
        }
        System.out.println();
    }
}