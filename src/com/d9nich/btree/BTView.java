package com.d9nich.btree;

import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Line;
import javafx.scene.text.Text;

public class BTView extends Pane {
    private final static double V_GAP = 50; // Gap between two levels in a tree
    private final BST<Integer> tree;

    public BTView(BST<Integer> tree) {
        this.tree = tree;
        setStatus("Tree is empty");
    }

    public void setStatus(String msg) {
        getChildren().add(new Text(20, 20, msg));
    }

    public void displayTree() {
        this.getChildren().clear(); // Clear the pane
        if (tree.getRoot() != null) {
            // Display tree recursively
            displayTree(tree.getRoot(), getWidth() / 2, V_GAP,
                    getWidth() / 4);
        }
    }

    /**
     * Display a subtree rooted at position (x, y)
     */
    private void displayTree(BST.TreeNode<Integer> root,
                             double x, double y, double hGap) {
        if (root.left != null) {
            // Draw a line to the left node
            getChildren().add(new Line(x - hGap, y + V_GAP, x, y));
            // Draw the left subtree recursively
            displayTree(root.left, x - hGap, y + V_GAP, hGap / 2);
        }

        if (root.right != null) {
            // Draw a line to the right node
            getChildren().add(new Line(x + hGap, y + V_GAP, x, y));
            // Draw the right subtree recursively
            displayTree(root.right, x + hGap, y + V_GAP, hGap / 2);
        }

        // Display a node
        // Tree node RADIUS
        final double RADIUS = 15;
        Circle circle = new Circle(x, y, RADIUS);
        circle.setFill(Color.WHITE);
        circle.setStroke(Color.BLACK);
        getChildren().addAll(circle,
                new Text(x - 4, y + 4, root.element + ""));
    }
}
