package MyStl;

import java.util.Iterator;
import java.util.NoSuchElementException;

/**
 * A class that implements a map/dictionrary by using hashing.
 * Implemented with linked list.
 * @author Jim
 */
public class MyHashMap<K, V> implements MyMapInterface<K, V>, Iterable<E>
{
    private Entry<K, V>[] tableEntry;  // Map entries
    private int entriesNum;  // no initial value here
    private static final int DFLT_SIZE = 101;  // a prime number
    private static final double MAX_FACTOR = 0.75;

    public MyHashMap() {
        this(DFLT_SIZE);
    }

    public MyHashMap(int size) {
        int prime = getNextPrime(size);
        entriesNum = 0;
        tableEntry = new Entry[prime];
    }

    public V add(K key, V value) {
        if (isTableCrowded()) {
            rehash();
        }

        int index = getHashIndex(key);
        try {
            if (tableEntry[index].getKey() == null) {
                tableEntry[index].setKey(key);
                tableEntry[index].setValue(value);
                return null;
            }
            // else, the tableEntry[index] is taken
            Entry<K, V> e = tableEntry[index].next;
            if (e == null) {
                tableEntry[index].next = new Entry<K, V>(key, value);
                return null;
            }
            while (e.next != null) {
                if (e.key == key) {
                    V oldValue = e.value;
                    e.value = value;
                    return oldValue;
                }
                e = e.next;
            }
            // find an empty position in the end of the list
            e.next = new Entry<K, V>(key, value);
            return null;
        } finally {
            entriesNum += 1;
        }
    }

    public V remove(K key) {
        int index = getHashIndex(key);
        Entry<K, V> e = tableEntry[index];
        try {
            if (e.getKey() == null || e.getKey() == key) { // no such key exists yet
                return null;
            }
            e = e.getNext();
            while (e != null) {
                if (e.getKey() == key) { // if key equals, the entry is found
                    V rmv = e.getValue();
                    e.prev = e.next; // delete e from the list

                    e.setKey(null);
                    e.setValue(null);
                    e = null;
                    return rmv;
                }
                e = e.getNext();
            }

            return null; // no such key exists
        }   finally {
            entriesNum -= 1;
        }
    }

    public V getValue(K key) {
        int index = getHashIndex(key);
        Entry<K, V> e = tableEntry[index];
        if (e.getValue() == null) {
            return null;
        }
        e = e.getNext();
        while (e != null) {
            if (e.getKey() == key) {
                return e.getValue();
            }
            e = e.getNext();
        }

        return null;
    }

    public boolean contains(K key) {
        int index = getHashIndex(key);
        Entry<K, V> e = tableEntry[index];
        if (e.getKey() == null) {
            return false;
        }
        e = e.getNext();
        while (e != null) {
            if (e.getKey() == key) {
                return true;
            }
            e = e.getNext();
        }

        return false;
    }


    /* private helper functions */
    private int getHashIndex(K k) {
        int hash = k.hashCode();
        return hash % tableEntry.length;
    }

    private int getNextPrime(int t) {
        t = getNextOdd(t);      // get the next odd
        
        while (!isOddPrime(t)) {
            t+= 2;              // try odds until a prime is found
        }
        
        return t;
    } // end getNextPrime
    
    private int getNextOdd(int t) {
        if (t%2 == 0) {
            return t+1;
        } else {
            return t;
        }
    } // end getNextPrime    

    // Precondition: t is an odd value
    private boolean isOddPrime(int t) {  // Not the most efficient method, but it will serve 
        int test = 3;
        boolean foundFactor = false;
        while (test*test < t && !foundFactor) {
            foundFactor = (t%test == 0);    // is it divisible by test
            test += 2;
        }
        
        return !foundFactor;
    } // end getNextPrime

    private boolean isTableCrowded() {
        return entriesNum > MAX_FACTOR * tableEntry.length; // use multiplex over division
    }

    private void rehash() {
        Entry<K, V>[] oldTableEntry = tableEntry;
        int newTableSize = getNextPrime(tableEntry.length * 2);
        tableEntry = new Entry[newTableSize];
        entriesNum = 0; // reset, since entriesNum will be incremented by add() below

        Entry<K, V> walker;
        for (int i = 0; i < oldTableEntry.length; i++) {
            walker = oldTableEntry[i];
            while (walker != null) {
                add(walker.getKey(), walker.getValue());
                walker = walker.getNext();
            }
        }
    }



    public Iterator<E> iterator() {
        return new 
    }



    /* private inner class Entry */
    private class Entry<S, T> {
        private S key;
        private T value;
        private Entry<S, T> next;
        private Entry<S, T> prev;

        private Entry() {
            this(null, null);
        }

        private Entry(S searchKey, T dataValue) {
            key = searchKey;
            value = dataValue;
            next = null;
            prev = null;
        }

        private S getKey() {
            return key;
        }
        private T getValue() {
            return value;
        }

        private Entry<S, T> getNext() {
            return next;
        }

        private Entry<S, T> getPrev() {
            return prev;
        }

        private void setKey(S k) {
            key = k;
        }

        private void setValue(T v) {
            value = v;
        }
    }
}