package MyStl;

import java.util.ArrayList;
import java.util.Scanner;

public class Graph {
    private final int V;  // numbers of vertex
    private int E;        // numbers of edges
    private ArrayList<Integer>[] adj;  // adjacent table
    
    public Graph(int V) {
        this.V = V;
        this.E = 0;
        adj = (ArrayList<Integer>[]) new ArrayList[V];
        for (int v = 0; v < V; v++) {
            adj[v] = new ArrayList<>();
        }
    }
    
    public Graph(Scanner in) {
        this(in.nextInt());
        int E = in.nextInt();
        for (int i = 0; i < E; i++) {
            int v = in.nextInt();
            int w = in.nextInt();
            addEdge(v, w);
        }
    }
    
    public Iterable<Integer> adj(int v) {
        return adj[v];
    }
    
    public int V() {
        return this.V;
    }
    
    public int E() {
        return this.E;
    }
    
    public void addEdge(int v, int w) {
        // both sides are added, that's the difference between directed and indirected graph
        adj[v].add(w);
        adj[w].add(v);
        E++;
    }
}