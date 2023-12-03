
/**
 * Write a description of class Day20 here.
 *
 * @author (your name)
 * @version (a version number or a date)
 */

import java.io.*;
import java.util.*;

public class Day20 // TODO: reread the prompt because I think I'm doing this wronge ??????
{
    public static void main (String[] args) throws IOException
    {
        File input = new File("input.txt");
        Scanner scn = new Scanner(input);
        
        ArrayList<String> particles = new ArrayList();
        
        int count = 0;
        
        while (scn.hasNextLine())
        {
            String data = scn.nextLine();
            
            data = data.replace("p", "");
            data = data.replace("v", "");
            data = data.replace("a", "");
            data = data.replace("=", "");
            data = data.replace("<", "");
            data = data.replace(">", "");
            data = data.replace(" ", "");
            
            
            particles.add(data);
        }
        ArrayList<String> colliders = (ArrayList<String>) particles.clone(); // for P2
        
        
        
        int minAcc = Integer.MAX_VALUE;
        for (int i = particles.size() - 1; i >= 0; i--)
        {
            String data = particles.get(i);
            
            Scanner dataScn = new Scanner(data);
            dataScn.useDelimiter(",");
            
            dataScn.next();
            dataScn.next();
            dataScn.next();
            dataScn.next();
            dataScn.next();
            dataScn.next();
            int a1 = dataScn.nextInt();
            int a2 = dataScn.nextInt();
            int a3 = dataScn.nextInt();
            
            if (Math.abs(a1) + Math.abs(a2) + Math.abs(a3) < minAcc)
            {
                minAcc = Math.abs(a1) + Math.abs(a2) + Math.abs(a3);
            }
        }
        for (int i = particles.size() - 1; i >= 0; i--)
        {
            String data = particles.get(i);
            
            Scanner dataScn = new Scanner(data);
            dataScn.useDelimiter(",");
            
            dataScn.next();
            dataScn.next();
            dataScn.next();
            dataScn.next();
            dataScn.next();
            dataScn.next();
            int a1 = dataScn.nextInt();
            int a2 = dataScn.nextInt();
            int a3 = dataScn.nextInt();
            
            if (Math.abs(a1) + Math.abs(a2) + Math.abs(a3) != minAcc)
            {
                particles.remove(i);
            }
        }
        
        System.out.println("Particles remaining (hopefully 1): " + particles.size()); // if not zero, will have to prune based on velocity and possible starting position
        System.out.println();
        for (String p : particles) // print narrowed down list to find particle manualy. FIND PARTICLE NUMBER FROM INPUT DATASET !!!!!
        {
            System.out.println(p);
        }
        System.out.println("Find answer to part 1 manualy above!");
        System.out.println();
        
        
        // PART 2 BELOW
        
        for (int step = 0; step < 300; step++)
        {
            // Visual for Part 2 (recomended!!!!!)
            
            System.out.println("step: " + step);
            System.out.println("particles: " + colliders.size());
            System.out.println();
            
            
            
            // check for collisions
            ArrayList<String> blackList = new ArrayList();
            
            for (int a = 0; a < colliders.size(); a++)
            {
                String data1 = colliders.get(a);
            
                Scanner dataScn1 = new Scanner(data1);
                dataScn1.useDelimiter(",");
                
                String pos1 = "";
                
                pos1 += dataScn1.nextInt() + ",";
                pos1 += dataScn1.nextInt() + ",";
                pos1 += dataScn1.nextInt() + ",";

                for (int b = a + 1; b < colliders.size(); b++)
                {
                    String data2 = colliders.get(b);
            
                    Scanner dataScn2 = new Scanner(data2);
                    dataScn2.useDelimiter(",");
                    
                    String pos2 = "";
                    
                    pos2 += dataScn2.nextInt() + ",";
                    pos2 += dataScn2.nextInt() + ",";
                    pos2 += dataScn2.nextInt() + ",";
                    
                    if (pos1.equals(pos2))
                    {
                        blackList.add(pos1);
                    }
                }
            }
            
            
            // destroy particles
            for (int p = colliders.size() - 1; p >= 0; p--)
            {
                String data = colliders.get(p);
            
                Scanner dataScn = new Scanner(data);
                dataScn.useDelimiter(",");
                
                String pos = "";
                
                pos += dataScn.nextInt() + ",";
                pos += dataScn.nextInt() + ",";
                pos += dataScn.nextInt() + ",";
                
                for (int i = 0; i < blackList.size(); i++)
                {
                    if (blackList.get(i).equals(pos))
                    {
                        colliders.remove(p);
                        i = blackList.size();
                    }
                }
            }
            
            
            // move particles
            for (int i = 0; i < colliders.size(); i++)
            {
                String data = colliders.get(i);
                
                Scanner dataScn = new Scanner(data);
                dataScn.useDelimiter(",");
                
                int p1 = dataScn.nextInt();
                int p2 = dataScn.nextInt();
                int p3 = dataScn.nextInt();
                
                int v1 = dataScn.nextInt();
                int v2 = dataScn.nextInt();
                int v3 = dataScn.nextInt();
                
                int a1 = dataScn.nextInt();
                int a2 = dataScn.nextInt();
                int a3 = dataScn.nextInt();
                
                v1 += a1;
                v2 += a2;
                v3 += a3;
                
                p1 += v1;
                p2 += v2;
                p3 += v3;
                
                String newData = p1 + "," + p2 + "," + p3 + "," + v1 + "," + v2 + "," + v3 + "," + a1 + "," + a2 + "," + a3;
                
                
                colliders.set(i, newData);
            }
        }
        
        System.out.println("Part 2 answer: " + colliders.size());
    }
}
