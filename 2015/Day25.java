
/**
 * Write a description of class Day25 here.
 *
 * @author (your name)
 * @version (a version number or a date)
 */

import java.util.*;
import java.io.*;

public class Day25
{
    public static void main(String args[]) throws IOException
    {
        int row = 2947;
        int col = 3029;
        
        
        long repititions = (((row + col - 1) * (row + col)) / 2) - (row - 1);
        
        System.out.println(repititions);
        
        long seed = 20151125;
        long mul = 252533;
        long div = 33554393;
        
        int rep = 1;
        
        
        while (rep < repititions)
        {
            rep++;
            
            seed = (seed * mul) % 33554393;
        }
        
        System.out.println(seed);
    }
}
