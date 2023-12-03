
/**
 * Write a description of class Day16 here.
 *
 * @author (your name)
 * @version (a version number or a date)
 */

import java.io.*;
import java.util.*;

public class Day16
{
    public static void main (String[] args) throws IOException
    {
        File input = new File("input.txt");
        Scanner scn = new Scanner(input);
        scn.useDelimiter(",");
        
        //hmefajngplkidocb
        char[] dance = new char[16];
        dance[0] = 'a';
        dance[1] = 'b';
        dance[2] = 'c';
        dance[3] = 'd';
        dance[4] = 'e';
        dance[5] = 'f';
        dance[6] = 'g';
        dance[7] = 'h';
        dance[8] = 'i';
        dance[9] = 'j';
        dance[10] = 'k';
        dance[11] = 'l';
        dance[12] = 'm';
        dance[13] = 'n';
        dance[14] = 'o';
        dance[15] = 'p';
        
        while (scn.hasNext())
        {
            String data = scn.next();
            
            char type = data.charAt(0);
            
            data = data.substring(1);
            Scanner dataScn = new Scanner(data);
            dataScn.useDelimiter("/");
            
            char[] copy = new char[16];
            if (type == 's') // swap
            {
                int shift = dataScn.nextInt();
                
                for (int i = 0; i < dance.length; i++)
                {
                    copy[i] = dance[(i + 16 - shift) % 16];
                }
            }
            else if (type == 'x') // exchange
            {
                int p1 = dataScn.nextInt();
                int p2 = dataScn.nextInt();
                
                for (int i = 0; i < dance.length; i++)
                {
                    copy[i] = dance[i];
                }
                copy[p1] = dance[p2];
                copy[p2] = dance[p1];
            }
            else if (type == 'p') // partner
            {
                char n1 = dataScn.next().charAt(0);
                char n2 = dataScn.next().charAt(0);
                
                for (int i = 0; i < dance.length; i++)
                {
                    if (dance[i] == n1) copy[i] = n2;
                    else if (dance[i] == n2) copy[i] = n1;
                    else copy[i] = dance[i];
                }
            }
            else
            {
                System.out.println("this should not print");
            }
            
            
            for (int i = 0; i < dance.length; i++)
            {
                dance[i] = copy[i];
            }
        }
        
        System.out.println("Part 1 answer: ");
        for (int i = 0; i < dance.length; i++)
        {
            System.out.print(dance[i]);
        }
        
        
        
        // PART 2 BEGINS HERE !!!!!
        
        // NOTE !!!!!!! FOR PART 2: take the index shift that occers for each dance (e.g. abcde --> dbcea has a shift of 4,0,0,-3,-1)
        // Apply this shift every time instead of doing the dance over and over again.
        // NOTE 2: because the swap by char value Occers an even number of times, it can be disregarded. Apply Note 1 afterwards.
        
        System.out.println();
        part2();
    }
    
    public static void part2() throws IOException // DOES NOT WORK FOR SOME REASON ?!?!?!?!?!?!?!?!?!?!?!?!?!?!?!
    {
        File input = new File("input.txt");
        Scanner scn = new Scanner(input);
        scn.useDelimiter(",");
        
        
        char[] dance = new char[16];
        dance[0] = 'a';
        dance[1] = 'b';
        dance[2] = 'c';
        dance[3] = 'd';
        dance[4] = 'e';
        dance[5] = 'f';
        dance[6] = 'g';
        dance[7] = 'h';
        dance[8] = 'i';
        dance[9] = 'j';
        dance[10] = 'k';
        dance[11] = 'l';
        dance[12] = 'm';
        dance[13] = 'n';
        dance[14] = 'o';
        dance[15] = 'p';
        
        
        char[] OGorder = dance.clone(); // for PART 2
        char[] megaDance = dance.clone(); // for PART 2
        char[] exchangeTemplate = dance.clone(); // for PART 2
        
        while (scn.hasNext())
        {
            String data = scn.next();
            
            char type = data.charAt(0);
            
            data = data.substring(1);
            Scanner dataScn = new Scanner(data);
            dataScn.useDelimiter("/");
            
            char[] copy = new char[16];
            if (type == 's') // swap
            {
                int shift = dataScn.nextInt();
                
                for (int i = 0; i < dance.length; i++)
                {
                    copy[i] = dance[(i + 16 - shift) % 16];
                }
            }
            else if (type == 'x') // exchange
            {
                int p1 = dataScn.nextInt();
                int p2 = dataScn.nextInt();
                
                for (int i = 0; i < dance.length; i++)
                {
                    copy[i] = dance[i];
                }
                copy[p1] = dance[p2];
                copy[p2] = dance[p1];
            }
            else if (type == 'p') // partner
            {
                char n1 = dataScn.next().charAt(0);
                char n2 = dataScn.next().charAt(0);
                
                char[] copy2 = new char[16];
                
                for (int i = 0; i < dance.length; i++)
                {
                    if (exchangeTemplate[i] == n1) copy2[i] = n2;
                    else if (exchangeTemplate[i] == n2) copy2[i] = n1;
                    else copy2[i] = exchangeTemplate[i];
                }
                for (int i = 0; i < dance.length; i++)
                {
                    exchangeTemplate[i] = copy2[i];
                }
                
                
                
                // do nothing to da other thing
                for (int i = 0; i < dance.length; i++)
                {
                    copy[i] = dance[i];
                }
            }
            else
            {
                System.out.println("this should not print");
            }
            
            
            for (int i = 0; i < dance.length; i++)
            {
                dance[i] = copy[i];
            }
        }
        
        
        
        // PART 2 BEGINS HERE !!!!!
        
        // NOTE !!!!!!! FOR PART 2: take the index shift that occers for each dance (e.g. abcde --> dbcea has a shift of 4,0,0,-3,-1)
        // Apply this shift every time instead of doing the dance over and over again.
        // NOTE 2: because the swap by char value Occers an even number of times, it can be disregarded. Apply Note 1 afterwards.
        
        
        int[] shifts = new int[16];
        for (int i = 0; i < dance.length; i++)
        {
            char target = megaDance[i];
            for (int s = 0; s < dance.length; s++)
            {
                if (dance[s] == target)
                {
                    shifts[i] = s;
                }
            }
        }
        
        
        for (int step = 0; step < 1000000000; step++)
        {
            char[] copy = new char[16];
            for (int i = 0; i < megaDance.length; i++)
            {
                copy[shifts[i]] = megaDance[i];
            }
            
            megaDance = copy;
            
            
            
            char[] copy2 = new char[16];
            for (int i = 0; i < megaDance.length; i++)
            {
                for (int s = 0; s < OGorder.length; s++)
                {
                    if (megaDance[i] == OGorder[s])
                    {
                        copy2[i] = exchangeTemplate[s];
                    }
                }
            }
            
            megaDance = copy2;
        }
        
        
        
            

        
        
        
        
        
        System.out.println("Part 2 answer: ");
        for (int i = 0; i < megaDance.length; i++)
        {
            System.out.print(megaDance[i]);
        }
    }
}
