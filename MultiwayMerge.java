package MyStl;

import java.util.Scanner;
import java.io.File;
import java.io.FileNotFoundException;

public class MultiwayMerge {
    
    public static void merge(Scanner[] streams) {
        int N = streams.length;
        IndexMinPQ<String> sPQ = new IndexMinPQ<>(N);
        // System.out.println("test for NullPointers " + streams[1].next());
        for (int i = 0; i < streams.length; i++) {
            if (streams[i].hasNext()) {
                sPQ.insert(i, streams[i].next());
            }
        }

        while (!sPQ.isEmpty()) {
            int next = sPQ.delMin();

            // process the minimum element
            System.out.println(streams[next].next());

            if (streams[next].hasNext()) {
                sPQ.insert(next, streams[next].next());
            }
        }
    }

    public static void main(String[] args) throws FileNotFoundException{
        int N = args.length;
        Scanner[] streams = new Scanner[N];
        for (int i = 0; i < N; i++) {
            streams[i] = new Scanner(new File(args[i]));
        }

        MultiwayMerge.merge(streams);

        for (Scanner s : streams) {
            s.close();
        }
        
    }
}