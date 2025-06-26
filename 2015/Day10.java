import java.util.*;
import java.io.*;

public class Day10
{
    public static void main(String args[]) throws IOException
    {
        File file = new File("input.txt");
        Scanner scn = new Scanner(file);
        
        ArrayList<Integer> numList = new ArrayList();
        
        String data = scn.nextLine();
        for (int i = 0; i < data.length(); i++)
        {
            numList.add( data.charAt(i) - '0' );
        }
        
        for (int step = 0; step < 50; step++)
        {
            ArrayList<Integer> tempList = new ArrayList();
            
            int current = numList.getFirst();
            int occurences = 0;
            
            for (int i = 0; i < numList.size(); i++)
            {
                int num = numList.get(i);
                
                if (num == current)
                {
                    occurences++;
                }
                else
                {
                    tempList.add(occurences);
                    tempList.add(current);
                    
                    current = num;
                    occurences = 1;
                }
            }
            tempList.add(occurences);
            tempList.add(current);
            
            numList = tempList;
        }
        
        System.out.println(numList.size());
    }
}