package com.example.avltre;

import javafx.scene.control.ToggleGroup;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;
//TODO: LAGE EIN KNAPP SOM GJØR OM TREET TIL INTEGER ELLER STRING
public class AVLAnimasjon extends BSTAnimasjon {

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) {
        AVLTre<String> avlTre = new AVLTre<>();
        BorderPane borderPane = new BorderPane();
        AVLView<String> view = new AVLView<>(avlTre);
        setPane(borderPane, view, avlTre);
        setStage(borderPane, primaryStage, "AVL Animasjon");
    }

}
