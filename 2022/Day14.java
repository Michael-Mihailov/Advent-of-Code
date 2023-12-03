
import java.io.*;
import java.util.*;

public class Day14
{
    public static void main (String[] args) throws Exception
    {
        File file = new File("input.txt");
        Scanner scn = new Scanner(file);
        
        boolean map[][] = new boolean[1000][1000];
        
        int xSpawn = 500;
        int ySpawn = 0;
        
        int yMax = 0;
        
        while (scn.hasNext()) // set up the map
        {
            String line = scn.nextLine();
            
            Scanner pointScn = new Scanner(line);
            pointScn.useDelimiter(" -> ");
            
            
            
            int x = -1;
            int y = -1;
            int xPrev;
            int yPrev;
            while (pointScn.hasNext())
            {
                String data = pointScn.next();
                
                Scanner splitScn = new Scanner(data);
                splitScn.useDelimiter(",");
                
                xPrev = x;
                yPrev = y;
                x = splitScn.nextInt();
                y = splitScn.nextInt();
                
                if (y > yMax)
                {
                    yMax = y;
                }
                
                if (xPrev != -1 || yPrev != -1)
                {
                    for (int vert = 0; vert < 1000; vert++)
                    {
                        for (int hori = 0; hori < 1000; hori++)
                        {
                            if (((vert <= y && vert >= yPrev) || (vert >= y && vert <= yPrev)) && ((hori <= x && hori >= xPrev) || (hori >= x && hori <= xPrev)))
                            {
                                map[hori][vert] = true;
                            }
                        }
                    }
                }
            }
        }
        
        
        boolean next = true;
        int count = 0;
        while (next == true) // sand simulation
        {
           int xPos = xSpawn;
           int yPos = ySpawn;
           
           boolean go = true;
           while (go == true)
           {
               if (yPos >= 999)
               {
                   go = false;
                   next = false;
                   
                   System.out.println("uh oh");
               }
               else if (map[xPos][yPos + 1] == false)
               {
                   yPos++;
               }
               else if (map[xPos - 1][yPos + 1] == false)
               {
                   xPos--;
                   yPos++;
               }
               else if (map[xPos + 1][yPos + 1] == false)
               {
                   xPos++;
                   yPos++;
               }
               else
               {
                   map[xPos][yPos] = true;
                   count++;
                   go = false;
                   
                   if (xPos == 500 && yPos == 0)
                   {
                       next = false;
                   }
               }
           }
           
        }
        
        
        for (int y = 0; y < 1000; y++)
        {
            for (int x = 0; x < 1000; x++)
            {
                if (map[x][y])
                {
                    System.out.print("#");
                }
                else
                {
                    System.out.print(".");
                }
            }
            System.out.println();
        }
        
        System.out.println(count); System.out.println(yMax);
    }
}

