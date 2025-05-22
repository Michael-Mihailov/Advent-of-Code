import java.util.*;
import java.io.*;

public class Day8
{
    public static void main(String args[]) throws IOException
    {
        File file = new File("input.txt");
        Scanner scn = new Scanner(file);
        
        int ans = 0;
        
        ArrayList<String> ogInstructions = new ArrayList();
        ArrayList<Integer> ogValues = new ArrayList();
        
        while (scn.hasNextLine())
        {
            ogInstructions.add(scn.next());
            ogValues.add( Integer.parseInt(scn.next()) );
        }
        
        for (int inst = 0; inst < ogInstructions.size(); inst++)
        {
            ArrayList<String> instructions = new ArrayList(ogInstructions);
            ArrayList<Integer> values = new ArrayList(ogValues);
            
            if (instructions.get(inst).equals("jmp"))
            {
                instructions.set(inst, "nop");
            }
            else if (instructions.get(inst).equals("nop"))
            {
                instructions.set(inst, "jmp");
            }
            
            int total = 0;
            int pos = 0;
            HashSet<Integer> visited = new HashSet();
            boolean ranLast = false;
            
            while (visited.contains(pos) == false && pos < instructions.size())
            {
                visited.add(pos);
                ranLast = (pos == instructions.size() - 1);
                
                if (instructions.get(pos).equals("acc"))
                {
                    total += values.get(pos);
                    pos++;
                }
                else if (instructions.get(pos).equals("jmp"))
                {
                    pos += values.get(pos);
                }
                else if (instructions.get(pos).equals("nop"))
                {
                    pos++;
                }
                else System.out.println("ERROR: " + instructions.get(pos));
            }
            
            if (ranLast)
            {
                ans = total;
                break;
            }
        }
        
        System.out.println(ans);
    }
}
