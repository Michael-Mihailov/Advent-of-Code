import java.util.*;
import java.io.*;

public class Day23
{
    public static void main(String args[]) throws IOException
    {        
        File file = new File("input.txt");
        Scanner scn = new Scanner(file);
        String[] input = scn.nextLine().split("|");
        
        
        HashMap<Integer, LinkCup> cupMap = new HashMap();
        LinkCup headCup = null;
        LinkCup tailCup = null;
        
        for (String cup : input)
        {
            LinkCup temp = new LinkCup(Integer.parseInt(cup), null); 
            cupMap.put(temp.getNum(), temp);
            
            if (headCup == null)
            {
                headCup = temp;
            }
            else
            {
                tailCup.setNextCup(temp);
            }
            
            tailCup = temp;
        }
        for (int i = headCup.chainSize() + 1; i <= 1000000; i++)
        {
            LinkCup temp = new LinkCup(i, null); 
            cupMap.put(temp.getNum(), temp);
            tailCup.setNextCup(temp);
            tailCup = temp;
        }
        
        int chainSize = headCup.chainSize();
        
        
        for (int move = 0; move < 10000000; move++)
        {            
            int currentCupNum = headCup.getNum();
            LinkCup moveCupHead = headCup.getNextCup();
            LinkCup moveCupTail = moveCupHead.getNextCup().getNextCup();
            
            headCup.setNextCup(moveCupTail.getNextCup());
            moveCupTail.setNextCup(null);
            
            int destinationCupNum = -1;
            while (destinationCupNum == -1)
            {
                if (currentCupNum <= 1) currentCupNum = chainSize + 1;
                
                if (moveCupHead.contains(currentCupNum - 1) == false)
                {
                    destinationCupNum = currentCupNum - 1;
                    break;
                }
                
                currentCupNum--;
            }
            
            LinkCup destinationCup = cupMap.get(destinationCupNum);
            moveCupTail.setNextCup(destinationCup.getNextCup());
            destinationCup.setNextCup(moveCupHead);
            
            tailCup = tailCup.getTailCup(); // in case if stuff was placed after the old tail
            tailCup.setNextCup(headCup);
            tailCup = headCup;
            headCup = headCup.getNextCup();
            tailCup.setNextCup(null);
        }
        
        
        long ans = 0;
        tailCup.setNextCup(headCup); // make the "linkedList" circular (MAKES SOME FUNCTIONS DANGEROUS)
        LinkCup daOne = cupMap.get(1);
        ans = (long)daOne.getNextCup().getNextCup().getNum() * (long)daOne.getNextCup().getNum();
        
        System.out.println(ans);
    }
}