package com.d9nich.btree;

import java.util.Arrays;
import java.util.Comparator;

public class Test {

    public static void main(String[] args) {
        final Integer[] integers = {60, 55, 45, 57, 59, 100, 67, 107, 101};
        BST<Integer> tree = new BST<>(Comparator.reverseOrder());
        tree.addAll(Arrays.asList(integers));
        tree.forEach(e -> System.out.print(e + " "));
    }
}
