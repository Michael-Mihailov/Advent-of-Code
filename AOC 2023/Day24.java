import java.util.*;
import java.io.*;

public class Day24
{
    public static void main(String[] args) throws IOException
    {
        File file = new File("input.txt");
        Scanner scn = new Scanner(file);
        
        final long min = 200000000000000L;
        final long max = 400000000000000L;
        
        int answer = 0;
        
        ArrayList<long[]> hailList = new ArrayList();
        
        while (scn.hasNextLine())
        {
            String data = scn.nextLine();
            
            data = data.replace(" ", "");
            data = data.replace("@", ",");
            
            long[] hail = new long[6];
            
            String[] dataArr = data.split(","); // length == 6
            for (int i = 0; i < 6; i++)
            {
                hail[i] = Long.valueOf(dataArr[i]);
            }
            
            hailList.add(hail);
        }
        
        for (int h1 = 0; h1 < hailList.size(); h1++)
        {
            boolean intercept = false;
            long[] hail1 = hailList.get(h1);
            
            long xInit1 = hail1[0];
            long yInit1 = hail1[1];
            double slope1 = (double)hail1[4] / (double)hail1[3];
            

            for (int h2 = h1 + 1; h2 < hailList.size(); h2++)
            {     
                double x = 0;
                double y = 0;
                
                long[] hail2 = hailList.get(h2);
                                
                long xInit2 = hail2[0];
                long yInit2 = hail2[1];
                double slope2 = (double)hail2[4] / (double)hail2[3];
                
                
                if (slope1 == slope2) continue; // parrallel lines
                
                
                // x given y are equal
                x = (slope2 * xInit2) - (slope1 * xInit1) + yInit1 - yInit2;
                x /= (slope2 - slope1);
                
                y = (slope1 * (x - xInit1)) + yInit1;
                
                
                if(hail1[3] < 0 && x > xInit1) continue; // past for h1
                if(hail1[3] > 0 && x < xInit1) continue; // past for h1
                if(hail1[4] < 0 && y > yInit1) continue; // past for h1
                if(hail1[4] > 0 && y < yInit1) continue; // past for h1
                if(hail2[3] < 0 && x > xInit2) continue; // past for h2
                if(hail2[3] > 0 && x < xInit2) continue; // past for h2
                if(hail2[4] < 0 && y > yInit2) continue; // past for h2
                if(hail2[4] > 0 && y < yInit2) continue; // past for h2
                
                
                if (x >= min && x <= max && y >= min && y <= max)
                {
                    answer++;
                    
                    //System.out.println(x + " " + y);
                }
            }
            
            //System.out.println();
        }
        
        System.out.println("Answer: " + answer);
    }
}
