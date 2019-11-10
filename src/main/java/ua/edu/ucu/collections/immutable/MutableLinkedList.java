package ua.edu.ucu.collections.immutable;

public class MutableLinkedList extends LinkedList{
    private int size;
    private Node first;
    private Node last;

    public MutableLinkedList(){
        super();
    }
    
    public MutableLinkedList(int from, int to, ImmutableLinkedList origin){
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
    
    public MutableLinkedList(MutableLinkedList first, MutableLinkedList second){
        this();
        this.first = first.getFirstNode();
        this.last = second.getLastNode();
        this.size = first.size()+second.size();
        first.clear();
        second.clear();
    }
    
    public MutableLinkedList(int from, int to, MutableLinkedList origin){
        //Copy all nodes, [from, to) from origin to this, clear orign
        this();
        if(from > to){
            throw new IndexOutOfBoundsException();
        }
        Node new_first = origin.getNode(from);
        Node new_last = origin.getNode(to-1);
        int new_size = to-from;
        this.first = new_first;
        this.last = new_last;
        this.size = new_size;
        this.last.unlinkFront();
        this.first.unlinkBack();
        origin.clear();
    }
    
    public MutableLinkedList(int from, int to, Object[] c){
        this();
        for (int i = from; i < to; i++) {
            this.addMutable(c[i]);
        }
    } 
    public MutableLinkedList(Object data){
        this(0, 1, new Object[]{data});
    }
    
    public MutableLinkedList addMutable(Object data){
        if(this.size == 0){
            this.first = new Node(data);
            this.last = this.first;
            this.size = 1;
        }else{
            Node temp = this.last;
            this.last = new Node(data);
            this.first.linkFront(temp);
            this.size +=1;
        }
    return this;
}
    
    public MutableLinkedList setMutable(int index, Object data){
        this.getNode(index).setData(data);
        return this;
    }
    public void clear(){
        this.size = 0;
        this.first=null;
        this.last = null;
    }

}
