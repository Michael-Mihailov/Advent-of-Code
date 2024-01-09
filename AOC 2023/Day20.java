import java.util.*;
import java.io.*;

public class Day20
{
    public static void main(String[] args) throws IOException
    {
        File file = new File("input.txt");
        Scanner scn = new Scanner(file);
        
        final int buttonPresses = 1000;
        long answer = 0;
        
        
        String[] broadcasts = null;
        
        
        HashMap<String, Character> typeMap = new HashMap();
        HashMap<String, String[]> connectionMap = new HashMap();
        // low pulse = false; high pulse = true
        HashMap<String, Boolean> flipFlop = new HashMap();
        HashMap<String, HashMap<String, Boolean>> conjunction = new HashMap();
        
        
        ArrayDeque<String> signals = new ArrayDeque(); // Queue is more efficient than ArrayList
        ArrayDeque<String> origins = new ArrayDeque(); 
        ArrayDeque<Boolean> pulses = new ArrayDeque(); 
        
        
        while (scn.hasNextLine()) // get data
        {
            String data = scn.nextLine();
            
            if (data.contains("broadcaster"))
            {
                broadcasts = data.substring(data.indexOf(" -> ") + 4).split(", ");
            }
            else
            {
                char type = data.charAt(0);
                String key = data.substring(1, data.indexOf(" -> "));
                String[] values = data.substring(data.indexOf(" -> ") + 4).split(", ");
                
                typeMap.put(key, type);
                connectionMap.put(key, values);
                
                if (type == '%')
                {
                    flipFlop.put(key, false);
                }
                else if (type == '&')
                {
                    HashMap<String, Boolean> tempMap = new HashMap();
                    conjunction.put(key, tempMap);
                }
                else System.out.println("Error");
            }
        }
        for (String s : connectionMap.keySet()) // set conjunction moduals' input connections
        {
            for (String v : connectionMap.get(s))
            {
                if (conjunction.containsKey(v))
                {
                    conjunction.get(v).put(s, false);
                }
            }
        }
        for (String v : broadcasts)
        {
            if (conjunction.containsKey(v))
            {
                conjunction.get(v).put("broadcaster", false);
            }
        }
        
        boolean done = false;
        for (; !done; answer++)
        {            
            if (answer % 10000000 == 0) System.out.println("count: " + answer);
                        
            for (String s : broadcasts)
            {
                signals.add(s);
                origins.add("broadcaster");
                pulses.add(false); // low pulse from button
            }
            
            while (signals.size() > 0)
            {
                String signal = signals.pop();
                String origin = origins.pop();
                boolean pulse = pulses.pop();
                
                
                if (signal.equals("rx"))
                {
                    if (pulse == false) done = true;
                    
                    // System.out.println(answer + " " + pulse + " " + origin);
                }
                
                
                if (typeMap.get(signal) != null)
                {
                    char type = typeMap.get(signal);
                    if (type == '%') //flipFlop
                    {
                        if (pulse == true) continue;
                        
                        flipFlop.put(signal, !flipFlop.get(signal)); // flips the value
                        
                        for (String con : connectionMap.get(signal))
                        {
                            signals.add(con);
                            origins.add(signal);
                            pulses.add(flipFlop.get(signal));
                        }
                    }
                    else if (type == '&') //conjunction
                    {
                        conjunction.get(signal).put(origin, pulse);
                        
                        boolean allHigh = true;
                        for (Boolean v : conjunction.get(signal).values())
                        {
                            if (v == false)
                            {
                                allHigh = false;
                                break;
                            }
                        }
                        
                        for (String con : connectionMap.get(signal))
                        {
                            signals.add(con);
                            origins.add(signal);
                            pulses.add(!allHigh);
                        }
                    }
                    else System.out.println("Error");
                }
            }
        }
        

        System.out.println("Part2: " + answer);
    }
}