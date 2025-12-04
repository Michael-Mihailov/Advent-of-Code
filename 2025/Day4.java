import java.util.*;
import java.io.*;

public class Day4
{
    public static void main(String[] args) throws IOException
    {
        File file = new File("input.txt");
        Scanner scn = new Scanner(file);
        
        int ans = 0;
        
        boolean[][] grid = new boolean[137][137];
        
        for (int y = 0; y < grid.length; y++)
        {
            String line = scn.nextLine();
            for (int x = 0; x < grid.length; x++)
            {
                if (line.charAt(x) == '@') grid[y][x] = true;
            }
        }
        
        int oldAns = -1;
        while (oldAns < ans)
        {
            oldAns = ans;
            for (int y = 0; y < grid.length; y++)
            {
                for (int x = 0; x < grid.length; x++)
                {
                    if (!grid[y][x]) continue;
                    int count = -1;
                    for (int i = -1; i <= 1; i++)
                    {
                        for (int j = -1; j <= 1; j++)
                        {
                            try
                            {
                                count += (grid[y+i][x+j]) ? 1 : 0;
                            }
                            catch (Exception e){}
                        }
                    }
                    if (count < 4) 
                    {
                        grid[y][x] = false;
                        ans++;
                    }
                }
            }
        }
        
        System.out.println(ans);
    }
}
