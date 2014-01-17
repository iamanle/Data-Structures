// Name: An Le
// Account: masc0369
// Class: CS 310
// Assignment 4
// BalancedTree.java

package data_structures;

import java.util.Iterator;
import java.util.TreeMap;

public class BalancedTree<K,V> implements DictionaryADT<K,V> {
	private TreeMap<K,V> treeMap;
		
	public BalancedTree(){
		treeMap = new TreeMap<K,V>();
	}
	
//------------------------------contains----------------------------
	public boolean contains(K key) {
		return treeMap.containsKey(key);
	}

//------------------------------insert------------------------------
	public boolean insert(K key, V value) {
		if (contains(key))
			return false;
		treeMap.put(key, value);
		return true;
	}

//------------------------------remove------------------------------
	public boolean remove(K key) {
		if (contains(key)){
			treeMap.remove(key);
			return true;
		}
		return false;
	}

//------------------------------getValue------------------------------
	public V getValue(K key) {
		return treeMap.get(key);
	}

//------------------------------getKey------------------------------
	@SuppressWarnings("unchecked")
	public K getKey(V value) {
		Iterator<K> keyIterator = treeMap.keySet().iterator();
		Iterator<V> valueIterator = treeMap.values().iterator();
		while(keyIterator.hasNext() && valueIterator.hasNext()){
			K nextKey = keyIterator.next();
			V nextValue = valueIterator.next();
			if (((Comparable<V>)value).compareTo(nextValue) == 0){
				return nextKey;
			}
		}
		return null;
	}

//------------------------------size------------------------------
	public int size() {
		return treeMap.size();
	}

//------------------------------isFull------------------------------
	public boolean isFull() {
		return false;
	}

//------------------------------isEmpty------------------------------
	public boolean isEmpty() {
		// inherited from Map interface
		return treeMap.isEmpty();
	}

//------------------------------clear------------------------------
	public void clear() {
		treeMap.clear();
	}

//------------------------------keys------------------------------
	public Iterator<K> keys() {
		return treeMap.keySet().iterator();
	}

//------------------------------values------------------------------
	public Iterator<V> values() {
		return treeMap.values().iterator();
	}
}
