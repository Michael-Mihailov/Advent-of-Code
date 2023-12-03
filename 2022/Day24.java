
/**
 * Write a description of class Day1 here.
 *
 * @author (your name)
 * @version (a version number or a date)
 */

import java.io.*;
import java.util.*;

public class Day24
{
    public static void main (String[] args) throws Exception
    {
        File file = new File("input.txt");
        Scanner scn = new Scanner(file);
        
        boolean[][] elfMap = new boolean[122][27]; // 122 wide, 27 tall
        
        boolean[][] wallMap = new boolean[122][27];
        
        boolean[][] leftMap = new boolean[122][27];
        boolean[][] rightMap = new boolean[122][27];
        boolean[][] upMap = new boolean[122][27];
        boolean[][] downMap = new boolean[122][27];
        
        for (int vert = 0; vert < 27; vert++)
        {
            String data = scn.nextLine();
            
            for (int i = 0; i < 122; i++)
            {
                char obj = data.charAt(i);
                
                if (obj == '#')
                {
                    wallMap[i][vert] = true;
                }
                else if (obj == '<')
                {
                    leftMap[i][vert] = true;
                }
                else if (obj == '>')
                {
                    rightMap[i][vert] = true;
                }
                else if (obj == '^')
                {
                    upMap[i][vert] = true;
                }
                else if (obj == 'v')
                {
                    downMap[i][vert] = true;
                }
            }
        }
        
        
        int count = 0;
        boolean goal = false;
        while (goal == false) // first wind, then elves, then spawn elf in top left, then kill
        {
            for (int col = 0; col <= 121; col++) // left
            {
                for (int row = 0; row < 27; row++)
                {
                    if(leftMap[col][row] == true)
                    {
                        if(col == 1)
                        {
                            leftMap[col][row] = false;
                            leftMap[121][row] = true;
                        }
                        else
                        {
                            leftMap[col][row] = false;
                            leftMap[col - 1][row] = true;
                        }
                    }
                }
            }
            for (int col = 121; col >= 0; col--) // right
            {
                for (int row = 0; row < 27; row++)
                {
                    if(rightMap[col][row] == true)
                    {
                        if(col == 120)
                        {
                            rightMap[col][row] = false;
                            rightMap[0][row] = true;
                        }
                        else
                        {
                            rightMap[col][row] = false;
                            rightMap[col + 1][row] = true;
                        }
                    }
                }
            }
            for (int col = 0; col < 122; col++) // up
            {
                for (int row = 0; row <= 26; row++)
                {
                    if(upMap[col][row] == true)
                    {
                        if(row == 1)
                        {
                            upMap[col][row] = false;
                            upMap[col][26] = true;
                        }
                        else
                        {
                            upMap[col][row] = false;
                            upMap[col][row - 1] = true;
                        }
                    }
                }
            }
            for (int col = 0; col < 122; col++) // down
            {
                for (int row = 26; row >= 0; row--)
                {
                    if(downMap[col][row] == true)
                    {
                        if(row == 25)
                        {
                            downMap[col][row] = false;
                            downMap[col][0] = true;
                        }
                        else
                        {
                            downMap[col][row] = false;
                            downMap[col][row + 1] = true;
                        }
                    }
                }
            }
            
            boolean[][] elfCopy = new boolean[122][27];
            for (int col = 0; col < 122; col++) // make copy of previous elf position
            {
                for (int row = 0; row < 27; row++)
                {
                    elfCopy[col][row] = elfMap[col][row];
                }
            }
            for (int col = 0; col < 122; col++) // elves 'move'
            {
                for (int row = 0; row < 27; row++)
                {
                    if(elfCopy[col][row] == true)
                    {
                        elfMap[col + 1][row] = true;
                        elfMap[col - 1][row] = true;
                        elfMap[col][row + 1] = true;
                        elfMap[col][row - 1] = true;
                    }
                }
            }
            
            count++; // increment counter
            if (elfMap[120][26]) // check if goal is complete
            {
                goal = true;
            }
            
            elfMap[1][1] = true; // spawn elf in top left
            
            for (int col = 0; col < 122; col++) // kill elves
            {
                for (int row = 0; row < 27; row++)
                {
                    if(wallMap[col][row] || leftMap[col][row] || rightMap[col][row] || upMap[col][row] || downMap[col][row])
                    {
                        elfMap[col][row] = false;
                    }
                }
            }
            
            
            
            
            for (int row = 0; row < 27; row++) // update screen frame
            {
                for (int col = 0; col < 122; col++)
                {
                    char x = '.';
                    
                    if(wallMap[col][row])
                    {
                        x = '#';
                    }
                    else if(leftMap[col][row])
                    {
                        x = '<';
                    }
                    else if(rightMap[col][row])
                    {
                        x = '>';
                    }
                    else if(upMap[col][row])
                    {
                        x = '^';
                    }
                    else if(downMap[col][row])
                    {
                        x = 'v';                    
                    }
                    else if(elfMap[col][row])
                    {
                        x = 'O';
                    }
                    
                    System.out.print(x);
                }
                System.out.println();
            }
            
            Thread.sleep(1000);
            
        }
        
        elfMap = new boolean[122][27];
        goal = false;
        while (goal == false) // first wind, then elves, then spawn elf in top left, then kill
        {
            for (int col = 0; col <= 121; col++) // left
            {
                for (int row = 0; row < 27; row++)
                {
                    if(leftMap[col][row] == true)
                    {
                        if(col == 1)
                        {
                            leftMap[col][row] = false;
                            leftMap[121][row] = true;
                        }
                        else
                        {
                            leftMap[col][row] = false;
                            leftMap[col - 1][row] = true;
                        }
                    }
                }
            }
            for (int col = 121; col >= 0; col--) // right
            {
                for (int row = 0; row < 27; row++)
                {
                    if(rightMap[col][row] == true)
                    {
                        if(col == 120)
                        {
                            rightMap[col][row] = false;
                            rightMap[0][row] = true;
                        }
                        else
                        {
                            rightMap[col][row] = false;
                            rightMap[col + 1][row] = true;
                        }
                    }
                }
            }
            for (int col = 0; col < 122; col++) // up
            {
                for (int row = 0; row <= 26; row++)
                {
                    if(upMap[col][row] == true)
                    {
                        if(row == 1)
                        {
                            upMap[col][row] = false;
                            upMap[col][26] = true;
                        }
                        else
                        {
                            upMap[col][row] = false;
                            upMap[col][row - 1] = true;
                        }
                    }
                }
            }
            for (int col = 0; col < 122; col++) // down
            {
                for (int row = 26; row >= 0; row--)
                {
                    if(downMap[col][row] == true)
                    {
                        if(row == 25)
                        {
                            downMap[col][row] = false;
                            downMap[col][0] = true;
                        }
                        else
                        {
                            downMap[col][row] = false;
                            downMap[col][row + 1] = true;
                        }
                    }
                }
            }
            
            boolean[][] elfCopy = new boolean[122][27];
            for (int col = 0; col < 122; col++) // make copy of previous elf position
            {
                for (int row = 0; row < 27; row++)
                {
                    elfCopy[col][row] = elfMap[col][row];
                }
            }
            for (int col = 0; col < 122; col++) // elves 'move'
            {
                for (int row = 0; row < 27; row++)
                {
                    if(elfCopy[col][row] == true)
                    {
                        elfMap[col + 1][row] = true;
                        elfMap[col - 1][row] = true;
                        elfMap[col][row + 1] = true;
                        elfMap[col][row - 1] = true;
                    }
                }
            }
            
            elfMap[120][25] = true; // spawn elf in top right
            
            count++; // increment counter
            if (elfMap[1][0]) // check if goal is complete (before killing)
            {
                goal = true;
            }
            
            for (int col = 0; col < 122; col++) // kill elves
            {
                for (int row = 0; row < 27; row++)
                {
                    if(wallMap[col][row] || leftMap[col][row] || rightMap[col][row] || upMap[col][row] || downMap[col][row])
                    {
                        elfMap[col][row] = false;
                    }
                }
            }
            
            
            
            
            /*
            for (int row = 0; row < 27; row++) // update screen frame
            {
                for (int col = 0; col < 122; col++)
                {
                    char x = '.';
                    
                    if(wallMap[col][row])
                    {
                        x = '#';
                    }
                    else if(leftMap[col][row])
                    {
                        x = '<';
                    }
                    else if(rightMap[col][row])
                    {
                        x = '>';
                    }
                    else if(upMap[col][row])
                    {
                        x = '^';
                    }
                    else if(downMap[col][row])
                    {
                        x = 'v';                    
                    }
                    else if(elfMap[col][row])
                    {
                        x = 'O';
                    }
                    
                    System.out.print(x);
                }
                System.out.println();
            }
            
            Thread.sleep(1000);
            */
        }
        
        elfMap = new boolean[122][27];
        goal = false;
        while (goal == false) // first wind, then elves, then spawn elf in top left, then kill
        {
            for (int col = 0; col <= 121; col++) // left
            {
                for (int row = 0; row < 27; row++)
                {
                    if(leftMap[col][row] == true)
                    {
                        if(col == 1)
                        {
                            leftMap[col][row] = false;
                            leftMap[121][row] = true;
                        }
                        else
                        {
                            leftMap[col][row] = false;
                            leftMap[col - 1][row] = true;
                        }
                    }
                }
            }
            for (int col = 121; col >= 0; col--) // right
            {
                for (int row = 0; row < 27; row++)
                {
                    if(rightMap[col][row] == true)
                    {
                        if(col == 120)
                        {
                            rightMap[col][row] = false;
                            rightMap[0][row] = true;
                        }
                        else
                        {
                            rightMap[col][row] = false;
                            rightMap[col + 1][row] = true;
                        }
                    }
                }
            }
            for (int col = 0; col < 122; col++) // up
            {
                for (int row = 0; row <= 26; row++)
                {
                    if(upMap[col][row] == true)
                    {
                        if(row == 1)
                        {
                            upMap[col][row] = false;
                            upMap[col][26] = true;
                        }
                        else
                        {
                            upMap[col][row] = false;
                            upMap[col][row - 1] = true;
                        }
                    }
                }
            }
            for (int col = 0; col < 122; col++) // down
            {
                for (int row = 26; row >= 0; row--)
                {
                    if(downMap[col][row] == true)
                    {
                        if(row == 25)
                        {
                            downMap[col][row] = false;
                            downMap[col][0] = true;
                        }
                        else
                        {
                            downMap[col][row] = false;
                            downMap[col][row + 1] = true;
                        }
                    }
                }
            }
            
            boolean[][] elfCopy = new boolean[122][27];
            for (int col = 0; col < 122; col++) // make copy of previous elf position
            {
                for (int row = 0; row < 27; row++)
                {
                    elfCopy[col][row] = elfMap[col][row];
                }
            }
            for (int col = 0; col < 122; col++) // elves 'move'
            {
                for (int row = 0; row < 27; row++)
                {
                    if(elfCopy[col][row] == true)
                    {
                        elfMap[col + 1][row] = true;
                        elfMap[col - 1][row] = true;
                        elfMap[col][row + 1] = true;
                        elfMap[col][row - 1] = true;
                    }
                }
            }
            
            elfMap[1][1] = true; // spawn elf in top left
            
            count++; // increment counter
            if (elfMap[120][26]) // check if goal is complete
            {
                goal = true;
            }
            
            for (int col = 0; col < 122; col++) // kill elves
            {
                for (int row = 0; row < 27; row++)
                {
                    if(wallMap[col][row] || leftMap[col][row] || rightMap[col][row] || upMap[col][row] || downMap[col][row])
                    {
                        elfMap[col][row] = false;
                    }
                }
            }
            
            
            
            /*
            for (int row = 0; row < 27; row++) // update screen frame
            {
                for (int col = 0; col < 122; col++)
                {
                    char x = '.';
                    
                    if(wallMap[col][row])
                    {
                        x = '#';
                    }
                    else if(leftMap[col][row])
                    {
                        x = '<';
                    }
                    else if(rightMap[col][row])
                    {
                        x = '>';
                    }
                    else if(upMap[col][row])
                    {
                        x = '^';
                    }
                    else if(downMap[col][row])
                    {
                        x = 'v';                    
                    }
                    else if(elfMap[col][row])
                    {
                        x = 'O';
                    }
                    
                    System.out.print(x);
                }
                System.out.println();
            }
            
            Thread.sleep(1000);
            */
        }
        System.out.println(count);
    }
}
