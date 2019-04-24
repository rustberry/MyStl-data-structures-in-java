package MyStl;


public class IndexMinPQ<Key extends Comparable<? super Key>> {
    private int N = 0;
    private int[] pq;
    private int[] qp;  // pq[qp[0]] = 0, the reverse of pq, the value of qp[i]
                       // is where index i is located in pq. -1 in qp means no such index in pq
    private Key[] keys;

    @SuppressWarnings("unchecked")
    public IndexMinPQ(int max) {
        pq = new int[max + 1];
        qp = new int[max + 1];
        this.keys = (Key[]) new Comparable[max + 1];  // does size of Key[] need to be 1 more?
        for (int i = 0; i < max; i++) {
            qp[i] = -1;
            pq[i] = 0;
        }
    }

    public void insert(int i, Key k) {
        for (int j = 0; j < pq.length; j++) {
            System.out.println("j is " + pq[j]);
        }
        System.out.println();

        keys[i] = k;
        pq[++N] = i;
        qp[i] = N;

        for (int j = 0; j < pq.length; j++) {
            System.out.println("j is " + pq[j]);
        }
        System.out.println("i is "+ i + '\n');

        swim(N);
    }

    /**
     * Set the element at index i to Key k
     * @param i  the index of the element to be changed
     * @param k  the element to replace the old one
     */
    public void change(int i, Key k) {
        keys[i] = k;
        int pqi = qp[i];
        sink(pqi);
        swim(pqi);
    }

    public boolean contains(int i) {
        return qp[i] != -1;
    }

    /**
     * deletes index i and its associated element
     * @param i  the index of the associated element in `keys`
     */
    public void delete(int i) {
        int pqi = qp[i];
        // deletion in keys and qp
        keys[i] = null;
        qp[i] = -1;
        // deletion in pq
        if (N - 1 > 0) {
            pq[pqi] = pq[N];// pq[N] = j, keys[j] = key, qp[j] = N
            qp[pq[N]] = pqi;
            pq[N + 1] = -1;
            sink(pqi);
        } else {
            pq[1] = -1;
        }
    }

    public Key min() {
        return keys[pq[1]];
    }

    public int minIndex() {
        return pq[1];
    }

    /**
     * deletes the minimun element and returns its index in keys
     * @return  the index of the minimum element in keys
     */
    public int delMin() {
        int ret = pq[1];
        delete(ret);
        return ret;
    }

    public boolean isEmpty() {
        return N == 0;
    }

    public int size() {
        return this.N;
    }

    private boolean less(int i, int j) {
        int ki = pq[i], kj = pq[j];

        // System.out.println("i, j " + i + j);
        // System.out.println("ki, kj " + ki + kj);
        // if (keys[ki] == null) {
        //     System.out.println("ki is null");
        // } else if (keys[kj] == null) {
        //     System.out.println("kj is null");
        //     System.out.println("kj is" + kj + " keys[ki]" + keys[ki]);
        // }

        if (keys[ki].compareTo(keys[kj]) < 0) {
            return true;
        } else {
            return false;
        }
    }

    private void exch(int i, int j) {
        int pqi = qp[i], pqj = qp[j];
        // exch index in pq
        pq[pqi] = j;
        pq[pqj] = i;
        
        qp[i] = pqj;
        qp[j] = pqi;
    }

    /**
     * The swim operation in a min-oriented PQ, i.e. the smaller one gets to be swum up
     * @param k  the index of the target key in PQ
     */
    private void swim(int k) {
        while (k > 1) {
            if (less(k, (k / 2))) {
                exch(k, (k / 2));
                k = k / 2;
            } else {
                break;
            }
        }
    }
    
    private void sink(int k) {
        int j = 2 * k;
        while (j < N) {
            if (less(j, (j + 1))) {
                j++;
            }
            if (less(j, k)) {
                exch(j, k);
                k = j;
                j *= 2;
            } else {
                break;
            }
        }
    }

    public static void main(String[] args) {
        // insert a bunch of strings
        String[] strings = { "it", "was", "the", "best", "of", "times", "it", "was", "the", "worst" };

        IndexMinPQ<String> pq = new IndexMinPQ<String>(strings.length);
        for (int i = 0; i < strings.length; i++) {
            pq.insert(i, strings[i]);
        }

        // delete and print each key
        while (!pq.isEmpty()) {
            int i = pq.delMin();
            System.out.println(i + " " + strings[i]);
        }
        System.out.println();

        // reinsert the same strings
        for (int i = 0; i < strings.length; i++) {
            pq.insert(i, strings[i]);
        }

        // print each key using the iterator
        for (int i = 0; i < pq.size(); i++) {
            System.out.println(i + " " + strings[i]);
        }
        while (!pq.isEmpty()) {
            pq.delMin();
        }

    }
}