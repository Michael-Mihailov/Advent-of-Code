
/**
 * Write a description of class Day22 here.
 *
 * @author (your name)
 * @version (a version number or a date)
 */

import java.io.*;
import java.util.*;

public class Day22
{
    public static void main (String[] args) throws IOException
    {
        File input = new File("input.txt");
        Scanner scn = new Scanner(input);
        
        int count = 0;
        
        
        HashMap<String, Boolean> virusMap = new HashMap();
        
        int len = 25; // length of the sides of the square (I think it's a square) FROM INPUT DATA !!!!!
        
        for (int y = len / 2; y >= len / -2; y--)
        {
            String data = scn.nextLine();
            for (int x = len / -2; x <= len / 2; x++)
            {
                if (data.charAt(x + (len / 2)) == '#')
                {
                    virusMap.put(x + " " + y, true);
                }   
            }
        }
        
        
        int xPos = 0;
        int yPos = 0;
        int dir = 0; // 0 == up, 1 == right, 2 == down, 3 == left
        
        
        for (int step = 0; step < 10000; step++)
        {
            if (virusMap.get(xPos + " " + yPos) == null)
            {
                count++;
                virusMap.put(xPos + " " + yPos, true);
                dir--;
            }
            else
            {
                virusMap.remove(xPos + " " + yPos);
                dir++;
            }
            
            if (dir == 4) dir = 0;
            else if (dir == -1) dir = 3;
            
            if (dir == 0) yPos++;
            else if (dir == 1) xPos++;
            else if (dir == 2) yPos--;
            else if (dir == 3) xPos--;
            else System.out.println("This should not print");
        }
        
        System.out.println("Part 1 answer: " + count);
        part2();
    }
    
    public static void part2() throws IOException
    {
        File input = new File("input.txt");
        Scanner scn = new Scanner(input);
        
        int count = 0;
        
        
        HashMap<String, Integer> virusMap = new HashMap();
        
        int len = 25; // length of the sides of the square (I think it's a square) FROM INPUT DATA !!!!!
        
        for (int y = len / 2; y >= len / -2; y--)
        {
            String data = scn.nextLine();
            for (int x = len / -2; x <= len / 2; x++)
            {
                if (data.charAt(x + (len / 2)) == '#')
                {
                    virusMap.put(x + " " + y, 2); // reads infected nodes from input
                }   
            }
        }
        
        
        int xPos = 0;
        int yPos = 0;
        int dir = 0; // 0 == up, 1 == right, 2 == down, 3 == left
        
        
        for (int step = 0; step < 10000000; step++)
        {
            if (virusMap.get(xPos + " " + yPos) == null) // weaken node
            {
                virusMap.put(xPos + " " + yPos, 3);
                dir--;
            }
            else
            {
                int num = virusMap.get(xPos + " " + yPos);
                
                virusMap.put(xPos + " " + yPos, num - 1);
                
                if (num == 1) // clean
                {
                    virusMap.remove(xPos + " " + yPos);
                    dir++;
                    dir++;
                }
                else if (num == 2) // flag
                {
                    dir++;
                }
                else if (num == 3) // infect
                {
                    count++;
                }
                else
                {
                    System.out.println("This should not print: " + num);
                }
            }
            
            if (dir >= 4) dir %= 4;
            else if (dir == -1) dir = 3;
            
            if (dir == 0) yPos++;
            else if (dir == 1) xPos++;
            else if (dir == 2) yPos--;
            else if (dir == 3) xPos--;
            else System.out.println("This should not print");
        }
        
        System.out.println("Part 2 answer: " + count);
    }
}
