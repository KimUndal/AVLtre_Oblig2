package com.example.avltre;

import java.util.ArrayList;
import java.util.Comparator;

public class AVLTre<E> extends BST<E> {


    public AVLTre() {
    }

    public AVLTre(Comparator<E> c) {
        super(c);
    }

    public AVLTre(E[] objekter) {
        super(objekter);
    }

    @Override
    protected AVLTreNode<E> createNewNode(E e) {
        return new AVLTreNode<>(e);
    }

    @Override
    public boolean insert(E e) {
        boolean suksess = super.insert(e);
        if (!suksess) return false;
        else {
            balancePath(e);
        }
        return true;

    }

    private void updateHeight(AVLTreNode<E> node) {
        if (node.venstre == null && node.hogre == null) node.hogde = 0;
        else if (node.venstre == null)
            node.hogde = 1 + ((AVLTreNode<E>) (node.hogre)).hogde;
        else if (node.hogre == null)
            node.hogde = 1 + ((AVLTreNode<E>) (node.venstre)).hogde;
        else
            node.hogde = 1 + Math.max(((AVLTreNode<E>) (node.hogre)).hogde, ((AVLTreNode<E>) (node.venstre)).hogde);
    }

    private void balancePath(E e) {
        ArrayList<TreNode<E>> path = path(e);
        for (int i = path.size() - 1; i >= 0; i--) {
            AVLTreNode<E> A = (AVLTreNode<E>) (path.get(i));
            updateHeight(A);
            AVLTreNode<E> foreldreAvA = (A == rot) ? null : (AVLTreNode<E>) (path.get(i - 1));

            switch (balanceFactor(A)) {
                case -2:
                    if (balanceFactor((AVLTreNode<E>) A.venstre) <= 0) {
                        balanceLL(A, foreldreAvA);
                    } else {
                        balanceLR(A, foreldreAvA);
                    }
                    break;
                case +2:
                    if (balanceFactor((AVLTreNode<E>) A.hogre) >= 0) {
                        balanceRR(A, foreldreAvA);
                    } else {
                        balanceRL(A, foreldreAvA);
                    }
            }
        }
    }

    private void balanceRL(AVLTreNode<E> A, AVLTreNode<E> foreldreAvA) {
        TreNode<E> B = A.hogre;
        TreNode<E> C = B.venstre;

        if (A == rot) {
            rot = C;
        } else {
            if (foreldreAvA.venstre == A) {
                foreldreAvA.venstre = C;
            } else {
                foreldreAvA.hogre = C;
            }
        }
        A.hogre = C.venstre;
        B.venstre = C.hogre;
        C.venstre = A;
        C.hogre = B;

        updateHeight((AVLTreNode<E>) A);
        updateHeight((AVLTreNode<E>) B);
        updateHeight((AVLTreNode<E>) C);

    }

    private void balanceRR(AVLTreNode<E> A, AVLTreNode<E> foreldreAvA) {
        TreNode<E> B = A.hogre;
        if (A == rot) {
            rot = B;
        } else {
            if (foreldreAvA.venstre == A) {
                foreldreAvA.venstre = B;
            } else {
                foreldreAvA.hogre = B;
            }
        }
        A.hogre = B.venstre;
        B.venstre = A;
        updateHeight((AVLTreNode<E>) A);
        updateHeight((AVLTreNode<E>) B);
    }

    private void balanceLR(AVLTreNode<E> A, AVLTreNode<E> foreldreAvA) {
        TreNode<E> B = A.venstre;
        TreNode<E> C = B.hogre;

        if (A == rot) {
            rot = C;
        } else {
            if (foreldreAvA.venstre == A) {
                foreldreAvA.venstre = C;
            } else {
                foreldreAvA.hogre = C;
            }
        }
        A.venstre = C.hogre;
        B.hogre = C.venstre;
        C.venstre = B;
        C.hogre = A;

        updateHeight((AVLTreNode<E>) A);
        updateHeight((AVLTreNode<E>) B);
        updateHeight((AVLTreNode<E>) C);
    }

    private void balanceLL(AVLTreNode<E> A, AVLTreNode<E> foreldreAvA) {
        TreNode<E> B = A.venstre;

        if (A == rot) {
            rot = B;
        } else {
            if (foreldreAvA.venstre == A) {
                foreldreAvA.venstre = B;
            } else {
                foreldreAvA.hogre = B;
            }
        }
        A.venstre = B.hogre;
        B.hogre = A;
        updateHeight((AVLTreNode<E>) A);
        updateHeight((AVLTreNode<E>) B);
    }

    private int balanceFactor(AVLTreNode<E> node) {
        if (node.hogre == null) {
            return -node.hogde;
        } else if (node.venstre == null) {
            return +node.hogde;
        } else
            return ((AVLTreNode<E>) node.hogre).hogde - ((AVLTreNode<E>) node.venstre).hogde;
    }

    @Override
    public boolean delete(E e) {
        if (rot == null) {
            return false;
        }
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

        if (current == null) {
            return false;
        }
        if (current.venstre == null) {
            if (foreldre == null) {
                rot = current.hogre;
            } else {
                if (c.compare(e, foreldre.element) < 0) {
                    foreldre.venstre = current.hogre;
                } else {
                    foreldre.hogre = current.hogre;
                }
                balancePath(foreldre.element);
            }
        } else {
            TreNode<E> foreldreMestHogre = current;
            TreNode<E> mestHogre = current.venstre;

            while (mestHogre.hogre != null) {
                foreldreMestHogre = mestHogre;
                mestHogre = mestHogre.hogre;
            }
            current.element = mestHogre.element;
            if (foreldreMestHogre.hogre == mestHogre)
                foreldreMestHogre.hogre = mestHogre.venstre;
            else
                foreldreMestHogre.venstre = mestHogre.venstre;

            balancePath(foreldreMestHogre.element);
        }
        storrelse--;
        return true;
    }


    protected static class AVLTreNode<E> extends TreNode<E> {
        protected int hogde = 0;

        public AVLTreNode(E element) {
            super(element);
        }
    }
}
