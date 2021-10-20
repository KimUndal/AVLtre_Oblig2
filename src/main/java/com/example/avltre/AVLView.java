package com.example.avltre;

import javafx.scene.text.Text;

public class AVLView<E> extends BSTView {
    private AVLTre<E> tre;
    private double vGap = 50;
    AVLView(BST<E> tre) {
        super(tre);
        this.tre = (AVLTre<E>) tre;
    }

    @Override
    public void setStatus(String melding) {
        getChildren().add(new Text(20,20, melding));
    }

    @Override
    public void displayTree() {
        this.getChildren().clear();
        if(tre.getRot() != null){
            displayTree(tre.getRot(), getWidth() / 2, vGap, getWidth()/ 4);
        }
    }
}
