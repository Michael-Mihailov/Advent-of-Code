
import java.util.*;

public class Tuple implements Comparable
{
    public ArrayList<Object> contents = new ArrayList();
    public int tupleLength = 0;
    
    public Tuple()
    {
        String numStr = "";
        
        boolean closeTuple = false;
        while (closeTuple == false)
        {
            char data = Day13.packetStr.charAt(0); // get the next char in the string
            Day13.packetStr = Day13.packetStr.substring(1); // deleat that char from the string
            
            if (data == '[') // add new nested Tuple
            {
                contents.add(new Tuple());
            }
            else if (data == ']') // close current Tuple
            {
                if (numStr != "")
                {
                    contents.add(Integer.parseInt(numStr));
                    // tupleLength++; OBSOLETE
                }
                closeTuple = true;
            }
            else if (data == ',') // end Current Integer
            {
                if (numStr != "")
                {
                    contents.add(Integer.parseInt(numStr));
                }
                // tupleLength++; OBSOLETE
            }
            else // add digit to current Integer-String
            {
                numStr += data;
            }
        }
        
        tupleLength = contents.size();
    }
    
    public Tuple(Integer value)
    {
        tupleLength = 1;
        contents.add(value);
        
        //System.out.println("Hello? " + value);
    }
    
    public int compareTo(Object other)
    {
        //System.out.println("Hi");
        
        int winner = 0;
        
        Tuple left = this;  //System.out.println(left);
        Tuple right = (Tuple) other;  //System.out.println(right);
        
        Tuple shorter;
        int leftLengthWin; // -1  == shorter, 0 == equal, 1 == longer
        
        if (left.tupleLength < right.tupleLength)
        {
            shorter = left;
            leftLengthWin = -1;
        }
        else if (left.tupleLength > right.tupleLength)
        {
            shorter = right;
            leftLengthWin = 1;
        }
        else
        {
            shorter = left; // could be assigned to either left or right
            leftLengthWin = 0;
        }
        
        for (int i = 0; i < shorter.tupleLength && winner == 0; i++)
        {
            // Tuple - Tuple comparison
            if ((left.contents.get(i) instanceof Integer) == false && (right.contents.get(i) instanceof Integer) == false)
            {
                Tuple t1 = (Tuple) left.contents.get(i);
                Tuple t2 = (Tuple) right.contents.get(i);
                
                winner = t1.compareTo(t2);
            }
            
            // Int - Tuple comparison
            if ((left.contents.get(i) instanceof Integer) == true && (right.contents.get(i) instanceof Integer) == false)
            {
                Integer t1 = (Integer) left.contents.get(i);
                Tuple t2 = (Tuple) right.contents.get(i);
                
                winner = (new Tuple(t1)).compareTo(t2);
            }
            
            // Tuple - Int comparison
            if ((left.contents.get(i) instanceof Integer) == false && (right.contents.get(i) instanceof Integer) == true)
            {
                Tuple t1 = (Tuple) left.contents.get(i);
                Integer t2 = (Integer) right.contents.get(i);
                
                winner = t1.compareTo(new Tuple(t2));
            }
            
            // Int - Int comparison
            if ((left.contents.get(i) instanceof Integer) == true && (right.contents.get(i) instanceof Integer) == true)
            {
                Integer t1 = (Integer) left.contents.get(i);
                Integer t2 = (Integer) right.contents.get(i);
                
                winner = t1.compareTo(t2);
                //System.out.println("Hello there");
            }
        }
        
        if (winner == 0)
        {
            winner = leftLengthWin;
            //System.out.println("Length Win: " + leftLengthWin);
            //System.out.println("Left: " + left.tupleLength + " :: Right: " + right.tupleLength);
        }
        
        //System.out.println(winner);
        
        return winner; // IMPORTANT: Make sure to return the comparison evaluation when it is complete and terminate the compareTo method
    }
}
