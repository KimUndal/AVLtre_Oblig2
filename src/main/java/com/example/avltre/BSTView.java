package com.example.avltre;

import javafx.application.Application;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Line;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class BSTView<E> extends Pane {

    private BST<E> tre;
    private double radius = 15;
    private double vGap = 50;

    BSTView(BST<E> tre){
        this.tre = tre;
        setStatus("treet er tomt");
    }

    public void setStatus(String melding) {
        getChildren().add(new Text(20,20, melding));
    }

    public void displayTree(){
        this.getChildren().clear();
        if(tre.getRot() != null){
            displayTree(tre.getRot(), getWidth() / 2, vGap, getWidth() / 4);
        }
    }

    public void removeTree(){
       this.getChildren().clear();
    }

    public void displayTree(BST.TreNode<E> rot, double x, double y, double hGap) {
        if(rot.venstre != null){
            getChildren().add(new Line(x - hGap, y + vGap, x, y));
            displayTree(rot.venstre, x- hGap, y+vGap, hGap / 2);
        }
        if(rot.hogre!= null){
            getChildren().add(new Line(x+hGap, y+vGap,x, y));
            displayTree(rot.hogre, x + hGap, y + vGap, hGap/2);
        }
        Circle sirkel = new Circle(x, y, radius);
        sirkel.setFill(Color.WHEAT);
        sirkel.setStroke(Color.BLACK);
        getChildren().addAll(sirkel,new Text(x-4,y+4,rot.element+ " "));
    }

    public void displayInorder() {
        displayTree();

    }

    public void displayPreorder() {
        displayTree();
    }

    public void displayPostorder() {
        displayTree();
    }
    public void displayInorder(BST<E> tre){
        tre.inorder();
    }
    public void displayPreorder(BST<E> tre){
        tre.preorder();
    }
    public void displayPostorder(BST<E> tre){
        tre.postorder();
    }
}
