import java.util.*;
import java.io.*;

public class Day24
{
    /*
     * I hate this problem.
     * The problem is very input data specific.
     * The constraints are were unclear.
     * This problem sucks.
     * My code is therefor very messy.
     */
    public static void main(String[] args) throws IOException
    {
        File file = new File("input.txt");
        Scanner scn = new Scanner(file);
                
        HashSet<Node> daNodes = new HashSet();
        
        ArrayList<Node> seedNodes = new ArrayList(); // the four output wires that got swapped (this took me forever to figure out through testing)
        
        boolean scanFlag = false;
        while (scn.hasNextLine()) // initial scan
        {
            String line = scn.nextLine();
            
            if (scanFlag == true)
            {
                String[] data = line.split(" "); 
                String type = data[1];
                
                Node tempNode = new Node(type);
                tempNode.layout = data;
                
                if (data[4].charAt(0) == 'z' && data[1].equals("XOR") == false) 
                {
                    if ((data[4].substring(1).equals("45") && data[1].equals("OR")) == false)
                        seedNodes.add(tempNode);
                }
                
                daNodes.add(tempNode);
            }
            
            if (line.length() == 0)
            {
                scanFlag = true;
            }
        }
        
        Node[] initialXORGates = new Node[45];
        Node[] initialANDGates = new Node[45];
        
        for (Node n : daNodes) // fill in initial output connections && the biIndex (if applicable)
        {
            String wire = n.layout[4]; // the wire name used to find connections
            if (wire.charAt(0) == 'z') // connection is an OUT Node
            {
                int tempNum = Integer.parseInt(wire.substring(1));
                Node tempZNode = new Node(tempNum);
                n.outputs.add(tempZNode);
            }
            else // connection is other Nodes
            {
                for (Node other : daNodes)
                {
                    if (other.layout[0].equals(wire) || other.layout[2].equals(wire))
                    {
                        n.outputs.add(other);
                    }
                }
            }
            
            if (n.layout[0].charAt(0) == 'x' || n.layout[0].charAt(0) == 'y') // check to see if this Node is an initial gate
            {
                int num = Integer.parseInt(n.layout[0].substring(1));
                n.biIndex = num;
                
                if (n.type.equals("XOR"))
                {
                    initialXORGates[n.biIndex] = n;
                }
                else if (n.type.equals("AND"))
                {
                    initialANDGates[n.biIndex] = n;
                }
                else System.out.println("Error 1");
            }
        }
        
        ArrayList<Node> problemNodes = new ArrayList();
        for (Node n : daNodes) // look for potential partners of the "seedNodes"
        {
            if (n.type.equals("XOR") == false) continue;
            
            if (n.layout[0].charAt(0) == 'x' || n.layout[0].charAt(0) == 'y') continue;
            
            if (n.outputs.size() == 1 && n.outputs.get(0).biIndex != -1) continue;
            
            problemNodes.add(n);
        }
        
        System.out.println("Num Seed Nodes: " + seedNodes.size());
        System.out.println("Num Problem Nodes: " + problemNodes.size());
        
        ArrayList<Node[]> pairs = new ArrayList(); // I know that there are 3 seed nodes and 3 problem nodes
        for (int i = 0; i < seedNodes.size(); i++)
        {
            for (int j = 0; j < problemNodes.size(); j++)
            {
                pairs.add(new Node[]{seedNodes.get(i),problemNodes.get(j)});
            }
        }
        
        ArrayList<String> ans = new ArrayList();
        for (Node n : daNodes)
        {
            for (Node m : daNodes)
            {
                n.swap(m);
                
                for (int i = 0; i < pairs.size(); i++)
                {
                    Node[] pair1 = pairs.get(i);
                    pair1[0].swap(pair1[1]);
                    for (int j = i + 1; j < pairs.size(); j++)
                    {
                        Node[] pair2 = pairs.get(j);
                        pair2[0].swap(pair2[1]);
                        for (int k = j + 1; k < pairs.size(); k++)
                        {
                            Node[] pair3 = pairs.get(k);
                            pair3[0].swap(pair3[1]);
                            
                            boolean res = solve(initialXORGates, initialANDGates);
                            if (res)
                            {
                                ans = new ArrayList();
                                ans.add(n.layout[4]);
                                ans.add(m.layout[4]);
                            }
                            
                            pair3[0].swap(pair3[1]);
                        }
                        pair2[0].swap(pair2[1]);
                    }
                    pair1[0].swap(pair1[1]);
                }
                
                n.swap(m);
            }
        }
        
        for (int i = 0; i < seedNodes.size(); i++)
        {
            ans.add(seedNodes.get(i).layout[4]);
        }
        for (int i = 0; i < problemNodes.size(); i++)
        {
            ans.add(problemNodes.get(i).layout[4]);
        }
        
        Collections.sort(ans);
        
        for (int i = 0; i < ans.size(); i++)
        {
            System.out.print(ans.get(i) + ",");
        }
    }
    
