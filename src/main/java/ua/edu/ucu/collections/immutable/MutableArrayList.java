package ua.edu.ucu.collections.immutable;

public class ImmutableArrayList implements ImmutableList{
    Object[] elems;
    public ImmutableArrayList(){
        this.elems = new Object[0];
    }
    public ImmutableArrayList(Object e){
        this.elems = new Object[] {e};
    }
    public ImmutableArrayList(int from, int to, ImmutableArrayList origin){
        this();
        this.resize(to-from);
        if(from>to){
            throw new IndexOutOfBoundsException();
        }
        for (int i = 0; i < elems.length; i++) {
            this.setMutable(i, origin.get(i));
        }
    }
    
    private void resize(int new_size){
        this.elems = new Object[new_size];
    }

    private void setMutable(int index, Object e){
        this.elems[index] = e;
    }

    public Object get(int index){
        return this.elems[index];
    }
    
}