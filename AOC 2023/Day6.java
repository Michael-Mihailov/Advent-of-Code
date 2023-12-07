import java.util.*;
import java.io.*;

public class Day6
{
    public static void main(String[] args) throws IOException
    {
        File file = new File("input.txt");
        Scanner scn = new Scanner(file);
        
        long answer = 0;
        
        Scanner timeScn = new Scanner(scn.nextLine()); timeScn.next();
        Scanner distScn = new Scanner(scn.nextLine()); distScn.next();
        
        Long time = new Long(timeScn.nextLine().replace(" ", ""));
        Long distance = new Long(distScn.nextLine().replace(" ", ""));
        
        //System.out.println(time + " " + distance);

        for (long s = 0; s <= time; s++)
        {            
            //System.out.println(s * (time - s));
            if (distance < s * (time - s))
            {
                answer++;
            }
        }
        
        System.out.println(answer);
    }
        
        
}

