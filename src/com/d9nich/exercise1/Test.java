package com.d9nich.exercise1;

import java.util.Arrays;

public class Test {
    public static void main(String[] args) {
        BST<Integer> tree = new BST<>(new Integer[]{60, 55, 45, 57, 59, 100, 67, 107, 101});
        tree.breadthFirstTraversal();
        System.out.println();
        System.out.println(tree.height());
        System.out.println("That`s tree is perfect?: "+tree.isPerfectBST());

        tree.clear();
        tree.addAll(Arrays.asList(2, 1, 3));
        tree.preorder();
        System.out.println();
        System.out.println("That`s tree is perfect?: "+tree.isPerfectBST());
    }
}
