import java.util.*;
import java.io.*;

public class Day22
{
    public static void main(String args[]) throws IOException
    {
        File file = new File("input.txt");
        Scanner scn = new Scanner(file);
        
        int[] playerStats = new int[]{50, 500}; // health, mana
        int[] bossStats = new int[2]; // health, damage
        
        scn.next();
        scn.next();
        bossStats[0] = scn.nextInt();
        scn.next();
        bossStats[1] = scn.nextInt();
        
        int ans = fight(playerStats, bossStats, 0, 0, 0, true);
        System.out.println(ans);
    }
    
    public static int fight (int[] playerStats, int[] bossStats, int shield, int poison, int recharge, boolean playerTurn)
    {
        if (playerStats[1] <= 0) return 1000000; // ran out of mana
        
        playerStats = new int[]{playerStats[0], playerStats[1]};
        bossStats = new int[]{bossStats[0], bossStats[1]};
        
        if (playerTurn) playerStats[0]--;
        
        int playerDefense = 0;
        if (shield > 0)
        {
            playerDefense = 7;
            shield--;
        }
        if (poison > 0)
        {
            bossStats[0] -= 3;
            poison--;
        }
        if (recharge > 0)
        {
            playerStats[1] += 101;
            recharge--;
        }
        
        if (bossStats[0] <= 0) return 0; // player wins
        if (playerStats[0] <= 0) return 1000000; // boss wins
        
        if (playerTurn)
        {
            int bestScore = 1000000;
            for (int option = 0; option < 5; option++)
            {
                int[] tempPlayerStats = new int[]{playerStats[0], playerStats[1]};
                int[] tempBossStats = new int[]{bossStats[0], bossStats[1]};
                
                int score = 1000000;
                
                if (option == 0)
                {
                    tempPlayerStats[1] -= 53;
                    tempBossStats[0] -= 4;
                    score = 53 + fight(tempPlayerStats, tempBossStats, shield, poison, recharge, !playerTurn);
                }
                else if (option == 1)
                {
                    tempPlayerStats[1] -= 73;
                    tempBossStats[0] -= 2;
                    tempPlayerStats[0] += 2;
                    score = 73 + fight(tempPlayerStats, tempBossStats, shield, poison, recharge, !playerTurn);
                }
                else if (option == 2 && shield == 0)
                {
                    tempPlayerStats[1] -= 113;
                    score = 113 + fight(tempPlayerStats, tempBossStats, 6, poison, recharge, !playerTurn);
                }
                else if (option == 3 && poison == 0)
                {
                    tempPlayerStats[1] -= 173;
                    score = 173 + fight(tempPlayerStats, tempBossStats, shield, 6, recharge, !playerTurn);
                }
                else if (option == 4 && recharge == 0)
                {
                    tempPlayerStats[1] -= 229;
                    score = 229 + fight(tempPlayerStats, tempBossStats, shield, poison, 5, !playerTurn);
                }
                
                if (score < bestScore) bestScore = score;
            }
            
            return bestScore;
        }
        else
        {
            playerStats = new int[]{playerStats[0], playerStats[1]};
            bossStats = new int[]{bossStats[0], bossStats[1]};
            
            int damage = bossStats[1] - playerDefense;
            damage = (damage <= 1 ? 1 : damage);
            
            playerStats[0] -= damage;
            
            return fight(playerStats, bossStats, shield, poison, recharge, !playerTurn);
        }
    }
}