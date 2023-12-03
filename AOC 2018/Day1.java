import java.util.*;
import java.io.*;

public class Day1
{
    public static void main(String[] args) throws IOException
    {
        HashMap<Integer, Boolean> answers = new HashMap();
        int answer = 0;
        
        File file = new File("input.txt");
        
        
        boolean go = true;
        while (go)
        {
            Scanner scn = new Scanner(file);
            
            while (scn.hasNext())
            {
                if (answers.get(answer) != null)
                {
                    System.out.println("answer: " + answer);
                    go = false;
                }
                answers.put(answer, true);
                answer += scn.nextInt();
            }
        }
    }
}
