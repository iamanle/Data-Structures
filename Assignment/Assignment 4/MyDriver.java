// Name: An Le
// Account: masc0369
// Class: CS 310
// Assignment 4
// MyDriver.java

import data_structures.*;
import java.util.Iterator;
import java.util.ConcurrentModificationException;

public class MyDriver {
	public static void main(String arg[]){
//		DictionaryADT<String,Integer> test = new Hashtable<String,Integer>(20);
//		
//		test.insert("Au", 24);
//		test.insert("Lien", 47);
//		test.insert("Trung", 58);
//		boolean checkInsert = test.insert("An", 22);
//		System.out.println("---------------Check Insert An:----------------\n" + checkInsert);
//		
//		Iterator<String> keys = test.keys();
//		Iterator<Integer> values = test.values();
//		System.out.println("---------------Check Iterator :---------------");
//		while(keys.hasNext()){
//			System.out.print(keys.next());
//			System.out.print(" " + values.next() + "\n");
//		}
//		
//		
//		boolean checkContain = test.contains("An");
//		System.out.println("---------------Check Contain An:--------------- \n" + checkContain);
//		
//		boolean checkRemove = test.remove("Au");
//		System.out.println("---------------Check Remove Au:---------------\n" + checkRemove);
//		
//		int checkGetValue = test.getValue("An");
//		System.out.println("---------------Check GetValue An-22:---------------\n" + checkGetValue);
//		
//		String checkGetKey = test.getKey(22);
//		System.out.println("---------------Check GetKey An-22:---------------\n" + checkGetKey);
		
		DictionaryADT<String,Integer> test2 = new BinarySearchTree<String,Integer>();
		test2.insert("An", 22);
		test2.insert("Lien", 47);
		test2.insert("Trung", 58);
		
		Integer checkGetValue = test2.getValue("An");
		System.out.println("---------------Check GetValue An-22:---------------\n" + checkGetValue);
		
		Integer checkGetValue2 = test2.getValue("Trung");
		System.out.println("---------------Check GetValue Trung-58:---------------\n" + checkGetValue2);
		
		Iterator<String> keys = test2.keys();
		Iterator<Integer> values = test2.values();
		System.out.println("---------------Check Iterator :---------------");
		while(keys.hasNext()){
			System.out.print(keys.next());
			System.out.print(" " + values.next() + "\n");
		}
		
		String checkGetKey = test2.getKey(22);
		System.out.println("---------------Check GetKey An-22:---------------\n" + checkGetKey);
		
		boolean checkContain = test2.contains("An");
		System.out.println("---------------Check GetContain An-22:---------------\n" + checkContain);
	}
	
}
