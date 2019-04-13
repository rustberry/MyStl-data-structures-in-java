package MyStl;

import java.util.Queue;
import java.util.Iterator;
import java.util.Stack;

/**
 * An interface for a tree
 * @author Jim
 */

public interface MyTreeInterface<Key> {
    /**
     * Add a new value to the tree.
     * @param key   data to be added
     * @return key  the added data
     */
    public Key insert(Key k);

    /**
     * Check whether the tree has any data in it
     * @return    true if is empty, false if not
     */
    public boolean isEmpty();
}