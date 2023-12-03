import java.math.*;

public class Cube
{
    private int x1;
    private int x2;
    private int y1;
    private int y2;
    private int z1;
    private int z2;
    private boolean on = false;
    
    
    public Cube(int X1, int X2, int Y1, int Y2, int Z1, int Z2)
    {
        x1 = X1;
        x2 = X2;
        y1 = Y1;
        y2 = Y2;
        z1 = Z1;
        z2 = Z2;
        
        if (x1 > x2)
        {
            int temp = x1; x1 = x2; x2 = temp;
        }
        if (y1 > y2)
        {
            int temp = y1; y1 = y2; y2 = temp;
        }
        if (z1 > z2)
        {
            int temp = z1; z1 = z2; z2 = temp;
        }
    }
    public Cube(int X1, int X2, int Y1, int Y2, int Z1, int Z2, boolean On)
    {
        x1 = X1;
        x2 = X2;
        y1 = Y1;
        y2 = Y2;
        z1 = Z1;
        z2 = Z2;
        
        on = On;
        
        if (x1 > x2)
        {
            int temp = x1; x1 = x2; x2 = temp;
        }
        if (y1 > y2)
        {
            int temp = y1; y1 = y2; y2 = temp;
        }
        if (z1 > z2)
        {
            int temp = z1; z1 = z2; z2 = temp;
        }
    }
    
    public void setStatus(boolean stat)
    {
        on = stat;
    }
    
    public boolean getStatus()
    {
        return on;
    }
    
    public int getX1() {return x1;}
    public int getX2() {return x2;}
    public int getY1() {return y1;}
    public int getY2() {return y2;}
    public int getZ1() {return z1;}
    public int getZ2() {return z2;}
    
    public BigInteger getArea()
    {
        BigInteger area = (new BigInteger("" + (x2 - x1 + 1))).multiply(new BigInteger("" + (y2 - y1 + 1))).multiply(new BigInteger("" + (z2 - z1 + 1)));
        
        return area;
    }
    
    public boolean intercepting(Cube other)
    {
        int x1N = other.getX1();
        int x2N = other.getX2();
        int y1N = other.getY1();
        int y2N = other.getY2();
        int z1N = other.getZ1();
        int z2N = other.getZ2();
        
        if (x1N > x2 || x2N < x1 || y1N > y2 || y2N < y1 || z1N > z2 || z2N < z1)
        {
            return false;
        }
        else
        {
            return true;
        }
    }
    
    public Cube overlap(Cube other)
    {
        int a1, a2, b1, b2, c1, c2;
        
        int x1N = other.getX1();
        int x2N = other.getX2();
        int y1N = other.getY1();
        int y2N = other.getY2();
        int z1N = other.getZ1();
        int z2N = other.getZ2();
        
        
        if (x1N >= x1 && x2N <= x2)
        {
            a1 = x1N;
            a2 = x2N;
        }
        else if (x1 > x1N && x2 < x2N)
        {
            a1 = x1;
            a2 = x2;
        }
        else if (x1 >= x1N)
        {
            a1 = x1;
            a2 = x2N;
        }
        else
        {
            a1 = x1N; 
            a2 = x2;
        }
                        
        if (y1N >= y1 && y2N <= y2)
        {
            b1 = y1N;
            b2 = y2N;
        }
        else if (y1 >= y1N && y2 <= y2N)
        {
            b1 = y1;
            b2 = y2;
        }
        else if (y1 >= y1N)
        {
            b1 = y1;
            b2 = y2N;
        }
        else
        {
            b1 = y1N; 
            b2 = y2;
        }
                        
        if (z1N >= z1 && z2N <= z2)
        {
            c1 = z1N;
            c2 = z2N;
        }
        else if (z1 >= z1N && z2 <= z2N)
        {
            c1 = z1;
            c2 = z2;
        }
        else if (z1 >= z1N)
        {
            c1 = z1;
            c2 = z2N;
        }
        else
        {
            c1 = z1N; 
            c2 = z2;
        }
                        
        
        return new Cube(a1, a2, b1, b2, c1, c2);
    }
}
