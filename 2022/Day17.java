
import java.io.*;
import java.util.*;

public class Day17
{
    public static void main (String[] args) throws Exception
    {
        Thread.sleep(3000);
        
        File file = new File("input.txt");
        Scanner scn = new Scanner(file);
        
        String data = scn.nextLine();
        int dataPos = 0;
        
        boolean map[][] = new boolean[9][10000]; // 9 wide, 10000 tall map
        
        int[][] p0 = {{0, 0, 0, 1, 0},{0, 0, 0, 4, 2},{0, 0, 0, 4, 2},{0, 0, 0, 4, 2},{0, 0, 0, 4, 2},{0, 0, 0, 3, 0}};
        int[][] p1 = {{0, 0, 1, 0, 0},{0, 0, 4, 5, 0},{0, 4, 4, 4, 2},{0, 0, 4, 6, 0},{0, 0, 3, 0, 0},{0, 0, 0, 0, 0}}; // possible error
        int[][] p2 = {{0, 0, 0, 1, 0},{0, 0, 0, 4, 2},{0, 1, 1, 4, 2},{0, 4, 4, 4, 2},{0, 3, 3, 3, 0},{0, 0, 0, 0, 0}}; // possible error
        int[][] p3 = {{1, 1, 1, 1, 0},{4, 4, 4, 4, 2},{3, 3, 3, 3, 0},{0, 0, 0, 0, 0},{0, 0, 0, 0, 0},{0, 0, 0, 0, 0}};
        int[][] p4 = {{0, 0, 1, 1, 0},{0, 0, 4, 4, 2},{0, 0, 4, 4, 2},{0, 0, 3, 3, 0},{0, 0, 0, 0, 0},{0, 0, 0, 0, 0}};
        // 0 is empty, 1 is left of block, 2 is below block, 3 is right of block, 4 is solid block
        int pCurrent = 0; // starting block is p0
        
        int height = 0; 
        
        int xPos = 0;
        int yPos = 0;
        
        for (int col = 0; col < 9; col++)
        {
            for (int row = 0; row < 10000; row++)
            {
                if (row == 0 || col == 0 || col == 8)
                {
                    map[col][row] = true; // fill in the map boarder
                }
            }
        }
        
        for (int iteration = 0; iteration < 805; iteration++)
        {
            pCurrent = iteration % 5;
            int[][] pNow;
            if (pCurrent == 0)
            {
                pNow = p0;     
            }
            else if (pCurrent == 1)
            {
                pNow = p1;
            }
            else if (pCurrent == 2)
            {
                pNow = p2;
            }
            else if (pCurrent == 3)
            {
                pNow = p3;
            }
            else if (pCurrent == 4)
            {
                pNow = p4;
            }
            else
            {
                pNow = new int[6][5];
                System.out.println("uh oh");
            }
            
            
            xPos = 2; // set spawn
            yPos = height + 7;
            
            boolean hit = false;
            
            while (hit == false)
            {
                char wind = data.charAt(dataPos % 10091); // 10091
                dataPos++; // iterate through the wind pattern
                
                if (dataPos / 10091 >= 1 && dataPos % 10091 == 0)
                {
                    System.out.println(height + " " + iteration);
                }
                
                boolean pass = true;
                
                if (wind == '<')
                {
                    for (int col = 0; col < 6; col++)
                    {
                        for (int row = 0; row < 5; row++)
                        {
                            if ((pNow[col][row] == 1 || pNow[col][row] == 5) && map[xPos + col][yPos - row] == true) // if not going to collide with the left side of the map
                            {
                                pass = false;
                            }
                        }
                    }
                    
                    if (pass == true)
                    {
                        xPos--;
                    }
                }
                else if (wind == '>')
                {
                    for (int col = 0; col < 6; col++)
                    {
                        for (int row = 0; row < 5; row++)
                        {
                            if ((pNow[col][row] == 3 || pNow[col][row] == 6) && map[xPos + col][yPos - row] == true) // if not going to collide with the right side of the map
                            {
                                pass = false;
                            }
                        }
                    }
                    
                    if (pass == true)
                    {
                        xPos++;
                    }
                }
                
                
                for (int col = 0; col < 6 && hit == false; col++)
                {
                    for (int row = 0; row < 5 && hit == false; row++)
                    {
                        if ((pNow[col][row] == 2 || pNow[col][row] == 5 || pNow[col][row] == 6) && map[xPos + col][yPos - row] == true) // if not going to collide with the bottom side of the map
                        {
                            hit = true; // turns block into part of map before spawning new block
                            
                            for (int col2 = 0; col2 < 6; col2++)
                            {
                                for (int row2 = 0; row2 < 5; row2++)
                                {
                                    if (pNow[col2][row2] == 4)
                                    {
                                        map[xPos + col2][yPos - row2] = true;
                                    }
                                }
                            }
                        }
                    }
                }
                
                if (hit == false) // move down if nothing is bellow
                {
                    yPos--;
                }
                
                for (int col = 1; col < 8; col++) // get the max height (exclude map walls)
                {
                    for (int row = 0; row < 10000; row++)
                    {
                        if (map[col][row] == true && row > height)
                        {
                            height = row;
                        }
                    }
                }
                
                
                
                // Tetris simulation
                
                /*
                boolean[][] mapTemp = new boolean[9][10000];
                
                for (int row = 9999; row >= 0; row--)
                {
                    for (int col = 0; col < 9; col++)
                    {
                        mapTemp[col][row] = map[col][row];
                    }
                }
                for (int col2 = 0; col2 < 6; col2++)
                {
                    for (int row2 = 0; row2 < 5; row2++)
                    {
                        if (pNow[col2][row2] == 4)
                        {
                            mapTemp[xPos + col2][yPos - row2] = true;
                        }
                    }
                }
                Thread.sleep(10000);
                System.out.println("down");
                
                
                
                for (int row = 9999; row >= 0; row--)
                {
                    for (int col = 0; col < 9; col++)
                    {
                        if (mapTemp[col][row] == true) // || (row == yPos && col == xPos))
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
                System.out.println(wind + " " + data.charAt(dataPos % 40) + " " + data.charAt((dataPos + 1) % 40));
                
                Thread.sleep(10000);
                */
                
            }
        }
                
        System.out.println(height);
    }
}

