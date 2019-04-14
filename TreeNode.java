package MyStl;

public class TreeNode<Value extends Comparable<? super Value>> implements BinaryNodeInterface<Value> {
    Value val;
    BinaryNodeInterface<Value> left;
    BinaryNodeInterface<Value> right;

    public Value getKey() {
        return this.val;
    }

    public void setKey(Value newData) {
        this.val = newData;
    }

    public boolean hasLeft() {
        return this.left != null;
    }

    public boolean hasRight() {
        return this.right != null;
    }

    public BinaryNodeInterface<Value> getLeftChild() {
        return this.left;
    }

    public BinaryNodeInterface<Value> getRightChild() {
        return this.right;
    }

    public void setLeftChild(BinaryNodeInterface<Value> leftChild) {
        this.left = leftChild;
    }

    public void setRightChild(BinaryNodeInterface<Value> rightChild) {
        this.right = rightChild;
    }


    TreeNode() {}
    
    TreeNode(Value x) { 
        val = x;
        // String cls = this.getClass().getName();
        // System.out.println(cls + " this.val is: " + this.val);
    }
    // Todo: solved "The type parameter Item is hiding the type Item"
    // by replacing Item with Value in the declaration of inner class TreeNode
}