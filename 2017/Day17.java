
/**
 * Write a description of class Day17 here.
 *
 * @author (your name)
 * @version (a version number or a date)
 */


import java.util.*;

public class Day17
{
    public static void main (String[] args)
    {
        ArrayList<Integer> numList = new ArrayList();
        numList.add(new Integer(0));
        
        int shift = 337; // given as input
        
        int index = 0;
        
        for (int i = 1; i <= 2017; i++)
        {
            index = ((index + shift) % i) + 1;
            numList.add(index, i);
        }
        
        boolean numFound = false;
        for (Integer num : numList)
        {
            if (numFound)
            {
                numFound = false;
                System.out.println("Part 1 answer: " + num);
            }
            if (num == 2017) numFound = true;
        }
        
        System.out.println();
        int p2Answer = 0;
        for (int i = 1; i <= 50000000; i++)
        {
            index = ((index + shift) % i) + 1;
            if (index == 1)
            {
                p2Answer = i;
            }
        }
        System.out.println("Part 2 answer: " + p2Answer);
    }
}
