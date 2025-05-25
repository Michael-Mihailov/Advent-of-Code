import java.util.*;
import java.io.*;

public class Day13
{
    public static void main(String args[]) throws IOException
    {
        File file = new File("input.txt");
        Scanner scn = new Scanner(file);
        
        scn.nextLine();
        String[] busInfo = scn.nextLine().split(",");
        
        ArrayList<Long> frequencyList = new ArrayList();
        ArrayList<Long> delayList = new ArrayList();
        
        for (int i = 0; i < busInfo.length; i++)
        {
            String bus = busInfo[i];
            if (bus.equals("x")) continue;
            
            long num = Long.parseLong(bus);
            
            frequencyList.add(num);
            delayList.add((long)i);
        }
        
        System.out.println("Countdown:");
        while (delayList.size() > 1)
        {
            long f1 = frequencyList.removeFirst();
            long d1 = delayList.removeFirst();
            long f2 = frequencyList.removeFirst();
            long d2 = delayList.removeFirst();
            
            long[] combinedBus = combineBuses(f1, d1, f2, d2);
            
            frequencyList.addFirst(combinedBus[0]);
            delayList.addFirst(combinedBus[1]);
            
            System.out.println(delayList.size());
        }
        
        System.out.println();
        System.out.println("Answer:");
        System.out.println(frequencyList.get(0) - delayList.get(0));
    }
    
    public static long[] combineBuses(long frequency1, long delay1, long frequency2, long delay2)
    {
        long largerFrequency = (frequency1 > frequency2) ? frequency1 : frequency2;
        long anchorDelay = (frequency1 > frequency2) ? delay1 : delay2;
        
        long repeatFrequency = -1;
        long initialDelay = -1;
        
        for (long t = anchorDelay; initialDelay == -1 && t >= delay1 && t >= delay2; t += largerFrequency)
        {
            if (delay1 % frequency1 == t % frequency1 && delay2 % frequency2 == t % frequency2)
            {
                initialDelay = t;
            }
        }
        
        System.out.println("check");
        
        for (long t = largerFrequency; repeatFrequency == -1; t += largerFrequency)
        {
            if (0 == t % frequency1 && 0 == t % frequency2)
            {
                repeatFrequency = t;
            }
        }
        
        return new long[]{repeatFrequency,initialDelay};
    }
}