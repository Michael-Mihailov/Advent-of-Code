import java.util.*;
import java.io.*;

public class Day4
{
    public static void main(String[] args) throws IOException
    {
        File file = new File("input.txt");
        Scanner scn = new Scanner(file);
        
        int ans = 0;
        
        while (scn.hasNextLine())
        {
            String data = scn.nextLine();
            
            String checksum = data.substring(data.indexOf('[') + 1, data.length() - 1);
            data = data.substring(0, data.indexOf('['));
            
            String[] tokens = data.split("-");
            int sectorID = Integer.parseInt(tokens[tokens.length - 1]);
            HashMap<String, Integer> heatMap = new HashMap();
            
            for (int i = 0; i < tokens.length - 1; i++)
            {
                for (int j = 0; j < tokens[i].length(); j++)
                {                    
                    String character = tokens[i].charAt(j) + "";
                    heatMap.put(character, heatMap.getOrDefault(character, 0) + 1);
                }
            }
                        
            boolean fail = false;
            
            for (int i = 0; i < checksum.length() && !fail; i++)
            {
                String checkCharacter = checksum.charAt(i) + "";
                int checkScore = heatMap.getOrDefault(checkCharacter, 0);
                heatMap.remove(checkCharacter);
                                
                for (String otherCharacter : heatMap.keySet())
                {
                    int otherScore = heatMap.get(otherCharacter);
                                        
                    if (otherScore > checkScore || (otherScore == checkScore && otherCharacter.charAt(0) < checkCharacter.charAt(0)))
                    {
                        fail = true;
                    }
                }
            }
            
            if (!fail)
            {
                ans += sectorID;
                
                String roomName = "";
                
                for (int i = 0; i < tokens.length - 1; i++)
                {
                    for (int j = 0; j < tokens[i].length(); j++)
                    {                    
                        int c = tokens[i].charAt(j);
                        c = c - 'a';
                        c += sectorID;
                        c %= 26;
                        c = c + 'a';
                        roomName = roomName + ((char)c);
                    }
                    roomName = roomName + " ";
                }
                System.out.println(roomName + " :: " + sectorID);
            }
        }
        
        System.out.println("Part 1 Answer: " + ans);
    }
}