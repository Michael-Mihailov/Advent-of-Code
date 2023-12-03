
/**
 * Write a description of class Day1 here.
 *
 * @author (your name)
 * @version (a version number or a date)
 */

import java.io.*;
import java.util.*;

public class Day5
{
    public static void main (String[] args) throws Exception
    {
        Scanner scn = new Scanner(new File("input.txt"));
        
        ArrayList<String>[] crates = new ArrayList[9];
        
        ArrayList<String> a0 = new ArrayList(Arrays.asList("s","l","w"));
        ArrayList<String> a1 = new ArrayList(Arrays.asList("j","t","n","q"));
        ArrayList<String> a2 = new ArrayList(Arrays.asList("s","c","h","f","j"));
        ArrayList<String> a3 = new ArrayList(Arrays.asList("t","r","m","w","n","g","b"));
        ArrayList<String> a4 = new ArrayList(Arrays.asList("t","r","l","s","d","h","q","b"));
        ArrayList<String> a5 = new ArrayList(Arrays.asList("m","j","b","v","f","h","r","l"));
        ArrayList<String> a6 = new ArrayList(Arrays.asList("d","w","r","n","j","m"));
        ArrayList<String> a7 = new ArrayList(Arrays.asList("b","z","t","f","h","n","d","j"));
        ArrayList<String> a8 = new ArrayList(Arrays.asList("h","l","q","n","b","f","t"));
        
        crates[0] = a0;
        crates[1] = a1;
        crates[2] = a2;
        crates[3] = a3;
        crates[4] = a4;
        crates[5] = a5;
        crates[6] = a6;
        crates[7] = a7;
        crates[8] = a8;
        
        while (scn.hasNext())
        {
            String data = scn.nextLine();
            Scanner dataScn = new Scanner(data);
            dataScn.next();
            
            int amount = dataScn.nextInt();
            
            dataScn.next();
            
            int p1 = dataScn.nextInt() - 1;
            
            dataScn.next();
            
            int p2 = dataScn.nextInt() - 1;
            
            for (int i = amount - 1; i >= 0; i--)
            {
                if (crates[p1].size() != 0)
                {
                    crates[p2].add(crates[p1].get(crates[p1].size() - 1 - i));
                    crates[p1].remove(crates[p1].size() - 1 - i);
                }
                else
                {
                    System.out.println("zero!");
                }
            }
        }
        
        System.out.println(crates[0].get(crates[0].size() - 1));
        System.out.println(crates[1].get(crates[1].size() - 1));
        System.out.println(crates[2].get(crates[2].size() - 1));
        System.out.println(crates[3].get(crates[3].size() - 1));
        System.out.println(crates[4].get(crates[4].size() - 1));
        System.out.println(crates[5].get(crates[5].size() - 1));
        System.out.println(crates[6].get(crates[6].size() - 1));
        System.out.println(crates[7].get(crates[7].size() - 1));
        System.out.println(crates[8].get(crates[8].size() - 1));
    }
}
