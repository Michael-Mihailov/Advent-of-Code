import java.util.*;
import java.io.*;

public class Day12
{    
    public static void main(String[] args) throws IOException
    {
        new Day12();
    }
    
    public Day12() throws IOException
    {
        File file = new File("input.txt");
        Scanner scn = new Scanner(file);
        
        long answer = 0;
        
        while (scn.hasNextLine())
        {
            String ogRow = scn.next();
            String ogSizes = scn.next();
            
            String row = ogRow;
            String sizesString = ogSizes;
            
            for (int i = 1; i <= 4; i++)
            {
                row += "?" + ogRow;
                sizesString += "," + ogSizes;
            }
            
            String[] sizesStringArray = sizesString.split(",");
            int[] sizes = new int[sizesStringArray.length];
            for (int i = 0; i < sizesStringArray.length; i++) sizes[i] = Integer.parseInt(sizesStringArray[i]);
            
            answer += numCombinations(row, sizes);
        }
        
        System.out.println("Answer: " + answer);
    }
    
    
    
    public long numCombinations(String row, int[] sizes)
    {
        long combinations = 0;
        
        
        row = row + "x";
        
        HashMap<key, Long> valueMap = new HashMap(); // key: [sizes-index, num] ;; value: occerences
        valueMap.put(new key(), new Long(1)); // the seed
        
        
        for (int i = 0; i < row.length(); i++)
        {
            if (row.charAt(i) == 'x') // end of row
            {
                Set<key> valueArray = valueMap.keySet();
                HashMap<key, Long> tempValueMap = new HashMap();
                
                for(key k : valueArray)
                {
                    key tempKey = null;
                    
                    if (k.getIndex() == sizes.length - 1 && k.getValue() == sizes[k.getIndex()])
                    {
                        tempKey = new key(k.getIndex() + 1, 0);
                    }
                    else if (k.getIndex() == sizes.length && k.getValue() == 0)
                    {
                        tempKey = k.copy();
                    }
                    
                    if (tempKey != null) // keys get combined 
                    {
                        long currentValue = (tempValueMap.get(tempKey) != null) ? tempValueMap.get(tempKey) : 0;
                        
                        tempValueMap.put(tempKey, valueMap.get(k) + currentValue);
                    }
                    // else: key gets discarded
                }
                
                valueMap = tempValueMap;
            }
            else if (row.charAt(i) == '.') // discard impossible combinations, increment the index of possible ones
            {
                Set<key> valueArray = valueMap.keySet();
                HashMap<key, Long> tempValueMap = new HashMap();
                
                for(key k : valueArray)
                {
                    key tempKey = null;
                    
                    if (k.getIndex() < sizes.length && k.getValue() == sizes[k.getIndex()])
                    {
                        tempKey = new key(k.getIndex() + 1, 0);
                    }
                    else if (k.getValue() == 0)
                    {
                        tempKey = k.copy();
                    }
                    
                    if (tempKey != null) // keys get combined 
                    {
                        long currentValue = (tempValueMap.get(tempKey) != null) ? tempValueMap.get(tempKey) : 0;
                        
                        tempValueMap.put(tempKey, valueMap.get(k) + currentValue);
                    }
                    // else: key gets discarded
                }
                
                valueMap = tempValueMap;
            }
            else if (row.charAt(i) == '#') // increment all key values by 1
            {
                Set<key> valueArray = valueMap.keySet();
                HashMap<key, Long> tempValueMap = new HashMap();
                
                for(key k : valueArray)
                {
                    key tempKey = new key(k.getIndex(), k.getValue() + 1);
                    
                    tempValueMap.put(tempKey, valueMap.get(k));
                }
                
                valueMap = tempValueMap;
            }
            else if (row.charAt(i) == '?') // combinations of '.' AND '#'
            {
                Set<key> valueArray = valueMap.keySet();
                HashMap<key, Long> tempValueMap = new HashMap();
                
                // ------------------------------------------------------------
                
                for(key k : valueArray)
                {
                    key tempKey = new key(k.getIndex(), k.getValue() + 1);
                    
                    tempValueMap.put(tempKey, valueMap.get(k));
                }
                                
                // ------------------------------------------------------------
                
                for(key k : valueArray)
                {
                    key tempKey = null;
                    
                    if (k.getIndex() < sizes.length && k.getValue() == sizes[k.getIndex()])
                    {
                        tempKey = new key(k.getIndex() + 1, 0);
                    }
                    else if (k.getValue() == 0)
                    {
                        tempKey = k.copy();
                    }
                    
                    if (tempKey != null) // keys get combined 
                    {
                        long currentValue = (tempValueMap.get(tempKey) != null) ? tempValueMap.get(tempKey) : 0;
                        
                        tempValueMap.put(tempKey, valueMap.get(k) + currentValue);
                    }
                    // else: key gets discarded
                }
                
                valueMap = tempValueMap;
            }
            else System.out.println("Error!!!");
        }
        
        /* //DEBUG!!!
        System.out.println(valueMap.size());
        for (key k : valueMap.keySet())
        {
            System.out.println(k + "     ;;; num: " + valueMap.get(k));
        }
        */
        
        for(key k : valueMap.keySet()) // should only be one key left
        {
            combinations += valueMap.get(k);
        }
        
        return combinations; 
    }
    
    public class key
    {
        private int index;
        private int value;
        
        public key()
        {
            index = 0;
            value = 0;
        }
        public key(int i, int v)
        {
            index = i;
            value = v;
        }
        
        public key copy()
        {
            return new key(index, value);
        }
        
        public int getIndex(){return index;}
        public int getValue(){return value;}
        
        public int hashCode()
        {
            return (index * 97) + (value * 13);
        }
        
        public String toString()
        {
            return "index: " + index + " :: value: " + value;
        }
        
        public boolean equals(Object o)
        {            
            key k = (key) o; 
            
            return index == k.index && value == k.value;
        }
    }
}