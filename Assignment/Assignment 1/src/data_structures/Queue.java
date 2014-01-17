// Name: An Le
// Account: masc0369
// Class: CS 310
// Assignment 1

package data_structures;

import java.util.Iterator;

public class Queue<E> implements Iterable<E> {
	private ListADT<E> list;
	
	//Constructor
	public Queue() {
		list = new LinkedListDS<E>();
	}
	
	//inserts the object obj into the queue
	public void enqueue(E obj){
		list.addLast(obj);
	}
	
	//removes and returns the object at the front of the queue
	public E dequeue(){
		return list.removeFirst();
	}
	
	//returns the number of objects currently in the queue
	public int size(){
		return list.size();
	}
	
	//returns true if the queue is empty, otherwise false
	public boolean isEmpty(){
		return list.isEmpty();
	}
	
	//returns but does not remove the object at the front of the queue
	public E peek(){
		return list.peekFirst();
	}
	
	//returns true if the Object obj is in the queue
	public boolean contains(E obj){
		return list.contains(obj);
	}
	
	//returns the queue to an empty state
	public void makeEmpty(){
		list.makeEmpty();
	}
	
	//removes the Object obj if it is in the queue and 
	//returns true, otherwise returns false.
	public boolean remove(E obj){
		return list.remove(obj);
	}
	
	//returns an iterator of the elements in the queue. The elements 
	//must be in the same sequence as dequeue would return them.
	public Iterator<E> iterator(){
		return list.iterator();
	}
}
