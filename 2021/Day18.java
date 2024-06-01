import java.util.*;
import java.io.*;

public class Day18
{    
    public static void main(String[] args) throws IOException
    {
        new Day18();
    }
    
    public Day18() throws IOException
    {
        int answer = 0;
        
        ArrayList<String> lines = new ArrayList<String>();
        
        File file = new File("input.txt");
        Scanner scn = new Scanner(file);        
        while (scn.hasNext())
        {
            lines.add(scn.nextLine());
        }
        
        for (int line1 = 0; line1 < lines.size(); line1++)
        {
            for (int line2 = 0; line2 < lines.size(); line2++)
            {
                if (line2 == line1) continue;
                
                Node snailSum = new Node(null);
                
                for (int k = 1; k <= 2; k++)
                {       
                    String data;
                    if (k == 1) data = lines.get(line1);
                    else data = lines.get(line2);
                    
                    // parse the line
                    data = data.substring(1);
                    
                    Stack<Node> parseStack = new Stack<Node>();
                    Node lineSum = new Node(null);
                    
                    lineSum.rightNode = new Node(lineSum);
                    lineSum.leftNode = new Node(lineSum);
                    parseStack.push(lineSum.rightNode);
                    parseStack.push(lineSum.leftNode);
                    
                    for (int i = 0; i < data.length(); i++)
                    {
                        char action = data.charAt(i);
                        
                        if (action == ',' || action == ']') continue;
                        
                        if (action == '[')
                        {
                            Node check = parseStack.pop();
                            check.rightNode = new Node(check);
                            check.leftNode = new Node(check);
                            parseStack.push(check.rightNode);
                            parseStack.push(check.leftNode);
                        }
                        else // is a number
                        {
                            Node check = parseStack.pop();
                            check.leafValue = new Integer(action - 48); // char to int conversion
                        }
                    }
                    
                    // add to the sum
                    if (k == 1)
                    {
                        snailSum = lineSum;
                        continue; // won't need to simplify if no addition has been done yet
                    }
                    else
                    {
                        Node tempNode = new Node(null);
                        snailSum.parentNode = tempNode;
                        lineSum.parentNode = tempNode;
                        tempNode.leftNode = snailSum;
                        tempNode.rightNode =  lineSum;
                        snailSum = tempNode;
                    }
                                
                    // simplify the sum
                    boolean simplified = false;
                    while (simplified == false)
                    {
                        simplified = true;
                        
                        ArrayList<Node> orderedLeaves;
                        
                        // exploding
                        boolean exploded = true;
                        while (exploded == true)
                        {
                            exploded = false;
                            orderedLeaves = snailSum.orderedLeaves();
                            for (int j = 0; j < orderedLeaves.size(); j++)
                            {
                                Node parent = orderedLeaves.get(j).parentNode;
                                if (parent.depth() > 4) System.out.println("ERROR, depth greater than 4");
                                else if (parent.depth() == 4)
                                {
                                    int leftValue = orderedLeaves.get(j).leafValue.intValue();
                                    int rightValue = orderedLeaves.get(j + 1).leafValue.intValue();
                                    if (j > 0)
                                    {
                                        orderedLeaves.get(j - 1).leafValue = new Integer(orderedLeaves.get(j - 1).leafValue.intValue() + leftValue);
                                    }
                                    if (j + 2 < orderedLeaves.size())
                                    {
                                        orderedLeaves.get(j + 2).leafValue = new Integer(orderedLeaves.get(j + 2).leafValue.intValue() + rightValue);
                                    }
                                    parent.leftNode = null;
                                    parent.rightNode = null;
                                    parent.leafValue = new Integer(0);
                                    
                                    exploded = true;
                                    break;
                                }
                            }
                        }
                        // splitting
                        orderedLeaves = snailSum.orderedLeaves();
                        for (int j = 0; j < orderedLeaves.size(); j++)
                        {
                            Node leaf = orderedLeaves.get(j);
                            int tempValue = leaf.leafValue.intValue();
                            if (tempValue >= 10)
                            {
                                leaf.leafValue = null;
                                leaf.leftNode = new Node(leaf);
                                leaf.rightNode = new Node(leaf);
                                leaf.leftNode.leafValue = new Integer(tempValue / 2);
                                leaf.rightNode.leafValue = new Integer((tempValue / 2) + (tempValue % 2));
                                
                                simplified = false;
                                break;
                            }
                        }
                    }
                }
                
                int posAnswer = snailSum.sumValue();
                if (posAnswer > answer) answer = posAnswer;
            }
        }
        
        System.out.println(answer);
    }
    
    public class Node
    {
        public Node parentNode;
        public Node leftNode;
        public Node rightNode;
        
        public Integer leafValue = null;
        
        public Node(Node parentNode)
        {
            this.parentNode = parentNode;
        }
        
        public boolean isLeaf()
        {
            return leafValue != null;
        }
        
        public int sumValue()
        {
            if (leafValue != null) return leafValue.intValue();
            return (3 * leftNode.sumValue()) + (2 * rightNode.sumValue());
        }
        
        public int depth()
        {
            if (parentNode == null) return 0;
            return 1 + parentNode.depth();
        }
        
        public ArrayList<Node> orderedLeaves()
        {
            if (leafValue != null)
            {
                ArrayList<Node> res = new ArrayList<Node>();
                res.add(this);
                return res;
            }
            ArrayList<Node> res = leftNode.orderedLeaves();
            res.addAll(rightNode.orderedLeaves());
            return res;
        }
    }
}