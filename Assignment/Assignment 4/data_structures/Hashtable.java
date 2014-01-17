// Name: An Le
// Account: masc0369
// Class: CS 310
// Assignment 4
// HashTable.java

package data_structures;
import java.util.Iterator;
import java.util.NoSuchElementException;
import java.util.ConcurrentModificationException;

public class Hashtable<K,V> implements DictionaryADT<K,V> {
	private int tableSize, maxSize, currentSize, modifiCounter;
	private ListADT<Wrapper<K,V>>[] list;
	
	// Wrapper to reuse LinkedList<E>
	@SuppressWarnings("hiding")
	protected class Wrapper<K,V> implements Comparable<Wrapper<K,V>>{
		K key;
		V value;
		
		public Wrapper(K key, V value){
			this.key = key;
			this.value = value;
		}
		
		@SuppressWarnings("unchecked")
		public int compareTo(Wrapper<K,V> o){
			return ((Comparable<K>)key).compareTo((K)o.key);
		}
	}
	
	// Constructor
	@SuppressWarnings("unchecked")
	public Hashtable(int n){
		maxSize = n;
		tableSize = (int)(1.3f * n);
		currentSize = 0;
		modifiCounter = 0;
		list = new LinkedListDS[tableSize];
		for (int i = 0; i < tableSize; i++)
			list[i] = new LinkedListDS<Wrapper<K,V>>();
	}
	
//------------------------------getIndex------------------------------
	private int getIndex(K key){
		return ((key.hashCode() & 0x7FFFFFFF) % tableSize);
	}
	
//------------------------------contains------------------------------
	// Returns true if the dictionary has an object identified by
	// key in it, otherwise false.
	public boolean contains(K key){
		return list[getIndex(key)].contains(new Wrapper<K,V>(key,null));
	}

//------------------------------insert------------------------------
	// Adds the given key/value pair to the dictionary.  Returns
	// false if the dictionary is full, or if the key is a duplicate.
	// Returns true if addition succeeded.
	public boolean insert(K key, V value){
		if (isFull())
			return false;
		if (contains(key))
			return false;
		list[getIndex(key)].addFirst(new Wrapper<K,V>(key,value));
		currentSize++;
		modifiCounter++;
		return true;
	}

//------------------------------Remove------------------------------
	// Deletes the key/value pair identified by the key parameter.
	// Returns true if the key/value pair was found and removed,
	// otherwise false.
	public boolean remove(K key){
		if(list[getIndex(key)].remove(new Wrapper<K,V>(key,null))){
			currentSize--;
			modifiCounter++;
			return true; 
		}
		return false;
	}

//------------------------------getValue------------------------------
	// Returns the value associated with the parameter key.  Returns
	// null if the key is not found or the dictionary is empty.
	public V getValue(K key){
		Wrapper<K,V> tmp = list[getIndex(key)].find(new Wrapper<K,V>(key,null));
		if (tmp == null){
			return null;
		}
		return tmp.value;
	}


//------------------------------getKey------------------------------
	// Returns the key associated with the parameter value.  Returns
	// null if the value is not found in the dictionary.  If more
	// than one key exists that matches the given value, returns the
	// first one found.
	@SuppressWarnings("unchecked")
	public K getKey(V value){
		for(int i = 0; i < tableSize; i++)
			for(Wrapper<K,V> n : list[i])
				if(((Comparable<V>)value).compareTo(n.value) == 0)
					return n.key;
		return null;
	}


//------------------------------size------------------------------
	// Returns the number of key/value pairs currently stored
	// in the dictionary
	public int size(){
		return currentSize;
	}

//------------------------------isFull------------------------------
	// Returns true if the dictionary is at max capacity
	public boolean isFull(){
		return currentSize == maxSize;
	}

//------------------------------isEmpty------------------------------
	// Returns true if the dictionary is empty
	public boolean isEmpty(){
		return currentSize == 0;
	}

//------------------------------clear------------------------------
	// Returns the Dictionary object to an empty state.
	public void clear(){
		for(int i = 0; i <tableSize; i++)
			list[i].makeEmpty();
		currentSize = 0;
		modifiCounter = 0;
	}

//------------------------------Iterator------------------------------
	// Returns an Iterator of the keys in the dictionary, in ascending
	// sorted order.  The iterator must be fail-fast.
	@SuppressWarnings({"rawtypes", "unchecked"})
	public Iterator<K> keys(){
		return new KeyIteratorHelper();
	}

	// Returns an Iterator of the values in the dictionary.  The
	// order of the values must match the order of the keys.
	// The iterator must be fail-fast.
	@SuppressWarnings({"rawtypes", "unchecked"})
	public Iterator<V> values(){
		return new ValueIteratorHelper();
	}
	
	//------------------------------KeyIterator------------------
	@SuppressWarnings("hiding")
	class KeyIteratorHelper<K> implements Iterator<K>{
		private Wrapper<K,V>[] nodes;
		private int idx;
		private long modCheck;
		
//		private Wrapper[] quickSort(Wrapper array[]){
//			Wrapper[] n = array;
//			quickSort(0,n.length-1);
//			return n;
//		}
//		
//		private void quickSort(int left, int right){
//			if(right-left <= 0) return;
//			Wrapper pivot = n[right];
//			
//		}
		
		// CS 310 Lecture Note Prof.Alan Riggins - Shell Sort
		@SuppressWarnings({"rawtypes", "unchecked"})
		private Wrapper[] shellSort(Wrapper array[]){
			Wrapper[] n = array;
			int in, out, h = 1;
			Wrapper temp;
			int size = n.length;
			
			while(h <= size/3)
				h = h*3+1;
			
			while(h > 0){
				for(out = h; out < size; out++){
					temp = n[out];
					in = out;
					while(in > h-1 && ((Comparable<Wrapper>)n[in-h]).compareTo(temp) >= 0){
						n[in] = n[in-h];
						in -= h;
					}
					n[in] = temp;
				}
				h = (h-1)/3;
			}
			return n;
		}
		
		@SuppressWarnings({"rawtypes", "unchecked"})
		public KeyIteratorHelper(){
			nodes = new Wrapper[currentSize];
			idx = 0;
			modCheck = modifiCounter;
			int j = 0;
			for (int i = 0; i <tableSize; i++)
				for (Wrapper n: list[i])
					nodes[j++] = n;
			nodes = shellSort(nodes);
		}
		
		public boolean hasNext(){
			if(modCheck != modifiCounter)
				throw new ConcurrentModificationException();
			return idx < currentSize;
		}
		
		public K next(){
			if(!hasNext())
				throw new NoSuchElementException();
			return nodes[idx++].key;
		}
		
		public void remove(){
			throw new UnsupportedOperationException();
		}
	}
	
	//------------------------------ValueIterator------------------
	@SuppressWarnings("hiding")
	class ValueIteratorHelper<V> implements Iterator<V>{
		Iterator<K> keyIter;
		
		public ValueIteratorHelper(){
			keyIter = keys();
		}
		
		public boolean hasNext(){
			return keyIter.hasNext();
		}
		
		@SuppressWarnings("unchecked")
		public V next(){
			return (V)getValue(keyIter.next());
		}
		
		public void remove(){
			keyIter.remove();
		}
	}
}
