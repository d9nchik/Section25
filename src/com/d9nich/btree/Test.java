package com.d9nich.btree;

import java.util.Iterator;

public class Test {

    public static void main(String[] args) {
        BST<Integer> tree = new BST<>(new Integer[]{60, 55, 45, 57, 59, 100, 67, 107, 101});
        Iterator<Integer> iterator = tree.iterator();
        while (iterator.hasNext())
            System.out.print(iterator.next() + " ");
        iterator = tree.postOrderIterator();
        System.out.println();
        while (iterator.hasNext())
            System.out.print(iterator.next() + " ");
    }
}
