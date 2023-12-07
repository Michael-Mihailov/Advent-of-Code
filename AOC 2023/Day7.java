import java.util.*;
import java.io.*;

public class Day7
{
    public static void main(String[] args) throws IOException
    {
        File file = new File("input.txt");
        Scanner scn = new Scanner(file);
        
        int answer = 0;
        
        ArrayList<Double[]> handInfo = new ArrayList(); //[type-strength, first card, multiplier]
        
        while (scn.hasNextLine())
        {
            String data = scn.next();
            int multiplier = scn.nextInt();
            
            int[] hand = new int[5];
            for (int i = 0; i < 5; i++)
            {
                if (data.charAt(i) == 'T')
                {
                    hand[i] = 10;
                }
                else if (data.charAt(i) == 'J')
                {
                    hand[i] = 1;
                }
                else if (data.charAt(i) == 'Q')
                {
                    hand[i] = 12;
                }
                else if (data.charAt(i) == 'K')
                {
                    hand[i] = 13;
                }
                else if (data.charAt(i) == 'A')
                {
                    hand[i] = 14;
                }
                else
                {
                    hand[i] = data.charAt(i) - 48; // char '0' == 48
                }
            }
            
            double secondPick = 0;
            for (int i = 0; i < 5; i++)
            {
                secondPick += hand[i] / Math.pow(100, i);
            }
            
            ArrayList<Integer> pairs = new ArrayList();
            int jokes = 0;
            for (int i = 0; i < 5; i++)
            {
                if (hand[i] == 1)
                {
                    jokes++;
                }
            }
            for (int i = 0; i < 5; i++)
            {
                if (hand[i] == -1)
                    continue;
                    
                
                
                int matches = 1;
                
                for (int j = i + 1; j < 5; j++)
                {
                    if (hand[i] == hand[j])
                    {
                        matches++;
                        
                        hand[j] = -1;
                    }
                }
                
                pairs.add(matches);
                
                hand[i] = -1;
            }
            
            
            
            int type = -1;
            if (pairs.contains(5))
            {
                type = 7;
            }
            else if(pairs.contains(4))
            {
                type = 6;
                
                type += jokes;
            }
            else if(pairs.contains(3) && pairs.contains(2))
            {
                type = 5;
                
                type += jokes;
            }
            else if(pairs.contains(3))
            {
                type = 4;
                
                if (jokes == 1)
                    type = 6;
                else if (jokes == 3)
                    type = 6;
            }
            else if(pairs.contains(2) && pairs.size() == 3)
            {
                type = 3;
                
                if (jokes == 1)
                {
                    type = 5;
                }
                else if (jokes == 2)
                {
                    type = 6;
                }
            }
            else if(pairs.contains(2))
            {
                type = 2;
                
                if (jokes > 0)
                    type = 4;
            }
            else
            {
                type = 1;
                
                if (jokes > 0)
                    type = 2;
            }
            
            if (type > 7)
                type = 7;
            
            Double[] info = new Double[3];
            info[0] = (double)type;
            info[1] = secondPick;
            info[2] = (double)multiplier;
            
            handInfo.add(info);
            
            //System.out.println(jokes + " " + type);
        }
        
        
        boolean done = false;
        while (!done)
        {
            done = true;
            
            for (int i = 0; i < handInfo.size() - 1; i++)
            {
                //System.out.println(handInfo.get(i)[1] + " " + handInfo.get(i + 1)[1]);
                //System.out.println(handInfo.get(i)[0] + " == " + handInfo.get(i + 1)[0] + " " + (Math.abs(handInfo.get(i)[0] - handInfo.get(i + 1)[0]) < 0.1));
                //System.out.println(handInfo.get(i)[1] > handInfo.get(i + 1)[1]);
                //System.out.println();
                
                if (handInfo.get(i)[0] > handInfo.get(i + 1)[0] || (Math.abs(handInfo.get(i)[0] - handInfo.get(i + 1)[0]) < 0.1 && handInfo.get(i)[1] > handInfo.get(i + 1)[1]))
                {
                    
                    
                    Double[] temp = handInfo.get(i);
                    handInfo.set(i, handInfo.get(i + 1));
                    handInfo.set(i + 1, temp);
                    
                    done = false;
                }
            }
        }
        
        for (int i = 0; i < handInfo.size(); i++)
        {
            answer += (i + 1) * handInfo.get(i)[2];
            
            //System.out.println(handInfo.get(i)[0] + " " + handInfo.get(i)[1] + " " + handInfo.get(i)[2] + " " + (i + 1));
        }
        
        System.out.println(answer);
    }
}
