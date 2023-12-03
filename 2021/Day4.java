
/**
 * Write a description of class Day2 here.
 *
 * @author (your name)
 * @version (a version number or a date)
 */

import java.io.*;
import java.util.Scanner;

public class Day4 
{
    public static void main (String args[]) throws Exception
    {
        Scanner scn = new Scanner(new File("input.txt"));
        
        String calls = scn.nextLine();
        Scanner callScn = new Scanner(calls);
        callScn.useDelimiter(",");
        
        int[][][] boards = new int[100][5][5]; // board number (100), row (5 each), colume (5 each) 
        
        int boardNum = -1;
        int rowNum = -1;
        int columeNum = -1;
        
        while (scn.hasNext())
        {
            String data = scn.nextLine();
            
            if(data.equals(""))
            {
                boardNum++;
                rowNum = -1;
            }
            else
            {
                rowNum++;
                columeNum = -1; 
                
                Scanner rowScn = new Scanner(data);
                while (columeNum < 4)
                {
                    String bingoNum = rowScn.next();
                    if (bingoNum.equals("") == false)
                    {
                        columeNum++;
                        boards[boardNum][rowNum][columeNum] = Integer.parseInt(bingoNum);
                    }
                }
            }
        }
                
        
        
        
        
        while (callScn.hasNext())
        {
            int call = Integer.parseInt(callScn.next());
            
            boardNum = -1;
            for (int[][] singleBoard : boards)
            {
                boardNum++;
                rowNum = -1;
                for (int[] singleRow : singleBoard)
                {
                    rowNum++;
                    columeNum = -1;
                    for (int bingoNum : singleRow)
                    {
                        columeNum++;
                        
                        if (bingoNum == call)
                        {
                            boards[boardNum][rowNum][columeNum] = 777;
                        } 
                    }
                }
            }
            
            for (int board = 0; board < 100; board++)
            {
                for (int row = 0; row < 5; row++)
                {
                    if (boards[board][row][0] == 777 && boards[board][row][1] == 777 && boards[board][row][2] == 777 && boards[board][row][3] == 777 && boards[board][row][4] == 777)
                    {
                        //System.out.println("Winner is board " + board);
                        //System.out.println("last call is " + call);
                        //System.out.println();
                        win(boards, board, call);
                    }
                }
                
                for (int colume = 0; colume < 5; colume++)
                {
                    if (boards[board][0][colume] == 777 && boards[board][1][colume] == 777 && boards[board][2][colume] == 777 && boards[board][3][colume] == 777 && boards[board][4][colume] == 777)
                    {
                        //System.out.println("Winner is board " + board);
                        //System.out.println("last call is " + call);
                        //System.out.println();
                        win(boards, board, call);
                    }
                }
                
                if (boards[board][0][0] == 777 && boards[board][1][1] == 777 && boards[board][2][2] == 777 && boards[board][3][3] == 777 && boards[board][4][4] == 777)
                {
                    //System.out.println("Winner is board " + board);
                    //System.out.println("last call is " + call);
                    //System.out.println();
                    win(boards, board, call);
                }
                
                if (boards[board][4][0] == 777 && boards[board][3][1] == 777 && boards[board][2][2] == 777 && boards[board][1][3] == 777 && boards[board][0][4] == 777)
                {
                    //System.out.println("Winner is board " + board);
                    //System.out.println("last call is " + call);
                    //System.out.println();
                    win(boards, board, call);
                }
            }
        }
    }
    
    static int winners = 0;
    static int[] previousWinners = new int[100];
    public static void win (int[][][] boards, int board, int call)
    {
        int sum = 0;
        for (int[] row : boards[board])
        {
            for (int num : row)
            {
                if (num != 777)
                {
                    sum += num;
                }
            }
        }
        
        
        
        boolean pass = true;
        for (int prevWin : previousWinners)
        {
            if (board == prevWin)
            {
                pass = false;
            }
        }
        
        if (pass)
        {
            previousWinners[winners] += board;
            winners++;
            System.out.println("winners: " + winners);
            System.out.println("board: " + board);
            System.out.println();
            if (winners == 99)
            {
                System.out.println(sum * call);
                new Scanner(System.in).next();
            }
        }
    }
}
