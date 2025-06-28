import java.util.*;
import java.io.*;

public class Day16
{
    public static void main(String args[]) throws IOException
    {
        File file = new File("input.txt");
        Scanner scn = new Scanner(file);
                
        HashMap<String, Integer> sueInfo = new HashMap();
        sueInfo.put("children",3);
        sueInfo.put("cats",7);
        sueInfo.put("samoyeds",2);
        sueInfo.put("pomeranians",3);
        sueInfo.put("akitas",0);
        sueInfo.put("vizslas",0);
        sueInfo.put("goldfish",5);
        sueInfo.put("trees",3);
        sueInfo.put("cars",2);
        sueInfo.put("perfumes",1);
        
        for (int sueNum = 1; scn.hasNextLine(); sueNum++)
        {
            String line = scn.nextLine();
            
            line = line.substring(line.indexOf(":") + 2);
            line = line.replace(":","");
            line = line.replace(",","");
            
            String[] data = line.split(" ");
            
            boolean valid = true;
            for (int i = 0; i < data.length; i += 2)
            {
                String thing = data[i];
                int num = Integer.parseInt(data[i+1]);
                if (sueInfo.containsKey(thing))
                {
                    if (thing.equals("cats") || thing.equals("trees"))
                    {
                        if (sueInfo.get(thing) >= num) valid = false;
                    }
                    else if (thing.equals("pomeranians") || thing.equals("goldfish"))
                    {
                        if (sueInfo.get(thing) <= num) valid = false;
                    }
                    else if (sueInfo.get(thing) != num) valid = false;
                }
            }
            
            if (valid) System.out.println(sueNum);
        }
    }
}