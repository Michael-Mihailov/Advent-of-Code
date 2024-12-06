import java.util.*;
import java.io.*;

public class Day6
{
    public static void main(String[] args) throws IOException
    {
        File file = new File("input.txt");
        Scanner scn = new Scanner(file);
        
        int ans = 0;
        
        boolean[][] uberMap = new boolean[130][130];
        
        int startX = -1;
        int startY = -1;
        
        int posX = -1;
        int posY = -1;
        int dir = 0; // 0 to 3
        
        boolean[][] path = new boolean[130][130];
        
        for (int j = 0; j < uberMap.length; j++)
        {
            String line = scn.nextLine();
            
            for (int i = 0; i < line.length(); i++)
            {
                if (line.charAt(i) == '#')
                {
                    uberMap[j][i] = true;
                }
                
                if (line.charAt(i) == '^')
                {
                    posX = i;
                    posY = j;
                    
                    startX = i;
                    startY = j;
                }
            }
        }
        
        for (int j = 0; j < uberMap.length; j++)
        {
            for (int i = 0; i < uberMap.length; i++)
            {
                //System.out.println(j + " " + i);
                if (j == startY && i == startX) continue;
                
                boolean[][] map = new boolean[130][130];
                for (int y = 0; y < map.length; y++)
                {
                    for (int x = 0; x < map.length; x++)
                    {
                        map[y][x] = uberMap[y][x];
                    }
                }
                map[j][i] = true;
                
                posX = startX;
                posY = startY;
                dir = 0;
                
                
                int step = 0;
                boolean stuck = false;
                while (posX >= 0 && posX < map.length && posY >= 0 && posY < map.length && stuck == false)
                {
                    step++;
                    if (step > 100000)
                    {
                        ans++;
                        stuck = true;
                    }
                    
                    path[posY][posX] = true;
                    
                    int prevX = posX;
                    int prevY = posY;
                    
                    if (dir == 0)
                    {
                        posY--;
                    }
                    else if (dir == 1)
                    {
                        posX++;
                    }
                    else if (dir == 2)
                    {
                        posY++;
                    }
                    else if (dir == 3)
                    {
                        posX--;
                    }
                    
                    try 
                    {
                        if (map[posY][posX] == true)
                        {
                            posX = prevX; 
                            posY = prevY;
                            dir++;
                            if (dir == 4) dir = 0;
                        }
                    } catch (Exception e) {}
                }
            }
        }
        
        
        /*
        for (int j = 0; j < map.length; j++)
        {
            for (int i = 0; i < map.length; i++)
            {
                if (path[j][i]) ans++;
            }
        }
        */
        
        System.out.println(ans);
    }
}