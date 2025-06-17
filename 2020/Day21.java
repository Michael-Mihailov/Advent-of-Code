import java.util.*;
import java.io.*;

public class Day21
{
    public static void main(String args[]) throws IOException
    {
        File file = new File("input.txt");
        Scanner scn = new Scanner(file);
        
        int ans = 0;
        
        HashSet<String> ingredientNames = new HashSet();
        HashMap<String, Integer> popularityMap = new HashMap();
        ArrayList<String[]> recipeList = new ArrayList();
        HashMap<String, ArrayList<Integer>> allergyMap = new HashMap();
        HashMap<String, ArrayList<String>> allergyTranslationMap = new HashMap(); // allergen -> ingredients
        
        for (int num = 0; scn.hasNextLine(); num++)
        {
            String line = scn.nextLine();
            
            String[] ingredients = line.substring(0, line.indexOf(" (contains ")).split(" ");
            recipeList.add(ingredients);
            
            for (String name : ingredients)
            {
                ingredientNames.add(name);
                popularityMap.put(name, 1 + popularityMap.getOrDefault(name, 0));
            }
            
            String[] allergies = line.substring(line.indexOf(" (contains ") + 11, line.length() - 1).split(", ");
            for (String allergy : allergies)
            {
                allergyMap.putIfAbsent(allergy, new ArrayList());
                ArrayList<Integer> recipeNums = allergyMap.get(allergy);
                recipeNums.add(num);
            }
        }
        
        for (String name : ingredientNames)
        {            
            boolean dangerous = false;
            for (String allergy : allergyMap.keySet())
            {
                boolean allPresent = true;
                for (int num : allergyMap.get(allergy))
                {
                    boolean found = false;
                    for (String ingredient : recipeList.get(num))
                    {
                        if (ingredient.equals(name) == true)
                        {
                            found = true;
                            break;
                        }
                    }
                    if (found == false)
                    {
                        allPresent = false;
                        break;
                    }
                }
                if (allPresent == true)
                {
                    allergyTranslationMap.putIfAbsent(allergy, new ArrayList());
                    ArrayList<String> allergens = allergyTranslationMap.get(allergy);
                    allergens.add(name);
                    
                    dangerous = true;
                }
            }
            
            if (dangerous == false) ans += popularityMap.get(name);
        }
        
        System.out.println("Part-1 Answer:\n" + ans);
        System.out.println();

        
        boolean changed = false;
        while (changed == false)
        {
            changed = true;
            for (String allergy : allergyTranslationMap.keySet())
            {
                ArrayList<String> allergens = allergyTranslationMap.get(allergy);
                if (allergens.size() == 1)
                {
                    String ingredient = allergens.get(0);
                    for (String allergy2 : allergyTranslationMap.keySet())
                    {
                        if (allergy2.equals(allergy)) continue;
                        
                        ArrayList<String> allergens2 = allergyTranslationMap.get(allergy2);
                        if (allergens2.contains(ingredient))
                        {
                            changed = false;
                            allergens2.remove(ingredient);
                        }
                    }
                }
            }
        }
        
        ArrayList<String> alphabeticalAllergies = new ArrayList( allergyTranslationMap.keySet() );
        alphabeticalAllergies.sort(String.CASE_INSENSITIVE_ORDER);
        
        String ans2 = "";
        for (String allergy : alphabeticalAllergies)
        {
            ans2 = ans2 + allergyTranslationMap.get(allergy).get(0) + ",";
        }
        ans2 = ans2.substring(0, ans2.length() - 1); // remove last comma
        
        System.out.println("Part-2 Answer:");
        System.out.println(ans2);
    }
}