import java.util.*;
import java.io.*;

public class Day5
{
    public static void main(String[] args) throws IOException
    {
        File file = new File("input.txt");
        Scanner scn = new Scanner(file);
        
        long answer = -1;
        
        ArrayList<Long[]> values = new ArrayList(); // starts as seeds and goes through blocks
        Scanner seedScn = new Scanner(scn.nextLine());
        seedScn.next();
        while (seedScn.hasNext())
        {
            Long min = new Long(seedScn.next()); // max range value
            Long max = new Long(seedScn.next()) + min - 1; // min range value
            
            Long[] range = new Long[2];
            range[0] = min;
            range[1] = max;
            
            values.add(range);
        }
        
        
        ArrayList<Long[]> conversions = null;
        while (scn.hasNextLine())
        {
            String data = scn.nextLine();
            if (data.equals(""));
            else if (data.contains(":")) // must run an identical block at end of loop
            {
                if (conversions == null) // for first block
                {
                    conversions = new ArrayList<Long[]>(); // initialize conversionList
                }
                else
                {
                    for (Long[] range : values) for(long value : range) System.out.println(value); System.out.println(); //DEBUG
                    
                    removeOverlap(values);
                    for (int v = values.size() - 1; v >= 0; v--) // convert values
                    {
                        Long[] range = values.remove(v); // remove the current value-range
                        
                        ArrayList<Long> cutRange = new ArrayList<Long>();
                        cutRange.add(range[0]);
                        cutRange.add(range[1]);
                                                
                        
                        for (int i = 0; i < conversions.size(); i++) // check range and conversion overlap
                        {
                            long conMin = conversions.get(i)[1];
                            long conMax = conMin + conversions.get(i)[2] - 1;
                            long rangeMin = range[0];
                            long rangeMax = range[1];
                            
                            if (rangeMax >= conMin && rangeMin <= conMax)
                            {
                                Long[] newRange = new Long[2];
                                
                                if (rangeMin >= conMin && rangeMax <= conMax) // con encompases range
                                {
                                    newRange[0] = rangeMin;
                                    newRange[1] = rangeMax;
                                }
                                else if (rangeMin < conMin && rangeMax > conMax) // range encompases con
                                {
                                    newRange[0] = conMin;
                                    newRange[1] = conMax;
                                }
                                else if (rangeMin <= conMin && rangeMax <= conMax) // left slice of con
                                {
                                    newRange[0] = conMin;
                                    newRange[1] = rangeMax;
                                }
                                else if (rangeMin >= conMin && rangeMax >= conMax) // right slice of con
                                {
                                    newRange[0] = rangeMin;
                                    newRange[1] = conMax;
                                }
                                else
                                {
                                    System.out.println("ERROR: this should not print");
                                }
                                
                                cutRange = slicedArray(cutRange, newRange);
                                
                                
                                newRange[0] = newRange[0] + (conversions.get(i)[0] - conMin);
                                newRange[1] = newRange[1] + (conversions.get(i)[0] - conMin);
                                
                                values.add(newRange);
                            }
                        }
                        
                        for(int i = 1; i < cutRange.size(); i += 2)
                        {
                            Long[] temp = new Long[2];
                            temp[0] = cutRange.get(i - 1);
                            temp[1] = cutRange.get(i);
                            
                            values.add(temp);
                            
                            //System.out.println("Added cutRange!");
                        }
                    }
                    
                    conversions = new ArrayList<Long[]>(); // reset conversionList
                }
            }
            else // add to conversionList
            {
                Scanner dataScn = new Scanner(data);
                Long[] convertTable = new Long[3];
                
                convertTable[0] = new Long(dataScn.next());
                convertTable[1] = new Long(dataScn.next());
                convertTable[2] = new Long(dataScn.next());
                
                conversions.add(convertTable);
            }
        }
        // TODO: must copy code block here
        for (Long[] range : values) for(long value : range) System.out.println(value); System.out.println(); //DEBUG
        
        removeOverlap(values);
        for (int v = values.size() - 1; v >= 0; v--) // convert values
        {
            Long[] range = values.remove(v); // remove the current value-range
            
            ArrayList<Long> cutRange = new ArrayList<Long>();
            cutRange.add(range[0]);
            cutRange.add(range[1]);
                                    
            
            for (int i = 0; i < conversions.size(); i++) // check range and conversion overlap
            {
                long conMin = conversions.get(i)[1];
                long conMax = conMin + conversions.get(i)[2] - 1;
                long rangeMin = range[0];
                long rangeMax = range[1];
                
                if (rangeMax >= conMin && rangeMin <= conMax)
                {
                    Long[] newRange = new Long[2];
                    
                    if (rangeMin >= conMin && rangeMax <= conMax) // con encompases range
                    {
                        newRange[0] = rangeMin;
                        newRange[1] = rangeMax;
                    }
                    else if (rangeMin < conMin && rangeMax > conMax) // range encompases con
                    {
                        newRange[0] = conMin;
                        newRange[1] = conMax;
                    }
                    else if (rangeMin <= conMin && rangeMax <= conMax) // left slice of con
                    {
                        newRange[0] = conMin;
                        newRange[1] = rangeMax;
                    }
                    else if (rangeMin >= conMin && rangeMax >= conMax) // right slice of con
                    {
                        newRange[0] = rangeMin;
                        newRange[1] = conMax;
                    }
                    else
                    {
                        System.out.println("ERROR: this should not print");
                    }
                    
                    cutRange = slicedArray(cutRange, newRange);
                    
                    
                    newRange[0] = newRange[0] + (conversions.get(i)[0] - conMin);
                    newRange[1] = newRange[1] + (conversions.get(i)[0] - conMin);
                    
                    values.add(newRange);
                }
            }
            
            for(int i = 1; i < cutRange.size(); i += 2)
            {
                Long[] temp = new Long[2];
                temp[0] = cutRange.get(i - 1);
                temp[1] = cutRange.get(i);
                
                values.add(temp);
                
                //System.out.println("Added cutRange!");
            }
        }
        
        
        for (Long[] range : values)
        {
            for (long value : range)
            {
                if (answer < 0 || value < answer)
                {
                    answer = value;
                }
            }
        }
        
        System.out.println(answer);
    }
    
