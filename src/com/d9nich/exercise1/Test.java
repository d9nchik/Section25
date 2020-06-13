package com.d9nich.exercise1;

public class Test {

    @SuppressWarnings("unchecked")
    public static void main(String[] args) {
        BST<Integer> tree = new BST<>(new Integer[]{60, 55, 45, 57, 59, 100, 67, 107, 101});
        tree.breadthFirstTraversal();

        BST<Integer> treeClone = (BST<Integer>) tree.clone();
        System.out.println("Tree clone equals link?: " + (treeClone == tree));
        System.out.println("Tree clone equals?: " + (treeClone.equals(tree)));
        treeClone.inorder();
    }
}
