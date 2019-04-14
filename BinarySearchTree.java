package MyStl;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;


public class BinarySearchTree <Item extends Comparable<? super Item>> implements MyTreeInterface<Item> {
    private BinaryNodeInterface<Item> root;

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

    public BinaryNodeInterface<Item> getRoot() {
        return root;
    }

    public static void main(String[] args) {
        BinarySearchTree<String> bst = new BinarySearchTree<>();
        System.out.println("test main");
    }


    private BinaryNodeInterface<Item> insertByRootNode(BinaryNodeInterface<Item> root, Item i) {
        BinaryNodeInterface<Item> x = new TreeNode<>(i);
        if (root == null) {
            root = x;
            // System.out.println("BinarySearchTree.insert:\troot.val is: " + root.val);
            
            return root;
        }
        BinaryNodeInterface<Item> walker = root;
        BinaryNodeInterface<Item> pre = null;
        while (walker != null) {
            pre = walker;

            if (i.compareTo(walker.getKey()) < 0) {
                walker = walker.getLeftChild();
            } else {
                walker = walker.getRightChild();
            }
        }

        // System.out.println("BinarySearchTree.insert:\tpre.val: " + pre.val +
        //                     " Item i: " + i);
        if (i.compareTo(pre.getKey()) < 0) {
            pre.setLeftChild(x);
        } else {
            pre.setRightChild(x);
        }

        return root;
    }

}