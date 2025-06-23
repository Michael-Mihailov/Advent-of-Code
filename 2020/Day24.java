import java.util.*;
import java.io.*;

public class Day24
{
    public static void main(String args[]) throws IOException
    {
        File file = new File("input.txt");
        Scanner scn = new Scanner(file);
        
        int ans = 0;
        
        HashSet<String> tileSet = new HashSet();
        
        
        
        while (scn.hasNextLine())
        {
            String line = scn.nextLine();
            
            int d1 = 0; // East : West
            int d2 = 0; // SouthEast : NorthWest
            int d3 = 0; // NorthEast : SouthWest
            
            for (int i = 0; i < line.length(); i++)
            {
                if (line.charAt(i) == 'e')
                {
                    d1++;
                }
                else if (line.charAt(i) == 'w')
                {
                    d1--;
                }
                else if (line.charAt(i) == 'n')
                {
                    if (line.charAt(i+1) == 'e')
                    {
                        d3++;                        
                    }
                    else if (line.charAt(i+1) == 'w')
                    {
                        d2--;
                    }
                    i++;
                }
                else if (line.charAt(i) == 's')
                {
                    if (line.charAt(i+1) == 'e')
                    {
                        d2++;
                    }
                    else if (line.charAt(i+1) == 'w')
                    {
                        d3--;
                    }
                    i++;
                }
            }
            
            d2 += d1;
            d3 += d1;
            
            String tile = d2 + " " + d3;
            if (tileSet.contains(tile)) tileSet.remove(tile);
            else tileSet.add(tile);
        }
        
        for (int day = 0; day < 100; day++)
        {
            HashSet<String> newTileSet = new HashSet();
            HashSet<String> testSet = new HashSet();
            
            for (String tile : tileSet)
            {
                String[] tileData = tile.split(" ");
                int axis1 = Integer.parseInt(tileData[0]);
                int axis2 = Integer.parseInt(tileData[1]);
                
                int neighbors = 0;
                
                for (int d1 = -1; d1 <= 1; d1++)
                {
                    for (int d2 = -1; d2 <= 1; d2++)
                    {
                        if (Math.abs(d1 - d2) == 2) continue; // non-adjacent
                        
                        testSet.add((axis1 + d1) + " " + (axis2 + d2));
                    }
                }
            }
            
            for (String tile : testSet)
            {
                String[] tileData = tile.split(" ");
                int axis1 = Integer.parseInt(tileData[0]);
                int axis2 = Integer.parseInt(tileData[1]);
                
                int neighbors = 0;
                
                for (int d1 = -1; d1 <= 1; d1++)
                {
                    for (int d2 = -1; d2 <= 1; d2++)
                    {
                        if (Math.abs(d1 - d2) == 2 || (d1 == 0 && d2 == 0)) continue; // non-adjacent
                        
                        neighbors += tileSet.contains((axis1 + d1) + " " + (axis2 + d2)) ? 1 : 0;
                    }
                }
                
                if (neighbors == 2 || (tileSet.contains(tile) && neighbors == 1))
                {
                    newTileSet.add(tile);
                }
            }
            
            tileSet = newTileSet;
        }
        
        ans = tileSet.size();
        System.out.println(ans);
    }
}