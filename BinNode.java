package com.company;

public class BinNode<T> {
    private T value;
    private BinNode<T> left;
    private BinNode<T> right;

    public BinNode(T value) {
        this.value = value;
        left = null;
        right = null;
    }

    public BinNode(T value, BinNode<T> left, BinNode<T> right) {
        this.value = value;
        this.left = left;
        this.right = right;
    }

    public T getValue() {
        return value;
    }

    public void setValue(T value) {
        this.value = value;
    }

    public BinNode<T> getLeft() {
        return left;
    }

    public void setLeft(BinNode<T> left) {
        this.left = left;
    }

    public BinNode<T> getRight() {
        return right;
    }

    public void setRight(BinNode<T> right) {
        this.right = right;
    }
    /**
     * the method connects the given node this node if possible (it's not already connected to 2 nodes)
     * @param other resistor to connect
     */
    public void connect(BinNode<T> other)
    {
        if(left == null)
            left = other;
        else if(right == null)
            right = other;
    }

    /**
     *
     * @return whether this node is connected to 2 nodes (after it)
     */
    public boolean split()
    {
        return !(left==null || right == null);
    }

    /**
     *
     * @return whether this resistor is not connected (after it) to any resistor (it means that it's connected to the voltage source)
     */
    public boolean isFinal()
    {
        return (left == null && right == null);
    }

    @Override
    public String toString() {
        return "BinNode{" +
                "value=" + value +
                ", left=" + left +
                ", right=" + right +
                '}';
    }
}
