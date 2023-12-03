

/**
 * Write a description of class Day1 here.
 *
 * @author (your name)
 * @version (a version number or a date)
 */

import java.io.*;
import java.util.*;

public class Day9
{
    public static void main (String[] args) throws Exception
    {
        Scanner scn = new Scanner(new File("input.txt"));
        
        HashMap<String, Integer> map = new HashMap();
        
        int count = 0;
        
        final int length = 10;
        
        int[] hx = new int[length];
        int[] hy = new int[length];
        
        int[] hxPrev = new int[length];
        int[] hyPrev = new int[length];

        
        map.put("" + 0 + "+" + 0, new Integer(1));
        
        while (scn.hasNext())
        {
            String data = scn.nextLine();
            Scanner splitScn = new Scanner(data);
            
            String left = splitScn.next();
            int right = splitScn.nextInt();
            
            
                        
            if (left.equals("L"))
            {
                for (int i = 0; i < right; i++)
                {
                    hxPrev[0] = hx[0];
                    hyPrev[0] = hy[0];
                    
                    hx[0]--;
                    for (int r = 1; r < length; r++)
                    {
                        hxPrev[r] = hx[r];
                        hyPrev[r] = hy[r];
                        
                        if (Math.abs(hx[r] - hx[r-1]) > 1 || Math.abs(hy[r] - hy[r-1]) > 1)
                        {
                            if (hx[r-1] - hx[r] > 0)
                            {
                                hx[r]++;
                            }
                            else if (hx[r-1] - hx[r] < 0)
                            {
                                hx[r]--;
                            }
                            if (hy[r-1] - hy[r] > 0)
                            {
                                hy[r]++;
                            }
                            else if (hy[r-1] - hy[r] < 0)
                            {
                                hy[r]--;
                            }

                            if (r == length - 1)
                            {
                                map.put("" + hx[r] + "+" + hy[r], new Integer(1));
                            }
                        }
                    }
                }
            }
            else if (left.equals("R"))
            {
                for (int i = 0; i < right; i++)
                {
                    hxPrev[0] = hx[0];
                    hyPrev[0] = hy[0];
                    
                    hx[0]++;
                    for (int r = 1; r < length; r++)
                    {
                        hxPrev[r] = hx[r];
                        hyPrev[r] = hy[r];
                        
                        if (Math.abs(hx[r] - hx[r-1]) > 1 || Math.abs(hy[r] - hy[r-1]) > 1)
                        {
                            if (hx[r-1] - hx[r] > 0)
                            {
                                hx[r]++;
                            }
                            else if (hx[r-1] - hx[r] < 0)
                            {
                                hx[r]--;
                            }
                            if (hy[r-1] - hy[r] > 0)
                            {
                                hy[r]++;
                            }
                            else if (hy[r-1] - hy[r] < 0)
                            {
                                hy[r]--;
                            }

                            if (r == length - 1)
                            {
                                map.put("" + hx[r] + "+" + hy[r], new Integer(1));
                            }
                        }
                    }
                }
            }
            else if (left.equals("D"))
            {
                for (int i = 0; i < right; i++)
                {
                    hxPrev[0] = hx[0];
                    hyPrev[0] = hy[0];
                    
                    hy[0]--;
                    for (int r = 1; r < length; r++)
                    {
                        hxPrev[r] = hx[r];
                        hyPrev[r] = hy[r];
                        
                        if (Math.abs(hx[r] - hx[r-1]) > 1 || Math.abs(hy[r] - hy[r-1]) > 1)
                        {
                            if (hx[r-1] - hx[r] > 0)
                            {
                                hx[r]++;
                            }
                            else if (hx[r-1] - hx[r] < 0)
                            {
                                hx[r]--;
                            }
                            if (hy[r-1] - hy[r] > 0)
                            {
                                hy[r]++;
                            }
                            else if (hy[r-1] - hy[r] < 0)
                            {
                                hy[r]--;
                            }

                            if (r == length - 1)
                            {
                                map.put("" + hx[r] + "+" + hy[r], new Integer(1));
                            }
                        }
                    }
                }
            }
            else if (left.equals("U"))
            {
                for (int i = 0; i < right; i++)
                {
                    hxPrev[0] = hx[0];
                    hyPrev[0] = hy[0];
                    
                    hy[0]++;
                    for (int r = 1; r < length; r++)
                    {
                        hxPrev[r] = hx[r];
                        hyPrev[r] = hy[r];
                        
                        if (Math.abs(hx[r] - hx[r-1]) > 1 || Math.abs(hy[r] - hy[r-1]) > 1)
                        {
                            if (hx[r-1] - hx[r] > 0)
                            {
                                hx[r]++;
                            }
                            else if (hx[r-1] - hx[r] < 0)
                            {
                                hx[r]--;
                            }
                            if (hy[r-1] - hy[r] > 0)
                            {
                                hy[r]++;
                            }
                            else if (hy[r-1] - hy[r] < 0)
                            {
                                hy[r]--;
                            }

                            if (r == length - 1)
                            {
                                map.put("" + hx[r] + "+" + hy[r], new Integer(1));
                            }
                        }
                    }
                }
            }
            else
            {
               // System.out.println("Uh oh");
            }
        }
        
        for (String key : map.keySet())
        {
            count++;
        }
        
        System.out.println(count);
    }
}
