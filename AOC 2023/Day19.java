import java.util.*;
import java.io.*;

public class Day19
{
    public static HashMap<String, String[]> conditions = new HashMap();
    
    public static void main(String[] args) throws IOException
    {
        File file = new File("input.txt");
        Scanner scn = new Scanner(file);
        
        long answer = -1;
        
        
        int[][] part = {{1, 4000}, {1, 4000}, {1, 4000}, {1, 4000}};
        
        //ArrayList<int[]> parts = new ArrayList(); // x, m, a, s
        
        
        boolean scanningParts = false;
        while (scn.hasNextLine())
        {
            String data = scn.nextLine();
            if (data.equals(""))
            {
                scanningParts = true;
                continue;
            }
            
            if (!scanningParts)
            {
                String key = data.substring(0, data.indexOf("{"));
                
                data = data.substring(data.indexOf("{"));
                    
                data = data.replace("{", "");
                data = data.replace("}", "");
                
                conditions.put(key, data.split(","));
            }
        }
                 
        answer = solve(part, "in");
        
        System.out.println("Answer: " + answer);
    }
    
    public static long solve(int[][] part, String key) // part is a range (min, max) (1, 4000)
    {        
        if (key.equals("R"))
        {
            // System.out.println("R: " + 0);
            return 0;
        }
        else if (key.equals("A"))
        {
            long sum = 1;
            for (int[] arr : part)
            {
                int temp = arr[1] - arr[0] + 1;
                
                if (temp <= 0) temp = 0;

                sum *= temp;
            }
            
            // System.out.println("A: " + sum);
            return sum;
        }
        
        
        
        String[] condition = conditions.get(key);
        
        
        long res = 0;
        
        
        for (String con : condition)
        {
            int[][] partCopy = copyArr(part);
            
            if (con.contains(":") == false)
            {
                res += solve(partCopy, con);
                                
                continue;
            }
            
            
            String[] tempValues = spliter(con);
            
            int check1 = Integer.valueOf(tempValues[0]);
            int check2 = Integer.valueOf(tempValues[2]);
            if (tempValues[1].equals(">") && part[check1][1] > check2)
            {
                part[check1][1] = check2;
                
                partCopy[check1][0] = check2 + 1;
            }
            else if (tempValues[1].equals("<") && part[check1][0] < check2)
            {
                partCopy[check1][1] = check2 - 1;
                
                part[check1][0] = check2;
            }
            
            // System.out.println("con: " + con);
            res += solve(partCopy, tempValues[3]);
        }
        
        
        
        
        return res;
    }
    
    public static int[][] copyArr(int[][] arr)
    {
        int[][] copy = new int[arr.length][];
        
        for (int i = 0; i < copy.length; i++)
        {
            copy[i] = copyArr(arr[i]);
        }
        
        return copy;
    }
    public static int[] copyArr(int[] arr)
    {
        int[] copy = new int[arr.length];
        
        for (int i = 0; i < copy.length; i++)
        {
            copy[i] = arr[i];
        }
        
        return copy;
    }
    
    public static String[] spliter(String check)
    {
        String res = check.substring(check.indexOf(":") + 1);
        check = check.substring(0, check.indexOf(":"));
        
        String index = "";
        String op = "";
        String value = "";
        
        if (check.contains(">")) op = ">";
        else op = "<";
                
        index = check.substring(0, check.indexOf(op));
        if (index.equals("x")) index = "0";
        else if (index.equals("m")) index = "1";
        else if (index.equals("a")) index = "2";
        else if (index.equals("s")) index = "3";
        else System.out.println("Error");
        
        value = check.substring(check.indexOf(op) + 1);
        
        String[] ans = new String[4];
        ans[0] = index;
        ans[1] = op;
        ans[2] = value;
        ans[3] = res;
        
        return ans;
    }
}
