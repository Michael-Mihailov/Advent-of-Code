import java.util.*;
import java.io.*;

public class Day11
{
    public static void main(String args[]) throws IOException
    {
        File file = new File("input.txt");
        Scanner scn = new Scanner(file);
        
        String ogPassword = scn.nextLine();
        
        String currentPassword = ogPassword;
        while (isValid(currentPassword) == false)
        {
            currentPassword = increment(currentPassword);    
        }
        
        System.out.println("Part-1 Answer: " + currentPassword);
        
        currentPassword = increment(currentPassword);
        while (isValid(currentPassword) == false)
        {
            currentPassword = increment(currentPassword);    
        }
        
        System.out.println("Part-2 Answer: " + currentPassword);
    }
    
    public static String increment(String password)
    {        
        boolean carry = true;
        for (int i = password.length() - 1; i >= 0 && carry; i--)
        {
            carry = false;
            if (password.charAt(i) == 'z')
            {
                password = password.substring(0, i) + 'a' + password.substring(i + 1);
                carry = true;
            }
            else
            {
                password = password.substring(0, i) + ((char)(password.charAt(i) + 1)) + password.substring(i + 1);
            }
        }
        return password;
    }
    
    public static boolean isValid (String password)
    {
        if (password.contains("i") || password.contains("o") || password.contains("l"))
        {
            return false;
        }
        
        int straight = 1;
        for (int i = 1; i < password.length(); i++)
        {
            if (password.charAt(i) - password.charAt(i-1) == 1)
            {
                straight++;
                if (straight == 3) break;
            }
            else
            {
                straight = 1;
            }
        }
        if (straight != 3) return false;
        
        int numPairs = 0;
        for (int i = 1; i < password.length(); i++)
        {
            if (password.charAt(i) == password.charAt(i-1))
            {
                numPairs++;
                i++;
            }
        }
        if (numPairs < 2) return false;
        
        return true;
    }
}