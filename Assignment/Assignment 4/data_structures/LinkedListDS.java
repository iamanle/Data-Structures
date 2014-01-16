// Name: An Le
// Account: masc0369
// Class: CS 310
// Assignment 1

package data_structures;

import java.util.Iterator;
import java.util.NoSuchElementException;

public class LinkedListDS<E> implements ListADT<E> {
	@SuppressWarnings("hiding")
	protected class Node<E>{
		E data;
		Node<E> next;

		public Node(E obj){
			data = obj;
			next = null;
		}
	}
	
	//Class variables:
	private Node<E> head, tail;
	private int currentSize;

	//Constructor
	public LinkedListDS() {
		head = tail = null;
		currentSize = 0;
	}

//------------------------------addFirst------------------------------
	//Adds the Object obj to the beginning of the list.
	public void addFirst(E obj){
		Node<E> newNode = new Node<E>(obj);
		if (head == null)
			head = tail = newNode;
		else {
			newNode.next = head;
			head = newNode;
		}
		currentSize++;
	}

//------------------------------addLast------------------------------
	//Adds the Object obj to the end of the list.
	public void addLast(E obj){
		Node<E> newNode = new Node<E>(obj);
		if (head == null)
			head = tail = newNode;
		else {
			tail.next = newNode;
			tail = newNode;
		}
		currentSize++;
	}

//------------------------------removeFirst------------------------------
	//Removes the first Object in the list and returns it.
	//Returns null if the list is empty.
	public E removeFirst(){
		if (head == null)
			return null;
		E tmp = head.data;
		if (head == tail)
			head = tail = null;
		else
			head = head.next;
		currentSize--;
		return tmp;
	}

//------------------------------removeLast------------------------------
	//Removes the last Object in the list and returns it.
	//Returns null if the list is empty.
	public E removeLast(){
		//If there is no element in list. Return null.
		if (head == null)
			return null;
		
		E tmp = tail.data;
		Node<E> previous = null, current = head;
		
//		if (current == tail){
//			head = tail = null;
//			currentSize--;
//			return tmp;
//		}
		
		//If there are more than 1 element:
		//Find previous and set tail to current.
		while (current != tail){
			previous = current;
			current = current.next;
		}
		//If there is one element.
		if (previous == null)
			head = tail = null;
		else{
			previous.next = null;
			tail = previous;
		}
		currentSize--;
		return tmp;
	}

//------------------------------peekFirst------------------------------
	//Returns the first Object in the list, but does not remove it
	//Return null if the list is empty
	public E peekFirst(){
		if (head == null)
			return null;
		return head.data;
	}

//------------------------------peekLast------------------------------
	//Returns the last Object in the list, but does not remove it
	//Return null if the list is empty
	public E peekLast(){
		if (tail == null)
			return null;
		return tail.data;
	}

//------------------------------find------------------------------
	//Finds and returns the Object obj if it is in the list, otherwise
	//returns null. Does not modify the list in anyway
	@SuppressWarnings("unchecked")
	public E find(E obj){
		Node<E> tmp = head;
		while (tmp != null){
			if (((Comparable<E>)obj).compareTo(tmp.data) == 0) return tmp.data;
			tmp = tmp.next;
		}
		return null;
	}

//------------------------------remove------------------------------
	//Removes the first instance of the specific Object obj from the list, if it exists.
	//Returns true if the Object obj was found and removed, otherwise false.
	@SuppressWarnings("unchecked")
	public boolean remove(E obj){
		Node<E> previous = null, current = head;
		
		//find the note to delete first
		while (current != null && ((Comparable<E>)obj).compareTo(current.data) != 0){
			previous = current;
			current = current.next;
		}
		
		//Can't find obj in the list.
		if(current == null)
			return false;
		
		//if current != null, then node was found. 
		//3 conditions: it's 1)head 2)tail 3)in the middle.
		if (current == head) {
			head = head.next;
			currentSize--;
		}	
		else if(current == tail){
			previous.next = null;
			tail = previous;
			currentSize--;
		}
		else {
			previous.next = current.next;
			currentSize--;
		}
		return true;
		
	}

//------------------------------makeEmpty------------------------------
	//The list is returned to an empty state.
	public void makeEmpty(){
		head = tail = null;
		currentSize = 0;
	}

//------------------------------contains------------------------------
	//Returns true if the list contains the Object obj, otherwise false.
	@SuppressWarnings("unchecked")
	public boolean contains(E obj){
		Node<E> tmp = head;
		while (tmp != null){
			if (((Comparable<E>)obj).compareTo(tmp.data) == 0) return true;
			tmp = tmp.next;
		}
		return false;
	}

//------------------------------isEmpty------------------------------
	//Returns true if the list is empty, otherwise false
	public boolean isEmpty(){
		return (size() ==  0);
	}

//------------------------------isFull------------------------------
	//Returns true if the list is full, otherwise false.
	public boolean isFull(){
		return false;
	}

//------------------------------size------------------------------
	//Returns the number of Objects currently in the list.
	public int size(){
		return currentSize;
	}

//------------------------------iterator------------------------------
	//Returns an Iterator of the values in the list, presented in
	//the same order as the list.
	public Iterator<E> iterator(){
		return new IteratorHelper();
	}
	
	//Iterator is an interface.
	class IteratorHelper implements Iterator<E>{
		Node<E> iterPtr;
		
		public IteratorHelper(){
			iterPtr = (Node<E>)head;
		}

		public boolean hasNext(){
			return iterPtr != null;
		}

		public E next(){
			if (!hasNext()) throw new NoSuchElementException();
			E tmp = iterPtr.data;
			iterPtr = iterPtr.next;
			return tmp;
		}

		public void remove(){
			throw new UnsupportedOperationException();
		}	
	}
}

