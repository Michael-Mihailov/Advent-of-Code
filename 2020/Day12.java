import java.util.*;
import java.io.*;

public class Day12
{
    public static void main(String args[]) throws IOException
    {
        File file = new File("input.txt");
        Scanner scn = new Scanner(file);
        
        int xPos = 0;
        int yPos = 0;
        
        int waypointX = 10;
        int waypointY = 1;
        
        while (scn.hasNextLine())
        {
            String line = scn.nextLine();
            
            char inst = line.charAt(0);
            int value = Integer.parseInt( line.substring(1) );
            
            if (inst == 'N') waypointY += value;
            else if (inst == 'S') waypointY -= value;
            else if (inst == 'E') waypointX += value;
            else if (inst == 'W') waypointX -= value;
            else if (inst == 'R')
            {
                for (int i = 0; i < value / 90; i++)
                {
                    int temp = waypointX;
                    waypointX = waypointY;
                    waypointY = -1 * temp;
                }
            }
            else if (inst == 'L')
            {
                for (int i = 0; i < value / 90; i++)
                {
                    int temp = waypointX;
                    waypointX = -1 * waypointY;
                    waypointY = temp;
                }
            }
            else if (inst == 'F')
            {
                xPos += waypointX * value;
                yPos += waypointY * value;
            }
            else System.out.println("ERROR: " + inst);
        }
        
        System.out.println(Math.abs(xPos) + Math.abs(yPos));
    }
}