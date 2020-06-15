package com.d9nich.btree;

import java.util.Arrays;
import java.util.Comparator;
import java.util.Scanner;

public class Test {

    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);

        System.out.print("Enter 10 integers: ");
        BST<Integer> tree = new BST<>();
        for (int i = 0; i < 10; i++)
            tree.add(input.nextInt());
        tree.remove(tree.root.element);
        tree.forEach(e -> {
            if (tree.isLeaf(e))
                System.out.println(tree.getPath(e));
        });
    }
}
