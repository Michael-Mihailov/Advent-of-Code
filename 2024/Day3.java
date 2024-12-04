import java.util.*;
import java.io.*;

public class Day3
{
    public static void main(String[] args) throws IOException
    {
        File file = new File("input.txt");
        Scanner scn = new Scanner(file);
        
        int ans = 0;
        
        String data = "";
        boolean enabled = true;
        
        while (scn.hasNextLine())
        {
            data += scn.nextLine();
        }
        
        /*
        String newData = "";
        Scanner doScan = new Scanner(data);
        doScan.useDelimiter("don't()");
        while (doScan.hasNext())
        {
            String temp = doScan.next();
            
            
            if (enabled)
            {
                newData += temp;
                enabled = false;
                
                doScan.useDelimiter("do()");
            }
            else
            {
                enabled = true;

                doScan.useDelimiter("don't()");
            }
        }
        data = newData;
        */
        /*
        String newData = "";
        while (data.indexOf(((enabled) ? "don't()" : "do()")) != -1)
        {
            String temp = data.substring(0, data.indexOf((enabled) ? "don't()" : "do()"));
            System.out.println(data.indexOf(((enabled) ? "don't()" : "do()")));
            if (enabled)
            {
                newData += temp;
                enabled = false;
            }
            else
            {
                enabled = true;
            }
            
            data = data.substring(data.indexOf((enabled) ? "don't()" : "do()") + 1);
        }
        data = newData;
        */
        
       
        String newData = "";
        for (int i = 0; i < data.length(); i++)
        {
            if (data.substring(i).indexOf("don't()") == 0)
            {
                enabled = false;
            }
            else if (data.substring(i).indexOf("do()") == 0)
            {
                enabled = true;
            }
            
            if (enabled)
            {
                newData += data.charAt(i);
            }
        }
        data = newData;
        
        Scanner mulScan = new Scanner(data);
        mulScan.useDelimiter("mul");
        
        mulScan.next();
        while (mulScan.hasNext())
        {
            String test = mulScan.next();
            
            if (test.charAt(0) == '(')
            {
                test = test.substring(1);
                if (test.indexOf(')') != -1)
                {
                    test = test.substring(0, test.indexOf(')'));
                    boolean valid = true;
                    
                    for (int i = 0; i < test.length(); i++)
                    {
                        if ((test.charAt(i) >= 48 && test.charAt(i) <= 57) == false)
                        {
                            if (test.charAt(i) != ',')
                                valid = false;
                        }
                    }
                    
                    if (valid)
                    {
                        String[] testArr = test.split(",");
                        
                        if (testArr.length == 2)
                        {
                            ans += Integer.parseInt(testArr[0]) * Integer.parseInt(testArr[1]);
                        }
                    }
                }
            }
        }
        
        
        System.out.println(ans);
    }
}