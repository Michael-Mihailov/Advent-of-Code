import java.util.*;
import java.io.*;

public class testing
{
    public static void main(String[] args)
    {
        String test = "Hello|World";
        
        String[] arr = test.split("|");
        
        for (String s : arr)
        {
            System.out.print(s + ",");
        }
        
        // output: H,e,l,l,o,|,W,o,r,l,d,
        
        // What the hell!
    }
}
