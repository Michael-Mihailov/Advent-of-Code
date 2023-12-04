import java.util.*;
import java.io.*;

public class Day4
{
    public static void main(String[] args) throws IOException
    {
        File file = new File("input.txt");
        Scanner scn = new Scanner(file);
        
        
        int totCards = 212; //212 cards
        
        int[] numCards = new int[totCards + 1];
        
        int answer = 0;
        
        
        int currentCard = 0;
        while (scn.hasNextLine())
        {
            currentCard++;
            numCards[currentCard] += 1;
            
            
            String data = scn.nextLine();
            
            String nums = data.substring(data.indexOf(":") + 1, data.indexOf("|"));
            String wins = data.substring(data.indexOf("|") + 1);
            
            Scanner numScn = new Scanner(nums);
            Scanner winScn = new Scanner(wins);
            
            HashSet<Integer> winSet = new HashSet();
            while (winScn.hasNext())
            {
                winSet.add(winScn.nextInt());
            }
            
            int score = 0;
            while (numScn.hasNext())
            {
                if (winSet.contains(numScn.nextInt()))
                {
                    score++;
                }
            }
            
            for (int i = 1; i <= score && (i + currentCard) <= totCards; i++)
            {
                numCards[currentCard + i] += numCards[currentCard];
            }
        }
        
        
        for (int card : numCards)
        {
            answer += card;
        }
        System.out.println(answer);
    }
}
