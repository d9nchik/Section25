package com.d9nich.btree;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.LinkedList;

import static org.junit.jupiter.api.Assertions.*;

class BSTTest {
    BST<Integer> empty = new BST<>();
    BST<Integer> medium = new BST<>();
    BST<Integer> high = new BST<>();


    @BeforeEach
    void setUP() {
        medium.addAll(Arrays.asList(2, 1, 3));
        for (int i = 0; i < 100; i++)
            high.add(i);
    }

    @Test
    void search() {
        assertTrue(high.search(99));
        assertFalse(high.search(-1));
    }

    @Test
    void searchAndReturn() {
        assertEquals(10, high.searchAndReturn(10));
        assertNull(high.searchAndReturn(-1));
    }

    @Test
    void insert() {
        assertEquals(0, empty.size);
        empty.insert(1);
        assertEquals(1, empty.size);
        assertTrue(empty.contains(1));
        empty.insert(3);
        assertEquals(2, empty.size);
        assertTrue(empty.contains(3));
    }

    @Test
    void createNewNode() {
        assertEquals(5, empty.createNewNode(5).element);
    }

    @Test
    void getSize() {
        assertEquals(0, empty.size);
        assertEquals(3, medium.size);
        assertEquals(100, high.size);
    }

    @Test
    void height() {
        assertEquals(0, empty.height());
        assertEquals(2, medium.height());
        assertEquals(100, high.height());
    }

    @Test
    void getNumberOfLeaves() {
        assertEquals(0, empty.getNumberOfLeaves());
        assertEquals(2, medium.getNumberOfLeaves());
        medium.add(0);
        assertEquals(2, medium.getNumberOfLeaves());
        assertEquals(1, high.getNumberOfLeaves());
    }

    @Test
    void getNumberOfNonLeaves() {
        assertEquals(0, empty.getNumberOfNonLeaves());
        assertEquals(1, medium.getNumberOfNonLeaves());
        medium.add(0);
        assertEquals(2, medium.getNumberOfNonLeaves());
        assertEquals(99, high.getNumberOfNonLeaves());
    }

    @Test
    void isPerfectBST() {
        assertTrue(empty.isPerfectBST());
        assertFalse(high.isPerfectBST());
        assertTrue(medium.isPerfectBST());
    }

    @Test
    void getRoot() {
        assertEquals(empty.root, empty.getRoot());
        assertEquals(medium.root, medium.getRoot());
        assertEquals(high.root, high.getRoot());
    }

    @Test
    void path() {
        assertTrue(empty.getPath(3).isEmpty());
        ArrayList<BST.TreeNode<Integer>> arrayList = high.path(3);
        ArrayList<Integer> arrayList1 = new ArrayList<>();
        arrayList.forEach(e -> arrayList1.add(e.element));
        int k = 0;
        for (Integer i : arrayList1)
            assertEquals(k++, i);
    }

    @Test
    void delete() {
        high.delete(20);
        assertEquals(99, high.size);
        assertFalse(high.contains(20));
        high.delete(-1);
        assertEquals(99, high.size);
    }

    @Test
    void isLeaf() {
        assertTrue(medium.isLeaf(3));
        assertFalse(medium.isLeaf(2));
    }

    @Test
    void getPath() {
        assertTrue(empty.getPath(3).isEmpty());
        ArrayList<Integer> arrayList = medium.getPath(3);
        int k = 3;
        for (Integer i : arrayList)
            assertEquals(k--, i);
    }

    @Test
    @SuppressWarnings("unchecked")
    void testClone() {
        BST<Integer> bst = (BST<Integer>) medium.clone();
        assertEquals(bst, medium);
        assertEquals(bst.root.element, medium.root.element);
    }

    @Test
    @SuppressWarnings("unchecked")
    void testEquals() {
        BST<Integer> bst = (BST<Integer>) medium.clone();
        assertEquals(bst, medium);
        assertNotEquals(bst, high);
    }

    @Test
    @SuppressWarnings("unchecked")
    void testHashCode(){
        BST<Integer> bst = (BST<Integer>) medium.clone();
        assertEquals(bst.hashCode(), medium.hashCode());
        assertNotEquals(bst.hashCode(), high.hashCode());
    }

    @Test
    void iterator() {
        int k = 1;
        for (int j : medium)
            assertEquals(k++, j);
    }

    @Test
    void preorderIterator() {
        Iterator<Integer> iterator = medium.preorderIterator();
        int[] array = {2, 1, 3};
        int k = 0;
        while (iterator.hasNext())
            assertEquals(array[k++], iterator.next());
    }

    @Test
    void postOrderIterator() {
        Iterator<Integer> iterator = medium.postOrderIterator();
        int[] array = {1, 3, 2};
        int k = 0;
        while (iterator.hasNext())
            assertEquals(array[k++], iterator.next());
    }

    @Test
    void clear() {
        high.clear();
        assertEquals(0, high.getSize());
        assertNull(high.root);
    }

    @Test
    void inorderList() {
        LinkedList<Integer> list = medium.inorderList();
        LinkedList<Integer> list1 = new LinkedList<>(Arrays.asList(1, 2, 3));
        assertEquals(list, list1);
    }

    @Test
    void preorderList() {
        LinkedList<Integer> list = medium.preorderList();
        LinkedList<Integer> list1 = new LinkedList<>(Arrays.asList(2, 1, 3));
        assertEquals(list, list1);
    }

    @Test
    void postOrderList() {
        LinkedList<Integer> list = medium.postOrderList();
        LinkedList<Integer> list1 = new LinkedList<>(Arrays.asList(1, 3, 2));
        assertEquals(list, list1);
    }

}