    public static void removeOverlap(ArrayList<Long[]> input)
    {
        boolean done = false;
        while (!done)
        {
            done = true;
            for (int i = 0; i < input.size(); i++)
            {
                for (int k = i + 1; k < input.size(); k++)
                {
                    if (removeOverlap(input.get(i), input.get(k)) != null)
                    {
                        done = false;
                        
                        Long[] temp = removeOverlap(input.get(i), input.get(k));
                        
                        input.remove(k);
                        input.remove(i);
                        
                        input.add(temp);
                    }
                }
            }
        }
    }
    
    public static Long[] removeOverlap(Long[] input1, Long[] input2)
    {
        if (input1[1] < input2[0] || input1[0] > input2[1]) // no overlap
            return null;
           
        long min = input1[0] < input2[0] ? input1[0] : input2[0];
        long max = input1[1] > input2[1] ? input1[1] : input2[1];
        
        Long[] ans = new Long[2];
        ans[0] = min;
        ans[1] = max;
        
        return ans;
    }
    
    
    public static ArrayList<Long> slicedArray(ArrayList<Long> original, Long[] cut) // both parameters are inclusive (cut should have length 2 and original.size() % 2 == 0)
    {
        if (original.size() == 0) return original; // nothing to cut
        
        if (cut[0] > original.get(original.size() - 1) || cut[1] < original.get(0)) return original; // cut is outside of range
        
        if (original.size() > cut.length)
        {
            ArrayList<Long> newArr = new ArrayList();
            
            newArr.add(original.remove(0));
            newArr.add(original.remove(0));
            
            newArr = slicedArray(newArr, cut);
            newArr.addAll(slicedArray(original, cut));
            return newArr;
        }
        
        if (cut[0] <= original.get(0) && cut[1] >= original.get(1)) // cut out entire list
        {
            return new ArrayList<Long>();
        }
        
        if (cut[0] <= original.get(0) && cut[1] < original.get(1)) // cut off left
        {
            ArrayList<Long> newArr = new ArrayList();
            
            newArr.add(cut[1] + 1);
            newArr.add(original.get(1));
            
            return newArr;
        }
        if (cut[0] > original.get(0) && cut[1] >= original.get(1)) // cut off right
        {
            ArrayList<Long> newArr = new ArrayList();
            
            newArr.add(original.get(0));
            newArr.add(cut[0] - 1);
            
            return newArr;
        }
        
        if (cut[0] > original.get(0) && cut[1] < original.get(1)) // cut middle
        {
            ArrayList<Long> newArr = new ArrayList();
            
            newArr.add(original.get(0));
            newArr.add(cut[0] - 1);
            newArr.add(cut[1] + 1);
            newArr.add(original.get(1));
            
            return newArr;
        }
        
        
        //else
        System.out.println("ERROR: this should not print! " + original.size());
        return null;
    }
}
