
import java.io.*;
import java.util.*;

public class Day19
{
    public static void main (String[] args) throws Exception
    {
        File file = new File("input.txt");
        Scanner scn = new Scanner(file);
        
        int total = 0;
        
        //int id = 0;
        while (scn.hasNext()) // edited input data
        {
            //id++;
            
            String data = scn.nextLine();
            Scanner dataScn = new Scanner(data);
            
            int oreCost = dataScn.nextInt();
            
            int clayCost = dataScn.nextInt();
            
            int obsidianCost1 = dataScn.nextInt();
            int obsidianCost2 = dataScn.nextInt();
            
            int geodeCost1 = dataScn.nextInt();
            int geodeCost2 = dataScn.nextInt();
            
            Miner test = new Miner(oreCost, clayCost, obsidianCost1, obsidianCost2, geodeCost1, geodeCost2);
            System.out.println(test.count);
            
            //total += (id * test.count);
            
            System.out.println("-----------------------------------");
        }
        
        //System.out.println(total);
    }
}

