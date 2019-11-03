package ua.edu.ucu.collections;

import ua.edu.ucu.collections.immutable.ImmutableLinkedList;

public class Stack {
    ImmutableLinkedList list;
    public Stack(){
        this.list = new ImmutableLinkedList();
    }
    public Object peek(){
        return this.list.getFirst();
    }
    public void push(Object e){
        this.list = this.list.addFirst(e);
    }
    public Object pop(){
        Object res = this.peek();
        this.list = this.list.removeFirst();
        return res;
    }
    
}
