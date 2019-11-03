package ua.edu.ucu.collections.immutable;

public class Node{
    private Node prev;
    private Node next;
    private Object data;
    
    public Node(Object data){
        this.data = data;
        this.next = null;
        this.prev = null;
    }

    public void linkBack(Node other){
        this.setNext(other);
        other.setPrev(this);
    }
    public void linkFront(Node other){
        other.linkBack(this);
    }

    public Node getPrev() {
        return prev;
    }

    public void setPrev(Node prev) {
        this.prev = prev;
    }

    public Node getNext() {
        return next;
    }

    public void setNext(Node next) {
        this.next = next;
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }

    


}