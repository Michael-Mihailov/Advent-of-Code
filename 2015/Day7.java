import java.util.*;
import java.io.*;

public class Day7
{
    public static void main(String args[]) throws IOException
    {
        File file = new File("input.txt");
        
        int ans = -1;
        HashMap<String, Integer> wireMap = new HashMap();
        
        int part1Ans = -1;
        
        while (wireMap.containsKey("a") == false || part1Ans == -1)
        {            
            if (wireMap.containsKey("a"))
            {
                part1Ans = wireMap.get("a");
                wireMap = new HashMap();
                wireMap.put("b", part1Ans);
                
                System.out.println("Part-1 Answer: " + part1Ans);
            }
            
            Scanner scn = new Scanner(file);
            while (scn.hasNextLine())
            {
                String data = scn.nextLine();
                
                String output = data.substring(data.indexOf(">") + 2);
                if (wireMap.containsKey(output)) continue;
                
                int outputVal = -1;
                
                Scanner dataScn = new Scanner(data);
                if (data.contains("AND"))
                {
                    int input1 = getValue( dataScn.next(), wireMap );
                    dataScn.next();
                    int input2 = getValue( dataScn.next(), wireMap );
                    if (input1 == -1 || input2 == -1) continue;
                    
                    outputVal = input1 & input2;
                }
                else if (data.contains("OR"))
                {
                    int input1 = getValue( dataScn.next(), wireMap );
                    dataScn.next();
                    int input2 = getValue( dataScn.next(), wireMap );
                    if (input1 == -1 || input2 == -1) continue;
                    
                    outputVal = input1 | input2;
                }
                else if (data.contains("NOT"))
                {
                    dataScn.next();
                    int input1 = getValue( dataScn.next(), wireMap );
                    if (input1 == -1) continue;
                    
                    outputVal = 65535 - input1;
                }
                else if (data.contains("RSHIFT"))
                {
                    int input = getValue( dataScn.next(), wireMap );
                    dataScn.next();
                    int shift = dataScn.nextInt();
                    if (input == -1) continue;
                    
                    outputVal = input >> shift;
                }
                else if (data.contains("LSHIFT"))
                {
                    int input = getValue( dataScn.next(), wireMap );
                    dataScn.next();
                    int shift = dataScn.nextInt();
                    if (input == -1) continue;
                    
                    outputVal = input << shift;
                }
                else
                {
                    int input = getValue( dataScn.next(), wireMap );
                    if (input == -1) continue;
                    
                    outputVal = input;
                }
                dataScn.close();
                
                if (outputVal > 65535) outputVal %= 65535;
                
                if (outputVal != -1) wireMap.put(output, outputVal);
            }    
        }
        
        
        ans = wireMap.get("a");
        System.out.println("Part-2 Answer: " + ans);
    }
    
    public static int getValue (String input, HashMap<String, Integer> wireMap)
    {
        if (wireMap.containsKey(input)) return wireMap.get(input);
        try
        {
            int res = Integer.parseInt(input);
            return res;
        }
        catch (Exception e) {}
        return -1;
    }
}