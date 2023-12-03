
/**
 * Write a description of class Day15 here.
 *
 * @author (your name)
 * @version (a version number or a date)
 */

import java.util.*;
import java.math.*;

public class Day15
{
    public static void main (String[] args)
    {
        BigInteger inpt1 = new BigInteger("679"); // NOTE: these two numbers are GIVEN as PUZZLE INPUT !!!!!
        BigInteger inpt2 = new BigInteger("771");
        
        BigInteger num1 = new BigInteger("0").add(inpt1);
        BigInteger num2 = new BigInteger("0").add(inpt2);
        
        BigInteger factor1 = new BigInteger("16807"); // should be the same for all puzzle inputs
        BigInteger factor2 = new BigInteger("48271");
        
        BigInteger remainder = new BigInteger("2147483647");
        BigInteger check = new BigInteger("65536");
        
        
        int count = 0;
        
        
        for (int step = 0; step < 40000000; step++)
        {
            num1 = num1.multiply(factor1);
            num1 = num1.remainder(remainder);
            
            num2 = num2.multiply(factor2);
            num2 = num2.remainder(remainder);
            
            if (num1.remainder(check).intValue() == num2.remainder(check).intValue())
            {
                count++;
            }
        }
        
        System.out.println("Part 1 answer: "  + count);
        System.out.println("Part 2 answer: "  + part2(inpt1, inpt2));
    }
    
    public static int part2(BigInteger inpt1, BigInteger inpt2)
    {
        BigInteger num1 = new BigInteger("0").add(inpt1);
        BigInteger num2 = new BigInteger("0").add(inpt2);
        
        BigInteger factor1 = new BigInteger("16807"); // should be the same for all puzzle inputs
        BigInteger factor2 = new BigInteger("48271");
        
        BigInteger remainder = new BigInteger("2147483647");
        BigInteger check = new BigInteger("65536");
        
        
        int count = 0;
        
        
        for (int step = 0; step < 5000000; step++)
        {
            num1 = num1.multiply(factor1);
            num1 = num1.remainder(remainder);
            while (num1.remainder(new BigInteger("4")).intValue() != 0)
            {
                num1 = num1.multiply(factor1);
                num1 = num1.remainder(remainder);
            }
            
            num2 = num2.multiply(factor2);
            num2 = num2.remainder(remainder);
            while (num2.remainder(new BigInteger("8")).intValue() != 0)
            {
                num2 = num2.multiply(factor2);
                num2 = num2.remainder(remainder);
            }
            
            if (num1.remainder(check).intValue() == num2.remainder(check).intValue())
            {
                count++;
            }
        }
        
        return count;
    }
}
