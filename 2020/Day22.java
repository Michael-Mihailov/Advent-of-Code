import java.util.*;
import java.io.*;

public class Day22
{
    public static void main(String args[]) throws IOException
    {
        File file = new File("input.txt");
        Scanner scn = new Scanner(file);
        
        int ans = 0;
        
        ArrayList<Integer> deck1 = new ArrayList();
        ArrayList<Integer> deck2 = new ArrayList();
        
        scn.nextLine();
        for (String line = scn.nextLine(); line.equals("") == false; line = scn.nextLine())
        {
            deck1.addLast(Integer.parseInt(line));
        }
        
        scn.nextLine();
        while (scn.hasNextLine())
        {
            String line = scn.nextLine();
            deck2.addLast(Integer.parseInt(line));
        }
        
        ans = Math.abs( game(deck1, deck2) );
        
        System.out.println(ans);
    }
    
    public static int game (ArrayList<Integer> deck1, ArrayList<Integer> deck2) // positive == player1 :: negative == player2
    {
        HashSet<String> prevStates = new HashSet();
        String state = deckString(deck1) + "::" +deckString(deck2);
        
        while(prevStates.contains(state) == false)
        {
            prevStates.add(state);
            
            if (deck2.size() == 0)
            {
                return score(deck1);
            }
            if (deck1.size() == 0)
            {
                return -1 * score(deck2);
            }
            
            int card1 = deck1.removeFirst();
            int card2 = deck2.removeFirst();
            
            if (deck1.size() >= card1 && deck2.size() >= card2)
            {
                ArrayList<Integer> subDeck1 = new ArrayList();
                ArrayList<Integer> subDeck2 = new ArrayList();
                for (int i = 0; i < card1; i++)
                {
                    subDeck1.add(deck1.get(i));
                }
                for (int i = 0; i < card2; i++)
                {
                    subDeck2.add(deck2.get(i));
                }
                
                int winner = game(subDeck1, subDeck2);
                if (winner > 0)
                {
                    deck1.addLast(card1);
                    deck1.addLast(card2);
                }
                else if (winner < 0)
                {
                    deck2.addLast(card2);
                    deck2.addLast(card1);
                }
                else System.out.println("Error");
            }
            else
            {
                if (card1 > card2)
                {
                    deck1.addLast(card1);
                    deck1.addLast(card2);
                }
                else
                {
                    deck2.addLast(card2);
                    deck2.addLast(card1);
                }
            }
            
            state = deckString(deck1) + "::" +deckString(deck2);
        }
        
        return score(deck1);
    }
    
    public static int score (ArrayList<Integer> deck)
    {
        int res = 0;
        for (int i = 1; deck.size() > 0; i++)
        {
            int card = deck.removeLast();
            res += card * i;
        }
        return res;
    }
    
    public static String deckString (ArrayList<Integer> deck)
    {
        String res = "";
        for (int num : deck)
        {
            res += num + " ";
        }
        return res;
    }
}