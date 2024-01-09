import java.util.*;
import java.io.*;

public class Day23
{
    public static final int SIZE = 141;
    
    public static HashMap<String, ArrayList<String>> connections; // the graphical representation of the hiking map
    public static HashMap<String, ArrayList<Integer>> distances; // stores the corresponding distances between connections
    
    public static String startPos;
    public static String endPos;
    
    
    public static void main(String[] args) throws IOException
    {
        File file = new File("input.txt");
        Scanner scn = new Scanner(file);
        
        int answer = 0;
        
        char[][] map = new char[SIZE][SIZE];
        connections = new HashMap();
        distances = new HashMap();
        
        for (int row = 0; row < SIZE; row++) // fill out the hiking map
        {
            String data = scn.nextLine();
            for (int col = 0; col < SIZE; col++)
            {
                map[col][row] = data.charAt(col);
                
                /*
                if (map[col][row] != '#') // remove directional tiles for PART 2 (COMMENT OUT FOR PART 1 !!!!!)
                {
                    map[col][row] = '.';
                } */ // literaly no idea why part 2 doesn't work with my input data
            }
        }
        
        
        ArrayList<String> keyPositions = new ArrayList();
        for (int row = 0; row < SIZE; row++) // find key positions
        {
            for (int col = 0; col < SIZE; col++)
            {
                if (isKeyPosition(map, col, row))
                {
                    String pos = col + " " + row;
                    
                    keyPositions.add(pos);
                }
            }
        }
        
        startPos = keyPositions.get(0); // start of graph
        endPos = keyPositions.get(keyPositions.size() - 1); // end of graph
        
        for (String pos : keyPositions)
        {
            ArrayList[] res = findConnections(map, pos);
            
            connections.put(pos, res[0]);
            distances.put(pos, res[1]);
        }
        
        answer = traverse(startPos, new HashSet<String>());
        
        System.out.println("answer: " + answer);
    }
    
    
    public static int traverse(String pos, HashSet<String> visited)
    {
        int res = 0;
        
        visited = new HashSet<String>(visited);
        visited.add(pos);
        
        boolean deadEnd = true;
        for (int i = 0; i < connections.get(pos).size(); i++)
        {
            if (visited.contains(connections.get(pos).get(i)) == false)
            {
                deadEnd = false;
                
                int temp = distances.get(pos).get(i) + traverse(connections.get(pos).get(i), visited);
                
                if (temp > res)
                {
                    res = temp;
                }
            }
        }
        
        if (deadEnd && endPos.equals(pos) == false)
            return Integer.MIN_VALUE;
        
        return res;
    }
    
    
    public static ArrayList[] findConnections(char[][] map, String pos)
    {
        ArrayList<String> connection = new ArrayList();
        ArrayList<Integer> distance = new ArrayList();
        
        
        boolean[][] posMap = new boolean[SIZE][SIZE];
        
        String[] posArr = pos.split(" ");
        int xStart = Integer.valueOf(posArr[0]);
        int yStart = Integer.valueOf(posArr[1]);
        
        posMap[xStart][yStart] = true; // seed value
        
        
        boolean changed = true;
        for (int step = 0; changed == true; step++)
        {
            changed = false;
            
            boolean[][] tempPosMap = new boolean[SIZE][SIZE];
            
            for (int row = 0; row < SIZE; row++)
            {
                for (int col = 0; col < SIZE; col++)
                {
                    if (posMap[col][row] == true)
                    {
                        tempPosMap[col][row] = true;
                        if (!(col == xStart && row == yStart) && isKeyPosition(map, col, row))
                        {
                            String temp = col + " " + row;
                            if (connection.contains(temp) == false)
                            {
                                connection.add(temp);
                                distance.add(step);
                            }
                        }
                        else
                        {
                            if (isValidPosition(map, col + 1, row) && (map[col][row] == '.' || map[col][row] == '>')) // right
                            {
                                if (posMap[col + 1][row] == false)
                                {
                                    changed = true;
                                    tempPosMap[col + 1][row] = true;
                                }
                            }
                            if (isValidPosition(map, col, row + 1) && (map[col][row] == '.' || map[col][row] == 'v')) // down
                            {
                                if (posMap[col][row + 1] == false)
                                {
                                    changed = true;
                                    tempPosMap[col][row + 1] = true;
                                }
                            }
                            if (isValidPosition(map, col - 1, row) && (map[col][row] == '.' || map[col][row] == '<')) // left
                            {
                                if (posMap[col - 1][row] == false)
                                {
                                    changed = true;
                                    tempPosMap[col - 1][row] = true;
                                }
                            }
                            if (isValidPosition(map, col, row - 1) && (map[col][row] == '.' || map[col][row] == '^')) // up
                            {
                                if (posMap[col][row - 1] == false)
                                {
                                    changed = true;
                                    tempPosMap[col][row - 1] = true;
                                }
                            }
                        }
                    }
                }
            }
            
            posMap = tempPosMap;
        }
        
        ArrayList[] res = new ArrayList[2];
        res[0] = connection;
        res[1] = distance;
        
        return res;
    }
    
    
    public static void debugDisplay(boolean[][] arr) // DEBUG
    {
        System.out.println();
        for (int y = 0; y < SIZE; y++)
        {
            for (int x = 0; x < SIZE; x++)
            {
                System.out.print(arr[x][y] ? "#" : ".");
            }
            System.out.println();
        }
        System.out.println();
    }
    
    
    public static boolean isKeyPosition(char[][] map, int x, int y)
    {
        if (isValidPosition(map, x, y) == false) return false;
        
        if (map[x][y] != '.') return false;
        
        if (y == 0 || y == SIZE - 1) return true; // is a start / end position
        
        int numAdj = 0;
        
        numAdj += (map[x + 1][y] != '#') ? 1 : 0;
        numAdj += (map[x][y + 1] != '#') ? 1 : 0;
        numAdj += (map[x - 1][y] != '#') ? 1 : 0;
        numAdj += (map[x][y - 1] != '#') ? 1 : 0;
        
        if (numAdj > 2) return true; // is a crossroads
        
        return false;
    }
    
    
    public static boolean isValidPosition(char[][] map, int x, int y)
    {
        if (x < 0 || x >= SIZE) return false;
        if (y < 0 || y >= SIZE) return false;
        
        if (map[x][y] == '#') return false;
        
        return true;
    }
}
