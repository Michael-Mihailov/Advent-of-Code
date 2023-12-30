import java.util.*;

/**
 * A shape of all straight 90 degree edges
 */
public class Shape
{
    // Given: A List of points in the order they appear
    ArrayList<long[]> points; // (x, y) pair

    // Calculated: A List of all edges in the order they appear
    ArrayList<long[][]> edges; //  ( (x1, y2), (x2, y2))
    
    // Calculated: And ordered List of all unique point y-values (least to greatest)
    ArrayList<Long> heights;
    
    // Calculated: Stores all of the sorted x-values (least to greatest) of important positions for each height (may be empty for a given height if it stores no important positions) 
    HashMap<Long, ArrayList<Long>> levelMap; // <height, List x-positions>
    
    public Shape(ArrayList<long[]> points)
    {
        this.points = points;
        //System.out.println(points.size()); // DEBUG
        
        this.edges = calculateEdges(points);
        //System.out.println(edges.size()); // DEBUG
        
        this.heights = calculateHeights(points);
        //System.out.println(heights.size()); // DEBUG
        
        this.levelMap = calculateLevels(edges, heights);
        //System.out.println(levelMap.size()); // DEBUG
    }
    
    
    // Methods for Calculating all of the needed instance variables
    public ArrayList<long[][]> calculateEdges(ArrayList<long[]> points)
    {
        ArrayList<long[][]> edges = new ArrayList<long[][]>();
        
        for (int i = 0; i < points.size(); i++)
        {
            long[][] temp = new long[2][];
            
            long[] p1 = copyPoint(points.get(i));
            long[] p2 = copyPoint(points.get( (i + 1) % points.size() ));
            
            // make sure that the y-value of temp[0] is less than or equal to that of temp[1]
            if (p1[1] <= p2[1])
            {
                temp[0] = p1;
                temp[1] = p2;
            }
            else
            {
                temp[0] = p2;
                temp[1] = p1;
            }
            
            edges.add(temp);
        }
        
        return edges;
    }
    
    public ArrayList<Long> calculateHeights (ArrayList<long[]> points)
    {
        ArrayList<Long> heights = new ArrayList<Long>();
        
        for (long[] p : points)
        {
            heights.add(p[1]);
        }
        
        // bubble sort is the best sort
        boolean done = false;
        while (!done)
        {
            done = true;
            
            for (int i = 0; i < heights.size() - 1; i++)
            {
                long current = heights.get(i);
                long next = heights.get(i + 1);
                
                if (current == next)
                {
                    heights.remove(i);
                    
                    done = false;
                }
                else if (current > next)
                {
                    heights.set(i, next);
                    heights.set(i + 1, current);
                    
                    done = false;
                }
            }
        }
        
        
        return heights;
    }
    
    public HashMap<Long, ArrayList<Long>> calculateLevels(ArrayList<long[][]> edges, ArrayList<Long> heights)
    {
        HashMap<Long, ArrayList<Long>> levelMap = new HashMap<Long, ArrayList<Long>>();
        
        
        for (long height : heights)
        {
            ArrayList<Long> values = new ArrayList<Long>(); // the list of x-values
            
            for (long[][] edge : edges)
            {
                if (height >= edge[0][1] && height < edge[1][1]) // should always be false for horizontal lines
                {
                    values.add(edge[0][0]);
                }
            }
            
            // bubble sort again!
            boolean done = false;
            while (!done)
            {
                done = true;
                for (int i = 0; i < values.size() - 1; i++)
                {
                    long current = values.get(i);
                    long next = values.get(i + 1);

                    if (current > next)
                    {
                        values.set(i, next);
                        values.set(i + 1, current);
                        
                        done = false;
                    }
                }
            }
            
            levelMap.put(height, values); // values.size() % 2 should ALWAYS equal 0
        }
        
        
        
        return levelMap;
    }
    
    
    
    // The Actual method to get the area :O
    public long getArea()
    {
        // uses  this.levelMap
        // uses this.height
        
        long area = 0;
        
        for (int i = 0; i < heights.size() - 1; i++)
        {
            long h1 = heights.get(i);
            long h2 = heights.get(i + 1);
            
            for (int j = 0; j < levelMap.get(h1).size(); j += 2)
            {
                long x1 = levelMap.get(h1).get(j);
                long x2 = levelMap.get(h1).get(j + 1);
                
                area += (x2 - x1) * (h2 - h1);
            }
        }
        
        return area;
    }
    
    
    
    // Helper methods below
    public long[] copyPoint(long[] point) // copies a given point
    {
        long[] temp = new long[2];
        temp[0] = point[0]; // copy x
        temp[1] = point[1]; // copy y
        
        return temp;
    }
    
    
    
    // TESTING
    public static void main (String args[])
    {
        ArrayList<long[]> testVals = new ArrayList();
        
        long[] p1 = {1, 0};
        long[] p2 = {7, 0};
        long[] p3 = {7, 3};
        long[] p4 = {5, 3};
        long[] p5 = {5, 4};
        long[] p6 = {7, 4};
        long[] p7 = {7, 10};
        long[] p8 = {0, 10};
        long[] p9 = {0, 7};
        long[] p10 = {2, 7};
        long[] p11 = {2, 5};
        long[] p12 = {0, 5};
        long[] p13 = {0, 2};
        long[] p14 = {1, 2};
        
        testVals.add(p1);
        testVals.add(p2);
        testVals.add(p3);
        testVals.add(p4);
        testVals.add(p5);
        testVals.add(p6);
        testVals.add(p7);
        testVals.add(p8);
        testVals.add(p9);
        testVals.add(p10);
        testVals.add(p11);
        testVals.add(p12);
        testVals.add(p13);
        testVals.add(p14);
        
        Shape testShape = new Shape(testVals); // seems to work with test values
        
        System.out.println("Area: " + testShape.getArea());
    }
}
