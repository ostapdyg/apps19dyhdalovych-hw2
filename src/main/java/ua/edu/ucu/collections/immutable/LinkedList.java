package ua.edu.ucu.collections.immutable;

public class LinkedList{
    private int size;
    private Node first;
    private Node last;

    public LinkedList(){
        this.size = 0;
        this.first = null;
        this.last = null;
    }
    ///Helper constructors; It would be better to use LinkedList for intermidiate changes, but it requires a lot of code to be rewritten
    public LinkedList(int from, int to, LinkedList origin){
        //Copy all nodes, [from, to) from origin to this
        this();
        if(to > origin.getSize()){
            throw new IndexOutOfBoundsException();
        }
        Node cur_node = origin.getNode(from);
        for (int i = from; i < to; i++) {
            this.addMutable(cur_node.getData());
            cur_node = cur_node.getNext();
        }
    }
    public LinkedList(int to, LinkedList origin){
        this(0, to, origin);
    }
    public LinkedList(LinkedList first, LinkedList second){
        this(0, first.getSize(), first);
        Node cur_node = second.getFirstNode();
        while(cur_node != null){
            this.addMutable(cur_node.getData());
            cur_node = cur_node.getNext();
        }
    }
    public LinkedList(Object e){
        this();
        this.addMutable(e);
    }
    public LinkedList(int from, int to, Object[] c){
        this();
        for (int i = from; i < to; i++) {
            this.addMutable(c[i]);
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
    
    private void addMutable(Object data){
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
}