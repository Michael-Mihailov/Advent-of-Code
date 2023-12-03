
/**
 * Write a description of class Day7 here.
 *
 * @author (your name)
 * @version (a version number or a date)
 */

import java.util.*;
import java.io.*;

public class Day7
{
    public static void main(String args[]) throws IOException
    {
        
        File file = new File("input.txt");
        Scanner scn = new Scanner(file);
        
        ArrayList<String> inst = new ArrayList();
        HashMap<String, Long> values = new HashMap();
        
        while (scn.hasNext())
        {
            String data = scn.nextLine();
            inst.add(data);
            
            values.put(data.substring(data.indexOf(">") + 2), null);
        }
        
        while (values.get("a") == null)
        {
            int nonNull = 0;
            for (Long val : values.values())
            {
                if (val != null)
                {
                    nonNull++;
                }
            }
            System.out.println(nonNull);
            
            for (String data : inst)
            {
                Scanner dataScn = new Scanner(data);
                
                String var = data.substring(data.indexOf(">") + 2);
                
                
                if (data.contains("AND"))
                {
                    String temp1 = dataScn.next();
                    dataScn.next();
                    String temp2 = dataScn.next();
                    
                    Long tempNum1 = null;
                    Long tempNum2 = null;
                    
                    if (values.containsKey(temp1) == false)
                    {
                        tempNum1 = new Long(temp1);
                    }
                    
                    if (values.containsKey(temp2) == false)
                    {
                        tempNum2 = new Long(temp2);
                    }
                    
                    if (tempNum1 != null && tempNum2 != null)
                    {
                        values.put(var, tempNum1 & tempNum2);
                    }
                    else if (tempNum1 != null && values.get(temp2) != null)
                    {
                        long num2 = values.get(temp2);
                        
                        num2 %= 65536;
                        
                        values.put(var, tempNum1 & num2);
                    }
                    else if (tempNum2 != null && values.get(temp1) != null)
                    {
                        long num1 = values.get(temp1);
                        
                        num1 %= 65536;
                        
                        values.put(var, tempNum2 & num1);
                    }
                    else if (values.get(temp1) != null && values.get(temp2) != null)
                    {
                        long num1 = values.get(temp1);
                        
                        num1 %= 65536;
                        
                        long num2 = values.get(temp2);
                        
                        num2 %= 65536;
                        
                        values.put(var, num1 & num2);
                    }
                }
                else if (data.contains("OR"))
                {
                    String temp1 = dataScn.next();
                    dataScn.next();
                    String temp2 = dataScn.next();
                    
                    Long tempNum1 = null;
                    Long tempNum2 = null;
                    
                    if (values.containsKey(temp1) == false)
                    {
                        tempNum1 = new Long(temp1);
                    }
                    
                    if (values.containsKey(temp2) == false)
                    {
                        tempNum2 = new Long(temp2);
                    }
                    
                    if (tempNum1 != null && tempNum2 != null)
                    {
                        values.put(var, tempNum1 | tempNum2);
                    }
                    else if (tempNum1 != null && values.get(temp2) != null)
                    {
                        long num2 = values.get(temp2);
                        
                        num2 %= 65536;
                        
                        values.put(var, tempNum1 | num2);
                    }
                    else if (tempNum2 != null && values.get(temp1) != null)
                    {
                        long num1 = values.get(temp1);
                        
                        num1 %= 65536;
                        
                        values.put(var, tempNum2 | num1);
                    }
                    else if (values.get(temp1) != null && values.get(temp2) != null)
                    {
                        long num1 = values.get(temp1);
                        
                        num1 %= 65536;
                        
                        long num2 = values.get(temp2);
                        
                        num2 %= 65536;
                        
                        values.put(var, num1 | num2);
                    }
                }
                else if (data.contains("NOT"))
                {
                    dataScn.next();
                    
                    String temp = dataScn.next();
                    
                    if (values.get(temp) != null)
                    {
                        long num = values.get(temp);
                        
                        num %= 65536;
                        
                        num = Math.abs(65535 - num);
                        
                        values.put(var, num);
                    }
                }
                else if (data.contains("RSHIFT"))
                {
                    String temp = dataScn.next();
                    
                    dataScn.next();
                    
                    long num = dataScn.nextInt();
                    
                    if (values.get(temp) != null)
                    {
                        long thingy = values.get(temp) + num;
                            
                        while (thingy > 65535)
                        {
                            thingy -= 65536;
                        }
                            
                        values.put(var, thingy);
                    }
                }
                else if (data.contains("LSHIFT"))
                {
                    String temp = dataScn.next();
                    
                    dataScn.next();
                    
                    long num = dataScn.nextInt();
                    
                    if (values.get(temp) != null)
                    {
                        long thingy = values.get(temp) - num;
                        
                        while (thingy < 0)
                        {
                            thingy += 65536;
                        }
                        
                        values.put(var, thingy);
                    }
                }
                else
                {
                    String temp = dataScn.next();
                    
                    Long test = null;
                    try
                    {
                        test = new Long(temp);
                    }
                    catch (Exception e){}
                    
                    if (test != null)
                    {
                        values.put(var,test);
                    }
                    else if (values.get(temp) != null)
                    {
                        values.put(var, values.get(temp));
                    }
                }
            }
        }
        
        System.out.println(values.get("a"));
    }
}
