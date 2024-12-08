import java.util.*;
import java.io.*;

public class Day8
{
    public static void main(String[] args) throws IOException
    {
        File file = new File("input.txt");
        Scanner scn = new Scanner(file);
        
        int ans = 0;
        
        final int SIZE = 50;
        
        boolean[][] map = new boolean[SIZE][SIZE];
        HashMap<String, ArrayList<int[]>> antMap = new HashMap();
        
        for (int y = 0; y < SIZE; y++)
        {
            String line = scn.nextLine();
            for (int x = 0; x < SIZE; x++)
            {
                char c = line.charAt(x);
                
                if (c != '.')
                {
                    if (antMap.containsKey(c + "") == false)
                    {
                        ArrayList<int[]> temp = new ArrayList();
                        antMap.put(c + "", temp);
                    }
                    ArrayList<int[]> temp = antMap.get(c + "");
                    temp.add(new int[]{y, x});
                    antMap.put(c + "", temp);
                }
            }
        }
        
        for (String s : antMap.keySet())
        {
            ArrayList<int[]> ants = antMap.get(s);
            
            for (int i = 0; i < ants.size(); i++)
            {
                int[] p1 = ants.get(i);
                for (int j = 0; j < ants.size(); j++)
                {
                    if (i == j) continue;
                    int[] p2 = ants.get(j);
                    
                    try
                    {
                        int yDiff = p1[0] - p2[0];
                        int xDiff = p1[1] - p2[1];
                        
                        int temp = gcf(yDiff, xDiff);
                        yDiff = yDiff / temp;
                        xDiff = xDiff / temp;
                        
                        for (int step = 0; step < 100; step++)
                        {
                            map[p1[0] + (yDiff * step)][p1[1] + (xDiff * step)] = true;
                        }
                    } catch (Exception e){}
                }
            }
        }
        
        for (int y = 0; y < SIZE; y++)
        {
            for (int x = 0; x < SIZE; x++)
            {
                if (map[x][y]) ans++;
            }
        }
        
        System.out.println(ans);
    }
    
    public static int gcf (int n1, int n2)
    {
        int ans = 1;
        
        for (int i = 1; i < 100; i++)
        {
            if (n1 % i == 0 && n2 % i == 0) ans = i;
        }
        
        return ans;
    }
}