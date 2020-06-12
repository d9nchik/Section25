package com.d9nich.exercise1;

import java.util.Arrays;
import java.util.Iterator;
import java.util.ListIterator;

public class Test {
    public static void main(String[] args) {
        BST<Integer> tree = new BST<>(new Integer[]{60, 55, 45, 57, 59, 100, 67, 107, 101});
        tree.breadthFirstTraversal();

        ListIterator<Integer> listIterator = tree.iterator();
        System.out.println();
        while (listIterator.hasNext()) {
            System.out.print(listIterator.next() + " ");
        }
        System.out.println();
        for (int i = 0; i < 4; i++) {
            listIterator.previous();
        }
        listIterator.set(5);
        tree.forEach(e -> System.out.print(e + " "));
    }
}
