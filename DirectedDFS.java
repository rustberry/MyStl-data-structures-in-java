package MyStl;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.ArrayDeque;
import java.util.Deque;
import java.util.Scanner;

public class DirectedDFS {
    private boolean[] marked;
    
    public DirectedDFS(Digraph G, int s) {
        marked = new boolean[G.V()];
        // marked[s] = true;
        Deque<Integer> callStack = new ArrayDeque<>();
        callStack.push(s);
        while (!callStack.isEmpty()) {
            int w = callStack.pop();
            marked[w] = true;  // w must be able to connect to s, therefore true
            for (int i : G.adj(w)) {
                if (!marked(i)) {
                    callStack.push(i);
                }
            }
        }
    }
    
    public boolean marked(int v) {
        return marked[v];
    }
    
    public static void main(String[] args) throws FileNotFoundException {
        Digraph G = new Digraph(new Scanner(new File(args[0])));
        DirectedDFS reachable = new DirectedDFS(G, Integer.parseInt(args[1]));
        
        for (int v = 0; v < G.V(); v++) {
            if (reachable.marked(v)) {
                System.out.println(v + " ");
            }
        }
        System.out.println();
    }
}