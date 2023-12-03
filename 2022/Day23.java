
import java.io.*;
import java.util.*;

public class Day23
{
    static boolean map[][] = new boolean[200][200];
    static int dibsMap[][] = new int [200][200];
    
    static int round = 0;
    static boolean go = true;
    
    public static void main (String[] args) throws Exception
    {
        File file = new File("input.txt");
        Scanner scn = new Scanner(file);
        
        int elfs = 2648;
        int count = 0;
        
        
        int firstCheck = 0; // 0 = N, 1 = S, 2 = W, 3 = E
        
        for (int y = 0; y < 73; y++)
        {
            String data = scn.nextLine();
            
            for (int x = 0; x < 73; x++)
            { 
                if (data.charAt(x) == '#')
                {
                    map[x + 50][y + 50] = true;
                }
            }
        }
        
        while (go == true)
        {
            /*
            for (int y = 50; y < 62; y++)
            {
                for (int x = 50; x < 64; x++)
                {
                    if (map[x][y] == true)
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
            System.out.println();
            */
            dibs(firstCheck);
            move(firstCheck);
            /*
            for (int y = 50; y < 62; y++)
            {
                for (int x = 50; x < 64; x++)
                {
                    System.out.print(dibsMap[x][y]);
                }
                System.out.println();
            }
            System.out.println();
            */
            
            go = false;
            for (int y = 0; y < 200; y++)
            {
                for (int x = 0; x < 200; x++)
                {
                    if (dibsMap[x][y] == 1)
                    {
                        go = true;
                    }
                }
            }
            
            dibsMap = new int [200][200];
            firstCheck++;
            
            round++;
            System.out.println(round);
        }
        /*
        int northMost = 200;
        int southMost = 0;
        int eastMost = 200;
        int westMost = 0;
        
        for (int y = 0; y < 200; y++)
        {            
            for (int x = 0; x < 200; x++)
            { 
                if (map[x][y] == true)
                {
                    if (x < eastMost)
                    {
                        eastMost = x;
                    }
                    if (x > westMost)
                    {
                        westMost = x;
                    }
                    if (y < northMost)
                    {
                        northMost = y;
                    }
                    if (y > southMost)
                    {
                        southMost = y;
                    }
                    count++;
                }
            }
        }
        
        for (int y = 50; y < 62; y++)
            {
                for (int x = 50; x < 64; x++)
                {
                    if (map[x][y] == true)
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
        
        System.out.println(count + " , " + elfs);
        System.out.println((northMost - southMost) +" , "+ (eastMost - westMost)); */
    }
    
    public static void dibs(int order)
    {
        for (int y = 0; y < 200; y++)
        {            
            for (int x = 0; x < 200; x++)
            { 
                if (map[x][y] == true && (map[x-1][y+1] || map[x][y+1] || map[x+1][y+1] || map[x-1][y] || map[x+1][y] || map[x-1][y-1] || map[x][y-1] || map[x+1][y-1]))
                {
                    //System.out.println();
                    dibsMap[x][y] += 7;
                    
                    boolean done = false;
                    for (int dir = 0; dir < 4 && done == false; dir++)
                    {
                        int dirCheck = order + dir;
                        //System.out.println(dirCheck);
                        if (dirCheck % 4 == 0) // North
                        {
                            if (!map[x-1][y-1] && !map[x][y-1] && !map[x+1][y-1])
                            {
                                dibsMap[x][y-1] += 1;
                                done = true;
                            }
                        }
                        else if (dirCheck % 4 == 1) // South
                        {
                            if (!map[x-1][y+1] && !map[x][y+1] && !map[x+1][y+1])
                            {
                                dibsMap[x][y+1] += 1;
                                done = true;
                            }
                        }
                        else if (dirCheck % 4 == 2) // West
                        {
                            if (!map[x-1][y-1] && !map[x-1][y] && !map[x-1][y+1])
                            {
                                dibsMap[x-1][y] += 1;
                                done = true;
                            }
                        }
                        else if (dirCheck % 4 == 3) // East
                        {
                            if (!map[x+1][y-1] && !map[x+1][y] && !map[x+1][y+1])
                            {
                                dibsMap[x+1][y] += 1;
                                done = true;
                            }
                        }
                        else
                        {
                            //System.out.println("ERROR1");
                        }
                    }
                }
            }
        }
    }
    
    public static void move(int order)
    {
        boolean mapTemp[][] = new boolean[200][200];
        for (int y = 0; y < 200; y++)
        {
            for (int x = 0; x < 200; x++)
            {
                mapTemp[x][y] = map[x][y];
            }
        }
        
        for (int y = 0; y < 200; y++)
        {            
            for (int x = 0; x < 200; x++)
            { 
                if (mapTemp[x][y] == true && (mapTemp[x-1][y+1] || mapTemp[x][y+1] || mapTemp[x+1][y+1] || mapTemp[x-1][y] || mapTemp[x+1][y] || mapTemp[x-1][y-1] || mapTemp[x][y-1] || mapTemp[x+1][y-1]))
                {
                    boolean done = false;
                    for (int dir = 0; dir < 4 && done == false; dir++)
                    {
                        int dirCheck = order + dir;
                        
                        if (dirCheck % 4 == 0) // North
                        {
                            if (!mapTemp[x-1][y-1] && !mapTemp[x][y-1] && !mapTemp[x+1][y-1])
                            {
                                if (dibsMap[x][y-1] == 1)
                                {
                                    map[x][y] = false;
                                    map[x][y-1] = true;
                                }
                                
                                done = true;
                            }
                        }
                        else if (dirCheck % 4 == 1) // South
                        {
                            if (!mapTemp[x-1][y+1] && !mapTemp[x][y+1] && !mapTemp[x+1][y+1])
                            {
                                if (dibsMap[x][y+1] == 1)
                                {
                                    map[x][y] = false;
                                    map[x][y+1] = true;
                                }
                                
                                done = true;
                            }
                        }
                        else if (dirCheck % 4 == 2) // West
                        {
                            if (!mapTemp[x-1][y-1] && !mapTemp[x-1][y] && !mapTemp[x-1][y+1])
                            {
                                if (dibsMap[x-1][y] == 1)
                                {
                                    map[x][y] = false;
                                    map[x-1][y] = true;
                                }
                                
                                done = true;
                            }
                        }
                        else if (dirCheck % 4 == 3) // East
                        {
                            if (!mapTemp[x+1][y-1] && !mapTemp[x+1][y] && !mapTemp[x+1][y+1])
                            {
                                if (dibsMap[x+1][y] == 1)
                                {
                                    map[x][y] = false;
                                    map[x+1][y] = true;
                                }
                                
                                done = true;
                            }
                        }
                        else
                        {
                            //System.out.println("ERROR2");
                        }
                    }
                }
            }
        }
    }
}
