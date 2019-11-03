package ua.edu.ucu.collections;

import ua.edu.ucu.collections.immutable.ImmutableLinkedList;

public class Queue {
    ImmutableLinkedList list;
    public Queue(){
        this.list = new ImmutableLinkedList();
    }
    public Object peek(){
        return this.list.getFirst();
    }
    public void enqueue(Object e){
        this.list = this.list.addLast(e);
    }
    public Object dequue(){
        Object res = this.peek();
        this.list = this.list.removeFirst();
        return res;
    }
}
