import java.util.*;
import java.io.*;

public class Day25
{
    public static void main(String[] args) throws IOException
    {
        new Day25();
    }
    
    public Day25() throws IOException
    {
        File file = new File("input.txt");
        Scanner scn = new Scanner(file);
                
        
        Node seedNode = null;
        HashMap<String, Node> nodeMap = new HashMap();
        ArrayList<String[]> connections = new ArrayList<String[]>();
        
        while (scn.hasNextLine())
        {
            String data = scn.nextLine();
            
            
            String key = data.substring(0, data.indexOf(":"));
            data = data.substring(data.indexOf(":") + 2);
            String[] connects = data.split(" ");
            
            if (nodeMap.get(key) == null)
            {
                nodeMap.put(key, new Node(key));
            }
            
            for (String con : connects)
            {
                String[] connection = new String[2];
                connection[0] = key;
                connection[1] = con;
                connections.add(connection);
                
                if (nodeMap.get(con) == null)
                {
                    nodeMap.put(con, new Node(con));
                }
                nodeMap.get(key).add(nodeMap.get(con));
                nodeMap.get(con).add(nodeMap.get(key));
            }
            
            
            seedNode = nodeMap.get(key);
        }
        
        
        int numComponents = graphSize(seedNode, new ArrayList<Node>()); // the size of the full graph
        
        // brute force all possible combinations of cut connections
        for (int i = 527; i < connections.size(); i++)
        {
            System.out.println(i + " / " + connections.size());
            
            for (int j = i + 1; j < connections.size(); j++)
            {                
                for (int k = j + 1; k < connections.size(); k++)
                {                    
                    String[][] forbidden = new String[3][2]; // 3 forbidden connections
                    forbidden[0] = connections.get(i);
                    forbidden[1] = connections.get(j);
                    forbidden[2] = connections.get(k);
                    
                    if (graphSize(seedNode, new HashSet<String>(), forbidden) != -1)
                    {
                        int group1 = graphSize(seedNode, new HashSet<String>(), forbidden);
                        int group2 = numComponents - group1;
                        int answer = group1 * group2;
                        
                        System.out.println();
                        System.out.println("group 1: " + group1);
                        System.out.println("group 2: " + group2);
                        System.out.println("Answer: " + answer);
                        System.out.println();
                    }
                }
            }
        }
    }
    // NOTE: this is assuming that the 3 wires to be cut contain 6 unique Nodes!!!
    public static int graphSize(Node current, HashSet<String> visited, String[][] forbidden)
    {
        if (visited.contains(current.key)) return 0;
        visited.add(current.key);
        
        boolean hasForbidden = false;
        String forbid = "";
        for (String[] pair : forbidden)
        {
            if (pair[0].equals(current.key))
            {                
                hasForbidden = true;
                forbid = pair[1];
            }
            else if (pair[1].equals(current.key))
            {                
                hasForbidden = true;
                forbid = pair[0];
            }
        }
        
        if (hasForbidden && visited.contains(forbid))
        {
            return -1;
        }
        
        ArrayList<Node> connections = current.connections;
        for (Node next : connections)
        {
            if (hasForbidden && forbid.equals(next.key)) 
            {                
                continue;                
            }
            
            if (graphSize(next, visited, forbidden) == -1) return -1;
        }
        
        return visited.size();
    }
    
    
    public static int graphSize(Node current, ArrayList<Node> visited)
    {
        if (visited.contains(current)) return 0;
        
        visited.add(current);
        
        ArrayList<Node> connections = current.connections;
        for (Node next : connections)
        {
            graphSize(next, visited);
        }
        
        return visited.size();
    }
    
    public class Node
    {
        public String key;
        public ArrayList<Node> connections;
        
        public Node(String key)
        {
            this.key = key;
            connections = new ArrayList<Node>();
        }
        
        public void add(Node value)
        {
            connections.add(value);
        }
        
        public Node copy()
        {
            Node temp = new Node(key);
            for (Node value : connections)
            {
                temp.add(value);
            }
            
            return temp;
        }
    }
}
