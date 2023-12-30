import java.util.*;
import java.io.*;
// Uses the shape class!!!

public class Day18
{
    public static void main(String[] args) throws IOException
    {
        File file = new File("input.txt");
        Scanner scn = new Scanner(file);
        
        long answer = 0;
        
        // one of these will be the total area, the other will be just the internal area
        ArrayList<long[]> points1 = new ArrayList<long[]>(); // coords (x, y)
        ArrayList<long[]> points2 = new ArrayList<long[]>(); // coords (x, y)
        
        ArrayList<long[]> moves = new ArrayList<long[]>(); // coords (dir, dist)
        ArrayList<Boolean> turns = new ArrayList<Boolean>();
        
        long dir = -1; // 0 == right, 1 == down, 2 == left, 3 == up
        long[] pos1 = new long[2]; // (x, y)
        long[] pos2 = new long[2]; // (x, y)
        
        while (scn.hasNextLine())
        {
            // part 2 stuff
            scn.next();
            scn.next();
            
            String color = scn.next();
            
            color = color.replace("#", "");
            color = color.replace("(", "");
            color = color.replace(")", "");
            
            String s = color.substring(0, color.length() - 1);
            String d = color.substring(color.length() - 1);
            
            long steps = Long.parseLong(s, 16);
            long direction = Long.valueOf(d);
            
            long[] toAdd = new long[2];
            toAdd[0] = direction;
            toAdd[1] = steps;
            
            moves.add(toAdd);
            
            
            // part 1 stuff
            /*
            String data = scn.nextLine();
            
            data = data.replace("R", "0");
            data = data.replace("D", "1");
            data = data.replace("L", "2");
            data = data.replace("U", "3");
            
            Scanner dataScn = new Scanner(data);
            
            
            long direction = dataScn.nextLong();
            long steps = dataScn.nextLong();
            String color = dataScn.next(); // NOTE: may need to remove # or () for part 2!!! 
            
            
            long[] toAdd = new long[2];
            toAdd[0] = direction;
            toAdd[1] = steps;
            
            moves.add(toAdd);
            */
        }
        
        dir = moves.get(moves.size() - 1)[0]; // get the initial direction before the first move
        for (int i = 0; i < moves.size(); i++)
        {      
            long[] move = moves.get(i);
            
            long nextDir = move[0];
            boolean turn = false; // false == turn left, true == turn right
            
            if (nextDir == (dir + 1) % 4)
            {
                turn = true;
            }
            
            turns.add(turn);
            
            dir = nextDir;
        }
        
        
        for (int i = 0; i < moves.size(); i++)
        {
            // System.out.println(pos2[0] + ", " + pos2[1]); // DEBUG
            
            
            points1.add(copyPoint(pos1));
            points2.add(copyPoint(pos2));
            
            
            long[] move = moves.get(i);
            
            long nextDir = move[0];
            long rawSteps = move[1];
            
            boolean previousTurn = turns.get(i);
            boolean nextTurn = turns.get((i + 1) % turns.size()); // turns.size() should equal moves.size();
            
            long steps1 = rawSteps;
            long steps2 = rawSteps;
            
            if (previousTurn == false && nextTurn == false)
            {
                steps1++;
                steps2--;
            }
            else if (previousTurn == true && nextTurn == true)
            {
                steps1--;
                steps2++;
            }
            
            if (nextDir == 0) // right
            {
                pos1[0] += steps1;
                pos2[0] += steps2;
            }
            else if (nextDir == 1) // down
            {
                pos1[1] -= steps1;
                pos2[1] -= steps2;
            }
            else if (nextDir == 2) // left
            {
                pos1[0] -= steps1;
                pos2[0] -= steps2;
            }
            else if (nextDir == 3) // up
            {
                pos1[1] += steps1;
                pos2[1] += steps2;
            }
            else System.out.println("Direction ERROR");
        }
        
        
        Shape shape1 = new Shape(points1);
        Shape shape2 = new Shape(points2);
        
        long potentialAnswer1 = shape1.getArea();
        long potentialAnswer2 = shape2.getArea();
        
        answer = (potentialAnswer1 > potentialAnswer2) ? potentialAnswer1 : potentialAnswer2;
        
        
        System.out.println(answer);
    }
    
    // Helper methods below
    public static long[] copyPoint(long[] point) // copies a given point
    {
        long[] temp = new long[2];
        temp[0] = point[0]; // copy x
        temp[1] = point[1]; // copy y
        
        return temp;
    }
}
