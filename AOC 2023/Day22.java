import java.util.*;
import java.io.*;

public class Day22
{
    public static void main(String[] args) throws IOException
    {
        File file = new File("input.txt");
        Scanner scn = new Scanner(file);
        
        int answer = 0;
        
        ArrayList<int[]> bricks = new ArrayList<int[]>(); // x1, y1, z1, x2, y2, z2
        
        while (scn.hasNextLine()) // get data
        {
            String data = scn.nextLine();
            data = data.replace('~', ',');
            
            Scanner dataScn = new Scanner(data);
            dataScn.useDelimiter(",");
                        
            int[] brick = new int[6];
            for (int i = 0; i < 6; i++)
            {
                brick[i] = dataScn.nextInt();
            }
            
            bricks.add(brick);
        }
        
        boolean done = false;
        while (!done) // let gravity do stuff
        {
            done = true;
            
            for (int b = 0; b < bricks.size(); b++)
            {
                int[] brick = bricks.get(b);
                
                int[] temp = copyArr(brick);
                temp[2] -= 1;
                temp[5] -= 1;
                
                if (check(bricks, brick, temp) == false)
                {
                    bricks.set(b, temp);
                    done = false;
                }
            }
        }
                
        for (int b = 0; b < bricks.size(); b++) // check above bricks
        {
            int[] brick = bricks.get(b);
                        
            answer += numFall(bricks, brick);
        }
        
        
        System.out.println(answer);
    }
    
    
    public static int numFall(ArrayList<int[]> oldBricks, int[] check)
    {
        int res = 0;
        
        ArrayList<int[]> bricks = new ArrayList();
        for (int[] brick : oldBricks)
        {
            if (brick != check)
            {
                bricks.add(copyArr(brick));
            }
        }
        
        
        boolean done = false;
        while (!done) // let gravity do stuff
        {
            done = true;
            
            for (int b = 0; b < bricks.size(); b++)
            {
                int[] brick = bricks.get(b);
                
                int[] temp = copyArr(brick);
                temp[2] -= 1;
                temp[5] -= 1;
                
                if (check(bricks, brick, temp) == false)
                {
                    bricks.remove(b);
                    done = false;
                    
                    res++;
                }
            }
        }
        
        return res;
    }
    
    
    public static int[] copyArr(int[] arr)
    {
        int[] temp = new int[arr.length];
        
        for (int i = 0; i < arr.length; i++)
        {
            temp[i] = arr[i];
        }
        
        return temp;
    }
    
    public static ArrayList<int[]> getBricks(ArrayList<int[]> bricks, int[] old, int[] area) // checks if there are any bricks in the area (brute force)
    {
        ArrayList<int[]> res = new ArrayList();
        
        for (int b = 0; b < bricks.size(); b++)
        {
            int[] brick = bricks.get(b);
            
            if (brick == old) continue;
            
            if ( ( ( (brick[3] < area[0] || brick[0] > area[3]) )
            || ( (brick[4] < area[1] || brick[1] > area[4]) )
            || ( (brick[5] < area[2] || brick[2] > area[5]) ) ) == false)
            {
                res.add(brick); // if intersecting area
            }
        }
        
        return res; // no intersecting area
    }
    
    public static boolean check(ArrayList<int[]> bricks, int[] old, int[] area) // checks if there are any bricks in the area (brute force)
    {
        if (area[2] <= 0 || area[5] <= 0)
        {
            return true; // under floor
        }
        
        for (int b = 0; b < bricks.size(); b++)
        {
            int[] brick = bricks.get(b);
            
            if (brick == old) continue;
            
            if ( ( ( (brick[3] < area[0] || brick[0] > area[3]) )
            || ( (brick[4] < area[1] || brick[1] > area[4]) )
            || ( (brick[5] < area[2] || brick[2] > area[5]) ) ) == false)
            {                
                return true; // true if intersecting area
            }
        }
                
        return false; // false if no intersecting area
    }
}
