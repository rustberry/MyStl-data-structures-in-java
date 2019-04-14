package MyStl;

import java.util.Arrays;
import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

import MyStl.*;

public class TreeTraverse<Item extends Comparable<? super Item>> {
    public static void main(String[] args) {
        // TreeTraverse.BinarySearchTreeTest();
        TreeTraverse.RBTreeTest();
    }


    public static void BinarySearchTreeTest() {
        List<String> al = new ArrayList<>(Arrays.asList("S", "E", "A", "R", "C", "H", "X", "Z", "T"));
        BinarySearchTree<String> t = new BinarySearchTree<>(al);
        BinaryNodeInterface<String> root = t.getRoot();
        System.out.println("root.val is: " + root.getKey());

        TreeTraverse<String> travs = new TreeTraverse<>();
        System.out.println("Now in-order recursively");
        travs.inOrderTraverseRecur(root);

        System.out.println("Now in-order iteratively");
        travs.inOrderTraverseIter(root);

        System.out.println(t.getRoot());
    }

    public static void RBTreeTest() {
        List<String> al = new ArrayList<>(Arrays.asList("S", "E", "A", "R", "C", "H", "X", "Z", "T"));
        RedBlackTree<String> t = new RedBlackTree<>(al);
        BinaryNodeInterface<String> root = t.getRoot();
        System.out.println("RBTreeTest: root.val is: " + root.getKey());
        System.out.println("root.left.val " + root.getLeftChild().getKey() + " root.left.color");

        TreeTraverse<String> travs = new TreeTraverse<>();
        System.out.println("Now in-order recursively");
        travs.inOrderTraverseRecur(root);

        System.out.println("Now in-order iteratively");
        travs.inOrderTraverseIter(root);

        System.out.println(t.getRoot());
    }



    public void inOrderTraverseRecur(BinaryNodeInterface<Item> root) {
        if (root == null) {
            return;
        }

        inOrderTraverseRecur(root.getLeftChild());

        System.out.println(root.getKey());

        inOrderTraverseRecur(root.getRightChild());
    }

    public void inOrderTraverseIter(BinaryNodeInterface<Item> root) {
        BinaryNodeInterface<Item> cur = root;
        // TreeNode<Item> pre = null;
        Stack<BinaryNodeInterface<Item>> tStack = new Stack<>();
        while (cur != null || !tStack.isEmpty()) {
            if (cur != null) {
                tStack.push(cur);
                cur = cur.getLeftChild();
            } else {
                cur = tStack.pop();
                // process the node
                System.out.println(cur.getKey());

                cur = cur.getRightChild();
            }
        }
    }
}