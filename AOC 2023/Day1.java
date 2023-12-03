import java.util.*;
import java.io.*;

public class Day1
{
    public static void main(String[] args) throws IOException
    {
        File file = new File("input.txt");
        Scanner scn = new Scanner(file);
        
        int answer = 0;
        
        while (scn.hasNext())
        {
            String data = scn.next();
            
            boolean first = true;
            int last = -1;
            
            for (int i = 0; i < data.length(); i++) // 48 == 0 && 57 == 9
            {
                if (data.charAt(i) >= 48 &&  data.charAt(i) <= 57)
                {
                    System.out.println(data.charAt(i) + " " + (data.charAt(i) - 48));
                    
                    if (first)
                    {
                        first = false;
                        answer += (data.charAt(i) - 48) * 10;
                        
                        last = data.charAt(i) - 48;
                    }
                    else
                    {
                        last = data.charAt(i) - 48;
                    }
                }
                else
                {
                    if (data.substring(i).indexOf("one") == 0)
                    {
                        int n = 1;
                        
                        if (first)
                        {
                            first = false;
                            answer += n * 10;
                            
                            last = n;
                        }
                        else
                        {
                            last = n;
                        }
                    }
                    else if (data.substring(i).indexOf("two") == 0)
                    {
                        int n = 2;
                        
                        if (first)
                        {
                            first = false;
                            answer += n * 10;
                            
                            last = n;
                        }
                        else
                        {
                            last = n;
                        }
                    }
                    else if (data.substring(i).indexOf("three") == 0)
                    {
                        int n = 3;
                        
                        if (first)
                        {
                            first = false;
                            answer += n * 10;
                            
                            last = n;
                        }
                        else
                        {
                            last = n;
                        }
                    }
                    else if (data.substring(i).indexOf("four") == 0)
                    {
                        int n = 4;
                        
                        if (first)
                        {
                            first = false;
                            answer += n * 10;
                            
                            last = n;
                        }
                        else
                        {
                            last = n;
                        }
                    }
                    else if (data.substring(i).indexOf("five") == 0)
                    {
                        int n = 5;
                        
                        if (first)
                        {
                            first = false;
                            answer += n * 10;
                            
                            last = n;
                        }
                        else
                        {
                            last = n;
                        }
                    }
                    else if (data.substring(i).indexOf("six") == 0)
                    {
                        int n = 6;
                        
                        if (first)
                        {
                            first = false;
                            answer += n * 10;
                            
                            last = n;
                        }
                        else
                        {
                            last = n;
                        }
                    }
                    else if (data.substring(i).indexOf("seven") == 0)
                    {
                        int n = 7;
                        
                        if (first)
                        {
                            first = false;
                            answer += n * 10;
                            
                            last = n;
                        }
                        else
                        {
                            last = n;
                        }
                    }
                    else if (data.substring(i).indexOf("eight") == 0)
                    {
                        int n = 8;
                        
                        if (first)
                        {
                            first = false;
                            answer += n * 10;
                            
                            last = n;
                        }
                        else
                        {
                            last = n;
                        }
                    }
                    else if (data.substring(i).indexOf("nine") == 0)
                    {
                        int n = 9;
                        
                        if (first)
                        {
                            first = false;
                            answer += n * 10;
                            
                            last = n;
                        }
                        else
                        {
                            last = n;
                        }
                    }
                }
            }
            
            answer += last;
        }
        
        System.out.println(answer);
    }
}
