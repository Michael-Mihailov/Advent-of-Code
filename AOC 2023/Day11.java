import java.util.*;
import java.io.*;

public class Day11
{
    public static void main(String[] args) throws IOException
    {
        File file = new File("input.txt");
        Scanner scn = new Scanner(file);
        
        long answer = 0;
        
        // input size 140 by 140
        final int boxSize = 140;
        boolean[] fullRows = new boolean[boxSize];
        boolean[] fullCols = new boolean[boxSize];
        
        ArrayList<int[]> stars = new ArrayList();
        
        int row = 0;
        while (scn.hasNextLine())
        {
            String data = scn.nextLine();
            
            if (data.contains("#"))
            {
                fullRows[row] = true;
            }
            
            for (int col = 0; col < boxSize; col++)
            {
                if (data.charAt(col) == '#')
                {
                    fullCols[col] = true;
                    
                    int[] temp = new int[2];
                    temp[0] = col;
                    temp[1] = row;
                    stars.add(temp);
                }
            }
            
            row++;
        }
        
        for (int s = 0; s < stars.size(); s++)
        {
            int[] pos1 = stars.get(s); 
                        
            for (int i = s + 1; i < stars.size(); i++)
            {                
                int[] pos2 = stars.get(i);
                
                long dist = 0;
                
                
                for (int x = pos1[0]; x != pos2[0]; x += (pos2[0] - pos1[0]) / Math.abs(pos1[0] - pos2[0]))
                {
                    dist++;
                    if (!fullCols[x])
                    {
                        dist += 1000000 - 1;
                    }
                }
                for (int y = pos1[1]; y != pos2[1]; y += (pos2[1] - pos1[1]) / Math.abs(pos1[1] - pos2[1]))
                {
                    dist++;
                    if (!fullRows[y])
                    {
                        dist += 1000000 - 1;
                    }
                }
                
                //System.out.println(dist); //DEBUG
                answer += dist;
            }
        }
        
        System.out.println(answer);
    }
}
