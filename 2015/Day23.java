
/**
 * Write a description of class Day23 here.
 *
 * @author (your name)
 * @version (a version number or a date)
 */

import java.util.*;
import java.io.*;

public class Day23
{
    public static void main(String args[]) throws IOException
    {
        File file = new File("input.txt");
        Scanner scn = new Scanner(file);
        
        String[] data = new String[49];
        
        for (int i = 0; i < 49; i++)
        {
            data[i] = scn.nextLine();
        }
        
        
        int a = 1;
        int b = 0;
        
        int pos = 0; // start reading line 0;
        while (pos < 49)
        {
            String instruction = data[pos].substring(0, 3); System.out.println(data[pos]);
            
            if (instruction.equals("hlf"))
            {
                if (data[pos].charAt(4) == 'a')
                {
                    a /= 2;
                }
                else
                {
                    b /= 2;
                }
                
                pos++;
            }
            else if (instruction.equals("tpl"))
            {
                if (data[pos].charAt(4) == 'a')
                {
                    a *= 3;
                }
                else
                {
                    b *= 3;
                }
                
                pos++;
            }
            else if (instruction.equals("inc"))
            {
                if (data[pos].charAt(4) == 'a')
                {
                    a++;
                }
                else
                {
                    b++;
                }
                
                pos++;
            }
            else if (instruction.equals("jmp"))
            {
                pos += Integer.parseInt(data[pos].substring(4));
            }
            else if (instruction.equals("jie"))
            {
                if ((data[pos].charAt(4) == 'a' && a % 2 == 0) || (data[pos].charAt(4) == 'b' && b % 2 == 0))
                {
                    pos += Integer.parseInt(data[pos].substring(7));
                }
                else
                {
                    pos++;
                }
            }
            else if (instruction.equals("jio"))
            {
                if ((data[pos].charAt(4) == 'a' && a == 1) || (data[pos].charAt(4) == 'b' && b == 1))
                {
                    pos += Integer.parseInt(data[pos].substring(7));
                }
                else
                {
                    pos++;
                }
            }
        }
        
        System.out.println(b);
    }
}
