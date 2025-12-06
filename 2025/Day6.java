import java.util.*;
import java.io.*;

public class Day6
{
    public static void main(String[] args) throws IOException
    {
        File file = new File("input.txt");
        Scanner scn = new Scanner(file);
        
        long ans = 0;
        
        // ArrayList<String[]> data = new ArrayList();
        
        ArrayList<String[]> dataArr = new ArrayList();
        while (scn.hasNextLine())
        {
            String line = scn.nextLine();
            dataArr.add(line.split("|"));
        }
        
        ArrayList<String> currentEquation = new ArrayList();
        for (int i = dataArr.get(0).length - 1; i >= 0; i--) // col
        {
            String numStr = "";
            for (int j = 0; j < dataArr.size() - 1; j++) // row
            {
                if (dataArr.get(j)[i].equals(" ") == false)
                {
                    numStr += dataArr.get(j)[i];
                }
            }
            currentEquation.add(numStr);
            
            if (dataArr.getLast()[i].equals("+") == true)
            {
                long tot = 0;
                for (int j = 0; j < currentEquation.size(); j++)
                {
                    tot += Long.parseLong(currentEquation.get(j));
                }
                ans += tot;
                currentEquation.clear();
                i--;
            }
            else if (dataArr.getLast()[i].equals("*") == true)
            {
                long tot = 1;
                for (int j = 0; j < currentEquation.size(); j++)
                {
                    tot *= Long.parseLong(currentEquation.get(j));
                }
                ans += tot;
                currentEquation.clear();
                i--;
            }
        }
        
        /*
        while (scn.hasNextLine())
        {
            String line = scn.nextLine();
            String oldLine = line + "#";
            while (oldLine.length() > line.length())
            {
                oldLine = line;
                line = line.replace("  ", " ");
            }
            String[] stuff = line.split(" ");
            data.add(stuff);
        }
        
        for (int i = 0; i < data.get(0).length; i++)
        {
            String op = data.getLast()[i];
            if (op.equals("+"))
            {
                long tot = 0;
                for (int j = 0; j < data.size() - 1; j++)
                {
                    tot += Long.parseLong(data.get(j)[i]);
                }
                ans += tot;
            }
            else if (op.equals("*"))
            {
                long tot = 1;
                for (int j = 0; j < data.size() - 1; j++)
                {
                    tot *= Long.parseLong(data.get(j)[i]);
                }
                ans += tot;
            }
        }
        */
        System.out.println(ans);
    }
}