package MyStl;

import java.util.Arrays;
import java.util.ArrayList;
import java.util.List;


public class RedBlackTree <Item extends Comparable<? super Item>> 
                    extends BinarySearchTree<Item> implements MyTreeInterface<Item> {
    private RBNode<Item> root;
    private static final boolean RED = true;
    private static final boolean BLACK = false;

    static class RBNode<Value extends Comparable<? super Value>> 
                    extends TreeNode<Value> implements BinaryNodeInterface<Value> {
        Value val;
        boolean color;
        RBNode<Value> left;
        RBNode<Value> right;
        
        RBNode(Value x) {
            super();
            this.val = x;
            this.color = RED;
            String cls = this.getClass().getName();
            // String mthd = this.getClass().
            System.out.println(cls + " in RBT this.val is: " + this.val);
        }

        public Value getKey() {
            return this.val;
        }

        // public void setKey(Value newData) {
        //     this.val = newData;
        // }

        // public boolean hasLeft() {
        //     return this.left != null;
        // }

        // public boolean hasRight() {
        //     return this.right != null;
        // }

        public BinaryNodeInterface<Value> getLeftChild() {
            return (BinaryNodeInterface<Value>) this.left;
        }
    
        public BinaryNodeInterface<Value> getRightChild() {
            return (BinaryNodeInterface<Value>) this.right;
        }

        private boolean isRed(RBNode<Value> node) {
            if (node == null) {
                return false;
            }
            return node.color == RED;
        }

        private boolean getColor() {
            return this.color;
        }

        private void setColor(boolean color) {
            this.color = color;
        }
    }

    RedBlackTree() {
        super();
    }

    RedBlackTree(List<Item> l) {
        // Todo: What will super(l) do here? One thing for sure: it does not invoke insertByRootNode
        // super(l), which is with an arg, would call the constructor in BinarySearchTree.
        // Then in that constructor, it calls BinarySearchTree.insertByRootNode, rather than the one in RedBlackTree

        // super(l);
        super();

        for (Item i : l) {
            insert(i);
        }
    }

    public Item insert(Item i) {
        root = insertByRootNode(root, i);
        root.setColor(BLACK);
        System.out.println("root is now " + root.getKey());
        return i;
    }

    @Override
    public BinaryNodeInterface<Item> getRoot() {
        return (BinaryNodeInterface<Item>) root;
    }


    private RBNode<Item> insertByRootNode(RBNode<Item> h, Item i) {
        if (h == null) {
            RBNode<Item> x = new RBNode<>(i);
            System.out.println("insertByR null root: root set to: " + x.val);
            return x;
        }
        assert (i != null );
        System.out.println("item may be " + i);
        assert (h.val != null) : "h.val is " + h.val;
        int cmp = i.compareTo(h.val);
        if (cmp < 0) {
            h.left = insertByRootNode(h.left, i);
        } else if (cmp > 0) {
            h.right = insertByRootNode(h.right, i);
        } else {
            h.val = i;
        }

        if (!h.isRed(h.left) && h.isRed(h.right)) {
            h = rotateLeft(h);
        }
        if (h.isRed(h.left) && h.isRed(h.left.left)) {
            h = rotateRight(h);
        }
        if (h.isRed(h.left) && h.isRed(h.right)) {
            flipColors(h);
        }

        return h;
    
    }

    // precondition: two children are red, node is black
    // postcondition: two children are black, node is red
    private void flipColors(RBNode<Item> h) {
        System.out.println("h.val is " + h.val + " h.color is" + h.color + " flipColors");
        assert !h.isRed(h) && h.isRed(h.left) && h.isRed(h.right) : "h.color is " + h.color;
        h.color = RED;
        h.left.color = BLACK;
        h.right.color = BLACK;
    }

    private RBNode<Item> rotateLeft(RBNode<Item> h) {
        System.out.println("rotateLeft: h.color is " + h.color + " h.val: " + h.val);
        assert (h != null) && h.isRed(h.right);
        RBNode<Item> x = h.right;
        h.right = x.left;
        x.left = h;
        x.color = h.color;
        h.color = RED;

        return x;
    }

    private RBNode<Item> rotateRight(RBNode<Item> h) {
        System.out.println("on rotateRight: h.color is " + h.color + " h.val: " + h.val);
        assert (h != null) && h.isRed(h.left);
        RBNode<Item> x = h.left;
        h.left = x.right;
        x.right = h;
        x.color = h.color;
        h.color = RED;

        return x;
    }

    public static void main(String [] args) {
        List<String> al = new ArrayList<>(Arrays.asList("S", "E", "A", "R", "C", "H", "X", "Z", "T"));
        RedBlackTree<String> t = new RedBlackTree<>(al);
        RBNode<String> root = (RBNode<String>) t.getRoot();

        System.out.println("\n\nRBTreeTest: root.val is: " + root.val + root.color);
        System.out.println("RBTreeTest: root.left.getKey() " + root.left.getKey() + root.left.color);

        RBNode<String> walker = root;
        while (walker != null) {
            System.out.println("walker.getKey(): " + walker.getKey() + " walker.color " + walker.color);
            walker = walker.left;
        }

        walker = root;
        System.out.println("\n\n right search");
        while (walker != null) {
            System.out.println("walker.getKey(): " + walker.getKey() + " walker.color " + walker.color);
            walker = walker.right;
        }
    }
}