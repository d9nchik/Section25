package com.d9nich.exercise1;

import java.io.Serializable;
import java.util.*;

public class BST<E extends Comparable<E>> implements Tree<E>, Serializable, Cloneable {
    protected TreeNode<E> root;
    protected int size = 0;

    /**
     * Create an empty binary tree
     */
    public BST() {
    }

    /**
     * Create a binary tree from an array of objects
     */
    public BST(E[] objects) {
        this.addAll(Arrays.asList(objects));
    }

    @Override
    /* Returns true if the element is in the tree */
    public boolean search(E e) {
        TreeNode<E> current = root; // Start from the root

        while (current != null) {
            if (e.compareTo(current.element) < 0) {
                current = current.left;
            } else if (e.compareTo(current.element) > 0) {
                current = current.right;
            } else // element matches current.element
                return true; // Element is found
        }

        return false;
    }

    public E searchAndReturn(E e) {
        TreeNode<E> current = root; // Start from the root

        while (current != null) {
            if (e.compareTo(current.element) < 0) {
                current = current.left;
            } else if (e.compareTo(current.element) > 0) {
                current = current.right;
            } else // element matches current.element
                return current.element; // Element is found
        }

        return null;
    }

    @Override
    /* Insert element e into the binary tree
     * Return true if the element is inserted successfully */
    public boolean insert(E e) {
        if (root == null)
            root = createNewNode(e); // Create a new root
        else {
            // Locate the parent node
            TreeNode<E> parent = null;
            TreeNode<E> current = root;
            while (current != null)
                if (e.compareTo(current.element) < 0) {
                    parent = current;
                    current = current.left;
                } else if (e.compareTo(current.element) > 0) {
                    parent = current;
                    current = current.right;
                } else
                    return false; // Duplicate node not inserted

            // Create the new node and attach it to the parent node
            if (e.compareTo(parent.element) < 0)
                parent.left = createNewNode(e);
            else
                parent.right = createNewNode(e);
        }

        size++;
        return true; // Element inserted successfully
    }

    protected TreeNode<E> createNewNode(E e) {
        return new TreeNode<>(e);
    }

    @Override
    /* Inorder traversal from the root */
    public void inorder() {
        inorder(root);
    }

    /**
     * Inorder traversal from a subtree
     */
    protected void inorder(TreeNode<E> root) {
        if (root == null) return;
        inorder(root.left);
        System.out.print(root.element + " ");
        inorder(root.right);
    }

    @Override
    /* Postorder traversal from the root */
    public void postorder() {
        postorder(root);
    }

    /**
     * Postorder traversal from a subtree
     */
    protected void postorder(TreeNode<E> root) {
        if (root == null) return;
        postorder(root.left);
        postorder(root.right);
        System.out.print(root.element + " ");
    }

    @Override
    /* Preorder traversal from the root */
    public void preorder() {
        preorder(root);
    }

    /**
     * Preorder traversal from a subtree
     */
    protected void preorder(TreeNode<E> root) {
        if (root == null) return;
        System.out.print(root.element + " ");
        preorder(root.left);
        preorder(root.right);
    }

    /**
     * Display the nodes in a breadth-first traversal
     */
    public void breadthFirstTraversal() {
        if (root == null)
            return;
        Queue<TreeNode<E>> queue = new LinkedList<>();
        queue.offer(root);
        while (queue.size() != 0) {
            TreeNode<E> current = queue.remove();
            System.out.print(current.element + " ");
            if (current.left != null)
                queue.offer(current.left);
            if (current.right != null)
                queue.offer(current.right);
        }
    }

    @Override
    /* Get the number of nodes in the tree */
    public int getSize() {
        return size;
    }

    /**
     * Return the height of this binary tree
     */
    public int height() {
        return height(root);
    }

    /**
     * Return the number of leaf nodes
     */
    public int getNumberOfLeaves() {
        return getNumberOfLeaves(root);
    }

