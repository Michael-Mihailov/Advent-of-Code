import java.util.*;
import java.io.*;

public class Day7
{
    public static void main(String[] args) throws IOException
    {
        File file = new File("input.txt");
        Scanner scn = new Scanner(file);
        
        int ans = 0;
        long ans2 = 0;
        
        long[][] beamGrid = new long[142][141];
        boolean[][] splitGrid = new boolean[142][141];
        
        for (int row = 0; row < beamGrid.length; row++)
        {
            String line = scn.nextLine();
            for (int col = 0; col < beamGrid[row].length; col++)
            {
                if (line.charAt(col) == 'S') beamGrid[row][col] = 1;
                if (line.charAt(col) == '^') splitGrid[row][col] = true;
            }
        }
        
        for (int row = 0; row < beamGrid.length - 1; row++)
        {
            for (int col = 0; col < beamGrid[row].length; col++)
            {
                long current = beamGrid[row][col];
                if (beamGrid[row][col] != 0) 
                {
                    if (splitGrid[row][col])
                    {
                        ans++;
                        beamGrid[row + 1][col - 1] += current;
                        beamGrid[row + 1][col + 1] += current;
                    }
                    else
                        beamGrid[row + 1][col] += current;
                }
            }
        }
        
        for (int col = 0; col < beamGrid[beamGrid.length - 1].length; col++)
        {
            ans2 += beamGrid[beamGrid.length - 1][col];
        }
        
        System.out.println("Part 1 Answer: " + ans);
        System.out.println("Part 2 Answer: " + ans2);
    }
}