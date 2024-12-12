import java.util.*;
import java.io.*;

public class Day12
{
    public static void main(String[] args) throws IOException
    {
        File file = new File("input.txt");
        Scanner scn = new Scanner(file);
        
        int ans = 0;
        
        final int SIZE = 140;
        
        char[][] map = new char[SIZE][SIZE];
        int[][] plotMap = new int[SIZE][SIZE];
        
        for (int j = 0; j < SIZE; j++)
        {
            String line = scn.nextLine();
            for (int i = 0; i < SIZE; i++)
            {
                map[j][i] = line.charAt(i);
            }
        }
        
        int plotId = 0;
        for (int j = 0; j < SIZE; j++)
        {            
            for (int i = 0; i < SIZE; i++)
            {
                if (plotMap[j][i] == 0)
                {
                    plotId++;
                    plotMap[j][i] = plotId;
                    for (int step = 0; step < 1000; step++)
                    {
                        for (int y = 0; y < SIZE; y++)
                        {
                            for (int x = 0; x < SIZE; x++)
                            {
                                if (plotMap[y][x] != plotId) continue;
                                
                                try
                                {
                                    if (map[y][x] == map[y + 1][x]) plotMap[y + 1][x] = plotId;
                                } catch (Exception e) {}
                                try
                                {
                                    if (map[y][x] == map[y - 1][x]) plotMap[y - 1][x] = plotId;
                                } catch (Exception e) {}
                                try
                                {
                                    if (map[y][x] == map[y][x + 1]) plotMap[y][x + 1] = plotId;
                                } catch (Exception e) {}
                                try
                                {
                                    if (map[y][x] == map[y][x - 1]) plotMap[y][x - 1] = plotId;
                                } catch (Exception e) {}
                            }
                        }
                    }
                }
            }
        }
        
        for (int id = 1; id <= plotId; id++)
        {
            int area = 0;
            int corners = 0;
            
            for (int y = 0; y < SIZE; y++)
            {
                for (int x = 0; x < SIZE; x++)
                {
                    if (plotMap[y][x] == id)
                    {
                        area++;
                    }
                }
            }
            
            for (int j = 0; j <= SIZE; j++) // top left most corner of map
            {
                for (int i = 0; i <= SIZE; i++)
                {
                    int numFalse = 0;
                    int numTrue = 0;
                    
                    boolean[][] mobius = new boolean[2][2];
                    
                    try
                    {
                        if (plotMap[j - 1][i - 1] == id)
                        {
                            numTrue++;
                            mobius[0][0] = true;
                        }
                        else
                        {
                            numFalse++;
                        }
                    } catch (Exception e) {numFalse++;}
                    try
                    {
                        if (plotMap[j - 1][i] == id)
                        {
                            numTrue++;
                            mobius[1][0] = true;
                        }
                        else
                        {
                            numFalse++;
                        }
                    } catch (Exception e) {numFalse++;}
                    try
                    {
                        if (plotMap[j][i - 1] == id)
                        {
                            numTrue++;
                            mobius[0][1] = true;
                        }
                        else
                        {
                            numFalse++;
                        }
                    } catch (Exception e) {numFalse++;}
                    try
                    {
                        if (plotMap[j][i] == id)
                        {
                            numTrue++;
                            mobius[1][1] = true;
                        }
                        else
                        {
                            numFalse++;
                        }
                    } catch (Exception e) {numFalse++;}
                    
                    if (numFalse + numTrue != 4) System.out.println("Error 1");
                    
                    if ( (numFalse == 3 && numTrue == 1) || (numFalse == 1 && numTrue == 3) )
                    {
                        corners++;
                    }
                    else if (numFalse == 2 && numTrue == 2)
                    {
                        if (mobius[0][0] == mobius[1][1])
                        {
                            corners += 2;
                        }
                    }
                }
            }
                        
            ans += area * corners;
        }
        
        /*
        for (int id = 1; id <= plotId; id++)
        {
            int area = 0;
            int antiPerimeter = 0;
            
            for (int y = 0; y < SIZE; y++)
            {
                for (int x = 0; x < SIZE; x++)
                {
                    if (plotMap[y][x] == id)
                    {
                        area++;
                        
                        try
                        {
                            if (plotMap[y][x] == plotMap[y + 1][x]) antiPerimeter++;
                        } catch (Exception e) {}
                        try
                        {
                            if (plotMap[y][x] == plotMap[y - 1][x]) antiPerimeter++;
                        } catch (Exception e) {}
                        try
                        {
                            if (plotMap[y][x] == plotMap[y][x + 1]) antiPerimeter++;
                        } catch (Exception e) {}
                        try
                        {
                            if (plotMap[y][x] == plotMap[y][x - 1]) antiPerimeter++;
                        } catch (Exception e) {}
                    }
                }
            }
            
            int perimeter = (4 * area) - antiPerimeter;
            
            ans += area * perimeter;
        }
        */
        
        
        System.out.println(ans);
    }
}