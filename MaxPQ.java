package MyStl;

import java.util.*;

public class MaxPQ<key extends Comparable<? super Key>> {
    private Key[] pq;
    private int N = 0;
    
    public MaxPQ() {
        this(16);
    }
    
    public MaxPQ(int max) {
        pq = new Key[max];
    }
    
    public MaxPQ(Key[] a) {
        pq = new Key[a.length];
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
    
    private void swim(int k) {
        while (k / 2 >= 1) {
            if (less((k / 2), k)) {
                exch((k / 2), k);
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