import java.util.*;
import java.io.*;

public class Day8
{
    public static void main(String[] args) throws IOException
    {
        File file = new File("input.txt");
        Scanner scn = new Scanner(file);
        
        long ans = 0;
        
        HashSet<long[]> boxSet = new HashSet();
        int nextCircuitID = 0;
        HashMap<Integer, HashSet<long[]>> circuitMap = new HashMap();
        HashMap<long[], HashSet<long[]>> connectionMap = new HashMap();
        
        while (scn.hasNextLine())
        {
            String[] data = scn.nextLine().split(",");
            long[] box = new long[3];
            for (int i = 0; i < 3; i++) box[i] = Long.parseLong(data[i]);
            boxSet.add(box);
            connectionMap.put(box, new HashSet());
        }
        
        for (int connectionStep = 0; true; connectionStep++)
        {
            //System.out.println("step: " + connectionStep);
            boolean finished = false;
            for (HashSet<long[]> circuit : circuitMap.values())
            {
                boolean done = true;
                for (long[] box : boxSet)
                {
                    if (circuit.contains(box) == false)
                    {
                        done = false;
                        break;
                    }
                }
                if (done == true) finished = true;
            }
            if (finished) break; // end simulation
            
            long minDist = Integer.MAX_VALUE;
            long[] b1 = null;
            long[] b2 = null;
            
            for (long[] box1 : boxSet)
            {
                for (long[] box2 : boxSet)
                {
                    if (box1 == box2) continue;
                    if (connectionMap.get(box1).contains(box2)) continue;
                    
                    long dist = distance(box1, box2);
                    if (dist < minDist)
                    {
                        b1 = box1;
                        b2 = box2;
                        minDist = dist;
                    }
                }
            }
            connectionMap.get(b1).add(b2);
            connectionMap.get(b2).add(b1);
            
            int c1 = -1;
            int c2 = -1;
            for (int num : circuitMap.keySet())
            {
                HashSet<long[]> circuit = circuitMap.get(num);
                if (circuit.contains(b1))
                {
                    c1 = num;
                }
                if (circuit.contains(b2))
                {
                    c2 = num;
                }
            }
            if (c1 == c2 && c1 != -1) continue;
            
            if (c1 != -1 && c2 == -1)
            {
                circuitMap.get(c1).add(b2);
            }
            else if (c1 == -1 && c2 != -1)
            {
                circuitMap.get(c2).add(b1);
            }
            else if (c1 != -1 && c2 != -1)
            {
                circuitMap.get(c1).addAll(circuitMap.get(c2));
                circuitMap.remove(c2);
            }
            else
            {
                HashSet<long[]> tempCir = new HashSet();
                tempCir.add(b1);
                tempCir.add(b2);
                circuitMap.put(nextCircuitID, tempCir);
                nextCircuitID++;
            }
            
            ans = b1[0] * b2[0];
        }
        /*
        ArrayList<Integer> orderedSizes = new ArrayList();
        for (HashSet<long[]> circuit : circuitMap.values())
        {
            int num = circuit.size();
            boolean added = false;
            for (int i = 0; i < orderedSizes.size(); i++)
            {
                if (num > orderedSizes.get(i))
                {
                    orderedSizes.add(i, num);
                    added = true;
                    break;
                }
            }
            if (added == false)
            {
                orderedSizes.add(num);
            }
        }
        
        ans = orderedSizes.get(0) * orderedSizes.get(1) * orderedSizes.get(2);
        */
        System.out.println(ans);
        
    }
    
    public static long distance (long[] p1, long[] p2)
    {
        long res = 0;
        for (int i = 0; i < 3; i++) res += ((p1[i] - p2[i]) * (p1[i] - p2[i]));
        return res;
    }
}