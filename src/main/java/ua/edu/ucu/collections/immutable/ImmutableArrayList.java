package ua.edu.ucu.collections.immutable;

import java.util.Arrays;

public class ImmutableArrayList implements ImmutableList{
    Object[] elems;
    public ImmutableArrayList(){
        this.elems = new Object[0];
    }
    ///Helper constructors; It would be better to use MutableArrayList for intermidiate changes, but it requires a lot of code to be rewritten
    public ImmutableArrayList(Object e){
        this.elems = new Object[] {e};
    }
    public ImmutableArrayList(int from, int to, ImmutableArrayList origin){
        this();
        this.clearAndResize(to-from);
        if(from>to){
            throw new IndexOutOfBoundsException();
        }
        if(from==to||origin.isEmpty()){return;}
        for (int i = from; i < to; i++) {
            this.setMutable(i-from, origin.get(i));
        }
    }

    public ImmutableArrayList(ImmutableArrayList first, ImmutableArrayList second){
        this();
        int first_size = first.size();
        int second_size = second.size();
        this.clearAndResize(first_size+second_size);
        for (int i = 0; i < first_size; i++) {
            this.setMutable(i, first.get(i));
        }
        for (int i = 0; i < second_size; i++) {
            this.setMutable(i+first_size, second.get(i));
        }
    }
    public ImmutableArrayList(int from, int to, Object[] c){
        this();
        this.clearAndResize(to-from);
        if(from>to){
            throw new IndexOutOfBoundsException();
        }
        for (int i = from; i < to; i++) {
            this.setMutable(i, c[i]);
        }

    }

    
    private void clearAndResize(int new_size){
        this.elems = new Object[new_size];
    }

    private void setMutable(int index, Object e){
        this.elems[index] = e;
    }

    public int size(){
        return this.elems.length;
    }
    public ImmutableArrayList addFirst(Object e){
        return new ImmutableArrayList(
            new ImmutableArrayList(e), 
            this);
    }
    public ImmutableArrayList addLast(Object e){
        return new ImmutableArrayList(
            this, 
            new ImmutableArrayList(e));
    }
    public ImmutableArrayList removeFirst(Object e){
        return new ImmutableArrayList(1, this.size(), this);
    }
    public ImmutableArrayList removeLast(Object e){
        return new ImmutableArrayList(0, this.size()-1, this);
    }
    public ImmutableArrayList add(Object e){
        return this.addLast(e);}

    public ImmutableArrayList add(int index, Object e){
        return new ImmutableArrayList(
            (new ImmutableArrayList(0, index, this)).add(e),
            new ImmutableArrayList(index, this.size(), this));
    }

    public ImmutableArrayList addAll(Object[] c){
        return new ImmutableArrayList(
            this,
            new ImmutableArrayList(0, c.length, c));
    }

    public ImmutableArrayList addAll(int index, Object[] c){
        return new ImmutableArrayList(
            (new ImmutableArrayList(0, index, this)).addAll(c),
            new ImmutableArrayList(index, this.size(), this));
    }

    public Object get(int index){
        return this.elems[index];
    }
    public ImmutableArrayList remove(int index){
        return new ImmutableArrayList((new ImmutableArrayList(0, index, this)),
                                        new ImmutableArrayList(index+1, this.size(), this));
    }
    public ImmutableArrayList set(int index, Object e){
        return new ImmutableArrayList((new ImmutableArrayList(0, index, this)).add(e),
                                        new ImmutableArrayList(index+1, this.size(), this));
    }
    public int indexOf(Object e){
        for (int i = 0; i < this.size(); i++) {
            if(this.get(i).equals(e)){
                return i;
            }
        }
        return -1;
    }

    public ImmutableArrayList clear(){
        return new ImmutableArrayList();
    }
    public boolean isEmpty(){
        return (this.size()==0);
    }
    public Object[] toArray(){
        return this.elems.clone();
    }
    @Override
    public String toString(){
        if(this.isEmpty()){
            return "";
        }
        StringBuilder sb = new StringBuilder(3*this.size()-2);
        for (int i = 0; i < this.size()-1; i++) {
            sb.append(this.get(i));
            sb.append(", ");
        }
        sb.append(this.get(this.size()));
        return sb.toString();
    }
}
