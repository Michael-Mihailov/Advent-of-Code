
import java.io.*;
import java.util.*;

public class Day20
{
    public static void main (String[] args) throws Exception
    {
        File file = new File("input.txt");
        Scanner scn = new Scanner(file);
        
        Long[] def = new Long[5000];
        Long[] order = new Long[5000];
        Long[] list = new Long[5000];
        
        Integer[] compression = new Integer[5000];
        
        for (int i = 0; i < 5000; i++)
        {
            long next = scn.nextInt();
            Long num = new Long(next * 811589153);
            
            //compression[i] = new Long((next * 811589153) / 5000);
            
            if (num == 0)
            {
                System.out.println("Index: " + i);
            }
            
            def[i] = new Long((next * 811589153) % 4999);
            order[i] = num;
            list[i] = num;
        }
        
        for (int round = 0; round < 10; round++)
        {
            for (int i = 0; i < 5000; i++) // only mix encryption once, as per instructions
            {
                int index = find(list, order[i]);
                
                for (int moves = 0; moves < Math.abs(def[i]); moves++)
                {                
                    if (order[i].longValue() < 0)
                    {
                        if (index == 0)
                        {
                            list[index] = list[4999];
                            index = 4999;
                            list[index] = order[i];
                        }
                        else
                        {
                            list[index] = list[index - 1];
                            index--;
                            list[index] = order[i];
                        }
                    }
                    else if (order[i].longValue() > 0)
                    {
                        if (index == 4999)
                        {
                            list[index] = list[0];
                            index = 0;
                            list[index] = order[i];
                        }
                        else
                        {
                            list[index] = list[index + 1];
                            index++;
                            list[index] = order[i];
                        }
                    }
                    else
                    {
                        System.out.println("Equals Zero");
                    }
                }
            }
        }
        
        int x = find(list, order[4071]);
        System.out.println(list[find(list, order[4071])]);
        System.out.println("Pos: " + x);
        /*
        System.out.println();
        for (int i = 0; i < 7; i++)
        {
            //System.out.print(def[find(order, list[i])] * 811589153 + " ");
            System.out.print(list[i] + " ");
        }
        System.out.println(); System.out.println();
        */
        //System.out.println(list[(4595 + 1000)%5000].intValue() + list[(4595 + 2000)%5000].intValue() + list[(4595 + 3000)%5000].intValue());
        //System.out.println((def[find(order, list[(2 + 1000)%7])]  * 811589153 + " " + def[find(order, list[(2 + 2000)%7])]  * 811589153 + " " + def[find(order, list[(2 + 3000)%7])]  * 811589153) );
        System.out.println(list[(4746 + 1000)%5000] + " " + list[(4746 + 2000)%5000] + " " + list[(4746 + 3000)%5000]);
        System.out.println(list[(4746 + 1000)%5000] + list[(4746 + 2000)%5000] + list[(4746 + 3000)%5000]);
        /*
        System.out.println();
        System.out.println((long) list[find(order, list[(4893 + 1000)%5000])] + (compression[find(order, list[(4893 + 1000)%5000])] * 5000));
        System.out.println((long) list[find(order, list[(4893 + 2000)%5000])] + (compression[find(order, list[(4893 + 2000)%5000])] * 5000));
        System.out.println((long) list[find(order, list[(4893 + 3000)%5000])] + (compression[find(order, list[(4893 + 3000)%5000])] * 5000));
        System.out.println();
        System.out.println((long) 203569091 + 1544377133 - 143061497);
        */
    }
    
    static int find(Long[] list, Long num)
    {
        for (int i = 0; i < 5000; i++)
        {
            if (list[i] == num)
            {
                return i;
            }
        }
        
        System.out.println("uh oh");
        return 0;
    }
}
