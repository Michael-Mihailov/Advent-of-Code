import java.util.*;
import java.io.*;

public class Day21
{    
    public static void main(String[] args) throws IOException
    {
        File file = new File("input.txt");
        Scanner scn = new Scanner(file);
        
        int middleMenCount = 25; // the number of robots in between (DIFFERENT BETWEEN PARTS 1 / 2)
        
        long ans = 0;
        
        char[][] numberPad = new char[][] // start == (3, 2)
        {{'7','8','9'},
         {'4','5','6'},
         {'1','2','3'},
         {'#','0','A'}};
        char[][] directionPad = new char[][] // start == (0, 2)
        {{'#','^','A'},
         {'<','v','>'}};
                
        HashMap<String, Long> shortCutMap = new HashMap(); // takes a directionPad sequence and gives the minimum number of presses :: example: "<A" == number of presses to reach '<' from 'A' 
        
        char[] directions = new char[]{'>','v','<','^','A'};
        for (char dStart : directions)
        {
            for (char dEnd : directions)
            {
                shortCutMap.put(dStart + "" + dEnd, 1l); // always takes 1 press for 'me' to press a button
            }
        }
        
        for (int m = 0; m < middleMenCount; m++)
        {
            HashMap<String, Long> newShortCutMap = new HashMap();
            for (char dStart : directions)
            {
                for (char dEnd : directions)
                {
                    newShortCutMap.put(dStart + "" + dEnd, shortestSequence(dEnd + "", 'A', 1, dStart, numberPad, directionPad, 2, shortCutMap));
                }
            }
            shortCutMap = newShortCutMap;
        }
        
        while (scn.hasNextLine())
        {
            String code = scn.nextLine();
            long numeric = Integer.parseInt(code.substring(0, code.length() - 1));
            long shortestLength = shortestSequence(code, 'A', 0, 'A', numberPad, directionPad, 1, shortCutMap);
            
            long temp = numeric * shortestLength;
            System.out.println(temp);
            ans += temp;
        }
        
        System.out.println("Answer: " + ans);
    }
    
    /*
     * String goal: The combination we are currently trying to type
     * char parentPos: the key the 'parent' is hovering over
     * int depth: [example] 0 == numPad, 1 == dirPad-3, 2 == dirPad-2, 3 == dirPad-1
     * char currentPos: the key 'we' are hovering over
     */
    public static long shortestSequence(String goal, char parentPos, int depth, char currentPos, char[][] numPad, char[][] dirPad, int maxDepth, HashMap<String, Long> shortCutMap)
    {
        if (goal.length() == 0) return 0;
        if (depth == maxDepth) return shortCutMap.get(currentPos + goal); // takes one press to click any button on 'master' directionPad
        
        char[][] pad;
        if (depth == 0) pad = numPad;
        else pad = dirPad;
                
        char goalChar = goal.charAt(0);
        
        int[] displacement = calculateDisplacement(currentPos, goalChar, pad);
        
        if (displacement[0] == 0 && displacement[1] == 0) // on top of goalChar :: must press 'A' on 'parent' pad
        {
            return shortestSequence("A", 'A', depth+1, parentPos, numPad, dirPad, maxDepth, shortCutMap) + shortestSequence(goal.substring(1), 'A', depth, goalChar, numPad, dirPad, maxDepth, shortCutMap);
        }
        
        long bestScore = Long.MAX_VALUE;
        if (displacement[0] != 0) // y-dist
        {
            if (displacement[0] > 0) // move down
            {
                char newPos = move(currentPos, 1, pad);
                if (newPos != '#')
                {
                    long score = shortestSequence("v", 'A', depth+1, parentPos, numPad, dirPad, maxDepth, shortCutMap) + shortestSequence(goal, 'v', depth, newPos, numPad, dirPad, maxDepth, shortCutMap);
                    if (score < bestScore) bestScore = score;
                }
            }
            else if (displacement[0] < 0) // move up
            {
                char newPos = move(currentPos, 3, pad);
                if (newPos != '#')
                {
                    long score = shortestSequence("^", 'A', depth+1, parentPos, numPad, dirPad, maxDepth, shortCutMap) + shortestSequence(goal, '^', depth, newPos, numPad, dirPad, maxDepth, shortCutMap);
                    if (score < bestScore) bestScore = score;
                }
            }
        }
        if (displacement[1] != 0) // x-dist
        {
            if (displacement[1] > 0) // move right
            {
                char newPos = move(currentPos, 0, pad);
                if (newPos != '#')
                {
                    long score = shortestSequence(">", 'A', depth+1, parentPos, numPad, dirPad, maxDepth, shortCutMap) + shortestSequence(goal, '>', depth, newPos, numPad, dirPad, maxDepth, shortCutMap);
                    if (score < bestScore) bestScore = score;
                }
            }
            else if (displacement[1] < 0) // move left
            {
                char newPos = move(currentPos, 2, pad);
                if (newPos != '#')
                {
                    long score = shortestSequence("<", 'A', depth+1, parentPos, numPad, dirPad, maxDepth, shortCutMap) + shortestSequence(goal, '<', depth, newPos, numPad, dirPad, maxDepth, shortCutMap);
                    if (score < bestScore) bestScore = score;
                }
            }
        }
        
        return bestScore;
    }
    
    public static char move(char pos, int dir, char[][] pad) // dir: 0 == right, 1 == down, 2 == left, 3 == up
    {
        int[][] dirs = new int[][]{{0,1},{1,0},{0,-1},{-1,0}};
        
        
        int row = 0;
        int col = 0;
        
        for (int y = 0; y < pad.length; y++)
        {
            for (int x = 0; x < pad[y].length; x++)
            {
                if (pad[y][x] == pos)
                {
                    row = y;
                    col = x;
                }
            }
        }
        try
        {    
            char ans = pad[row+dirs[dir][0]][col+dirs[dir][1]];
            return ans;
        } catch (Exception e) {};
        
        return '#'; // means move was invalid
    }
    
    public static int[] calculateDisplacement (char pos, char goal, char[][] pad)
    {
        int row = 0;
        int col = 0;
        
        for (int y = 0; y < pad.length; y++)
        {
            for (int x = 0; x < pad[y].length; x++)
            {
                if (pad[y][x] == pos)
                {
                    row = y;
                    col = x;
                }
            }
        }
        
        for (int y = 0; y < pad.length; y++)
        {
            for (int x = 0; x < pad[y].length; x++)
            {
                if (pad[y][x] == goal)
                {
                    return new int[]{y-row, x-col};
                }
            }
        }
        return null; // should not be reached
    }
}