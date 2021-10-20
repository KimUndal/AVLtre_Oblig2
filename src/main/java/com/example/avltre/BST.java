package com.example.avltre;

import java.util.*;

public class BST<E> implements ITre<E> {

    protected TreNode<E> rot;
    protected int storrelse;
    protected Comparator<E> c;

    public TreNode<E> getRot() {
        return rot;
    }

    public BST() {
        this.c = (e1, e2) -> ((Comparable<E>) e1).compareTo(e2);
    }

    public BST(Comparator<E> c) {
        this.c = c;
    }

    public BST(E[] objects) {
        this.c = (e1, e2) -> ((Comparable<E>) e1).compareTo(e2);
        for (E object : objects) {
            insert(object);
        }
    }

    @Override
    public boolean search(E e) {
        TreNode<E> current = rot;
        while (current != null) {
            if (c.compare(e, current.element) < 0) {
                current = current.venstre;
            } else if (c.compare(e, current.element) > 0) {
                current = current.hogre;
            } else
                return true;
        }
        return false;
    }

    @Override
    public boolean insert(E e) {
        if (rot == null) {
            rot = createNewNode(e);
        } else {
            TreNode<E> foreldre = null;
            TreNode<E> current = rot;
            while (current != null) {
                if (c.compare(e, current.element) < 0) {
                    foreldre = current;
                    current = current.venstre;
                } else if (c.compare(e, current.element) > 0) {
                    foreldre = current;
                    current = current.hogre;
                } else
                    return false;
            }
            if (c.compare(e, foreldre.element) < 0)
                foreldre.venstre = createNewNode(e);
            else
                foreldre.hogre = createNewNode(e);
        }
        storrelse++;
        return true;
    }


    @Override
    public boolean delete(E e) {
        TreNode<E> foreldre = null;
        TreNode<E> current = rot;
        while (current != null) {
            if (c.compare(e, current.element) < 0) {
                foreldre = current;
                current = current.venstre;
            } else if (c.compare(e, current.element) > 0) {
                foreldre = current;
                current = current.hogre;
            } else
                break;
        }
        if (current == null)
            return false;
        if (current.venstre == null) {
            if (foreldre == null) {
                rot = current.hogre;
            } else {
                if (c.compare(e, foreldre.element) < 0)
                    foreldre.venstre = current.hogre;
                else
                    foreldre.hogre = current.hogre;
            }
        } else {

            TreNode<E> foreldreMestHogre = current;
            TreNode<E> mestHogre = current.venstre;

            while (mestHogre.hogre != null) {
                foreldreMestHogre = mestHogre;
                mestHogre = foreldreMestHogre.hogre;
            }
            current.element = mestHogre.element;
            if (foreldreMestHogre.hogre == mestHogre) {
                foreldreMestHogre.hogre = mestHogre.venstre;
            } else
                foreldreMestHogre.venstre = mestHogre.venstre;
        }
        storrelse--;
        return true;
    }

    @Override
    public void inorder() {
        inorder(rot);
    }

    private void inorder(TreNode<E> rot) {

        if (rot == null) return;
        inorder(rot.venstre);
        System.out.print(rot.element + " ");
        inorder(rot.hogre);
        System.out.println();
    }

    @Override
    public void postorder() {
        postorder(rot);
    }

    private void postorder(TreNode<E> rot) {
        if (rot == null) return;
        postorder(rot.venstre);
        postorder(rot.hogre);
        System.out.print(rot.element + " ");
        System.out.println();
    }

    @Override
    public void preorder() {
        preorder(rot);
    }

    private void preorder(TreNode<E> rot) {
        if (rot == null) return;
        System.out.print(rot.element + " ");
        preorder(rot.venstre);
        preorder(rot.hogre);
        System.out.println();
    }

    @Override
    public void breadthFirstTraversal() {
        if(this.rot == null){
            return;
        }
        LinkedList<TreNode<E>>linkedList = new LinkedList<>();
        linkedList.add(this.rot);
        while(!linkedList.isEmpty()){
            TreNode<E>current = linkedList.element();
            if(current.venstre != null){
                linkedList.add(current.venstre);
            }
            if(current.hogre!= null){
                linkedList.add(current.hogre);
            }
            System.out.println(linkedList.poll()+ ", ");
        }
    }



    @Override
    public int getSize() {
        return storrelse;
    }

    @Override
    public boolean isEmpty() {
        return false;
    }

    public ArrayList<TreNode<E>> path(E e) {
        ArrayList<TreNode<E>> nodeListe = new ArrayList<>();
        TreNode<E> current = rot;
        while (current != null) {
            nodeListe.add(current);
            if (c.compare(e, current.element) < 0) {
                current = current.venstre;
            } else if (c.compare(e, current.element) > 0) {
                current = current.hogre;
            } else
                break;
        }
        return nodeListe;
    }

    /**
     * Returns an iterator over elements of type {@code T}.
     *
     * @return an Iterator.
     */
    @Override
    public Iterator<E> iterator() {
        return new InorderIterator();
    }

    private class InorderIterator implements Iterator<E> {
        private ArrayList<E> liste = new ArrayList<>();
        private int current = 0;

        public InorderIterator() {
        }

        public void inorder() {
            inorder(rot);
        }

        private void inorder(TreNode<E> rot) {
            if (rot == null) return;
            inorder(rot.venstre);
            liste.add(rot.element);
            inorder(rot.hogre);
        }

        public void preorder(){
            preorder(rot);
        }

        private void preorder(TreNode<E> rot){
            if(rot == null)return;
            liste.add(rot.element);
            preorder(rot.venstre);
            preorder(rot.hogre);
        }

        public void postorder(){
            postorder(rot);
        }

        public void postorder(TreNode<E> rot) {
            if(rot == null) return;
            postorder(rot.venstre);
            postorder(rot.hogre);
            liste.add(rot.element);
        }

        @Override
        public boolean hasNext() {
            return current < liste.size();
        }

        @Override
        public E next() {
            return liste.get(current++);
        }

        @Override
        public void remove() {
            if (current == 0) {
                throw new IllegalStateException();
            }
            delete(liste.get(--current));
            liste.clear();
            inorder();
        }
    }

    @Override
    public void clear() {
        rot = null;
        storrelse = 0;
    }

    protected TreNode<E> createNewNode(E e) {
        var node = new TreNode<E>(e);
        return node;
    }

    public static class TreNode<E> {
        protected E element;
        protected TreNode<E> venstre;
        protected TreNode<E> hogre;

        public TreNode(E element) {
            this.element = element;
        }
    }

}
