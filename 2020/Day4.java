
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
            
            passport += data + " ";
            
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
                    boolean valid = true;
                    
                    String byr = passport.substring(passport.indexOf("byr:") + 4);
                    byr = byr.substring(0, byr.indexOf(" "));
                    if (Integer.parseInt(byr) < 1920 || Integer.parseInt(byr) > 2002) valid = false;
                    
                    String iyr = passport.substring(passport.indexOf("iyr:") + 4);
                    iyr = iyr.substring(0, iyr.indexOf(" "));
                    if (Integer.parseInt(iyr) < 2010 || Integer.parseInt(iyr) > 2020) valid = false;
                    
                    String eyr = passport.substring(passport.indexOf("eyr:") + 4);
                    eyr = eyr.substring(0, eyr.indexOf(" "));
                    if (Integer.parseInt(eyr) < 2020 || Integer.parseInt(eyr) > 2030) valid = false;
                    
                    String hgt = passport.substring(passport.indexOf("hgt:") + 4);
                    hgt = hgt.substring(0, hgt.indexOf(" "));
                    String unit = hgt.substring(hgt.length() - 2);
                    if (unit.equals("cm"))
                    {
                        int magnitude = Integer.parseInt(hgt.substring(0, hgt.length() - 2));
                        if (magnitude < 150 || magnitude > 193) valid = false;
                    }
                    else if (unit.equals("in"))
                    {
                        int magnitude = Integer.parseInt(hgt.substring(0, hgt.length() - 2));
                        if (magnitude < 59 || magnitude > 76) valid = false;
                    }
                    else valid = false;
                    
                    String hcl = passport.substring(passport.indexOf("hcl:") + 4);
                    hcl = hcl.substring(0, hcl.indexOf(" "));
                    if (hcl.charAt(0) != '#' || hcl.length() != 7) valid = false;
                    else
                    { 
                        for (int i = 1; i < 7; i++)
                        {
                            if (!(hcl.charAt(i) >= '0' && hcl.charAt(i) <= '9') && !(hcl.charAt(i) >= 'a' && hcl.charAt(i) <= 'f'))
                                valid = false;
                        }
                    }
                    
                    String ecl = passport.substring(passport.indexOf("ecl:") + 4);
                    ecl = ecl.substring(0, ecl.indexOf(" "));
                    HashSet<String> validEyes = new HashSet(Set.of("amb","blu","brn","gry","grn","hzl","oth"));
                    if (validEyes.contains(ecl) == false) 
                    {
                        valid = false; 
                    }
                    
                    String pid = passport.substring(passport.indexOf("pid:") + 4);
                    pid = pid.substring(0, pid.indexOf(" "));
                    if(pid.length() != 9) valid = false;
                    else
                    {
                        for (int i = 0; i < 9; i++)
                        {
                            if (pid.charAt(i) < '0' || pid.charAt(i) > '9') valid = false;
                        }
                    }
                    
                    
                    if (valid) count++;
                }
                
                passport = "";
            }
        }
        
        System.out.println(count);
    }
}
