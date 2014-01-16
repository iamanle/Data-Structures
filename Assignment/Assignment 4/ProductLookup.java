// Name: An Le
// Account: masc0369
// Class: CS 310
// Assignment 4
// ProductLookup.java

import data_structures.*;

import java.util.Iterator;

public class ProductLookup {
	private DictionaryADT<String,StockItem> lookup;
   
//------------------------------Constructor------------------------------
    // Constructor.  There is no argument-less constructor, or default size
    public ProductLookup(int maxSize){
    	lookup = 
    			new Hashtable<String,StockItem>(maxSize);
//    			new BalancedTree<String,StockItem>();
//    			new BinarySearchTree<String,StockItem>();
    	
    }
     
//------------------------------addItem------------------------------
    // Adds a new StockItem to the dictionary
    public void addItem(String SKU, StockItem item){
    	lookup.insert(SKU, item);
    }
       
//------------------------------getItem------------------------------
    // Returns the StockItem associated with the given SKU, if it is
    // in the ProductLookup, null if it is not.
    public StockItem getItem(String SKU){
    	return lookup.getValue(SKU);
    }
       
//------------------------------getRetail------------------------------
    // Returns the retail price associated with the given SKU value.
    // -.01 if the item is not in the dictionary
    public float getRetail(String SKU) {
    	if (lookup.contains(SKU)){
    		return lookup.getValue(SKU).getRetail();
    	}
    	return (-.01f);
    }
    
//------------------------------getCost------------------------------
    // Returns the cost price associated with the given SKU value.
    // -.01 if the item is not in the dictionary
    public float getCost(String SKU)  {
    	if (lookup.contains(SKU)){
    		return lookup.getValue(SKU).getCost();
    	}
    	return (-.01f);
    }
    
//------------------------------getDescription------------------------------
    // Returns the description of the item, null if not in the dictionary.
    public String getDescription(String SKU)   {
    	if (lookup.contains(SKU)){
    		return lookup.getValue(SKU).getDescription();
    	}
    	return null;
    }
       
//------------------------------deleteItem------------------------------
    // Deletes the StockItem associated with the SKU if it is
    // in the ProductLookup.  Returns true if it was found and
    // deleted, otherwise false.  
    public boolean deleteItem(String SKU){
    	if (lookup.contains(SKU)){
    		
    		
    		
    		
    		// Delete StockItem only or delete key/value pair?
    		
    		
    		
    		lookup.remove(SKU);
    		return true;
    	}
    	return false;
    }
       
//------------------------------printAll------------------------------
    // Prints a directory of all StockItems with their associated
    // price, in sorted order (ordered by SKU).
    public void printAll(){
    	Iterator<StockItem> itemIter = values();
    	while(itemIter.hasNext()){
    		System.out.println(itemIter.next());
    	}
    }
    
//------------------------------print------------------------------
    // Prints a directory of all StockItems from the given vendor, 
    // in sorted order (ordered by SKU).
    public void print(String vendor){
    	Iterator<StockItem> itemIter = values();
    	while(itemIter.hasNext()){
    		StockItem currentItem = itemIter.next();
    		if(currentItem.getVendor().equalsIgnoreCase(vendor))
    			System.out.println(currentItem);
    	}
    }
    
//------------------------------Iterators------------------------------
    // An iterator of the SKU keys.
    public Iterator<String> keys(){
    	return lookup.keys();
    }
    
    // An iterator of the StockItem values.    
    public Iterator<StockItem> values(){
    	return lookup.values();
    }
    
//------------------------------test------------------------------
    public static void main(String arg[]){
    	ProductLookup test = new ProductLookup(10);
    	
    	System.out.println("----------Test Add and Get methods-----------");

    	StockItem item2 = new StockItem("002", "Xbox One", "Microsoft", 199.99f, 499.99f);
    	test.addItem("002", item2);
    	
    	StockItem item4 = new StockItem("004", "Playstation 4", "Sony", 199.99f, 399.99f);
    	test.addItem("004", item4);
    	
    	StockItem item8 = new StockItem("008", "Core i7-4770K", "Intel", 99.99f, 319.99f);
    	test.addItem("008", item8);
    	
    	StockItem item7 = new StockItem("007", "iPad", "Apple", 199.99f, 499.99f);
    	test.addItem("007", item7);
    	
    	StockItem item1 = new StockItem("001", "iPhone", "Apple", 99.99f, 699.99f);
    	test.addItem("001", item1);
    	
    	System.out.println("getItem 002: " + test.getItem("002") + "\n");
    	System.out.println("getRetail 002: " + test.getRetail("002"));
    	System.out.println("getRetail 003 not in dict: " + test.getRetail("003") + "\n");
    	
    	System.out.println("getCost 002: " + test.getCost("002"));
    	System.out.println("getCost 003 not in dict: " + test.getCost("003")+ "\n");
    	
    	System.out.println("getDescription 002: " + test.getDescription("002"));
    	System.out.println("getDescription 003 not in dict: " + test.getDescription("003")+ "\n");
    	
    	System.out.println("----------Iterators-----------");
    	Iterator<String> keyIterator = test.keys();
    	Iterator<StockItem> valueIterator = test.values();
    	while(keyIterator.hasNext()){
    		System.out.print("Keys: " + keyIterator.next());
    		System.out.println("   Values: " + valueIterator.next());
    	}

    	System.out.println("----------Before Delete printAll-----------");
    	test.printAll();
    	
    	System.out.println("----------After Delete 004-----------");
    	test.deleteItem("004");
    	test.printAll();
    	
    	System.out.println("---------Print with vendor------------");
    	test.print("apple");
    }
}