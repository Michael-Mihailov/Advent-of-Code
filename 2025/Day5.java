import java.util.*;
import java.io.*;

public class Day5
{
    public static void main(String[] args) throws IOException
    {
        File file = new File("input.txt");
        Scanner scn = new Scanner(file);
        
        long ans = 0;
        
        ArrayList<long[]> rangeList = new ArrayList();
        
        while (scn.hasNextLine())
        {
            String line = scn.nextLine();
            if (line.length() == 0) break;
            
            String[] data = line.split("-");
            long[] range = new long[2];
            range[0] = Long.parseLong(data[0]);
            range[1] = Long.parseLong(data[1]);
            rangeList.add(range);
        }
        /*
        while (scn.hasNextLine())
        {
            String line = scn.nextLine();
            long val = Long.parseLong(line);
            
            for (long[] range : rangeList)
            {
                if (val >= range[0] && val <= range[1])
                {
                    ans++;
                    break;
                }
            }
        }
        */
        
        int oldSize = Integer.MAX_VALUE;
        while (rangeList.size() != oldSize)
        {
            oldSize = rangeList.size();
            
            for (int i = 0; i < rangeList.size(); i++)
            {
                for (int j = 0; j < rangeList.size(); j++)
                {
                    if (i == j) continue;
                    if (rangeList.get(i)[1] >= rangeList.get(j)[0] && rangeList.get(i)[1] <= rangeList.get(j)[1])
                    {
                        // overlap
                        long newLow = rangeList.get(i)[0] < rangeList.get(j)[0] ? rangeList.get(i)[0] : rangeList.get(j)[0];
                        long newHigh = rangeList.get(i)[1] > rangeList.get(j)[1] ? rangeList.get(i)[1] : rangeList.get(j)[1];
                        long[] newRange = new long[]{newLow, newHigh};
                        if (i > j)
                        {
                            rangeList.remove(i);
                            rangeList.remove(j);
                        }
                        else
                        {
                            rangeList.remove(j);
                            rangeList.remove(i);
                        }
                        rangeList.add(newRange);
                        i = 0;
                        j = 0;
                    }
                }
            }
        }
        
        for (long[] range : rangeList)
        {
            ans += range[1] - range[0] + 1;
        }
        
        System.out.println(ans);
    }
}