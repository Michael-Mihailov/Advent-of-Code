import java.util.*;
import java.io.*;

public class Day17
{
    /*
     * NOTE: I kinda broke my part-1 code while trying to do part-2
     */
    public static void main(String[] args) throws IOException
    {
        File file = new File("input.txt");
        Scanner scn = new Scanner(file);
        
        //long ans = 0;
        
        String instructionString = "2,4,1,1,7,5,1,5,4,0,5,5,0,3,3,0";
            
        String[] stringInstructions = instructionString.split(",");
        int[] instructions = new int[stringInstructions.length];
        for (int i = 0; i < instructions.length; i++)
        {
            instructions[i] = Integer.parseInt(stringInstructions[i]);
        }
        
        String tempStr = "";
        for (int i = 0; i < instructions.length; i++) // last three digits get cut off per output
        {
            tempStr += "XXX";
        }
        System.out.println(tempStr);
        String[] aValueString = tempStr.split("|");
        
        System.out.println(aValueString.length + ", " + (instructions.length - 1));
        aValueString = fillOut(aValueString, instructions.length - 1, instructions);
        
        tempStr = "";
        for (int i = 0; i < aValueString.length; i++)
        {
            tempStr = aValueString[i] + tempStr;
        }
        
        System.out.println(tempStr);
        System.out.println("Answer: " + Long.parseLong(tempStr, 2));
    }
    
    public static String[] fillOut (String[] aValue, int goalIndex, int[] instructions)
    {
        if (goalIndex < 0) return aValue;
        
        int checkPos = (goalIndex * 3);
        int goalOutput = instructions[goalIndex];
        
        for (int val = 0; val < 8; val++)
        {
            if (val == 0 && checkPos == aValue.length - 3) continue; 
            
            String testString = toBinaryString(val); // the octate we are testing
            while (testString.length() < 3)
            {
                testString = "0" + testString;
            }
            
            // 2,4
            String[] bValue = new String[3];
            for (int i = 0; i < 3; i++)
            {
                bValue[i] = testString.charAt(2 - i) + "";
            }
            
            // 1,1     (1 == 001)
            if (bValue[0].equals("0")) bValue[0] = "1";
            else if (bValue[0].equals("1")) bValue[0] = "0";
            
            // 7,5
            String[] cValue = new String[3];
            int currentB = Integer.parseInt(bValue[2] + bValue[1] + bValue[0], 2);
            int cOffset = currentB;
            for (int i = 0; i < 3; i++)
            {
                int totalOffset = cOffset + i;
                String temp = "";
                if (totalOffset == 0) temp = testString.charAt(2) + "";
                else if (totalOffset == 1) temp = testString.charAt(1) + "";
                else if (totalOffset == 2) temp = testString.charAt(0) + "";
                else if (totalOffset + checkPos >= aValue.length)
                {
                    temp = "0";
                }
                else
                {
                    temp = aValue[totalOffset + checkPos];
                }
                cValue[i] = temp;
            }
            
            // 1,5
            for (int i = 0; i < 3; i+=2) // 5 == 101
            {
                if (bValue[i].equals("0")) bValue[i] = "1";
                else if (bValue[i].equals("1")) bValue[i] = "0";
            }
            
            // 4,0
            for (int i = 0; i < 3; i++)
            {
                if (bValue[i].equals(cValue[i])) bValue[i] = "0";
                else bValue[i] = "1";
            }
            
            // 5,5
            currentB = Integer.parseInt(bValue[2] + bValue[1] + bValue[0], 2);
            if (currentB == goalOutput)
            {
                String[] tempAValue = new String[aValue.length];
                for (int i = 0; i < aValue.length; i++)
                {
                    tempAValue[i] = aValue[i];
                }
                tempAValue[checkPos + 2] = testString.charAt(0) + "";
                tempAValue[checkPos + 1] = testString.charAt(1) + "";
                tempAValue[checkPos] = testString.charAt(2) + "";
                
                String[] temp = fillOut(tempAValue, goalIndex - 1, instructions); 
                
                if (temp != null) return temp;
            }
            
            // 0,3
            //      ignore
            
            // 3,0
            //      ignore
        }
        
        return null;
    }
    
    public static String toBinaryString (long num)
    {
        String ans = "";
        while (num > 0)
        {
            ans = (num % 2) + ans;
            num /= 2;
        }
        
        if (ans.length() == 0) return "0";
        return ans;
    }
    
    public static int instructionScore (long aValue, int[] instructions, String[] stringInstructions)
    {        
        int currentInstMatchIndex = 0;
                
        int pointer = 0;
        
        long[] registers = new long[]{aValue, 0, 0}; // A,B,C registers
        
        
        while (pointer < instructions.length - 1)
        {
            int opcode = instructions[pointer];
            int operand = instructions[pointer + 1];
            
            if (opcode == 0)
            {
                registers[0] = registers[0] / (long)Math.pow(2, combo(operand, registers));
            }
            else if (opcode == 1)
            {
                registers[1] = registers[1] ^ operand;
            }
            else if (opcode == 2)
            {
                registers[1] = combo(operand, registers) % 8;
            }
            else if (opcode == 3)
            {
                if (registers[0] != 0)
                {
                    pointer = operand - 2;
                }
            }
            else if (opcode == 4)
            {
                registers[1] = registers[1] ^ registers[2];
            }
            else if (opcode == 5)
            {
                if (currentInstMatchIndex >= stringInstructions.length || (stringInstructions[currentInstMatchIndex].charAt(0) - '0' != combo(operand, registers) % 8))
                {
                    pointer = 999;
                }
                else
                {
                    currentInstMatchIndex++;
                }
            }
            else if (opcode == 6)
            {
                registers[1] = registers[0] / (long)Math.pow(2, combo(operand, registers));
            }
            else if (opcode == 7)
            {
                registers[2] = registers[0] / (long)Math.pow(2, combo(operand, registers));
            }
            
            pointer += 2;
        }
        
        return currentInstMatchIndex;
    }
    
    public static long combo (int operand, long[] registers)
    {
        if (operand <= 3) return operand;
        if (operand <= 6) return registers[operand-4];
        
        System.out.println("Error 1");
        return -1;
    }
}