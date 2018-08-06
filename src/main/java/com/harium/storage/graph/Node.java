package com.harium.storage.graph;

public class Node<T> {

    private T data;

    private Node<T> parent;

    public Node(T data) {
        super();
        setData(data);
    }

    public Node<T> getParent() {
        return parent;
    }

    public void setParent(Node<T> parent) {
        this.parent = parent;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

}
