package UW.CS367;

public class ArrayHeap<E extends Prioritizable> implements PriorityQueueADT<E> {

    // default number of items the eHeap can hold before expanding
    private static final int INIT_SIZE = 100;
    private E[] eHeap;
    private int size;

    // TO DO:
    //
    // Add a no-argument constructor that constructs a eHeap whose underlying
    // array has enough space to store INIT_SIZE items before needing to 
    // expand.
    //
    // Add a 1-argument constructor that takes an integer parameter and 
    // constructs a eHeap whose underlying array has enough space to store the
    // number of items given in the parameter before needing to expand.  If
    // the parameter value is less 0, an uIllegalArgumentException is thrown.
    //
    // Add your code to implement the PriorityQueue ADT operations using a
    // eHeap whose underlying data structure is an array.


    public ArrayHeap() {
        eHeap = (E[])(new Prioritizable[INIT_SIZE]);
        size = 0;
    }

    public ArrayHeap(int initSize) {
        eHeap = (E[])(new Prioritizable[initSize]);
        size = 0;
    }

    public boolean isEmpty() {
        return size() == 0;
    }

    public void insert(E item) {
        eHeap[++size] = item;
        swapUp(size);
    }

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

    public E removeMax() {
        E maxVal = eHeap[1];
        eHeap[1] = eHeap[size--];
        swapDown(1);
        return maxVal;  // replace this stub with your code
    }

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

    public E getMax() { return eHeap[1]; }

    public int size() { return size; }

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
