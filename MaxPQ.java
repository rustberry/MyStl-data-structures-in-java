package MyStl;


public class MaxPQ<Key extends Comparable<? super Key>> {
    private Key[] pq;
    private int N = 0;
    
    public MaxPQ() {
        this(16);
    }
    
    @SuppressWarnings("unchecked")
    public MaxPQ(int max) {
        pq = (Key[]) new Comparable[max + 1];
    }
    
    @SuppressWarnings("unchecked")
    public MaxPQ(Key[] a) {
        pq = (Key[]) new Comparable[a.length + 1];
        for (Key k : a) {
            Insert(k);
        }
    }
    
    public void Insert(Key v) {
        pq[++N] = v;
        swim(N);
    }
    
    public Key max() {
        return pq[1];
    }
    
    public Key delMax() {
        Key ret = pq[1];
        pq[1] = pq[N];
        pq[N--] = null;
        sink(1);
        return ret;
    }
    
    public boolean isEmpty() {
        return N == 0;
    }
    
    public int size() {
        return this.N;
    }
    
    private boolean less(int i, int j) {
        if (pq[i].compareTo(pq[j]) < 0) {
            return true;
        } else {
            return false;
        }
    }
    
    private void exch(int i, int j) {
        Key tmp = pq[i];
        pq[i] = pq[j];
        pq[j] = tmp;
    }
    
    /**
     * The swim operation in a max-oriented PQ, i.e. the bigger one gets to be swum up
     * @param k  the index of the target key in PQ
     */
    private void swim(int k) {
        while (k > 1 && less((k / 2), k)) {
            exch((k / 2), k);
            k = k / 2;
        }
    }
    
    /**
     * The sink operation in a max-oriented PQ, i.e. the smaller one gets sunk down
     * @param k
     */
    private void sink(int k) {
        int j = 2 * k;
        while (j <= N) {
            if (j+1 <= N && less(j, (j + 1))) {
                j++;
            }
            if (less(k, j)) {
                exch(k, j);
                k = j;
                j *= 2;
            } else {
                break;
            }
        }
    }
}