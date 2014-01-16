// Name: An Le
// Account: masc0369
// Class: CS 310
// Assignment 4
// StockItem.java

import java.util.Iterator;
import data_structures.*;

public class StockItem implements Comparable<StockItem> {
    String SKU;
    String description;
    String vendor;
    float cost;
    float retail;
   
//------------------------------Constructor------------------------------
    // Constructor.  Creates a new StockItem instance.  
    public StockItem(String SKU, String description, String vendor,
                     float cost, float retail){
    	this.SKU = SKU;
    	this.description = description;
    	this.vendor = vendor;
    	this.cost = cost;
    	this.retail = retail;
    }
    
//------------------------------compareTo------------------------------
    // Follows the specifications of the Comparable Interface.
    // The SKU is always used for comparisons, in dictionary order.  
    public int compareTo(StockItem n){
    	return SKU.compareTo(n.SKU);
    }
       
//------------------------------hashCode------------------------------
    // Returns an int representing the hashCode of the SKU.
    public int hashCode(){
    	return SKU.hashCode();
    }
       
//------------------------------Get Methods------------------------------
    // standard get methods
    public String getDescription() {
    	return this.description;
    }
    
    public String getVendor(){
    	return this.vendor;
    }
    
    public float getCost(){
    	return this.cost;
    }
    
    public float getRetail(){
    	return this.retail;
    }
        
//------------------------------toString------------------------------
    // All fields in one line, in order   
    public String toString(){
    	return "SKU: " + this.SKU 
    			+ " Description: " + this.description 
    			+ " Vendor: " + this.vendor 
    			+ " Cost: " + this.cost 
    			+ " Retail: " + this.retail;
    }
} 