import java.util.*;
import java.io.*;

public class Day15
{
    public static void main(String args[]) throws IOException
    {
        File file = new File("input.txt");
        Scanner scn = new Scanner(file);
        
        int ans = 0;
        
        ArrayList<String> ingredients = new ArrayList();
        HashMap<String, int[]> nutritionMap = new HashMap();
        
        while (scn.hasNextLine())
        {
            String line = scn.nextLine();
            line = line.replace(":", "");
            line = line.replace(",", "");
            
            String[] data = line.split(" ");
            
            String name = data[0];
            int capacity = Integer.parseInt(data[2]);
            int durability = Integer.parseInt(data[4]);
            int flavor = Integer.parseInt(data[6]);
            int texture = Integer.parseInt(data[8]);
            int calories = Integer.parseInt(data[10]);
            
            int[] nutrition = new int[]{capacity, durability, flavor, texture, calories};
            nutritionMap.put(name, nutrition);
            
            ingredients.add(name);
        }
        
        ans = bestCookie(ingredients, new HashMap(), nutritionMap);
        System.out.println(ans);
    }
    
    public static int bestCookie(ArrayList<String> remainingIngredients, HashMap<String, Integer> cookieMap, HashMap<String, int[]> nutritionMap)
    {
        int numTeaspoons = 0;
        for (int value : cookieMap.values()) numTeaspoons += value;
                
        if (numTeaspoons == 100)
        {
            int[] nutritionSum = new int[4]; // exclude "calories"
            int calories = 0;
            for (String ingredient : cookieMap.keySet())
            {
                int num = cookieMap.get(ingredient);
                int[] nutrition = nutritionMap.get(ingredient);
                for (int i = 0; i < nutritionSum.length; i++)
                {
                    nutritionSum[i] += num * nutrition[i];
                }
                calories += num * nutrition[4];
            }
            
            if (calories != 500) return 0;
            
            int score = 1;
            for (int i = 0; i < nutritionSum.length; i++)
            {
                if (nutritionSum[i] <= 0) return 0;
                score *= nutritionSum[i];
            }
            
            return score;
        }
        if (remainingIngredients.size() == 0)
        {
            return 0;
        }
        
        int bestScore = 0;
        
        remainingIngredients = new ArrayList(remainingIngredients);
        String currentIngredient = remainingIngredients.removeLast();
        for (int i = 0; i <= 100 - numTeaspoons; i++)
        {
            HashMap<String, Integer> newCookieMap = new HashMap(cookieMap);
            newCookieMap.put(currentIngredient, i);
            
            int score = bestCookie(remainingIngredients, newCookieMap, nutritionMap);
            if (score > bestScore) bestScore = score;
        }
        
        return bestScore;
    }
}