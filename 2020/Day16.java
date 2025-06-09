import java.util.*;
import java.io.*;

public class Day16
{
    public static void main(String args[]) throws IOException
    {
        File file = new File("input.txt");
        Scanner scn = new Scanner(file);
        
        long ans = 1;
        
        HashMap<String, int[]> fields = new HashMap();
        
        for (String line = scn.nextLine(); line.equals("") == false; line = scn.nextLine())
        {
            String name = line.substring(0, line.indexOf(":"));
            
            line = line.substring(line.indexOf(":") + 2);
            line = line.replace("-", " ");
            line = line.replace(" or ", " ");
            
            String[] data = line.split(" ");
            int[] ranges = new int[data.length];
            for (int i = 0; i < data.length; i++)
            {
                ranges[i] = Integer.parseInt(data[i]);
            }
            
            fields.put(name, ranges);
        }
        
        scn.nextLine();
        
        String[] ticketData = scn.nextLine().split(",");
        int[] myTicket = new int[ticketData.length];
        for (int i = 0; i < ticketData.length; i++)
        {
            myTicket[i] = Integer.parseInt(ticketData[i]);
        }
        
        scn.nextLine(); scn.nextLine();
        
        HashMap<String, boolean[]> orderMap = new HashMap();
        for (String field : fields.keySet())
        {
            boolean[] mask = new boolean[myTicket.length];
            for (int i = 0; i < mask.length; i++) mask[i] = true;
            
            orderMap.put(field, mask);
        }
        
        while (scn.hasNextLine())
        {
            String line = scn.nextLine();
            String[] data = line.split(",");
            int[] nums = new int[data.length];
            
            for (int i = 0; i < data.length; i++)
            {
                nums[i] = Integer.parseInt(data[i]);
            }
            
            boolean discard = false;
            for (int num : nums)
            {
                boolean valid = false;
                for (int[] range : fields.values())
                {
                    if (num >= range[0] && num <= range[1]) valid = true;
                    if (num >= range[2] && num <= range[3]) valid = true;
                }
                
                if (valid == false)
                {
                    discard = true;
                    break;
                }
            }
            if (discard == true) continue;
            
            for (int i = 0; i < nums.length; i++)
            {
                int num = nums[i];
                
                for (String field : fields.keySet())
                {
                    int[] range = fields.get(field);
                    boolean valid = false;
                    
                    if (num >= range[0] && num <= range[1]) valid = true;
                    if (num >= range[2] && num <= range[3]) valid = true;
                    
                    if (valid == false)
                    {
                        orderMap.get(field)[i] = false;
                    }
                }
            }
        }
        
        for (int step = 0; step < orderMap.size(); step++)
        {
            for (String field : orderMap.keySet())
            {
                boolean[] mask = orderMap.get(field);
                int numValid = 0;
                for (int i = 0; i < mask.length; i++) if (mask[i] == true) numValid++;
                
                if (numValid == 1)
                {
                    int daIndex = -1;
                    for (int i = 0; i < mask.length; i++) if (mask[i] == true) daIndex = i;
                    
                    for (String otherField : orderMap.keySet())
                    {
                        if (otherField.equals(field)) continue;
                        orderMap.get(otherField)[daIndex] = false;
                    }
                }
            }
        }
        
        for (String field : orderMap.keySet())
        {
            boolean[] mask = orderMap.get(field);
            for (int i = 0; i < mask.length; i++)
            {
                System.out.print(mask[i] ? " 1 " : " 0 ");
            }
            System.out.println(":: " + field);
            
            if (field.contains("departure") == false) continue;            
            for (int i = 0; i < mask.length; i++)
            {
                if (mask[i] == true) 
                {
                    ans *= myTicket[i];
                }
            }
        }
        
        System.out.println();
        System.out.println("Answer: " + ans);
    }
}