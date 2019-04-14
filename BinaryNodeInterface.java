package MyStl;

/**
 * Interface for the node in a tree
 * @author Jim
 */

interface BinaryNodeInterface<Key> {
    /**
     * Get the data of the node.
     * @return    data object
     */
    public Key getKey();

    /** 
    *  Sets the data portion of this node.
    *  @param  newKey  the data object 
    */
    public void setKey(Key newKey);

    /**
     * Checks whether the node has a left child.
     * @param  node  the node to check
     */
    public boolean hasLeft();

    /**
     * Checks whether the node has a right child.
     * @param  node  the node to check
     */
    public boolean hasRight();

    public BinaryNodeInterface<Key> getLeftChild();

    public BinaryNodeInterface<Key> getRightChild();

    public void setLeftChild(BinaryNodeInterface<Key> leftChild);

    public void setRightChild(BinaryNodeInterface<Key> rightChild);

}