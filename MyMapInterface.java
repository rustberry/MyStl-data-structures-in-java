package MyStl;

import java.util.Iterator;
/**
 * An Interface for a map
 * @author Jim
 */
public interface MyMapInterface<K, V> {

    /**
     * Add a new entry to the map. If the key given already exists,
     * replaces the corresponding value.
     * @param key   search key of the new entry
     * @param value an object associated with the key
     * @return either null if successfully added,
     *         or the value being replaced if the entry already exsits
     */
    public V add(K key, V value);

    /**
     * Removes a specific entry from the map.
     * @param key  search key of the entry to be removed
     * @return either the value of the removed entry,
     *         or null if no such key exists
     */
    public V remove(K key);

    /**
     * Gets the value associated with the given search key.
     * @param key search key of the entry to be retrieved
     * @return the value object associated or null if no such entry exists
     */
    public V getValue(K key);

    /**
     * Checks whether a specific entry is in this map.
     * @param key object search key
     * @return true if key is in the map
     */
    public boolean contains(K key);

    /**
     * Creates an iterator that traverses all search keys in this map.
     * @return an iterator that provides sequential access to the keys
     */
    public Iterator<K> getKeyIterator();

    /**
     * Creates an iterator that traverses all values in this map.
     * @return an iterator that provides sequential access to the values
     */
    public Iterable<V> getValueIterator();

    /**
     * Checks whether the map is empty
     * @return true if the map is empty
     */
    public boolean isEmpty();

    /**
     * Gets the size of this map
     * @return the number of the key-value pairs currently in the map
     */
    public int getSize();

    /**
     * Removes all entries in this map
     */
    public void clear();
}