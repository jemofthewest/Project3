package UW.CS367;
///////////////////////////////////////////////////////////////////////////////
// Main Class File:  WordCloudGenerator.java
// File:             ArrayHeap.java
// Semester:         CS367 Fall 2015
//
// Author:           Jeremy West
// CS Login:         west8
// Lecturer's Name:  Charles Fischer
// Lab Section:      3
//////////////////////////// 80 columns wide //////////////////////////////////

/**
 * Priority queue implemented using an array heap
 *
 * @author Jeremy West
 */
public class ArrayHeap<E extends Prioritizable> implements PriorityQueueADT<E> {

    // default number of items the eHeap can hold before expanding
    private static final int INIT_SIZE = 100;
    private E[] eHeap; // Array to hold the heap
    private int size; // the location of the last node in the array

    public ArrayHeap() {
        eHeap = (E[])(new Prioritizable[INIT_SIZE]);
        size = 0;
    }

    public ArrayHeap(int initSize) {
        eHeap = (E[])(new Prioritizable[initSize]);
        size = 0;
    }

    /**
     * Determines if the heap is empty
     * @return true if the structure is empty
     */
    public boolean isEmpty() {
        return size() == 0;
    }

    /**
     * Inserts the given key at the end of the array and increments size.
     * @param item the item to insert into the priority queue
     */
    public void insert(E item) {
        eHeap[++size] = item;
        swapUp(size);
    }

    /**
     * Recursive helper function to swap each child with it's parent if it is
     * greater priority.
     * @param loc the array position
     */
    private void swapUp(int loc) {
        if (loc == 1) {
            return;
        }

        if (eHeap[loc].getPriority() > eHeap[loc/2].getPriority()) {
            E tmp = eHeap[loc];
            eHeap[loc] = eHeap[loc/2];
            eHeap[loc/2] = tmp;
            swapUp(loc/2);
        }
    }

    /**
     * Gets and removes the highest priority item.
     * @return a reference to the highest priority item
     */
    public E removeMax() {
        E maxVal = eHeap[1];
        eHeap[1] = eHeap[size--];
        swapDown(1);
        return maxVal;  // replace this stub with your code
    }

    /**
     * Recursive helper function to swap a parent with its higher priority child
     * if it has a lower priority.
     * @param loc the array position
     */
    private void swapDown(int loc) {
        if (loc*2+1> size) { // We're as far down as we can go
            return;
        }

        E tmp;
        int tmpLoc;
        if (eHeap[loc*2].getPriority() > eHeap[loc*2+1].getPriority()) {
            tmpLoc = loc*2;
        } else {
            tmpLoc = loc*2+1;
        }
        if (eHeap[loc].getPriority() < eHeap[tmpLoc].getPriority()) {
            tmp = eHeap[loc];
            eHeap[loc] = eHeap[tmpLoc];
            eHeap[tmpLoc] = tmp;
            swapDown(tmpLoc);
        }
    }

    /**
     * Non-destructive return of the highest priority item
     * @return the highest priority item
     */
    public E getMax() { return eHeap[1]; }

    /**
     * Returns the size of the queue
     * @return the size of the queue
     */
    public int size() { return size; }

    /**
     * Custom toString function
     * @return a string with each item in order with it's priority.
     */
    @Override
    public String toString() {
        E[] copy = (E[])(new Prioritizable[eHeap.length]);
        System.arraycopy(eHeap,0,copy,0,eHeap.length);
        int tmpSize = size;
        String retString = "";
        E tmp;
        while (size >0) {
            tmp = removeMax();
            retString+=tmp.toString() + ",";
        }
        eHeap = copy;
        size = tmpSize;
        return retString;
    }
}