    /**
     * Return the number of nonleaf nodes
     */
    public int getNumberOfNonLeaves() {
        return size - getNumberOfLeaves();
    }

    /**
     * Return the number of leaf nodes in subtree
     */
    protected int getNumberOfLeaves(TreeNode<E> root) {
        if (root == null)
            return 0;
        if (root.right == null && root.left == null)
            return 1;
        return getNumberOfLeaves(root.right) + getNumberOfLeaves(root.left);
    }

    /**
     * Return the height of this binary subtree
     */
    protected int height(TreeNode<E> root) {
        if (root == null)
            return 0;
        return Math.max(height(root.right), height(root.left)) + 1;
    }

    /**
     * Returns true if the tree is a perfect binary tree
     */
    public boolean isPerfectBST() {
        return size + 1 == 1 << height();
    }

    /**
     * Returns the root of the tree
     */
    public TreeNode<E> getRoot() {
        return root;
    }

    /* Returns a path from the root leading to the specified element */
    public java.util.ArrayList<TreeNode<E>> path(E e) {
        java.util.ArrayList<TreeNode<E>> list =
                new java.util.ArrayList<>();
        TreeNode<E> current = root; // Start from the root

        while (current != null) {
            list.add(current); // Add the node to the list
            if (e.compareTo(current.element) < 0) {
                current = current.left;
            } else if (e.compareTo(current.element) > 0) {
                current = current.right;
            } else
                break;
        }

        return list; // Return an array list of nodes
    }

    @Override
    /* Delete an element from the binary tree.
     * Return true if the element is deleted successfully
     * Return false if the element is not in the tree */
    public boolean delete(E e) {
        // Locate the node to be deleted and also locate its parent node
        TreeNode<E> parent = null;
        TreeNode<E> current = root;
        while (current != null) {
            if (e.compareTo(current.element) < 0) {
                parent = current;
                current = current.left;
            } else if (e.compareTo(current.element) > 0) {
                parent = current;
                current = current.right;
            } else
                break; // Element is in the tree pointed at by current
        }
        if (current == null)
            return false; // Element is not in the tree

        // Case 1: current has no left child
        if (current.left == null) {
            // Connect the parent with the right child of the current node
            if (parent == null) {
                root = current.right;
            } else {
                if (e.compareTo(parent.element) < 0)
                    parent.left = current.right;
                else
                    parent.right = current.right;
            }
        } else {
            // Case 2: The current node has a left child
            // Locate the rightmost node in the left subtree of
            // the current node and also its parent
            TreeNode<E> parentOfRightMost = current;
            TreeNode<E> rightMost = current.left;

            while (rightMost.right != null) {
                parentOfRightMost = rightMost;
                rightMost = rightMost.right; // Keep going to the right
            }

            // Replace the element in current by the element in rightMost
            current.element = rightMost.element;

            // Eliminate rightmost node
            if (parentOfRightMost.right == rightMost)
                parentOfRightMost.right = rightMost.left;
            else
                // Special case: parentOfRightMost == current
                parentOfRightMost.left = rightMost.left;
        }

        size--;
        return true; // Element deleted successfully
    }

    @SuppressWarnings("unchecked")
    @Override
    public Object clone() {
        try {
            BST<E> bst = (BST<E>) super.clone();
            if (bst.root != null)
                bst.root = (TreeNode<E>) bst.root.clone();
            return bst;
        } catch (CloneNotSupportedException ex) {
            return null;
        }
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        BST<?> bst = (BST<?>) o;

        if (size != bst.size) return false;
        return Objects.equals(root, bst.root);
    }

    @Override
    public int hashCode() {
        int result = root != null ? root.hashCode() : 0;
        result = 31 * result + size;
        return result;
    }

    @Override
    /* Obtain an iterator. Use inorder. */
    public java.util.ListIterator<E> iterator() {
        return new InorderListIterator(() -> inorderList(root));
    }

