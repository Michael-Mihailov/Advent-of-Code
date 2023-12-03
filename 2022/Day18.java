
import java.io.*;
import java.util.*;

public class Day18
{
    static boolean[][][] outside = new boolean[30][30][30];
    static boolean[][][] map = new boolean[30][30][30];

    
    public static void main (String[] args) throws Exception
    {
        File file = new File("input.txt");
        Scanner scn = new Scanner(file);
        
        int count = 0;
        
        while (scn.hasNext())
        {
            String data = scn.nextLine();
            
            Scanner splitScn = new Scanner(data);
            splitScn.useDelimiter(",");
            
            int x = splitScn.nextInt();
            int y = splitScn.nextInt();
            int z = splitScn.nextInt();
            
            map[x+1][y+1][z+1] = true;
        }
        
        outside[0][0][0] = true;
        for(int r = 0; r < 1000; r ++)
        {
            for (int z = 0; z < 30; z++)
            {
                for (int y = 0; y < 30; y++)
                {
                    for (int x = 0; x < 30; x++)
                    {
                        if (outside[x][y][z] == true)
                        {
                            if (x != 29 && map[x+1][y][z] == false)
                            {
                                outside[x+1][y][z] = true;
                            }
                            if (x != 0 && map[x-1][y][z] == false)
                            {
                                outside[x-1][y][z] = true;
                            }
                            if (y != 29 && map[x][y+1][z] == false)
                            {
                                outside[x][y+1][z] = true;
                            }
                            if (y != 0 && map[x][y-1][z] == false)
                            {
                                outside[x][y-1][z] = true;
                            }
                            if (z != 29 && map[x][y][z+1] == false)
                            {
                                outside[x][y][z+1] = true;
                            }
                            if (z != 0 && map[x][y][z-1] == false)
                            {
                                outside[x][y][z-1] = true;
                            }
                        }
                    }
                }
            }
        }
        
        for (int z = 0; z < 30; z++)
        {
            for (int y = 0; y < 30; y++)
            {
                for (int x = 0; x < 30; x++)
                {
                    if (map[x][y][z] == true)
                    {
                        if (map[x+1][y][z] == false && outside[x+1][y][z] == true)
                        {
                            count++;
                        }
                        if (map[x-1][y][z] == false && outside[x-1][y][z] == true)
                        {
                            count++;
                        }
                        if (map[x][y+1][z] == false && outside[x][y+1][z] == true)
                        {
                            count++;
                        }
                        if (map[x][y-1][z] == false && outside[x][y-1][z] == true)
                        {
                            count++;
                        }
                        if (map[x][y][z+1] == false && outside[x][y][z+1] == true)
                        {
                            count++;
                        }
                        if (map[x][y][z-1] == false && outside[x][y][z-1] == true)
                        {
                            count++;
                        }
                    }
                }
            }
        }
        
        System.out.println(count);
    }
    
    static void check (int x, int y, int z)
    {
        if (outside[x][y][z] == false)
        {
            outside[x][y][z] = true;
            if (x != 29 && map[x+1][y][z] == false)
            {
                check(x+1, y, z);
            }
            if (x != 0 && map[x-1][y][z] == false)
            {
                check(x-1, y, z);
            }
            if (y != 29 && map[x][y+1][z] == false)
            {
                check(x, y+1, z);
            }
            if (y != 0 && map[x][y-1][z] == false)
            {
                check(x, y-1, z);
            }
            if (z != 29 && map[x][y][z+1] == false)
            {
                check(x, y, z+1);
            }
            if (z != 0 && map[x][y][z-1] == false)
            {
                check(x, y, z-1);
            }
        }
    }
}
