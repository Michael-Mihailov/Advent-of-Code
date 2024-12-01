import java.util.*;
import java.io.*;

public class Day8
{
    public static void main(String args[]) throws IOException
    {
        File file = new File("input.txt");
        Scanner scn = new Scanner(file);
        
        String data = scn.nextLine();
        
        int width = 25;
        int height = 6;
        
        String[] layers = new String[data.length() / (width * height)];
        
        int minZeros = Integer.MAX_VALUE;
        int ans = 0;
        
        for (int i = 0; i < layers.length; i++)
        {
            layers[i] = data.substring((data.length() * i / layers.length), (data.length() * (i + 1) / layers.length));
                        
            int numZeros = 0;
            int numOnes = 0;
            int numTwos = 0;
            for (int j = 0; j < layers[i].length(); j++)
            {
                if (layers[i].charAt(j) == '0') numZeros++;
                if (layers[i].charAt(j) == '1') numOnes++;
                if (layers[i].charAt(j) == '2') numTwos++;
            }
            
            if (numZeros < minZeros)
            {
                minZeros = numZeros;
                ans = numOnes * numTwos;
            }
        }
        
        // System.out.println(ans); // part 1 answer
        
        String[] image = new String[width * height];
        
        for (int i = layers.length - 1; i >= 0; i--)
        {
            for (int j = 0; j < layers[i].length(); j++)
            {
                if (layers[i].charAt(j) == '0') image[j] = ".";
                if (layers[i].charAt(j) == '1') image[j] = "O";
            }
        }
        
        for (int y = 0; y < height; y++)
        {
            for (int x = 0; x < width; x++)
            {
                System.out.print(image[(y * width) + x]);
            }
            System.out.println();
        }
    }
}
