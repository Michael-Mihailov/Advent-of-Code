import java.util.*;
import java.io.*;

public class Day10
{
    public static void main(String[] args) throws IOException
    {
        File file = new File("input.txt");
        Scanner scn = new Scanner(file);
        
        int answer = 0;
        
        
        int[] startPos = new int[2];
        
        
        int mapSize = 140;
        char[][] map = new char[mapSize][mapSize];
        
        
        for (int row = 0; row < map.length; row++)
        {
            String data = scn.nextLine();
            for (int col = 0; col < map[row].length; col++)
            {
                map[col][row] = data.charAt(col);
                if (data.charAt(col) == 'S')
                {
                    startPos[0] = col;
                    startPos[1] = row;
                }
            }
        }

        
        ArrayList<int[]> currentPositions = new ArrayList(); currentPositions.add(startPos);
        ArrayList<int[]> previousPositions = new ArrayList(); previousPositions.add(startPos);
        
        boolean go = true;
        while(go)
        {
            answer++;
            
            //System.out.println(previousPositions.size());
            
            for (int i = currentPositions.size() - 1; i >= 0; i--)
            {
                int[] pos = currentPositions.get(i);
                
                if (map[pos[0]][pos[1]] == 'S') // left and down (found manualy)
                {
                    // found manualy (THIS HERE CODE BLOCK WOULD NEED TO BE CHANGED FOR A DIFFERENT PUZZLE INPUT)
                    map[pos[0]][pos[1]] = '7'; 
                    
                    
                    int[] newPos = new int[2];
                    newPos[0] = pos[0]; 
                    newPos[1] = pos[1];
                    newPos[0] = newPos[0] - 1;
                    int[] newPos2 = new int[2];
                    newPos2[0] = pos[0];
                    newPos2[1] = pos[1];
                    newPos2[1] = newPos2[1] + 1;
                    
                    currentPositions.add(newPos);
                    currentPositions.add(newPos2);
                    previousPositions.add(pos);
                    currentPositions.remove(pos);
                }
                else
                {
                    ArrayList<Integer> moves = getConnections(map[pos[0]][pos[1]]);
                    for (Integer move : moves)
                    {
                        int[] newPos = new int[2];
                        newPos[0] = pos[0];
                        newPos[1] = pos[1];
                        
                        if (move == 1)
                        {
                            newPos[1] = newPos[1] - 1;
                        }
                        else if (move == 2)
                        {
                            newPos[0] = newPos[0] + 1;
                        }
                        else if (move == 3)
                        {
                            newPos[1] = newPos[1] + 1;
                        }
                        else if (move == 4)
                        {
                            newPos[0] = newPos[0] - 1;
                        }
                        
                        if (contained(newPos, previousPositions) == false)
                        {
                            currentPositions.add(newPos);
                            if (contained(pos, previousPositions) == false)
                            {
                                previousPositions.add(pos);
                                currentPositions.remove(pos);
                            }
                        }
                    }
                }
            }
            
            if (currentPositions.get(0)[0] == currentPositions.get(1)[0]
            && currentPositions.get(0)[1] == currentPositions.get(1)[1])
            {
                go = false;
                previousPositions.add(currentPositions.get(0));
            }
        }
        
        /* // DEBUG
        for (int row = 0; row < map.length; row++)
        {
            System.out.println();
            for (int col = 0; col < map[row].length; col++)
            {
                int[] temp = new int[2];
                temp[0] = col;
                temp[1] = row;
                
                if (contained(temp, previousPositions))
                {
                    System.out.print("x");
                }
                else
                {
                    System.out.print(".");
                }
            }
        }
        System.out.println();
        for (int row = 0; row < map.length; row++)
        {
            System.out.println();
            for (int col = 0; col < map[row].length; col++)
            {
                System.out.print(map[col][row]);
            }
        }
        */
        
        System.out.println("Part 1: " + answer);
        
        // ---------------------------------------------------------------------------------------------------------------------------------------------------
        // Part 2:
        answer = 0;
        
        
        /* Stuff utilized from part 1:
         * 
         * mapSize: the size of the grid
         * map: grid of all tiles
         * previousPositions: designate the loop
         */
        
        boolean[][] innerGrid = new boolean[mapSize - 1][mapSize - 1]; // marks all points between 4 tiles that are NOT inside the loop
        
        
        // README: This could be made more efficient by having a while loop that stops executing when innerGrid stops getting updated (i'm kinda lazy though)
        for (int rep = 0; rep < 10000; rep++) // fill out innerGrid (rep should be large enough to have innerGrid filled by the end)
        {            
            for (int y = 0; y < innerGrid.length; y++)
            {
                int height = innerGrid.length;
                for (int x = 0; x < innerGrid[y].length; x++)
                {
                    int width = innerGrid[y].length;
                    
                    if ((x == 0 && y == 0) || (x == width - 1 && y == 0) || (x == 0 && y == height - 1) || (x == width - 1 && y == height - 1)) // corners are seeds
                    {
                        innerGrid[x][y] = true;
                    }
                    
                    if (innerGrid[x][y] == true)
                    {
                        char topRight = map[x + 1][y]; if (topRight == '.') topRight = 'x';
                        char topLeft = map[x][y]; if (topLeft == '.') topLeft = 'x';
                        char bottomLeft = map[x][y + 1]; if (bottomLeft == '.') bottomLeft = 'x';
                        char bottomRight = map[x + 1][y + 1]; if (bottomRight == '.') bottomRight = 'x';
                        
                        
                        if (x < width - 1) // try right
                        {
                            if ((getConnections(topRight).contains(3) && getConnections(bottomRight).contains(1)) == false)
                            {
                                innerGrid[x + 1][y] = true;
                            }
                        }
                        
                        if (y < height - 1) // try down
                        {
                            if ((getConnections(bottomLeft).contains(2) && getConnections(bottomRight).contains(4)) == false)
                            {
                                innerGrid[x][y + 1] = true;
                            }
                        }
                        
                        if (x > 0) // try left
                        {
                            if ((getConnections(topLeft).contains(3) && getConnections(bottomLeft).contains(1)) == false)
                            {
                                innerGrid[x - 1][y] = true;
                            }
                        }
                        
                        if (y > 0) // try up
                        {
                            if ((getConnections(topLeft).contains(2) && getConnections(topRight).contains(4)) == false)
                            {
                                innerGrid[x][y - 1] = true;
                            }
                        }
                    }
                }
            }
        }
        /* //Debug
        for (int y = 0; y < innerGrid.length; y++)
        {
            System.out.println();
            for (int x = 0; x < innerGrid[y].length; x++)
            {
                if (innerGrid[x][y] == true)
                {
                    System.out.print("x");
                }
                else
                {
                    System.out.print(".");
                }
            }
        }
        */
        
        for (int y = 1; y < map.length - 1; y++)
        {
            for (int x = 1; x < map[y].length - 1; x++)
            {
                if (!innerGrid[x - 1][y - 1] && !innerGrid[x - 1][y] && !innerGrid[x][y - 1] && !innerGrid[x][y])
                {
                    answer++;
                }
            }
        } 
        
        System.out.println("Part 2: " + answer);
    }
    
    public static boolean contained(int[] pos, ArrayList<int[]> previousPositions)
    {
        for (int[] p : previousPositions)
        {
            if (pos[0] == p[0] && pos[1] == p[1])
            {
                return true;
            }
        }
        return false;
    }
    
    public static ArrayList<Integer> getConnections(char c) // 1 == up, 2 == right, 3 == down, 4 == left
    {
        ArrayList<Integer> con = new ArrayList();
        if (c == '|')
        {
            con.add(1);
            con.add(3);
        }
        else if (c == '-')
        {
            con.add(2);
            con.add(4);
        }
        else if (c == 'L')
        {
            con.add(1);
            con.add(2);
        }
        else if (c == 'J')
        {
            con.add(1);
            con.add(4);
        }
        else if (c == '7')
        {
            con.add(4);
            con.add(3);
        }
        else if (c == 'F')
        {
            con.add(2);
            con.add(3);
        }
        else if (c == 'x')
        {
            
        }
        else
        {
            con.add(1);
            con.add(2);
            con.add(3);
            con.add(4);
        }
        
        return con;
    }
}
