
/**
 * Write a description of class Day1 here.
 *
 * @author (your name)
 * @version (a version number or a date)
 */

import java.io.*;
import java.util.*;

public class Day4
{
    public static void main (String[] args) throws Exception
    {
        Scanner scn = new Scanner(new File("input.txt"));
        
        int count = 0;
        
        while (scn.hasNext())
        {
            Scanner sectionScn = new Scanner(scn.nextLine());
            sectionScn.useDelimiter(",");
            String left = sectionScn.next();
            String right = sectionScn.next();
            
            Scanner pile1Scn = new Scanner(left);
            pile1Scn.useDelimiter("-");
            Scanner pile2Scn = new Scanner(right);
            pile2Scn.useDelimiter("-");
            
            int x1 = pile1Scn.nextInt();
            int x2 = pile1Scn.nextInt();
            int y1 = pile2Scn.nextInt();
            int y2 = pile2Scn.nextInt();
 
            
            if ((x1 <= y1 && x2 >= y2) || (y1 <= x1 && y2 >= x2) || (x1 <= y1 && x2 >= y1) || (x1 <= y2 && x2 >= y2))
            {
                count++;
            }
        }
        System.out.println(count);
    }
}
