import java.util.*;
import java.io.*;

public class Day9
{
    public static void main(String[] args) throws IOException
    {
        File file = new File("input.txt");
        Scanner scn = new Scanner(file);
        
        long ans = 0;
        
        String[] data = scn.nextLine().split("|");
        ArrayList<Integer> drive = new ArrayList();
        
        ArrayList<int[]> map = new ArrayList(); // size, id; id == -1 if space
        
        for (int i = 0; i < data.length; i++)
        {
            if (i % 2 == 0)
            {
                map.add(new int[]{Integer.parseInt(data[i]), i / 2});
            }
            else
            {
                map.add(new int[]{Integer.parseInt(data[i]), -1});
            }
        }
        
        //debugMap(map);
        
        for (int i = data.length - 1; i > 0; i--) // rightmost file
        {
            if (map.get(i)[1] == -1) continue;
            
            boolean foundSpace = false;
            for (int j = 0; j < i && foundSpace == false; j++) // leftmost free-space
            {
                if (map.get(j)[1] == -1)
                {
                    if (map.get(j)[0] >= map.get(i)[0])
                    {
                        int newSpace = map.get(j)[0] - map.get(i)[0];
                        map.add(j, new int[]{map.get(i)[0], map.get(i)[1]});
                        map.set(i+1, new int[]{map.get(i+1)[0], -1});
                        map.set(j+1, new int[]{newSpace, -1});
                        // TODO: maybe i++
                        
                        foundSpace = true;
                    }
                }
            }
            //debugMap(map);
        }
        
        for (int i = 0; i < map.size(); i++)
        {
            int num = map.get(i)[0];
            for (int j = 0; j < num; j++)
            {
                if (map.get(i)[1] != -1) drive.add(map.get(i)[1]);
                else drive.add(0);
            }
        }
        
        /*
        for (int i = 0; i < data.length; i++)
        {
            int num = Integer.parseInt(data[i]);
            if (i % 2 == 0) // block
            {
                for (int j = 0; j < num; j++)
                {
                    drive.add(i / 2);
                }
            }
            else // empty
            {
                int moveIndex = -1;
                
                boolean end = false;
                for (int j = data.length - 1; j > i && !end; j -= 2)
                {
                    if (Integer.parseInt(data[j]) <= num && Integer.parseInt(data[j]) > 0)
                    {
                        moveIndex = j;
                        end = true;
                    }
                }
                for (int j = 0; j < num; j++)
                {
                    if (moveIndex != -1 && j < Integer.parseInt(data[moveIndex]))
                    {
                        drive.add(moveIndex / 2);
                    }
                    else
                    {
                        drive.add(-1);
                    }
                }
                
                if (moveIndex != -1)
                {
                    if (moveIndex != data.length - 1)
                    {
                        data[moveIndex - 1] = ( Integer.parseInt(data[moveIndex - 1]) + Integer.parseInt(data[moveIndex]) + Integer.parseInt(data[moveIndex + 1]) ) + "";
                        data[moveIndex] = "0";
                        data[moveIndex + 1] = "0";
                    } 
                    else
                    {
                        data[moveIndex - 1] = ( Integer.parseInt(data[moveIndex - 1]) + Integer.parseInt(data[moveIndex]) ) + "";
                        data[moveIndex] = "0";
                    }
                }
            }
        }
        */
        /*
        for (int i = 0; i < drive.size(); i++)
        {
            if (drive.get(i) == -1)
            {
                boolean flag = true;
                for (int j = drive.size() - 1; flag; j--)
                {
                    flag = drive.get(j) == -1;
                    drive.set(i, drive.remove(j));
                }
            }
        }
        */
        for (int i = 0; i < drive.size(); i++)
        {
            if (drive.get(i) != -1)
            {
                ans += i * drive.get(i);
            }
        }
        
        System.out.println(ans);
    }
    
    public static void debugMap(ArrayList<int[]> map)
    {
        for (int i = 0; i < map.size(); i++)
        {
            int num = map.get(i)[0];
            int id = map.get(i)[1];
            for(int j = 0; j < num; j++)
            {
                if (id != -1) System.out.print(id);
                else System.out.print(".");
            }
        }
        System.out.println();
    }
    
    public static int sum()
    {
        String s = "00992111777.44.333....5555.6666.....8888..";
        int ans = 0;
        for (int i = 0; i < s.length(); i++)
        {
            ans += (s.charAt(i) - '0') * i;
        }
        return ans;
    }
}