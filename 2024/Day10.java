import java.util.*;
import java.io.*;

public class Day10
{
    public static void main(String[] args) throws IOException
    {
        File file = new File("input.txt");
        Scanner scn = new Scanner(file);
        
        long ans = 0;
        
        final int SIZE = 60;
        
        int[][] map = new int[SIZE][SIZE];
        
        for (int y = 0; y < SIZE; y++)
        {
            String line = scn.nextLine();
            for (int x = 0; x < SIZE; x++)
            {
                map[y][x] = line.charAt(x) - '0';
            }
        }
        
        for (int y = 0; y < SIZE; y++)
        {
            for (int x = 0; x < SIZE; x++)
            {
                if (map[y][x] != 0) continue;
                
                int[][] found = new int[SIZE][SIZE];
                found[y][x] = 1;
                
                for (int step = 0; step < 10000; step++)
                {
                    for (int j = 0; j < SIZE; j++)
                    {
                        for (int i = 0; i < SIZE; i++)
                        {
                            if (found[j][i] == 0) continue;
                            
                            try
                            {
                                if (map[j + 1][i] == map[j][i] + 1) found[j + 1][i] +=  found[j][i];
                            } catch (Exception e){}
                            try
                            {
                                if (map[j - 1][i] == map[j][i] + 1) found[j - 1][i] +=  found[j][i];
                            } catch (Exception e){}
                            try
                            {
                                if (map[j][i + 1] == map[j][i] + 1) found[j][i + 1] +=  found[j][i];
                            } catch (Exception e){}
                            try
                            {
                                if (map[j][i - 1] == map[j][i] + 1) found[j][i - 1] +=  found[j][i];
                            } catch (Exception e){}
                            
                            if (map[j][i] != 9)
                            {
                                found[j][i] = 0;
                            }
                        }
                    }
                }
                
                for (int j = 0; j < SIZE; j++)
                {
                    for (int i = 0; i < SIZE; i++)
                    {
                        if (map[j][i] == 9 && found[j][i] != 0)
                        {
                            ans += found[j][i];
                        }
                    }
                }
            }
        }
        
        System.out.println(ans);
    }
}