    public static boolean solve (Node[] initialXORGates, Node[] initialANDGates)
    {
        Node carryOver = null;
        
        for (int i = 0; i < initialXORGates.length; i++)
        {
            ArrayList<Node> XORouts = initialXORGates[i].outputs;
            ArrayList<Node> ANDouts = initialANDGates[i].outputs;
            
            if (i == 0)
            {
                if (XORouts.size() != 1 || XORouts.get(0).biIndex != 0)
                {
                    return false;
                }
                carryOver = initialANDGates[i];
            }
            else
            {                
                if (XORouts.size() != 2) return false;
                if (ANDouts.size() != 1) return false;
                if (ANDouts.get(0).type.equals("OR") == false) return false;
                
                if (carryOver.outputs.containsAll(XORouts) == false) return false;
                
                for (int j = 0; j < 2; j++)
                {
                    if (XORouts.get(j).outputs.size() != 1) return false; 
                    else if (XORouts.get(j).type.equals("AND"))
                    {
                        if (XORouts.get(j).outputs.get(0) != ANDouts.get(0))
                        {
                            return false;
                        }
                    }
                    else // type == "XOR"
                    {
                        if (XORouts.get(j).outputs.get(0).biIndex != i)
                        {
                            return false;
                        }
                    }
                }
                
                carryOver = ANDouts.get(0);
            }
            
            if (i == initialXORGates.length - 1)
            {
                System.out.println("Check");
                
                if (carryOver.outputs.size() != 1 || carryOver.outputs.get(0).biIndex != i + 1) return false;
            }
        }
        
        return true;
    }
    
    public static HashSet<Node> getBranch (Node root)
    {
        HashSet<Node> ans = new HashSet();
        if (root.type.equals("OUT")) return ans;
        
        ans.add(root); 
        
        if (root.type.equals("OR")) return ans; // idk about this !!!!!
        
        for (Node child : root.outputs)
        {
            ans.addAll(getBranch(child));
        }
        return ans;
    }
    
    public String connectionType (Node start)
    {
        ArrayList<Node> connections = start.outputs;
        
        if (connections.size() == 1)
        {
            if (connections.get(0).biIndex != -1) return "OUTPUT";
            else return "SUM";
        }
        else if (connections.size() == 2)
        {
            return "CARRY";
        }
        else
        {
            System.out.println("Error 2: connections too large");
        }
        
        return null;
    }
    
    public static HashSet<Node> getProblemNodes (Node[] initialXORGates, Node[] initialANDGates) // find potential nodes to be swapped
    {        
        HashSet<Node> ans = new HashSet();
        
        for (int i = 0; i < initialXORGates.length; i++)
        {
            ArrayList<Node> XORouts = initialXORGates[i].outputs;
            ArrayList<Node> ANDouts = initialANDGates[i].outputs;
            
            if (i == 0)
            {
                if (XORouts.size() != 1 || XORouts.get(0).biIndex != 0)
                {
                    ans.addAll(getBranch(initialXORGates[i]));
                }
                ans.addAll(getBranch(initialANDGates[i]));
            }
            else
            {
                boolean flag = false;
                
                if (XORouts.size() != 2) flag = true;
                if (ANDouts.size() != 1) flag = true;
                if (flag == false && ANDouts.get(0).type.equals("OR") == false) flag = true;
                if (flag == false)
                {
                    for (int j = 0; j < 2 && flag == false; j++)
                    {
                        if (XORouts.get(j).outputs.size() != 1) flag = true; 
                        else if (XORouts.get(j).type.equals("AND"))
                        {
                            if (XORouts.get(j).outputs.get(0) != ANDouts.get(0))
                            {
                                flag = true;
                            }
                        }
                        else // type == "XOR"
                        {
                            if (XORouts.get(j).outputs.get(0).biIndex != i)
                            {
                                ans.addAll(getBranch(XORouts.get(j)));
                            }
                        }
                    }
                }
                
                if (flag)
                {
                    ans.addAll(getBranch(initialXORGates[i]));
                    ans.addAll(getBranch(initialANDGates[i]));
                }
                else
                {
                    ans.addAll(getBranch(ANDouts.get(0)));
                }
            }
        }
        
        return ans;
    }
}