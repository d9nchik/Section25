package com.d9nich;

import com.d9nich.btree.BST;
import com.d9nich.btree.BTView;
import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class BSTAnimation extends Application {

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) {
        BST<Integer> tree = new BST<>(); // Create a tree

        BorderPane pane = new BorderPane();
        BTView view = new BTView(tree); // Create a View
        Text header = new Text();
        HBox headerBox = new HBox(header);
        headerBox.setAlignment(Pos.CENTER);
        pane.setTop(headerBox);
        pane.setCenter(view);

        TextField tfKey = new TextField();
        tfKey.setPrefColumnCount(3);
        tfKey.setAlignment(Pos.BASELINE_RIGHT);
        Button btInsert = new Button("Insert");
        Button btDelete = new Button("Delete");
        Button btInorder = new Button("Show Inorder");
        Button btPreorder = new Button("Show Preorder");
        Button btPostOrder = new Button("Show PostOrder");
        HBox hBox = new HBox(5);
        hBox.getChildren().addAll(new Label("Enter a key: "),
                tfKey, btInsert, btDelete, btInorder, btPreorder, btPostOrder);
        hBox.setAlignment(Pos.CENTER);
        pane.setBottom(hBox);

        btInsert.setOnAction(e -> {
            int key = Integer.parseInt(tfKey.getText());
            if (tree.search(key)) { // key is in the tree already
                view.displayTree();
                view.setStatus(key + " is already in the tree");
            } else {
                tree.insert(key); // Insert a new key
                view.displayTree();
                view.setStatus(key + " is inserted in the tree");
            }
        });

        btDelete.setOnAction(e -> {
            int key = Integer.parseInt(tfKey.getText());
            if (!tree.search(key)) { // key is not in the tree
                view.displayTree();
                view.setStatus(key + " is not in the tree");
            } else {
                tree.delete(key); // Delete a key
                view.displayTree();
                view.setStatus(key + " is deleted from the tree");
            }
        });

        btInorder.setOnAction(e -> header.setText("Inorder: " + tree.inorderList()));
        btPreorder.setOnAction(e -> header.setText("Preorder: " + tree.preorderList()));
        btPostOrder.setOnAction(e -> header.setText("PostOrder: " + tree.postOrderList()));

        // Create a scene and place the pane in the stage
        Scene scene = new Scene(pane, 450, 250);
        primaryStage.setTitle("BSTAnimation"); // Set the stage title
        primaryStage.setScene(scene); // Place the scene in the stage
        primaryStage.show(); // Display the stage
    }
}
