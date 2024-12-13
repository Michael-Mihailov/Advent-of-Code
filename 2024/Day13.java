import java.util.*;
import java.io.*;

public class Day13
{
    public static void main(String[] args) throws IOException
    {
        File file = new File("input.txt");
        Scanner scn = new Scanner(file);
        
        long ans = 0;
        
        while (scn.hasNextLine())
        {            
            scn.next();
            scn.next();
            String s1 = scn.next();
            String s2 = scn.next();
            scn.next();
            scn.next();
            String s3 = scn.next();
            String s4 = scn.next();
            scn.next();
            String s5 = scn.next();
            String s6 = scn.next();
            
            try {scn.nextLine();} catch (Exception e) {}
            
            long ax = Long.parseLong(s1.substring(2, s1.length() - 1));
            long ay = Long.parseLong(s2.substring(2));
            long bx = Long.parseLong(s3.substring(2, s3.length() - 1));
            long by = Long.parseLong(s4.substring(2));
            
            long px = Long.parseLong(s5.substring(2, s5.length() - 1));
            long py = Long.parseLong(s6.substring(2));
            
            px += 10000000000000l;
            py += 10000000000000l;
            
            System.out.println(ax + " " + ay + " " + bx + " " + by + " " + px + " " + py);
            
            final long BILL = 1000000000;
            final long MILL = 1000000;
            final long THOU = 1000;
            
            long minTokens = Long.MAX_VALUE;
            
            for (long aPressBillion = 0; aPressBillion < 20000; aPressBillion++)
            {
                for (long bPressBillion = 0; bPressBillion < 20000; bPressBillion++)
                {
                    if(ax * (aPressBillion * BILL) + bx * (bPressBillion * BILL) <= px 
                    && ax * ((aPressBillion + 1) * BILL) + bx * ((bPressBillion + 1) * BILL) > px
                    && ay * (aPressBillion * BILL) + by * (bPressBillion * BILL) <= py 
                    && ay * ((aPressBillion + 1) * BILL) + by * ((bPressBillion + 1) * BILL) > py)
                    {
                        //System.out.println("test1");
                        for (long aPressMillion = 0; aPressMillion < 1000; aPressMillion++)
                        {
                            for (long bPressMillion = 0; bPressMillion < 1000; bPressMillion++)
                            {
                                if(ax * (aPressBillion * BILL + aPressMillion * MILL) + bx * (bPressBillion * BILL + bPressMillion * MILL) <= px 
                                && ax * (aPressBillion * BILL + (aPressMillion + 1) * MILL) + bx * (bPressBillion * BILL + (bPressMillion + 1) * MILL) > px
                                && ay * (aPressBillion * BILL + aPressMillion * MILL) + by * (bPressBillion * BILL + bPressMillion * MILL) <= py 
                                && ay * (aPressBillion * BILL + (aPressMillion + 1) * MILL) + by * (bPressBillion * BILL + (bPressMillion + 1) * MILL) > py)
                                {
                                    //System.out.println("test2");
                                    for (long aPressThousand = 0; aPressThousand < 1000; aPressThousand++)
                                    {
                                        for (long bPressThousand = 0; bPressThousand < 1000; bPressThousand++)
                                        {
                                            if(ax * (aPressBillion * BILL + aPressMillion * MILL + aPressThousand * THOU) + bx * (bPressBillion * BILL + bPressMillion * MILL + bPressThousand * THOU) <= px 
                                            && ax * (aPressBillion * BILL + aPressMillion * MILL + (aPressThousand + 1) * THOU) + bx * (bPressBillion * BILL + bPressMillion * MILL + (bPressThousand + 1) * THOU) > px
                                            && ay * (aPressBillion * BILL + aPressMillion * MILL + aPressThousand * THOU) + by * (bPressBillion * BILL + bPressMillion * MILL + bPressThousand * THOU) <= py 
                                            && ay * (aPressBillion * BILL + aPressMillion * MILL + (aPressThousand + 1) * THOU) + by * (bPressBillion * BILL + bPressMillion * MILL + (bPressThousand + 1) * THOU) > py)
                                            {
                                                //System.out.println("test3");
                                                for (long aPress = 0; aPress <= 1000; aPress++)
                                                {
                                                    for (long bPress = 0; bPress <= 1000; bPress++)
                                                    {
                                                        //if(ax * (aPressBillion * BILL + aPressMillion * MILL + aPressThousand * THOU + aPress) + bx * (bPressBillion * BILL + bPressMillion * MILL + bPressThousand * THOU + bPress) == px) System.out.println("case1");
                                                        //if(ay * (aPressBillion * BILL + aPressMillion * MILL + aPressThousand * THOU + aPress) + by * (bPressBillion * BILL + bPressMillion * MILL + bPressThousand * THOU + bPress) == py) System.out.println("case2");
                                                        
                                                        if(ax * (aPressBillion * BILL + aPressMillion * MILL + aPressThousand * THOU + aPress) + bx * (bPressBillion * BILL + bPressMillion * MILL + bPressThousand * THOU + bPress) == px 
                                                        && ay * (aPressBillion * BILL + aPressMillion * MILL + aPressThousand * THOU + aPress) + by * (bPressBillion * BILL + bPressMillion * MILL + bPressThousand * THOU + bPress) == py)
                                                        {
                                                            //System.out.println("test4");
                                                            long temp = 3 * (aPressBillion * BILL + aPressMillion * MILL + aPressThousand * THOU + aPress) + (bPressBillion * BILL + bPressMillion * MILL + bPressThousand * THOU + bPress);
                                                            if (temp < minTokens)
                                                            {
                                                                minTokens = temp;
                                                                //System.out.println(minTokens);
                                                            }
                                                            
                                                        }
                                                    }
                                                }
                                                
                                            }
                                        }
                                    }
                                    
                                }
                            }
                        }
                    }
                        
                }
            }
            
            if (minTokens != Long.MAX_VALUE)
            {
                ans += minTokens;
                
                System.out.println(minTokens);
            }
        }
        
        System.out.println("answer: " + ans);
    }
}

/*
int minTokens = Integer.MAX_VALUE;
for (int aPress = 0; aPress <= 100; aPress++)
{
    for (int bPress = 0; bPress <= 100; bPress++)
    {
        if (ax * aPress + bx * bPress == px && ay * aPress + by * bPress == py)
        {
            if (aPress * 3 + bPress < minTokens)
            {
                minTokens = aPress * 3 + bPress;
            }
        }
    }
}

if (minTokens != Integer.MAX_VALUE)
{
    ans += minTokens;
}
*/