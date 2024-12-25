import java.util.*;
import java.io.*;

public class Day25
{
    public static void main(String[] args) throws IOException
    {
        File file = new File("input.txt");
        Scanner scn = new Scanner(file);
        
        int ans = 0;
        
        ArrayList<int[]> keyList = new ArrayList();
        ArrayList<int[]> lockList = new ArrayList();
        
        while (scn.hasNextLine())
        {
            boolean isKey = false;
            int[] tempData = new int[5];

            for (int j = 0; j < 7; j++)
            {
                String line = scn.nextLine();
                
                if (j == 0) 
                {
                    if (line.charAt(0) == '#') // key
                    {
                        isKey = true;
                    }
                    else
                    {
                        isKey = false;
                    }
                }
                
                for (int i = 0; i < tempData.length; i++)
                {
                    if (line.charAt(i) == '#')
                    {
                        tempData[i] += 1;
                    }
                }
                
                if (scn.hasNextLine() && j == 6) scn.nextLine();
            }
            
            if (isKey) keyList.add(tempData);
            else lockList.add(tempData);
        }
        
        for (int[] key : keyList)
        {
            for (int[] lock : lockList)
            {
                boolean flag = true;
                for (int i = 0; i < key.length; i++)
                {
                    if (key[i] + lock[i] > 7) flag = false;
                }
                if (flag) ans++;
            }
        }
        
        System.out.println(ans);
    }
}