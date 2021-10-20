package com.example.avltre;

import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;

public class BSTAnimasjon extends Application {

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) {
        BST<String> tre = new BST<>();
        BorderPane borderPane = new BorderPane();
        BSTView view = new BSTView(tre);
        setPane(borderPane, view, tre);
        setStage(borderPane, primaryStage, "BST Animasjon");
    }

    public void setStage(BorderPane borderPane, Stage primaryStage, String title) {
        Scene scene = new Scene(borderPane, 500, 500);
        primaryStage.setTitle(title);
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    public void setPane(BorderPane borderPane, BSTView view, BST<String> tre) {
        borderPane.setCenter(view);
        TextField textField = new TextField();
        textField.setPrefColumnCount(3);
        textField.setAlignment(Pos.BASELINE_RIGHT);
        Button insert = new Button("insert");
        Button delete = new Button("delete");
        Button search = new Button("Search");
        Button fyllTreet = new Button("Fyll treet");
        HBox hBox = new HBox(5);
        hBox.getChildren().addAll(new Label("Enter an element"), textField, insert, delete, search, fyllTreet);
        hBox.setAlignment(Pos.CENTER);
        handleFunctionalities(textField, insert, delete, tre, view, search, fyllTreet);
        borderPane.setBottom(hBox);
    }

    public void handleFunctionalities(TextField textField, Button insert, Button delete, BST<String> tre, BSTView view, Button search, Button fyllTreet) {

        insert.setOnAction(e -> {
            String key = textField.getText();
            if (tre.search(key)) {
                view.displayTree();
                view.setStatus(key + " is already in the tree");

            } else {
                tre.insert(key);
                view.displayTree();
                view.setStatus(key + " is inserted in the tree");
                textField.clear();
            }
        });

        delete.setOnAction(e -> {
            String key = textField.getText();
            if (!tre.search(key)) {
                view.displayTree();
                view.setStatus(key + " is not in the tree");
                textField.clear();
            } else {
                tre.delete(key);
                view.displayTree();
                view.setStatus(key + " is deleted from the tree");
                textField.clear();
            }
        });
        search.setOnAction(e -> {
            String key = textField.getText();
            if (tre.search(key)) {
                view.displayTree();
                view.setStatus(key + " er funnet i treet");
                textField.clear();
            } else {
                view.displayTree();
                view.setStatus(key + " er ikkje funnet i treet");
                textField.clear();
            }
        });

        fyllTreet.setOnAction(e -> {
            for (char j = 'a'; j < 'z'; j++) {
                tre.insert(String.valueOf(rndChar()));
            }
            view.displayTree();
        });

    }

    private static char rndChar() {
        int rnd = (int) (Math.random() * 11); // or use Random or whatever
        char base = (rnd < 26) ? 'A' : 'a';
        return (char) (base + rnd % 26);

    }
}
