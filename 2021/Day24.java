
import java.io.*;
import java.util.*;

public class Day24 
{
    public static void main (String args[]) throws Exception
    {
        int answer = -1;
        int[] inputs = new int[14];
        for (int i = 0; i < 14; i++) inputs[i] = 9;
        
        ArrayList<String> dataList = new ArrayList();
        File file = new File("input.txt");
        Scanner scn = new Scanner(file);
        while (scn.hasNextLine()) dataList.add(scn.nextLine());
        
        
        check(inputs, dataList);
        
        for (int i = 0; i < 14; i++) System.out.print(inputs[i]);
    }
    
    public static int[] increment(int[] inputs)
    {
        int index = 13;
                
        boolean go = true;
        while (go)
        {
            if (inputs[index] > 1)
            {
                inputs[index] = inputs[index] - 1;
                go = false;
            }
            else
            {
                inputs[index] = 9;
                index--;
            }
        }
        
        
        if (inputs[13] + inputs[12] + inputs[11] + inputs[10] == 4)
        {
            for (int i = 0; i < 14; i++) System.out.print(inputs[i]);
            System.out.println();
        }
        
        return inputs;
    }
    
    public static int check(int[] inputs, ArrayList<String> dataList) throws Exception
    {
        File file = new File("input.txt");
        Scanner scn = new Scanner(file);
        
        int index = 0; // index for "inputs"
        int w = 0;
        int x = 0;
        int y = 0;
        int z = 0;
        
        while (scn.hasNextLine())
        {
            //System.out.println(w + " " + x + " " + y + " " + z);
            
            String data = scn.nextLine();
            Scanner splitScn = new Scanner(data);
            
            String type = splitScn.next();
            String v1 = splitScn.next();
            
            if (type.equals("inp"))
            {
                int temp = inputs[index];
                index++;
                
                if (v1.equals("w")) w = temp;
                else if (v1.equals("x")) x = temp;
                else if (v1.equals("y")) y = temp;
                else if (v1.equals("z")) z = temp;
            }
            else
            {
                String v2 = splitScn.next();
                
                int temp;
                if (v2.equals("w")) temp = w;
                else if (v2.equals("x")) temp = x;
                else if (v2.equals("y")) temp = y;
                else if (v2.equals("z")) temp = z;
                else temp = Integer.parseInt(v2);
                
                //System.out.println(temp);
                
                if (type.equals("add"))
                {
                    if (v1.equals("w")) w += temp;
                    else if (v1.equals("x")) x += temp;
                    else if (v1.equals("y")) y += temp;
                    else if (v1.equals("z")) z += temp;
                }
                else if (type.equals("mul"))
                {
                    if (v1.equals("w")) w *= temp;
                    else if (v1.equals("x")) x *= temp;
                    else if (v1.equals("y")) y *= temp;
                    else if (v1.equals("z")) z *= temp;
                }
                else if (type.equals("div"))
                {
                    if (v1.equals("w")) w /= temp;
                    else if (v1.equals("x")) x /= temp;
                    else if (v1.equals("y")) y /= temp;
                    else if (v1.equals("z")) z /= temp;
                }
                else if (type.equals("mod"))
                {
                    if (v1.equals("w")) w %= temp;
                    else if (v1.equals("x")) x %= temp;
                    else if (v1.equals("y")) y %= temp;
                    else if (v1.equals("z")) z %= temp;
                }
                else if (type.equals("eql"))
                {
                    if (v1.equals("w")) if (w == temp) w = 1; else w = 0;
                    else if (v1.equals("x")) if (x == temp) x = 1; else x = 0;
                    else if (v1.equals("y")) if (y == temp) y = 1; else y = 0;
                    else if (v1.equals("z")) if (z == temp) z = 1; else z = 0;
                }
            }
        }
        
        //System.out.println(w + " " + x + " " + y + " " + z);
        
        return z;
    }
}
