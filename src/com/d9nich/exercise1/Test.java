package com.d9nich.exercise1;

public class Test {
    public static void main(String[] args) {
        BST<Integer> tree = new BST<>(new Integer[]{60, 55, 45, 57, 59, 100, 67, 107, 101});
        tree.breadthFirstTraversal();
        System.out.println();
        System.out.println(tree.height());
    }
}
