import java.util.*;
import java.io.*;

public class Day11
{
    public static void main(String args[]) throws IOException
    {
        File file = new File("input.txt");
        Scanner scn = new Scanner(file);
        
        int ans = 0;
        
        boolean[][] seatMap = new boolean[99][98];
        boolean[][] peopleMap = new boolean[99][98];
        
        for (int i = 0; scn.hasNextLine(); i++)
        {
            String line = scn.nextLine();
            
            for (int j = 0; j < line.length(); j++)
            {
                seatMap[i][j] = line.charAt(j) == 'L';
            }
        }
        
        for (int step = 0; step < 1000; step++)
        {
            boolean[][] tempPeopleMap = new boolean[99][98];
            
            for (int i = 0; i < seatMap.length; i++)
            {
                for (int j = 0; j < seatMap[i].length; j++)
                {
                    if (seatMap[i][j] == false) continue;
                    
                    int adjacent = numAdjacent(i, j, peopleMap, seatMap);
                    
                    if (peopleMap[i][j] == false && adjacent == 0)
                    {
                        tempPeopleMap[i][j] = true;
                    }
                    else if (peopleMap[i][j] == true && adjacent >= 5)
                    {
                        tempPeopleMap[i][j] = false;
                    }
                    else
                    {
                        tempPeopleMap[i][j] = peopleMap[i][j];
                    }
                }
            }
            
            peopleMap = tempPeopleMap;
        }
        
        for (int i = 0; i < seatMap.length; i++)
        {
            for (int j = 0; j < seatMap[i].length; j++)
            {
                ans += peopleMap[i][j] ? 1 : 0;
            }
        }
        
        System.out.println(ans);
    }
    
    public static int numAdjacent(int row, int col, boolean[][] peopleMap, boolean[][] seatMap)
    {
        int total = 0;
        
        for (int y = row - 1; y >= 0; y--)
        {
            if (seatMap[y][col] == true)
            {
                if (peopleMap[y][col] == true) total++;
                break;
            }
        }
        for (int y = row + 1; y < seatMap.length; y++)
        {
            if (seatMap[y][col] == true)
            {
                if (peopleMap[y][col] == true) total++;
                break;
            }
        }
        for (int x = col - 1; x >= 0; x--)
        {
            if (seatMap[row][x] == true)
            {
                if (peopleMap[row][x] == true) total++;
                break;
            }
        }
        for (int x = col + 1; x < seatMap[row].length; x++)
        {
            if (seatMap[row][x] == true)
            {
                if (peopleMap[row][x] == true) total++;
                break;
            }
        }
        
        for (int y=row-1, x=col-1; y>=0 && x>=0; y--,x--)
        {
            if (seatMap[y][x] == true)
            {
                if (peopleMap[y][x] == true) total++;
                break;
            }
        }
        for (int y=row+1, x=col+1; y<seatMap.length && x<seatMap[y].length; y++,x++)
        {
            if (seatMap[y][x] == true)
            {
                if (peopleMap[y][x] == true) total++;
                break;
            }
        }
        for (int y=row+1, x=col-1; y<seatMap.length && x>=0; y++,x--)
        {
            if (seatMap[y][x] == true)
            {
                if (peopleMap[y][x] == true) total++;
                break;
            }
        }
        for (int y=row-1, x=col+1; y>=0 && x<seatMap[y].length; y--,x++)
        {
            if (seatMap[y][x] == true)
            {
                if (peopleMap[y][x] == true) total++;
                break;
            }
        }
        
        return total;
    }
}