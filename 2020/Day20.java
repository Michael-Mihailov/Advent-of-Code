import java.util.*;
import java.io.*;

public class Day20
{
    public static void main(String args[]) throws IOException
    {
        File file = new File("input.txt");
        Scanner scn = new Scanner(file);
        
        long ans = 0;
        
        HashMap<Integer, boolean[][]> tileMap = new HashMap();
        HashMap<Integer, int[]> neighborMap = new HashMap(); // {right, bottom, left, top}
        HashMap<Integer, Integer> rotationMap = new HashMap(); // the number of 90 degree rotations
        HashMap<Integer, Boolean> flipMap = new HashMap();
        
        while (scn.hasNextLine())
        {
            String header = scn.nextLine();
            header = header.replace("Tile ", "");
            header = header.replace(":", "");
            
            int tileNum = Integer.parseInt(header);
            boolean[][] tile = new boolean[10][10];
            
            for (int row = 0; row < 10; row++)
            {
                String line = scn.nextLine();
                for (int col = 0; col < 10; col++)
                {
                    tile[row][col] = (line.charAt(col) == '#');
                }
            }
            
            tileMap.put(tileNum, tile);
            neighborMap.put(tileNum, new int[4]);
            
            if (scn.hasNextLine()) scn.nextLine();
        }
        
        for (Integer key : tileMap.keySet())
        {
            boolean[][] tile = tileMap.get(key);
            //int count = 0;
            
            for (Integer otherKey : tileMap.keySet())
            {
                if (key == otherKey) continue;

                boolean[][] otherTile = tileMap.get(otherKey);
                
                int temp = match(tile,otherTile)[0];
                if (temp != -1)
                {
                    neighborMap.get(key)[temp] = otherKey;
                    //count++;
                }
            }
            //System.out.println(count);
        }
        
        int mapLength = (int)Math.round( Math.sqrt(tileMap.size()) ); // presume the map is a square
        int[][] tileGrid = new int[mapLength][mapLength];
        
        int topLeftKey = -1; // anchors the grid
        for (Integer key : tileMap.keySet())
        {
            int[] neighbors = neighborMap.get(key);
            
            int count = 0;
            for (int d = 0; d < 4; d++)
            {
                if (neighbors[d] != 0) count++;
            }
            if (count != 2) continue;
            
            for (int rot = 0; rot < 4; rot++)
            {
                if (neighbors[(4 - rot) % 4] != 0 && neighbors[(5 - rot) % 4] != 0)
                {
                    topLeftKey = key;
                    rotationMap.put(topLeftKey, rot);
                    flipMap.put(topLeftKey, false);
                    
                    break;
                }
            }
        }
        tileGrid[0][0] = topLeftKey;
        
        //System.out.println("Top-Left: " + topLeftKey);
        
        for (int row = 0; row < mapLength; row++)
        {
            if (row != 0) // align with tile above
            {
                int neighborKey = tileGrid[row - 1][0];
                int neighborRotation = rotationMap.get(neighborKey);
                
                int key = neighborMap.get(neighborKey)[(1 - neighborRotation + 4) % 4];
                int rotation = -1;
                for (int dir = 0; dir < 4; dir++)
                {
                    if (neighborMap.get(key)[(3 - dir)] == neighborKey)
                    {
                        rotation = dir;
                        break;
                    }
                }
                                
                tileGrid[row][0] = key;
                rotationMap.put(key, rotation);
                
                int[] temp = match(tileMap.get(key), tileMap.get(neighborKey));
                boolean flip = (temp[1] == 1 ? !flipMap.get(neighborKey) : flipMap.get(neighborKey));
                flipMap.put(key, flip);
            }
            
            for (int col = 1; col < mapLength; col++) // align with tile to left
            {  
                int neighborKey = tileGrid[row][col - 1];
                int neighborRotation = rotationMap.get(neighborKey);
                
                int key = neighborMap.get(neighborKey)[(4 - neighborRotation) % 4];
                if (col == 1 && flipMap.get(neighborKey))
                {
                    key = neighborMap.get(neighborKey)[(2 + 4 - neighborRotation) % 4];
                }
                int rotation = -1;
                //System.out.println(neighborKey + ", " + neighborRotation + ", " + flipMap.get(neighborKey) + ", " + key);
                for (int dir = 0; dir < 4; dir++)
                {
                    if (neighborMap.get(key)[((2 - dir) + 4) % 4] == neighborKey)
                    {
                        rotation = dir;
                        break;
                    }
                }
                                
                tileGrid[row][col] = key;
                rotationMap.put(key, rotation);
                
                int[] temp = match(tileMap.get(key), tileMap.get(neighborKey));
                boolean flip = (temp[1] == 1 ? !flipMap.get(neighborKey) : flipMap.get(neighborKey));
                flipMap.put(key, flip);
            }
        }
        
        boolean[][] grid = new boolean[mapLength * 8][mapLength * 8];
        
        for (int tRow = 0; tRow < mapLength; tRow++)
        {
            for (int row = 0; row < 8; row++)
            {
                for (int tCol = 0; tCol < mapLength; tCol++)
                {
                    boolean[][] tile = tileMap.get(tileGrid[tRow][tCol]);
                    
                    for (int rot = 0; rot < rotationMap.get(tileGrid[tRow][tCol]); rot++) tile = rotate(tile);
                    if (flipMap.get(tileGrid[tRow][tCol])) // must flip relative to neighbor
                    {
                        if (tCol == 0)
                        {
                            tile = flip(tile); 
                        }
                        else
                        {
                            tile = rotate(tile);
                            tile = flip(tile);
                            tile = rotate(tile);
                            tile = rotate(tile);
                            tile = rotate(tile);
                        }
                    }
                    
                    
                    for (int col = 0; col < 8; col++)
                    {
                        grid[(tRow * 8) + row][(tCol * 8) + col] = tile[row+1][col+1];
                        //System.out.print(tile[row+1][col+1] ? "#" : ".");
                    }
                    //System.out.print(" ");
                }
                //System.out.println();
            }
            //System.out.println();
        }
        
        String[] monster = new String[]{"                  # ",
                                        "#    ##    ##    ###",
                                        " #  #  #  #  #  #   "};
        
        int mostMonster = 0;
        for (int rot = 0; rot < 4; rot++)
        {
            boolean[][] monsterGrid = new boolean[grid.length][grid.length]; 
            for (int f = 0; f < 2; f++)
            {
                for (int row = 0; row <= grid.length - monster.length; row++)
                {
                    for (int col = 0; col <= grid.length - monster[0].length(); col++)
                    {
                        boolean found = true;
                        for (int y = 0; y < monster.length; y++)
                        {
                            for (int x = 0; x < monster[0].length(); x++)
                            {
                                if(monster[y].charAt(x) == '#' && grid[row + y][col + x] == false) found = false;
                            }
                        }
                        
                        if (found)
                        {
                            for (int y = 0; y < monster.length; y++)
                            {
                                for (int x = 0; x < monster[0].length(); x++)
                                {
                                    if(monster[y].charAt(x) == '#') monsterGrid[row + y][col + x] = true;
                                }
                            }
                        }
                    }
                }
                
                int count = 0;
                for (int row = 0; row < monsterGrid.length; row++)
                {
                    for (int col = 0; col < monsterGrid.length; col++)
                    {
                        count += (monsterGrid[row][col] ? 1 : 0);
                    }
                }
                if (count > mostMonster) mostMonster = count;
                
                grid = flip(grid);
            }
            grid = rotate(grid);
        }
        
        for (int row = 0; row < grid.length; row++)
        {
            for (int col = 0; col < grid.length; col++)
            {
                ans += (grid[row][col] ? 1 : 0);
            }
        }
        ans -= mostMonster;
        
        System.out.println("Answer: " + ans);
    }
        
