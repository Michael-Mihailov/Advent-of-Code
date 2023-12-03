
/**
 * Write a description of class Day4 here.
 *
 * @author (your name)
 * @version (a version number or a date)
 */

import java.util.*;
import java.io.*;

public class Day4 // MODIFIED INPUT: 1 extra line at the end
{
    public static void main(String args[]) throws IOException
    {
        File file = new File("input.txt");
        Scanner scn = new Scanner(file);
        
        
        int count = 0;
        String passport = "";
        while (scn.hasNextLine())
        {
            String data = scn.nextLine();
            
            passport += data;
            
            if (data.equals(""))
            {
                if (passport.contains("byr:") && 
                passport.contains("iyr:") &&
                passport.contains("eyr:") &&
                passport.contains("hgt:") &&
                passport.contains("hcl:") &&
                passport.contains("ecl:") &&
                passport.contains("pid:"))
                {
                    count++;
                }
                
                passport = "";
            }
        }
        
        System.out.println(count);
    }
}
