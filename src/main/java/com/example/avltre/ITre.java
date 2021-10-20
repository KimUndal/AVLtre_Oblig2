package com.example.avltre;

public interface ITre<E> extends Iterable<E>{
    boolean search(E t);
    boolean insert(E t);
    boolean delete(E t);
    void inorder();
    void postorder();
    void preorder();
    void breadthFirstTraversal();
    int getSize();
    boolean isEmpty();
    void clear();
}
