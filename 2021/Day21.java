import java.io.*;
import java.util.*;

class Day21 {
  public static void main(String[] args) {


      int rolls = 0;
      int die = 0;
      
      boolean turn = true;
      int p1 = 10;
      int s1 = 0;
      int p2 = 9;
      int s2 = 0;

      while (s1 < 1000 && s2 < 1000)
          {
              int count = 0;

            count += ++die;
            if (die == 100)
            {
                die = 0;
            }
            count += ++die;
            if (die == 100)
            {
                die = 0;
            }
            count += ++die;
            if (die == 100)
            {
                die = 0;
            }
              
              if (turn)
              {
                  p1 += count;
                  while (p1 > 10) p1 -= 10;
                  s1 += p1;
              }
              else{
                  p2 += count;
                  while (p2 > 10) p2 -= 10;
                  s2 += p2;
              }
              System.out.println(s1 + " " + s2); 

              if (turn)
              {
                  turn = false;
              }
              else
              {
                  turn = true;
              }

              rolls += 3;
          }

      
      
     System.out.println(rolls); 
  }
}