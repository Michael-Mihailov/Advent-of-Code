import java.util.*;
import java.io.*;

public class Day9
{
    public static void main(String[] args) throws IOException
    {
        File file = new File("input.txt");
        Scanner scn = new Scanner(file);
        
        int answer = 0;
        
        while (scn.hasNextLine())
        {
            String data = scn.nextLine();
            
            Scanner dataScn = new Scanner(data);
            
            ArrayList<ArrayList<Integer>> subSequences = new ArrayList();
            ArrayList<Integer> sequence = new ArrayList();
            
            while (dataScn.hasNext())
            {
                sequence.add(dataScn.nextInt());
            }
            
            while (!allZero(sequence))
            {
                ArrayList<Integer> temp = new ArrayList();
                for (int i = 1; i < sequence.size(); i++)
                {
                    temp.add(sequence.get(i) - sequence.get(i - 1));
                }
                
                subSequences.add(sequence);
                sequence = temp;
            }
            
            int value = 0;
            for (int i = subSequences.size() - 1; i >= 0; i--)
            {
                value = subSequences.get(i).get(0) - value;
            }
            
            answer += value;
        }
        
        System.out.println(answer);
    }
    
    public static boolean allZero(ArrayList<Integer> a)
    {
        for (int num : a)
            if (num != 0) return false;
            
        return true;
    }
}
