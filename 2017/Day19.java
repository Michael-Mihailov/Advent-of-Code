
/**
 * Write a description of class Day19 here.
 *
 * @author (your name)
 * @version (a version number or a date)
 */

import java.io.*;
import java.util.*;

public class Day19
{
    public static void main (String[] args) throws IOException
    {
        File input = new File("input.txt");
        Scanner scn = new Scanner(input);
        
        char[][] map = new char[200][200];
        
        String answer = "";
        int count = 0;
        
        for (int y = 0; y < 200; y++)
        {
            String data = scn.nextLine();
            
            for (int x = 0; x < 200; x++)
            {
                map[x][y] = data.charAt(x);
            }
        }
        
        
        int xPos = 55; // given from the INPUT DATA!!!
        int yPos = 0;
        int dir = 0; // 0 == down, 1 == left, 2 == up, 3 == right (down is positive y and right is positive x)
        
        while (answer.length() < 10) // must count the number of letters from the INPUT DATA!!!
        {
            count++;
            
            if (dir == 0) yPos++;
            else if (dir == 1) xPos--;
            else if (dir == 2) yPos--;
            else if (dir == 3) xPos++;
            else System.out.println("problem factory");
            
            if (map[xPos][yPos] == '+')
            {
                if (dir % 2 == 0)
                {
                    if (xPos > 0 && (map[xPos - 1][yPos] == '-' || map[xPos - 1][yPos] == '+'))
                    {
                        dir = 1;
                    }
                    else
                    {
                        dir = 3;
                    }
                }
                else
                {
                    if (yPos > 0 && (map[xPos][yPos - 1] == '|' || map[xPos][yPos - 1] == '+'))
                    {
                        dir = 2;
                    }
                    else 
                    {
                        dir = 0;
                    }
                }
            }
            else if (map[xPos][yPos] != ' ' && map[xPos][yPos] != '|' && map[xPos][yPos] != '-')
            {
                answer = answer + map[xPos][yPos];
            }
        }
        count++; // for the last letter landed on that is not yet counted
        
        System.out.println("Part 1 answer: " + answer); // answer to part 1
        System.out.println("Part 2 answer: " + count); // answer to part 2
    }
}
