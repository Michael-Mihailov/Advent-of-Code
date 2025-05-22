import java.util.*;
import java.io.*;

public class Day10
{
    public static void main(String args[]) throws IOException
    {
        File file = new File("input.txt");
        Scanner scn = new Scanner(file);
        
        ArrayList<Integer> adapters = new ArrayList();
        
        int highest = 0;
        while (scn.hasNextLine())
        {
            int num = scn.nextInt();
            adapters.add(num);
            if (num > highest) highest = num;
        }
        adapters.add(0);
        adapters.add(highest + 3);
        
        adapters.sort( (a,b) -> { return a < b ? -1 : 1; } );
        
        long[] numValid = new long[adapters.size()];
        numValid[0] = 1;
        for (int i = 0; i < numValid.length; i++)
        {
            for (int j = i + 1; j < numValid.length && adapters.get(i) >= adapters.get(j) - 3; j++)
            {
                numValid[j] += numValid[i];
            }
        }
        
        System.out.println(numValid[numValid.length - 1]);
    }
}
