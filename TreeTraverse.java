package MyStl;

import java.util.Arrays;
import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

import MyStl.*;

public class TreeTraverse<Item extends Comparable<? super Item>> {
    public static void main(String[] args) {
        TreeTraverse.BinarySearchTreeTest();
        // TreeTraverse.RBTreeTest();
    }


    public static void BinarySearchTreeTest() {
        List<String> al = new ArrayList<>(Arrays.asList("S", "E", "A", "R", "C", "H", "X", "Z", "T"));
        BinarySearchTree<String> t = new BinarySearchTree<>(al);
        BinarySearchTree.TreeNode<String> root = t.getRoot();
        System.out.println("root.val is: " + root.val);

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
        RedBlackTree.RBNode<String> root = t.getRoot();
        System.out.println("RBTreeTest: root.val is: " + root.val);
        System.out.println("root.left.val " + root.left.val + " root.left.color");

        TreeTraverse<String> travs = new TreeTraverse<>();
        System.out.println("Now in-order recursively");
        travs.inOrderTraverseRecur(root);

        System.out.println("Now in-order iteratively");
        travs.inOrderTraverseIter(root);

        System.out.println(t.getRoot());
    }



    public void inOrderTraverseRecur(BinarySearchTree.TreeNode<Item> root) {
        if (root == null) {
            return;
        }

        inOrderTraverseRecur(root.left);

        System.out.println(root.val);

        inOrderTraverseRecur(root.right);
    }

    public void inOrderTraverseIter(BinarySearchTree.TreeNode<Item> root) {
        BinarySearchTree.TreeNode<Item> cur = root;
        // BinarySearchTree.TreeNode<Item> pre = null;
        Stack<BinarySearchTree.TreeNode<Item>> tStack = new Stack<>();
        while (cur != null || !tStack.isEmpty()) {
            if (cur != null) {
                tStack.push(cur);
                cur = cur.left;
            } else {
                cur = tStack.pop();
                // process the node
                System.out.println(cur.val);

                cur = cur.right;
            }
        }
    }
}