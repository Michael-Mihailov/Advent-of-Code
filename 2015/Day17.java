import java.util.*;
import java.io.*;

public class Day17
{
    public static void main(String args[]) throws IOException
    {
        File file = new File("input.txt");
        Scanner scn = new Scanner(file);
        
        int ans = 0;
        
        ArrayList<Integer> cups = new ArrayList();
        
        while (scn.hasNextLine()) cups.add(scn.nextInt());
        
        for (int maxCups = 1; ans == 0; maxCups++)
        {
            ans = numOptions(cups, 0, 150, maxCups);
        }
        
        System.out.println(ans);
    }
    
    public static int numOptions (ArrayList<Integer> cups, int filled, int maxVolume, int remainingCups)
    {
        if (remainingCups < 0) return 0;
        if (filled > maxVolume) return 0;
        if (filled == maxVolume) return 1;
        
        int res = 0;
        
        for (int i = 0; i < cups.size(); i++)
        {
            int cup = cups.get(i);
            ArrayList<Integer> newCups = new ArrayList();
            for (int j = i + 1; j < cups.size(); j++) newCups.add(cups.get(j));
            
            res += numOptions(newCups, filled + cup, maxVolume, remainingCups - 1);
        }
        
        return res;
    }
}