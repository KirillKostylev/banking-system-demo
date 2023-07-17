package com.intexsoft.demo.utils;

public class Pair<P1, P2> {
    private final P1 left;
    private final P2 right;

    public Pair(P1 left, P2 right) {
        this.left = left;
        this.right = right;
    }

    public P1 getLeft() {
        return left;
    }

    public P2 getRight() {
        return right;
    }
}
