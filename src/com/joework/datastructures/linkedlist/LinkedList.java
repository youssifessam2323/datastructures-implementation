package com.joework.datastructures.linkedlist;


import java.util.Iterator;
import java.util.Spliterator;
import java.util.function.Consumer;

public class  LinkedList<T> implements Iterable<T> {

    private int size = 0;
    private Node<T> head = null;
    private Node<T> tail = null;

    @Override
    public Iterator<T> iterator() {

        return new Iterator<T>() {
            private Node<T> curr = head;
            @Override
            public boolean hasNext() {
                return curr != null;
            }

            @Override
            public T next() {
                var data = curr.data;
                curr = curr.next;
                return data;
            }

            @Override
            public void remove() {
                throw new UnsupportedOperationException();
            }
        };
    }


    static class Node<T>{
        T data ;
        Node<T> next;

        public Node(T data, Node<T> next){
            this.data = data;
            this.next = next;
        }

        @Override
        public String toString() {
            return "Node{" +
                    "data=" + data +
                    '}';
        }
    }


    // O(1)
    public void add(T ele){
        if(head == null)
            head = tail = new Node<T>(ele,null);
        else {
            tail.next = new Node<T>(ele, null);
            tail = tail.next;
        }
        size++;
    }

    public int getSize() {
        return size;
    }

    public boolean isEmpty(){
        return getSize() == 0;
    }

    // O(1)
    public void addFirst(T data){ // s =>h => n1 => n2  ==> ->s
        Node<T> first = new Node<T>(data,null);
        if(head == null){
            head = first;
        }else {
            first.next = head;
            head = first;
        }
        size++;
    }

    // return the first value in the list without modify remove it
    public T peekFirst(){
        return head.data;
    }

    public T peekLast(){
        return tail.data;
    }


    public T remove(T ele){
        if(head == null){
            throw new RuntimeException("list is empty..");
        }
        var curr = head; // 1 2 3 4 5 6 7

        while(curr != null){

            if(head == tail && !isEmpty()){
                var data = head.data;
                head = tail = null;
                return data;
            }

            else if(ele == head.data){
                return removeFirst();
            }

            else if(curr != tail && curr.next.data == ele){
                var data = curr.next.data;
                curr.next = curr.next.next;
                return data;
            }

            curr = curr.next;
        }
        throw new RuntimeException("Element not found");
    }

    public T removeFirst(){

        if(head == null)
            throw new RuntimeException("list is empty");

        var data = head.data;
        head = head.next;
        size--;

        if(isEmpty())
            tail = null;

        return data;
    }

    public void display(){
        var curr = head;
        while(curr != null) {
            System.out.println("curr is " + curr);
            curr = curr.next;
        }
    }

    public static void main(String[] args) {
        LinkedList<Integer> ints = new LinkedList<>();
        ints.add(1);
        ints.add(2);
        ints.add(3);
        ints.add(4);
        ints.add(5);
        ints.add(6);
        ints.add(7);


        for(Integer n : ints) {
            System.out.println("data : " + n);
        }
    }
}
