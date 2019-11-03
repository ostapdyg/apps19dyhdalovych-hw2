package ua.edu.ucu.collections.immutable;

import java.util.function.Function;

public class ImmutableLinkedList implements ImmutableList{
    private int size;
    private Node first;
    private Node last;

    public ImmutableLinkedList(){
        this.size = 0;
        this.first = null;
        this.last = null;
    }

    public ImmutableLinkedList(int from, int to, ImmutableLinkedList origin){
        //Copy all nodes, [from, to) from origin to this
        this();
        if(to > origin.getSize()){
            throw new IndexOutOfBoundsException();
        }
        Node cur_node = origin.getNode(from);
        for (int i = from; i < to; i++) {
            this.addMuttable(cur_node.getData());
            cur_node = cur_node.getNext();
        }
    }
    public ImmutableLinkedList(int to, ImmutableLinkedList origin){
        this(0, to, origin);
    }
    public ImmutableLinkedList(ImmutableLinkedList first, ImmutableLinkedList second){
        this(0, first.getSize(), first);
        Node cur_node = second.getFirstNode();
        while(cur_node != null){
            this.addMuttable(cur_node.getData());
            cur_node = cur_node.getNext();
        }
    }
    public ImmutableLinkedList(Object e){
        this();
        this.addLast(e);
    }
    public ImmutableLinkedList(Object[] c){
        this();
        for (Object e : c) {
            this.addMuttable(e);
        }
    } 
   
    public Node getNode(int index){
        if(index > this.size){
            throw new IndexOutOfBoundsException();
        }
        else if(index <= this.size/2){
            Node res = this.first;
            for (int i = 0; i < index; i++) {
                res = res.getNext();
            }
            return res;
        }else{Node res = this.last;
            for (int i = 0; i < index; i++) {
                res = res.getPrev();
            }
            return res;
        }
    }
    
    public int getSize(){
        return this.size;
    }
    public Node getFirstNode(){
        return this.first;
    }
    public Node getLastNode(){
        return this.last;
    }
    public Object getFirst(){
        return this.getFirstNode().getData();
    }
    public Object getLast(){
        return this.getLastNode().getData();
    }
    
    private void addMuttable(Object data){
        if(this.size == 0){
            this.first = new Node(data);
            this.last = this.first;
            this.size = 1;
        }else{
            Node temp = this.last;
            this.last = new Node(data);
            this.first.linkFront(temp);
            this.size +=1;
    }}


    public ImmutableLinkedList addFirst(Object e){
        return new ImmutableLinkedList(new ImmutableLinkedList(e), new ImmutableLinkedList(this.getSize(), this));
    }
    public ImmutableLinkedList addLast(Object e){
        return new ImmutableLinkedList(new ImmutableLinkedList(this.getSize(), this), new ImmutableLinkedList(e));
    }
    public ImmutableLinkedList removeFirst(Object e){
        return new ImmutableLinkedList(1, this.getSize(), this);
    }
    public ImmutableLinkedList removeLast(Object e){
        return new ImmutableLinkedList(0, this.getSize()-1, this);
    }
    public ImmutableLinkedList add(Object e){
        return this.addLast(e);}

    public ImmutableLinkedList add(int index, Object e){
        return new ImmutableLinkedList((new ImmutableLinkedList(0, index, this)).add(e),
                                        new ImmutableLinkedList(index, this.getSize(), this));
    }

    public ImmutableLinkedList addAll(Object[] c){
        return new ImmutableLinkedList(new ImmutableLinkedList(this), new ImmutableLinkedList(c));
    }

    public ImmutableLinkedList addAll(int index, Object[] c){
        return new ImmutableLinkedList((new ImmutableLinkedList(0, index, this)).addAll(c),
                                        new ImmutableLinkedList(index, this.getSize(), this));
    }

    public Object get(int index){
        return this.getNode(index).getData();
    }
    public ImmutableLinkedList remove(int index){
        return new ImmutableLinkedList((new ImmutableLinkedList(0, index, this)),
                                        new ImmutableLinkedList(index+1, this.getSize(), this));
    }
    public ImmutableLinkedList set(int index, Object e){
        return new ImmutableLinkedList((new ImmutableLinkedList(0, index, this)).add(e),
                                        new ImmutableLinkedList(index+1, this.getSize(), this));
    }
    public int indexOf(Object e){
        Node cur_node = this.getFirstNode();
        for (int i = 0; i < this.size; i++) {
            if(cur_node.getData() == e){
                return i;
            }
            cur_node = cur_node.getNext();
        }
        return -1;
    }
    public int size(){
        return this.size();
    }
    public ImmutableLinkedList clear(){
        return new ImmutableLinkedList();
    }
    public boolean isEmpty(){
        return (this.size==0);
    }
    public Object[] toArray(){
        Object[] res = new Object[this.size];
        Node cur_node = this.getFirstNode();
        for (int i = 0; i < this.size; i++) {
            res[i] = cur_node.getData();
            cur_node = cur_node.getNext();
        }
        return res;
    }
    @Override
    public String toString(){
        StringBuilder sb = new StringBuilder()
        Node cur_node = this.getFirstNode();
        for (int i = 0; i < this.size-1; i++) {
            sb.append(cur_node.getData());
            sb.append(", ");
            cur_node = cur_node.getNext();
        }
        sb.append(cur_node.getData());
        return sb.toString();
    }

}
