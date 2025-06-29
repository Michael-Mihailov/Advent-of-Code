import java.util.*;
import java.io.*;

public class Day21 // Shop placed in file !!!!!
{
    public static void main(String args[]) throws IOException
    {
        File file = new File("input.txt");
        Scanner scn = new Scanner(file);
        
        int[] bossStats = new int[3]; // Health, Damage, Armor
        scn.next(); scn.next(); bossStats[0] = scn.nextInt();
        scn.next(); bossStats[1] = scn.nextInt();
        scn.next(); bossStats[2] = scn.nextInt();
        
        int baseHealth = 100;
        
        HashMap<String, int[]> weaponMap = new HashMap();
        HashMap<String, int[]> armorMap = new HashMap();
        HashMap<String, int[]> ringMap = new HashMap();
        
        scn.nextLine();
        int section = 0;
        while (scn.hasNextLine())
        {
            String line = scn.nextLine();
            if (line.equals(""))
            {
                scn.nextLine();
                section++;
                continue;
            }
            while (line.contains("  ")) line = line.replace("  ", " ");
            line = line.replace(" +", "+");
            
            String[] data = line.split(" ");
            String name = data[0];
            int cost = Integer.parseInt(data[1]);
            int damage = Integer.parseInt(data[2]);
            int armor = Integer.parseInt(data[3]);
            
            if (section == 1)
            {
                weaponMap.put(name, new int[]{cost, damage, armor});
            }
            else if (section == 2)
            {
                armorMap.put(name, new int[]{cost, damage, armor});
            }
            else if (section == 3)
            {
                ringMap.put(name, new int[]{cost, damage, armor});
            }
        }
        armorMap.put("none", new int[3]); // blank stuff
        ringMap.put("noneLeft", new int[3]); // blank stuff
        ringMap.put("noneRight", new int[3]); // blank stuff
        
        int ans = 0;
        for (String weapon : weaponMap.keySet())
        {
            int[] weaponStats = weaponMap.get(weapon);
            for (String armor : armorMap.keySet())
            {
                int[] armorStats = armorMap.get(armor);
                for (String ring1 : ringMap.keySet())
                {
                    int[] ring1Stats = ringMap.get(ring1);
                    for (String ring2 : ringMap.keySet())
                    {
                        if (ring2.equals(ring1)) continue;
                        int[] ring2Stats = ringMap.get(ring2);
                        
                        int cost = weaponStats[0] + armorStats[0] + ring1Stats[0] + ring2Stats[0];
                        if (cost <= ans) continue;
                        
                        int[] playerStats = new int[3];
                        playerStats[0] = baseHealth;
                        for (int i = 1; i < playerStats.length; i++)
                        {
                            playerStats[i] = weaponStats[i] + armorStats[i] + ring1Stats[i] + ring2Stats[i];
                        }
                        
                        boolean victory = fight(playerStats, bossStats);
                        
                        if (victory == false)
                        {
                            ans = cost;
                        }
                    }
                }
            }
        }
        
        System.out.println(ans);
    }
    
    public static boolean fight (int[] playerStats, int[] bossStats)
    {
        int[] tempPlayer = new int[3];
        int[] tempBoss = new int[3];
        for (int i = 0; i < 3; i++)
        {
            tempPlayer[i] = playerStats[i];
            tempBoss[i] = bossStats[i];
        }
        
        playerStats = tempPlayer;
        bossStats = tempBoss;
        
        int playerAttack = playerStats[1] - bossStats[2];
        playerAttack = (playerAttack < 1 ? 1 : playerAttack);
        
        bossStats[0] -= playerAttack;
        if (bossStats[0] <= 0) return true;
        
        int bossAttack = bossStats[1] - playerStats[2];
        bossAttack = (bossAttack < 1 ? 1 : bossAttack);
        
        playerStats[0] -= bossAttack;
        if (playerStats[0] <= 0) return false;
        
        return fight(playerStats, bossStats);
    }
}