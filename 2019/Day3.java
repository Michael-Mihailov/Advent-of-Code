import java.util.*;
import java.io.*;

public class Day3
{
    public static void main(String args[]) throws IOException
    {
        File file = new File("input.txt");
        Scanner scn = new Scanner(file);
        
        String[] wire1 = scn.nextLine().split(",");
        String[] wire2 = scn.nextLine().split(",");
        
        int answer = Integer.MAX_VALUE;
        
        int x1 = 0;
        int y1 = 0;
        int x2 = 0;
        int y2 = 0;
        
        ArrayList<int[]> wire1Positions = new ArrayList<int[]>();
        ArrayList<int[]> wire2Positions = new ArrayList<int[]>();
        int[] startPos = {0, 0};
        wire1Positions.add(startPos);
        wire2Positions.add(startPos);
        
        for (int step = 0; step < wire1.length; step++)
        {
            int newX1 = x1;
            int newY1 = y1;
            int newX2 = x2;
            int newY2 = y2;
            
            if (wire1[step].charAt(0) == 'R')
            {
                newX1 += Integer.parseInt(wire1[step].substring(1));
            }
            else if (wire1[step].charAt(0) == 'L')
            {
                newX1 -= Integer.parseInt(wire1[step].substring(1));
            }
            else if (wire1[step].charAt(0) == 'U')
            {
                newY1 += Integer.parseInt(wire1[step].substring(1));
            }
            else if (wire1[step].charAt(0) == 'D')
            {
                newY1 -= Integer.parseInt(wire1[step].substring(1));
            }
            
            if (wire2[step].charAt(0) == 'R')
            {
                newX2 += Integer.parseInt(wire2[step].substring(1));
            }
            else if (wire2[step].charAt(0) == 'L')
            {
                newX2 -= Integer.parseInt(wire2[step].substring(1));
            }
            else if (wire2[step].charAt(0) == 'U')
            {
                newY2 += Integer.parseInt(wire2[step].substring(1));
            }
            else if (wire2[step].charAt(0) == 'D')
            {
                newY2 -= Integer.parseInt(wire2[step].substring(1));
            }
            
            x1 = newX1;
            y1 = newY1;
            x2 = newX2;
            y2 = newY2;
            
            int[] w1Pos = {x1, y1};
            int[] w2Pos = {x2, y2};
            wire1Positions.add(w1Pos);
            wire2Positions.add(w2Pos);    
        }
        
        int total1 = 0;
        for (int i = 1; i < wire1Positions.size(); i++)
        {
            total1 += dist(wire1Positions.get(i-1)[0], wire1Positions.get(i-1)[1], wire1Positions.get(i)[0], wire1Positions.get(i)[1]);
            
            int total2 = 0;
            for (int j = 1; j < wire2Positions.size(); j++)
            {
                total2 += dist(wire2Positions.get(j-1)[0], wire2Positions.get(j-1)[1], wire2Positions.get(j)[0], wire2Positions.get(j)[1]);
                
                int[] temp = interDist(
                wire1Positions.get(i-1)[0], wire1Positions.get(i)[0], wire1Positions.get(i-1)[1], wire1Positions.get(i)[1],
                wire2Positions.get(j-1)[0], wire2Positions.get(j)[0], wire2Positions.get(j-1)[1], wire2Positions.get(j)[1]);
                
                if (temp != null && !(temp[0] == 0 && temp[1] == 0))
                {
                    int extra = dist(wire1Positions.get(i)[0], wire1Positions.get(i)[1], temp[0], temp[1]) + dist(wire2Positions.get(j)[0], wire2Positions.get(j)[1], temp[0], temp[1]);
                    
                    //System.out.println(total1 + total2 - extra);
                    
                    if (total1 + total2 - extra < answer) answer = total1 + total2 - extra;
                }
                
                //if (temp < answer) answer = temp;
            }
        }
        
        System.out.println(wire1.length == wire2.length); // must be true
        System.out.println(answer);
    }
    
    public static int dist(int x1, int y1, int x2, int y2)
    {
        return Math.abs(x1 - x2) + Math.abs(y1 - y2);
    }
    
    public static int[] interDist(int x1Start, int x1End, int y1Start, int y1End, int x2Start, int x2End, int y2Start, int y2End)
    {
        //int ans = Integer.MAX_VALUE;
        
        if (x1Start > x1End)
        {
            int temp = x1Start;
            x1Start = x1End;
            x1End = temp;
        }
        if (y1Start > y1End)
        {
            int temp = y1Start;
            y1Start = y1End;
            y1End = temp;
        }
        if (x2Start > x2End)
        {
            int temp = x2Start;
            x2Start = x2End;
            x2End = temp;
        }
        if (y2Start > y2End)
        {
            int temp = y2Start;
            y2Start = y2End;
            y2End = temp;
        }
        
        if (x2Start == x2End && x1Start <= x2Start && x2Start <= x1End && y2Start <= y1Start && y1Start <= y2End)
        {
            //ans = Math.abs(y1Start) + Math.abs(x2Start);
            return new int[]{x2Start, y1Start};
        }
        else if (y2Start == y2End && y1Start <= y2Start && y2Start <= y1End && x2Start <= x1Start && x1Start <= x2End)
        {
            //ans = Math.abs(x1Start) + Math.abs(y2Start);
            return new int[]{x1Start, y2Start};
        }
                
        //return (ans > 0 ? ans : Integer. MAX_VALUE);
        return null;
    }
}
