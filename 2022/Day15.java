
import java.io.*;
import java.util.*;

public class Day15
{
    public static void main (String[] args) throws Exception
    {
        File file = new File("input.txt");
                
        
        //int count = 0;
        
        
        
        //int rowToScn = 0;
        
        for (int hello = 3214126; hello <= 3214126; hello++)
        {
            boolean[] map = new boolean[4000001]; // maybe should be 4000000 + 1
            int count = 0;
            
            Scanner scn = new Scanner(file);
            
            while (scn.hasNext())
            {
                String data = scn.nextLine();
                
                int xPos;
                int yPos;
                
                int xDis;
                int yDis;
                
                xPos = Integer.parseInt(data.substring(data.indexOf("Sensor at x=") + 12, data.indexOf(",. y=")));
                yPos = Integer.parseInt(data.substring(data.indexOf(",. y=") + 5, data.indexOf(": closest beacon is at x=")));
                
                xDis = Integer.parseInt(data.substring(data.indexOf(": closest beacon is at x=") + 25, data.indexOf(", y=")));
                yDis = Integer.parseInt(data.substring(data.indexOf(", y=") + 4));
                
                int distance = Math.abs(xPos - xDis) + Math.abs(yPos - yDis);
                
                int num = distance - Math.abs(yPos - hello);
                
                if (num >= 0)
                {
                    for (int i = -num; i <= num; i++)
                    {
                        if (xPos + i >= 0 && xPos + i <= 4000000)
                            map[xPos + i] = true;
                    }
                }
    
                
                /*
                for (int x = xPos - distance; x <= xPos + distance; x++)
                {
                    if (Math.abs(yPos - 10) + Math.abs(xPos - x) <= distance)
                    {
                        count++;
                    }
                }
                */
            }
            
            
            for (int i = 0; i <= 4000000; i++)
            {
                if (map[i] == true)
                {
                    //System.out.println("hole found");
                    count++;
                }
                else
                {
                    System.out.println(hello + " " + i);
                }
            }
            if (count != 4000001)
                System.out.println(count + " " + hello);
            
            if (hello % 1000 == 0)
                System.out.println("Number: " + hello);
        }
        
        
        //System.out.println();
        //System.out.println(count);

    }
}
