import java.util.*;
import java.io.*;

public class Day3
{
    public static void main(String[] args) throws IOException
    {
        File file = new File("input.txt");
        Scanner scn = new Scanner(file);
        
        int answer = 0;
        int[][] fabric = new int[1000][1000];
        
        while (scn.hasNextLine())
        {
            String data = scn.nextLine();
            
            data = data.replace("#"," ");
            data = data.replace("@"," ");
            data = data.replace(","," ");
            data = data.replace(":"," ");
            data = data.replace("x"," ");
            
            Scanner dataScn = new Scanner(data);
            
            int id = dataScn.nextInt();
            int xpos = dataScn.nextInt();
            int ypos = dataScn.nextInt();
            int xsize = dataScn.nextInt();
            int ysize = dataScn.nextInt();
            
            for (int x = xpos; x < xpos + xsize; x++)
            {
                for (int y = ypos; y < ypos + ysize; y++)
                {
                    fabric[x][y] += 1;
                }
            }
        }
        
        for (int[] row : fabric)
        {
            for (int col : row)
            {
                if (col > 1)
                {
                    answer++;
                }
            }
        }
        
        System.out.println("Part 1 answer: " + answer);
        
        // Part 2
        
        scn = new Scanner(file);
        answer = 0;
        
        while (scn.hasNextLine())
        {
            String data = scn.nextLine();
            
            data = data.replace("#"," ");
            data = data.replace("@"," ");
            data = data.replace(","," ");
            data = data.replace(":"," ");
            data = data.replace("x"," ");
            
            Scanner dataScn = new Scanner(data);
            
            int id = dataScn.nextInt();
            int xpos = dataScn.nextInt();
            int ypos = dataScn.nextInt();
            int xsize = dataScn.nextInt();
            int ysize = dataScn.nextInt();
            
            boolean daOne = true;
            
            for (int x = xpos; x < xpos + xsize; x++)
            {
                for (int y = ypos; y < ypos + ysize; y++)
                {
                    if (fabric[x][y] != 1)
                    {
                        daOne = false;
                    }
                }
            }
            
            if (daOne)
            {
                answer = id;
                System.out.println("Part 2 answer found!");
            }
        }
        
        System.out.println("Part 2 answer: " + answer);
    }
}
