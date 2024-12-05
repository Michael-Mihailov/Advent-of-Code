import java.util.*;
import java.io.*;

public class Day5
{
    public static void main(String[] args) throws IOException
    {
        File file = new File("input.txt");
        Scanner scn = new Scanner(file);
        
        int ans = 0;
        
        HashMap<Integer, ArrayList<Integer>> map = new HashMap();
        
        boolean switchScan = false;
        while (scn.hasNextLine())
        {
            String line = scn.nextLine();
            
            if (line.equals(""))
            {
                switchScan = true;
                continue;
            }
            
            if (!switchScan)
            {
                String[] data = new String[2]; //line.split(",");
                
                data[0] = line.substring(0, line.indexOf("|"));
                data[1] = line.substring(line.indexOf("|") + 1);
                
                //System.out.println(data[0] + "," + data[1]);
                if (map.containsKey(Integer.parseInt(data[0])) == false)
                {
                    ArrayList<Integer> temp = new ArrayList();
                    temp.add(Integer.parseInt(data[1]));
                    map.put(Integer.parseInt(data[0]), temp);
                }
                else
                {
                    ArrayList<Integer> temp = map.get(Integer.parseInt(data[0]));
                    temp.add(Integer.parseInt(data[1]));
                    map.put(Integer.parseInt(data[0]), temp);
                }
            }
            else
            {
                String[] tData = line.split(",");
                Integer[] data = new Integer[tData.length];
                for (int i = 0; i < data.length; i++)
                {
                    data[i] = Integer.parseInt(tData[i]);
                    //System.out.print(data[i] + ",");
                }
                //System.out.println();
                
                
                boolean good = true;
                for (int i = 0; i < data.length; i++)
                {
                    //System.out.println(data[i]);
                    if (map.containsKey(data[i]))
                    {
                        //System.out.println("test1");
                        for (int j = 0; j < i; j++)
                        {
                            for (Integer num : map.get(data[i]))
                            {
                                if (data[j] == num)
                                {
                                    //System.out.println("test2");
                                    good = false;
                                    
                                    data[j] = data[i];
                                    data[i] = num;
                                }
                            }
                        }
                    }
                }
                
                if (good == false)
                {
                    //System.out.println(data[data.length / 2]);
                    ans += data[data.length / 2];
                }
            }
        }
        
        System.out.println(ans);
    }
}