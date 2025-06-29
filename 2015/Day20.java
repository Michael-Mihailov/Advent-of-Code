import java.util.*;
import java.io.*;

public class Day20
{
    public static void main(String args[]) throws IOException
    {
        File file = new File("input.txt");
        Scanner scn = new Scanner(file);
        
        int ans = 0;
        
        int goal = scn.nextInt();
        int[] houses = new int[goal]; // ans must be inside this array
        
        for (int i = 1; i <= houses.length; i++)
        {
            for (int j = i - 1, deliveries = 0; j < houses.length && deliveries < 50; j += i, deliveries++)
            {
                houses[j] += 11 * i;
            }
        }
        
        for (int i = 0; i < houses.length; i++)
        {
            if (houses[i] >= goal)
            {
                ans = i+1;
                break;
            }
        }
        
        System.out.println("Answer: " + ans);
    }
}