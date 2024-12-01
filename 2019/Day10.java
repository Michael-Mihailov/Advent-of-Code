import java.util.*;
import java.io.*;

public class Day10
{
    public static void main(String args[]) throws IOException
    {
        File file = new File("input.txt");
        Scanner scn = new Scanner(file);
        
        int ans = 0;
        int xStation = 0;
        int yStation = 0;
        
        boolean[][] map = new boolean[33][33];
        
        for (int y = 0; y < map.length; y++)
        {
            String line = scn.nextLine();
            for (int x = 0; x < map.length; x++)
            {
                if (line.charAt(x) == '#') map[x][y] = true;
            }
        }
        
        
        
        for (int a = 0; a < map.length; a++)
        {
            for (int b = 0; b < map.length; b++)
            {
                // (a,b) is the build location being checked
                if (map[a][b] == false) continue;

                int score = 0;
                                
                for (int x = 0; x < map.length; x++)
                {
                    for (int y = 0; y < map.length; y++)
                    {
                        // (x,y) is the asteroid being checked
                        if (map[x][y] == false || (x == a && y == b)) continue;
                        
                        boolean visible = true;
                        ArrayList<int[]> colinear = colinearPositions(a, b, x, y);
                        for (int[] pos : colinear)
                        {
                            if (map[pos[0]][pos[1]]) visible = false;
                        }
                        if (visible) score++;
                    }
                }
                
                if (score > ans)
                {
                    ans = score;
                    xStation = a;
                    yStation = b;
                }
            }
        }
        
        // System.out.println(ans); //part 1
                        
        boolean[][] killMap = new boolean[map.length][map.length];
        for (int x = 0; x < map.length; x++)
        {
            for (int y = 0; y < map.length; y++)
            {
                // (x,y) is the asteroid being checked
                if (map[x][y] == false || (x == xStation && y == yStation)) continue;
                
                boolean visible = true;
                ArrayList<int[]> colinear = colinearPositions(xStation, yStation, x, y);
                for (int[] pos : colinear)
                {
                    if (map[pos[0]][pos[1]]) visible = false;
                }
                if (visible) 
                {
                    killMap[x][y] = true;
                }
            }
        }
        
        double[][] rotationMap = new double[map.length][map.length];
                
        for (int x = 0; x < map.length; x++)
        {
            for (int y = 0; y < map.length; y++)
            {
                double temp = -Math.atan2(x - xStation, y - yStation) + Math.PI;
                
                rotationMap[x][y] = temp;
            }
        }
        
        
        int kills = 0;
        
        for (double theta = 0; theta < 2 * Math.PI; theta += 0.01)
        {
            for (int x = 0; x < map.length; x++)
            {
                for (int y = 0; y < map.length; y++)
                {
                    if (killMap[x][y] && rotationMap[x][y] < theta)
                    {
                        killMap[x][y] = false;
                        kills++;
                                                
                        if(kills == 200)
                        {
                            System.out.println((x * 100) + y); //part 2 answer
                        }
                    }
                }
            }
        }
        
        /*
        System.out.println(rotationMap[xStation][yStation - 1]);
        System.out.println(rotationMap[xStation + 1][yStation - 1]);
        System.out.println(rotationMap[xStation + 1][yStation]);
        System.out.println(rotationMap[xStation + 1][yStation + 1]);
        System.out.println(rotationMap[xStation][yStation + 1]);
        System.out.println(rotationMap[xStation - 1][yStation + 1]);
        System.out.println(rotationMap[xStation - 1][yStation]);
        System.out.println(rotationMap[xStation - 1][yStation - 1]);
        */
    }
    
    public static ArrayList<int[]> colinearPositions(int x1, int y1, int x2, int y2)
    {
        ArrayList<int[]> ans = new ArrayList<>();
        
        int xDiff = x2 - x1;
        int yDiff = y2 - y1;
        
        int steps = gcf(xDiff, yDiff); // how many in between points to check
        
        xDiff /= steps;
        yDiff /= steps;
        
        for (int step = 1; step < steps; step++)
        {
            ans.add(new int[]{x1 + (xDiff * step), y1 + (yDiff * step)});
        }
        
        return ans;
    }
    
    public static int gcf(int x, int y) //gfc up to 100
    {
        for (int num = 100; num > 0; num--)
        {
            if (x % num == 0 && y % num == 0) return num;
        }
        return 1;
    }
}