/*  LinkedListTimer.java
    CS310 Fall 2013
    Alan Riggins
    This is a sample timer program.  You may need to adjust the value
    in this code for your machine.
*/
    
import java.util.Iterator;
import data_structures.*;

public class LinkedListTimer {
    private ListADT<Integer> list;
    private long start, stop;
    private int [] array;
    private int [] scrambled_array;
    private static final int STRUCTURE_SIZE = 500000;
    
    public LinkedListTimer() {
        list = new LinkedListDS<Integer>();
        array = new int[STRUCTURE_SIZE];
        scrambled_array = new int[STRUCTURE_SIZE];                 
        for(int i=0; i < STRUCTURE_SIZE; i++) 
            array[i] = scrambled_array[i] = (i+1);
            
        // randomly rearrange array elements for better testing
        for(int i=0; i < STRUCTURE_SIZE; i++) {
            int tmp = scrambled_array[i];
            int new_index = (int) (STRUCTURE_SIZE * Math.random());
            scrambled_array[i] = scrambled_array[new_index];
            scrambled_array[new_index] = tmp;
            }
        }
    
    // Since n is the structure size, we need to have a structure of
    // the same size during testing.  But if we add or remove elements
    // we change n.  So we do two operations inside the loop to keep
    // n the same size throughout.
    public void timeAddFirst() {
        System.out.println("Timing AddFirst/removeFirst:");
        int size = 1000;
        int iterations = 1000000;                
            
        for(int j=0; j < 10; j++) {
            for(int i=0; i < size; i++)  // build structure of size n
                list.addFirst(i);
            start = System.currentTimeMillis();
            for(int i=0; i < iterations; i++) {
                list.addFirst(i);
                list.removeFirst();
                }
            stop = System.currentTimeMillis();
            System.out.println(
                "Time in milliseconds for " + size + " elements: " + (stop-start));            
            size <<= 1;
            list.makeEmpty();                
        }
    }
    
    // Same comments as the method above, keep n constant.  However,
    // note that addLast is O(1), but removeLast is O(n).  So we are 
    // *really* timing the removeLast here.
    public void timeAddLast() {
        System.out.println("\nTiming AddLast/removeLast:");    
        int size = 10;
        int iterations = 100000;                
            
        for(int j=0; j < 10; j++) {
            for(int i=0; i < size; i++)
                list.addLast(i);
            start = System.currentTimeMillis();
            for(int i=0; i < iterations; i++) {
                list.addLast(i);
                list.removeLast();
                }
            stop = System.currentTimeMillis();
            System.out.println(
                "Time in milliseconds for " + size + " elements: " + (stop-start));            
            size <<= 1;
            list.makeEmpty();                
        }
    }
    
    public void timeRemove() {
        System.out.println("\nTiming remove:");    
        int size = 1000;
        int iterations = 1000;     
        
        for(int j=0; j < 10; j++) {
            array = new int[size];
            scrambled_array = new int[size];                 
            for(int i=0; i < size; i++) 
                array[i] = scrambled_array[i] = (i+1);
                
            // randomly rearrange array elements for better testing
            for(int i=0; i < size; i++) {
                int tmp = scrambled_array[i];
                int new_index = (int) (size * Math.random());
                scrambled_array[i] = scrambled_array[new_index];
                scrambled_array[new_index] = tmp;
                }  
                
            for(int i=0; i < size; i++)
                list.addFirst(array[i]);
                
            // now time remove for size=n  
            start = System.currentTimeMillis();
            int index=0;
            for(int i=0; i < iterations; i++) {
                list.remove(scrambled_array[index]);
                list.addFirst(array[index++]);
                if(index == size)index = 0;
                }
            stop = System.currentTimeMillis();
            System.out.println(
                "Time in milliseconds for " + size + " elements: " + (stop-start));
            list.makeEmpty();
            size <<= 1;         
            }
        }
        
    public static void main(String [] args) {
        LinkedListTimer timer = new LinkedListTimer();
        timer.timeAddFirst();
        timer.timeAddLast(); 
        timer.timeRemove();       
        }
    }                