    public static int[] match (boolean[][] tile,  boolean[][] otherTile) // tells which side "otherTile" matches to, 1 == flip :: 0 == no flip
    {
        for (int rot1 = 0; rot1 < 4; rot1++)
        {
            for (int rot2 = 0; rot2 < 4; rot2++)
            {
                for (int f = 0; f < 2; f++)
                {
                    boolean match = true;
                    for (int col = 0; col < tile.length; col++)
                    {
                        if (tile[0][col] != otherTile[tile.length - 1][col])
                        {
                            match = false;
                            break;
                        }
                    }
                    
                    if (match) return (new int[]{3 - rot1, f});

                    tile = flip(tile);
                }
                otherTile = rotate(otherTile); 
            }
            tile = rotate(tile);
        }
        
        return (new int[]{-1,-1});
    }
    
    public static boolean[][] rotate (boolean[][] tile) // rotate 90 degrees clockwise
    {
        boolean[][] res = new boolean[tile.length][tile.length];
        
        for (int row = 0; row < tile.length; row++)
        {
            for (int col = 0; col < tile.length; col++)
            {
                res[col][(tile.length - 1) - row] = tile[row][col];
            }
        }
        
        return res;
    }
    
    public static boolean[][] flip (boolean[][] tile) // flip over vertical axis
    {
        boolean[][] res = new boolean[tile.length][tile.length];
        
        for (int row = 0; row < tile.length; row++)
        {
            for (int col = 0; col < tile.length; col++)
            {
                res[row][(tile.length - 1) - col] = tile[row][col];
            }
        }
        
        return res;
    }
}