    /**
     * Return an iterator for traversing the elements in preorder
     */
    public java.util.ListIterator<E> preorderIterator() {
        return new InorderListIterator(() -> preorderList(root));
    }

    @Override
    /* Remove all elements from the tree */
    public void clear() {
        root = null;
        size = 0;
    }

    /*
     * This inner class is static, because it does not access
     * any instance members defined in its outer class
     */
    public static class TreeNode<E> implements Serializable, Cloneable {
        protected E element;
        protected TreeNode<E> left;
        protected TreeNode<E> right;

        public TreeNode(E e) {
            element = e;
        }

        @SuppressWarnings("unchecked")
        @Override
        public Object clone() {
            try {
                TreeNode<E> treeNode = (TreeNode<E>) super.clone();
                if (treeNode.left != null)
                    treeNode.left = (TreeNode<E>) treeNode.left.clone();
                if (treeNode.right != null)
                    treeNode.right = (TreeNode<E>) treeNode.right.clone();
                return treeNode;
            } catch (CloneNotSupportedException ex) {
                return null;
            }
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;

            TreeNode<?> treeNode = (TreeNode<?>) o;

            if (!element.equals(treeNode.element)) return false;
            if (!Objects.equals(left, treeNode.left)) return false;
            return Objects.equals(right, treeNode.right);
        }

        @Override
        public int hashCode() {
            int result = element.hashCode();
            result = 31 * result + (left != null ? left.hashCode() : 0);
            result = 31 * result + (right != null ? right.hashCode() : 0);
            return result;
        }
    }

    private LinkedList<E> inorderList(TreeNode<E> root) {
        LinkedList<E> list = new LinkedList<>();
        if (root == null) return list;
        list.addAll(inorderList(root.left));
        list.add(root.element);
        list.addAll(inorderList(root.right));
        return list;
    }

    private LinkedList<E> preorderList(TreeNode<E> root) {
        LinkedList<E> list = new LinkedList<>();
        if (root == null) return list;
        list.add(root.element);
        list.addAll(preorderList(root.left));
        list.addAll(preorderList(root.right));
        return list;
    }

    @SuppressWarnings("RawUseOfParameterized")
    @FunctionalInterface
    interface Order {
        LinkedList getList();
    }

    // Inner class InorderListIterator
    @SuppressWarnings("unchecked")
    private class InorderListIterator implements ListIterator<E> {
        // Store the elements in a list
        private final Order order;
        private ListIterator<E> listIterator;
        private E previousElement = null;

        public InorderListIterator(Order order) {
            this.order = order;
            listIterator = order.getList().listIterator();
        }

        @Override
        public boolean hasNext() {
            return listIterator.hasNext();
        }

        @Override
        public E next() {
            previousElement = listIterator.next();
            return previousElement;
        }

        @Override
        public boolean hasPrevious() {
            return listIterator.hasPrevious();
        }

        @Override
        public E previous() {
            previousElement = listIterator.previous();
            return previousElement;
        }

        @Override
        public int nextIndex() {
            return listIterator.nextIndex();
        }

        @Override
        public int previousIndex() {
            return listIterator.previousIndex();
        }

        @Override
        public void remove() {
            if (listIterator.nextIndex() == 0) // next() has not been called yet
                throw new IllegalStateException();

            delete(previousElement);
            listIterator.remove();
        }

        @Override
        public void set(E e) {
            if (listIterator.nextIndex() == 0) // next() has not been called yet
                throw new IllegalStateException();

            delete(previousElement);
            insert(e);
            int pos = nextIndex();
            listIterator = order.getList().listIterator(pos);
        }

        @Override
        public void add(E e) {
            if (listIterator.nextIndex() == 0) // next() has not been called yet
                throw new IllegalStateException();

            insert(e);
            int pos = nextIndex();
            listIterator = order.getList().listIterator(pos);
        }
    }
}