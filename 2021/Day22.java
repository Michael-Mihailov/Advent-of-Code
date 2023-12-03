
import java.io.*;
import java.util.*;
import java.math.*;

public class Day22
{
    public static void main (String args[]) throws Exception
    {
        File file = new File("input.txt");
        Scanner scn = new Scanner(file);
        
        ArrayList<Cube> cubesOld = new ArrayList(); // list of cubes from input data
        ArrayList<Cube> cubesNew = new ArrayList(); // broken down into all cubes neccessary to find total area
        
        while (scn.hasNext()) // read input data into "cubesOld"
        {
            Scanner dataScn = new Scanner(scn.nextLine());
            
            boolean onOff = dataScn.next().equals("on");
            
            dataScn.useDelimiter(",");
            
            String xStr = dataScn.next();
            String yStr = dataScn.next();
            String zStr = dataScn.next();
            
            int x1 = Integer.parseInt(xStr.substring(xStr.indexOf("=") + 1, xStr.indexOf("..")));
            int x2 = Integer.parseInt(xStr.substring(xStr.indexOf("..") + 2));
            
            int y1 = Integer.parseInt(yStr.substring(yStr.indexOf("=") + 1, yStr.indexOf("..")));
            int y2 = Integer.parseInt(yStr.substring(yStr.indexOf("..") + 2));
            
            int z1 = Integer.parseInt(zStr.substring(zStr.indexOf("=") + 1, zStr.indexOf("..")));
            int z2 = Integer.parseInt(zStr.substring(zStr.indexOf("..") + 2));
            
            cubesOld.add(new Cube(x1, x2, y1, y2, z1, z2, onOff));
        }
        
        for (Cube oldCube : cubesOld)
        {
            int i;
            int stop;
            int step;
            
            if (oldCube.getStatus() == false)
            {
                i = cubesNew.size() - 1;
                stop = -1;
                step = -1;
            }
            else
            {
                i = 0;
                stop = cubesNew.size();
                step = 1;
            }
            
            for (; i != stop; i += step)
            {
                if (oldCube.intercepting(cubesNew.get(i)))
                {
                    Cube c = oldCube.overlap(cubesNew.get(i));
                    if (cubesNew.get(i).getStatus() == true)
                    {
                        c.setStatus(false);
                    }
                    else
                    
                    {
                        c.setStatus(true);
                    }
                    
                    cubesNew.add(c);
                }
            }
            
            if (oldCube.getStatus() == true)
            {
                cubesNew.add(oldCube);
            }
        }
        
        System.out.println(cubesOld.size());
        System.out.println(cubesNew.size());
        
        BigInteger count = new BigInteger("0");
        for (Cube newCube : cubesNew)
        {
            BigInteger num = newCube.getArea();
            if (newCube.getStatus() == false)
            {
                count = count.subtract(num);
            }
            else
            {
                count = count.add(num);
            }
        }
        
        System.out.println(count);
    }
}
