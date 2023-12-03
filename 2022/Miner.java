
import java.util.*;

public class Miner
{
    public int count = 0;
    
    ArrayList<Integer[]> possibilities = new ArrayList();
    Integer[] stuff = new Integer[8]; // 0-3 == count by collecter type :: 4-7 == count by reasource type
    
    public Miner(int oreCost, int clayCost, int obsidianCost1, int obsidianCost2, int geodeCost1, int geodeCost2)
    {
        stuff[0] = 1;
        stuff[1] = 0;
        stuff[2] = 0;
        stuff[3] = 0;
        stuff[4] = 0;
        stuff[5] = 0;
        stuff[6] = 0;
        stuff[7] = 0;
        possibilities.add(stuff); // add the 'seed' of a single oreCollector
        
        for (int turn = 0; turn < 32; turn++) // run for 24 turns :: spend ore, collect ore, finish building robot
        {
            int possibilitiesLength = possibilities.size();
            
            System.out.println("Step: " + turn + " :: Size: " + possibilitiesLength);
            
            for (int pos = 0; pos < possibilitiesLength; pos++)
            {
                Integer[] possibility = possibilities.get(pos);
                
                for (int i = 0; i < 5; i++) // do nothing, create :: ore, clay, obsidian, or geode robot
                {
                    Integer[] variant = new Integer[8];
                    variant[0] = possibility[0];
                    variant[1] = possibility[1];
                    variant[2] = possibility[2];
                    variant[3] = possibility[3];
                    variant[4] = possibility[4];
                    variant[5] = possibility[5];
                    variant[6] = possibility[6];
                    variant[7] = possibility[7];
                    
                    if (i == 4) // build nothing, main branch
                    {
                        // do not spend resources in this option
                        
                        possibility[4] += possibility[0];
                        possibility[5] += possibility[1];
                        possibility[6] += possibility[2];
                        possibility[7] += possibility[3];
                        
                        // build nothing
                    }
                    else if (i == 0 && variant[4] >= oreCost) // build ore miner
                    {
                        variant[4] -= oreCost; // spend resources
                        
                        variant[4] += variant[0]; // mine more resources
                        variant[5] += variant[1];
                        variant[6] += variant[2];
                        variant[7] += variant[3];
                        
                        variant[0]++; // build new robot
                        
                        possibilities.add(variant); // add variant to possibilities
                    }
                    else if (i == 1 && variant[4] >= clayCost) // build clay miner
                    {
                        variant[4] -= clayCost;
                        
                        variant[4] += variant[0];
                        variant[5] += variant[1];
                        variant[6] += variant[2];
                        variant[7] += variant[3];
                        
                        variant[1]++;
                        
                        possibilities.add(variant);
                    }
                    else if (i == 2 && variant[4] >= obsidianCost1 && variant[5] >= obsidianCost2) // build obsidian miner
                    {
                        variant[4] -= obsidianCost1;
                        variant[5] -= obsidianCost2;
                        
                        variant[4] += variant[0];
                        variant[5] += variant[1];
                        variant[6] += variant[2];
                        variant[7] += variant[3];
                        
                        variant[2]++;
                        
                        possibilities.add(variant);
                    }
                    else if (i == 3 && variant[4] >= geodeCost1 && variant[6] >= geodeCost2) // build geode miner
                    {
                        variant[4] -= geodeCost1;
                        variant[6] -= geodeCost2;
                        
                        variant[4] += variant[0];
                        variant[5] += variant[1];
                        variant[6] += variant[2];
                        variant[7] += variant[3];
                        
                        variant[3]++;
                        
                        possibilities.add(variant);
                    }
                }
            }
            
            int trimmed = 0;
            System.out.println("Trimming!");
            
            int maxGeode = 0;
            int extraMax = (((31 - turn) * ((31 - turn) + 1)) / 2);
            for (int i = 0; i < possibilities.size(); i++)
            {
                if ((possibilities.get(i)[3] * (31 - turn)) + possibilities.get(i)[7] > maxGeode)
                {
                    maxGeode = (possibilities.get(i)[3] * (31 - turn)) + possibilities.get(i)[7];
                }
            }
            System.out.println("Most: " + maxGeode + " :: Max: " + extraMax);
            for (int i = 0; i < possibilities.size(); i++)
            {
                if (((possibilities.get(i)[3] * (31 - turn)) + possibilities.get(i)[7]) + extraMax < maxGeode)
                {
                    possibilities.remove(i);
                    i--;
                    trimmed++;
                }
            }
            
            
            //int startSize = possibilities.size();
            //boolean end = false;
            boolean go = true;
            while (go == true) //&& end == false)// trim the possibilities list
            {
                go = false;
                
                for (int p1 = 0; p1 < possibilities.size(); p1++)
                {
                    for (int p2 = 0; p2 < possibilities.size(); p2++)
                    {
                        if (p1 != p2)
                        {
                            Integer[] v1 = possibilities.get(p1);
                            Integer[] v2 = possibilities.get(p2);
                            
                            if (v1[0] >= v2[0] && v1[1] >= v2[1] && v1[2] >= v2[2] && v1[3] >= v2[3] && v1[4] >= v2[4] && v1[5] >= v2[5] && v1[6] >= v2[6] && v1[7] >= v2[7])
                            {
                                possibilities.remove(p2);
                                
                                if (p1 > p2)
                                {
                                    p1--;
                                }
                                // else do nothing
                                
                                trimmed++;
                                
                                //go = true; // IDK if this is helping ?????
                            }
                            else if (v1[0] <= v2[0] && v1[1] <= v2[1] && v1[2] <= v2[2] && v1[3] <= v2[3] && v1[4] <= v2[4] && v1[5] <= v2[5] && v1[6] <= v2[6] && v1[7] <= v2[7])
                            {
                                possibilities.remove(p1);
                                
                                if (p2 > p1)
                                {
                                    p1 = p2;
                                    p1--;
                                }
                                else
                                {
                                    p1 = p2;
                                }
                                
                                trimmed++;
                                
                                //go = true; // IDK if this is helping ?????
                            }
                        }
                    } 
                    /*
                    if (end == false && possibilities.size() < startSize / 1) // IDK if this is helping ?????
                    {
                        System.out.println("Trim ended!!!");
                        end = true;
                    }
                    */
                }
            }
            
            System.out.println("Trimmed: " + trimmed);
        }
        
        for (int i = 0; i < possibilities.size(); i++)
        {
            if (possibilities.get(i)[7] > count)
            {
                count = possibilities.get(i)[7];
            }
        }
    }
}
