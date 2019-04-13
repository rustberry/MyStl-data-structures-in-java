package MyStl;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;


public class BinarySearchTree <Item extends Comparable<? super Item>> implements MyTreeInterface<Item> {
    private TreeNode<Item> root;

    public static class TreeNode<Value extends Comparable<? super Value>> implements BinaryNodeInterface<Value> {
        Value val;
        TreeNode<Value> left;
        TreeNode<Value> right;

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

        TreeNode() {}
        
        TreeNode(Value x) { 
            val = x;
            // String cls = this.getClass().getName();
            // System.out.println(cls + " this.val is: " + this.val);
        }
        // Todo: solved "The type parameter Item is hiding the type Item"
        // by replacing Item with Value in the declaration of inner class TreeNode
    }

    BinarySearchTree() {
        // assert false : "BinarySearchTree no-arg init invoked"; 
        // List<String> al = new ArrayList<>(Arrays.asList("E", "A", "R", "C", "H", "X", "Z", "T"));
        // root = new TreeNode<Item>((Item)"S");
        // for (String i : al) {
        //     insertByRootNode(root, (Item)i);
        // }
        this.root = null;
    }

    BinarySearchTree(List<Item> l) {
        // assert false : "BinarySearchTree init invoked";
        for (Item i : l) {
            root = insertByRootNode(root, i);
        }
    }

    public Item insert(Item i) {
        insertByRootNode(root, i);
        return i;
    }

    public boolean isEmpty() {
        return root != null;
    }


    public static void main(String[] args) {
        System.out.println("test main");
    }

    public TreeNode<Item> getRoot() {
        return root;
    }

    private TreeNode<Item> insertByRootNode(TreeNode<Item> root, Item i) {
        TreeNode<Item> x = new TreeNode<>(i);
        if (root == null) {
            root = x;
            // System.out.println("BinarySearchTree.insert:\troot.val is: " + root.val);
            
            return root;
        }
        TreeNode<Item> walker = root;
        TreeNode<Item> pre = null;
        while (walker != null) {
            pre = walker;

            if (i.compareTo(walker.val) < 0) {
                walker = walker.left;
            } else {
                walker = walker.right;
            }
        }

        // System.out.println("BinarySearchTree.insert:\tpre.val: " + pre.val +
        //                     " Item i: " + i);
        if (i.compareTo(pre.val) < 0) {
            pre.left = x;
        } else {
            pre.right = x;
        }

        return root;
    }

}