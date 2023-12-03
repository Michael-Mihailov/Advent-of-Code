
/**
 * Write a description of class CopyOfDay_Default here.
 *
 * @author (your name)
 * @version (a version number or a date)
 */

import java.util.*;
import java.io.*;
import java.math.*;

import java.security.*;

public class Day4 // stole some code from geeks for geeks
{
    public static void main(String args[]) throws NoSuchAlgorithmException 
    {
        String data = "ckczppom";
        
        int count = 0;
        
        String hashtext = "111111";
        while (hashtext.substring(0, 6).equals("000000") == false)
        {
            count++;
            
            MessageDigest md = MessageDigest.getInstance("MD5");
            
            byte[] messageDigest = md.digest((data + count).getBytes());
            BigInteger no = new BigInteger(1, messageDigest);
            hashtext = no.toString(16);
            while (hashtext.length() < 32) 
            {
                hashtext = "0" + hashtext;
            }
        }
        
        System.out.println(count);
    }
}
