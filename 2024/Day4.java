import java.util.*;
import java.io.*;

public class Day4
{
    public static void main(String[] args) throws IOException
    {
        File file = new File("input.txt");
        Scanner scn = new Scanner(file);
        
        int ans = 0;
        
        String word = "XMAS";
        char[][] map = new char[140][140];
        
        
        for (int j = 0; j < map.length; j++)
        {
            String line = scn.next();
            
            for (int i = 0; i < line.length(); i++)
            {
                map[j][i] = line.charAt(i);
            }
        }
        
        for (int y = 1; y < map.length - 1; y++)
        {
            for (int x = 1; x < map[y].length - 1; x++)
            {
                if (x1(map, word, x, y)) ans++;
                if (x2(map, word, x, y)) ans++;
                if (x3(map, word, x, y)) ans++;
                if (x4(map, word, x, y)) ans++;
                /*
                if (checkRight(map, word, x, y)) ans++;
                if (checkLeft(map, word, x, y)) ans++;
                if (checkDown(map, word, x, y)) ans++;
                if (checkUp(map, word, x, y)) ans++;
                if (d1(map, word, x, y)) ans++;
                if (d2(map, word, x, y)) ans++;
                if (d3(map, word, x, y)) ans++;
                if (d4(map, word, x, y)) ans++;
                */
            }
        }
        
        System.out.println(ans);
    }
    public static boolean x1(char[][] map, String word, int x, int y)
    {
        return map[y][x] == 'A' &&
        map[y-1][x-1] == 'M' &&
        map[y-1][x+1] == 'M' &&
        map[y+1][x+1] == 'S' &&
        map[y+1][x-1] == 'S';
    }
    public static boolean x2(char[][] map, String word, int x, int y)
    {
        return map[y][x] == 'A' &&
        map[y-1][x-1] == 'S' &&
        map[y-1][x+1] == 'S' &&
        map[y+1][x+1] == 'M' &&
        map[y+1][x-1] == 'M';
    }
    public static boolean x3(char[][] map, String word, int x, int y)
    {
        return map[y][x] == 'A' &&
        map[y-1][x-1] == 'M' &&
        map[y+1][x-1] == 'M' &&
        map[y-1][x+1] == 'S' &&
        map[y+1][x+1] == 'S';
    }
    public static boolean x4(char[][] map, String word, int x, int y)
    {
        return map[y][x] == 'A' &&
        map[y-1][x-1] == 'S' &&
        map[y+1][x-1] == 'S' &&
        map[y-1][x+1] == 'M' &&
        map[y+1][x+1] == 'M';
    }
    
    /*
    public static boolean checkRight(char[][] map, String word, int x, int y)
    {
        return x <= map.length - word.length() && 
        map[y][x] == word.charAt(0) && 
        map[y][x + 1] == word.charAt(1) && 
        map[y][x + 2] == word.charAt(2) && 
        map[y][x + 3] == word.charAt(3);
    }
    public static boolean checkLeft(char[][] map, String word, int x, int y)
    {
        return x - word.length() + 1 >= 0 && 
        map[y][x] == word.charAt(0) && 
        map[y][x - 1] == word.charAt(1) && 
        map[y][x - 2] == word.charAt(2) && 
        map[y][x - 3] == word.charAt(3);
    }
    public static boolean checkDown(char[][] map, String word, int x, int y)
    {
        return  y <= map.length - word.length() && 
        map[y][x] == word.charAt(0) && 
        map[y + 1][x] == word.charAt(1) && 
        map[y + 2][x] == word.charAt(2) && 
        map[y + 3][x] == word.charAt(3);
    }
    public static boolean checkUp(char[][] map, String word, int x, int y)
    {
        return  y - word.length() + 1 >= 0 && 
        map[y][x] == word.charAt(0) && 
        map[y - 1][x] == word.charAt(1) && 
        map[y - 2][x] == word.charAt(2) && 
        map[y - 3][x] == word.charAt(3);
    }
    public static boolean d1(char[][] map, String word, int x, int y) // SE
    {
        return x <= map.length - word.length() && y <= map.length - word.length() && 
        map[y][x] == word.charAt(0) && 
        map[y + 1][x + 1] == word.charAt(1) && 
        map[y + 2][x + 2] == word.charAt(2) && 
        map[y + 3][x + 3] == word.charAt(3);
    }
    public static boolean d2(char[][] map, String word, int x, int y) // NW
    {
        return x - word.length() + 1 >= 0 && y - word.length() + 1 >= 0 && 
        map[y][x] == word.charAt(0) && 
        map[y - 1][x - 1] == word.charAt(1) && 
        map[y - 2][x - 2] == word.charAt(2) && 
        map[y - 3][x - 3] == word.charAt(3);
    }
    public static boolean d3(char[][] map, String word, int x, int y) // NE
    {
        return x <= map.length - word.length() && y - word.length() + 1 >= 0 && 
        map[y][x] == word.charAt(0) && 
        map[y - 1][x + 1] == word.charAt(1) && 
        map[y - 2][x + 2] == word.charAt(2) && 
        map[y - 3][x + 3] == word.charAt(3);
    }
    public static boolean d4(char[][] map, String word, int x, int y) // SW
    {
        return x - word.length() + 1 >= 0 && y <= map.length - word.length() && 
        map[y][x] == word.charAt(0) && 
        map[y + 1][x - 1] == word.charAt(1) && 
        map[y + 2][x - 2] == word.charAt(2) && 
        map[y + 3][x - 3] == word.charAt(3);
    }
    */
    
}