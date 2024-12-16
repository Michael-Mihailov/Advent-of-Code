import java.util.*;
import java.io.*;

public class Day14
{
    public static void main(String[] args) throws IOException
    {
        File file = new File("input.txt");
        Scanner scn = new Scanner(file);
        
        int ans = 0;
        
        final int WIDTH = 101;
        final int HEIGHT = 103;
        
        ArrayList<int[]> robots = new ArrayList();
        //HashSet<String> seen = new HashSet();
        
        while (scn.hasNextLine())
        {
            String line = scn.nextLine();
            
            line = line.replace("p=", "");
            line = line.replace("v=", "");
            line = line.replace(",", " ");
            
            //System.out.println(line);
            
            String[] data = line.split(" ");
            
            int[] tempRobot = new int[data.length];
            for (int i = 0; i < data.length; i++)
            {
                tempRobot[i] = Integer.parseInt(data[i]);
            }
            
            robots.add(tempRobot);
        }
        
        final int patternStart = 4; // found manually
        final int patternIncrement = 101; // found manually
        Scanner checkScan = new Scanner(System.in);
        for (int step = 0; step < 100000; step++)
        {     
            //String gridStr = gridString(getGrid(robots, WIDTH, HEIGHT));
            if ((step - patternStart) % patternIncrement == 0)
            {
                System.out.println(step);
                display(robots, WIDTH, HEIGHT);
                System.out.println();
                System.out.println();
                System.out.println();
                
                //seen.add(gridStr);
                
                checkScan.next();
            }
            
            for (int i = 0; i < robots.size(); i++)
            {
                robots.get(i)[0] += robots.get(i)[2];
                robots.get(i)[1] += robots.get(i)[3];
                
                if (robots.get(i)[0] < 0) robots.get(i)[0] += WIDTH;
                if (robots.get(i)[0] >= WIDTH) robots.get(i)[0] -= WIDTH;
                
                if (robots.get(i)[1] < 0) robots.get(i)[1] += HEIGHT;
                if (robots.get(i)[1] >= HEIGHT) robots.get(i)[1] -= HEIGHT;
            }
        }
        
        
        
        //ans = quadrants[0] * quadrants[1] * quadrants[2] * quadrants[3];
        
        //System.out.println(ans);
    }
    
    public static String gridString (boolean[][] grid)
    {
        String ans = "";
        for (boolean[] row : grid)
        {
            for (boolean val : row)
            {
                if (val) ans += "1";
                else ans += "0";
            }
        }
        return ans;
    }
    
    public static boolean[][] getGrid (ArrayList<int[]> robots, int WIDTH, int HEIGHT)
    {
        boolean[][] grid = new boolean[HEIGHT][WIDTH];
        
        for (int[] robot : robots)
        {
            grid[robot[1]][robot[0]] = true;
        }
        
        return grid;
    }
    
    public static int[] getQuadrants (ArrayList<int[]> robots, int WIDTH, int HEIGHT)
    {
        int[] quadrants = new int[4];
        
        for (int[] robot : robots)
        {
            if (robot[0] > WIDTH / 2)
            {
                if (robot[1] > HEIGHT / 2)
                {
                    quadrants[3]++;
                }
                else if (robot[1] < HEIGHT / 2)
                {
                    quadrants[0]++;
                }
            }
            else if (robot[0] < WIDTH / 2)
            {
                if (robot[1] > HEIGHT / 2)
                {
                    quadrants[2]++;
                }
                else if (robot[1] < HEIGHT / 2)
                {
                    quadrants[1]++;
                }
            }
        }
        
        return quadrants;
    }
    
    public static void display (ArrayList<int[]> robots, int width, int height)
    {
        boolean[][] grid = new boolean[height][width];
        
        for (int[] robot : robots)
        {
            grid[robot[1]][robot[0]] = true;
        }
        
        for (int y = 0; y < height; y++)
        {
            for (int x = 0; x < width; x++)
            {
                if (grid[y][x]) System.out.print("#");
                else System.out.print(".");
            }
            System.out.println();
        }
    }
}