
/**
 * Write a description of class Day21 here.
 *
 * @author (your name)
 * @version (a version number or a date)
 */

import java.io.*;
import java.util.*;

public class Day21
{
    public static void main (String[] args) throws IOException
    {
        File input = new File("input.txt");
        Scanner scn = new Scanner(input);
        
        HashMap<String, String> enhanceMap = new HashMap();
        
        char[][] charMap = new char[3][3];
        
        set(charMap, 3, 0, 0, ".#./..#/###"); // the starting seed for all inputs
        
        while (scn.hasNext()) // read input data
        {
            String data = scn.nextLine();
            Scanner dataScn = new Scanner(data);
            dataScn.useDelimiter(" => ");
            
            String key = dataScn.next();
            String value = dataScn.next();
            
            enhanceMap.put(key, value);
        }
        
        // have step = 5 for PART 1 !!!!! and step = 18 for PART 2 !!!!!
        for (int step = 0; step < 18; step++) // the number of enhancement iterations to perform
        {
            System.out.println("step: " + (step + 1));
            
            char[][] charMapNew;
            int size;
            int width;
            if (charMap.length % 2 == 0)
            {
                charMapNew = new char[(charMap.length / 2) * 3][(charMap.length / 2) * 3];
                size = 2;
            }
            else
            {
                charMapNew = new char[(charMap.length / 3) * 4][(charMap.length / 3) * 4];
                size = 3;
            }
            width = charMap.length / size;

            
            for (int yPos = 0; yPos < width; yPos++)
            {
                for (int xPos = 0; xPos < width; xPos++)
                {
                    String key = get(charMap, size, xPos, yPos);
                    
                    boolean found = false;
                    while (!found) // rotates and flips until the mapping is found
                    {
                        if (enhanceMap.containsKey(key))
                        {
                            found = true;
                        }
                        else
                        {
                            if (Math.random() > 0.5) // Because I am too lazy to check systematicaly, I use math.Random()
                            {
                                key = rotate(key);
                            }
                            else
                            {
                                key = flip(key);;
                            }
                        }
                    }
                    
                    String output = enhanceMap.get(key);
                    if (output.length() > 11)
                    {
                        String[] outputs = split(output);
                        
                        set(charMapNew, 2, xPos * 2, yPos * 2, outputs[0]);
                        set(charMapNew, 2, xPos * 2 + 1, yPos * 2, outputs[1]);
                        set(charMapNew, 2, xPos * 2, yPos * 2 + 1, outputs[2]);
                        set(charMapNew, 2, xPos * 2 + 1, yPos * 2 + 1, outputs[3]);
                    }
                    else
                    {
                        set(charMapNew, 3, xPos, yPos, output);
                    }
                }
            }
            charMap = charMapNew;
        }
        
        
        
        int count = 0;
        for (int y = 0; y < charMap.length; y++)
        {
            for (int x = 0; x < charMap.length; x++)
            {
                if (charMap[x][y] == '#')
                {
                    count++;
                }
            }
        }
        
        System.out.println(count);
    }
    
    
    public static String get(char[][] map, int size, int xPos, int yPos)
    {
        String answer = "";
        
        for(int y = 0; y < map.length; y++)
        {
            if (y >= (yPos * size) && y < ((yPos + 1) * size))
            {
                for(int x = 0; x < map.length; x++)
                {
                    if (x >= (xPos * size) && x < ((xPos + 1) * size))
                    {
                        answer = answer + map[x][y];
                    }
                }
                answer = answer + "/";
            }
        }
        
        
        answer = answer.substring(0, answer.length() - 1);
        
        return answer;
    }
    public static void set(char[][] map, int size, int xPos, int yPos, String value)
    {
        value = value.replace("/", "");
        
        for(int y = 0; y < map.length; y++)
        {
            if (y >= (yPos * size) && y < ((yPos + 1) * size))
            {
                for(int x = 0; x < map.length; x++)
                {
                    if (x >= (xPos * size) && x < ((xPos + 1) * size))
                    {
                        map[x][y] = value.charAt(0);
                        value = value.substring(1);
                    }
                }
            }
        }
    }
    
    public static String[] split (String base) // splits a (4 by 4) into 4 (2 by 2) squares 
    {
        Scanner scn = new Scanner(base);
        scn.useDelimiter("/");
        
        String r0 = scn.next();
        String r1 = scn.next();
        String r2 = scn.next();
        String r3 = scn.next();

        String[] answers = new String[4];
        
        answers[0] = r0.substring(0, 2) + "/" + r1.substring(0, 2);
        answers[1] = r0.substring(2) + "/" + r1.substring(2);
        answers[2] = r2.substring(0, 2) + "/" + r3.substring(0, 2);
        answers[3] = r2.substring(2) + "/" + r3.substring(2);
        
        return answers;
    }
    
    public static String flip (String base) // flip over y axis
    {
        Scanner scn = new Scanner(base);
        scn.useDelimiter("/");
        
        String answer = "";
        
        while (scn.hasNext())
        {
            answer = scn.next() + "/" + answer;
        }
        answer = answer.substring(0, answer.length() - 1);
        
        return answer;
    }
    
    public static String rotate (String base) // rotate 90 degrees counterclockwise
    {
        Scanner scn = new Scanner(base);
        scn.useDelimiter("/");
        
        String answer = "";
        
        ArrayList<String> parts = new ArrayList();
        
        while (scn.hasNext())
        {
            parts.add(scn.next());
        }
        
        
        for (int i = parts.size() - 1; i >= 0; i--)
        {
            for (int p = 0; p < parts.size(); p++)
            {
                answer = answer + parts.get(p).charAt(i);
            }
            answer = answer + "/";
        }
        
        answer = answer.substring(0, answer.length() - 1);
        
        return answer;
    }
}
