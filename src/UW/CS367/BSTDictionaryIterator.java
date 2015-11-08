package UW.CS367;

import java.util.*;

/**
 * BSTDictionaryIterator implements an iterator for a binary search tree (BST)
 * implementation of a Dictionary.  The iterator iterates over the tree in 
 * order of the key values (from smallest to largest).
 */
public class BSTDictionaryIterator<K> implements Iterator<K> {
    private Stack<BSTnode<K>> bstNodes;

    // TO DO:
    //
    // Add your code to implement the BSTDictionaryIterator.  To receive full
    // credit:
    // - You must not use recursion in any of methods or constructor.
    // - The constructor must have a worst-case complexity of O(height of BST).
    // 
    // Hint: use a Stack and push/pop nodes as you iterate through the BST.
    // The constructor should push all the nodes needed so the *first* call 
    // to next() returns the value in the node with the smallest key.
    // (You can use the Java API Stack or implement your own Stack - if you
    // implement your own, make sure to hand it in.)


    public BSTDictionaryIterator(BSTnode<K> root) {
        bstNodes.push(root);
        BSTnode<K> tmpNode = root;
        pushSmallValues(tmpNode);
    }

    public boolean hasNext() { return !bstNodes.isEmpty(); }

    public K next() {
        BSTnode<K> tmpNode = bstNodes.pop(); // pop off the smallest value
        K curData = tmpNode.getKey();
        if (tmpNode.getRight() != null) { // we've visited the "root", now go right
            tmpNode = tmpNode.getRight();
            bstNodes.push(tmpNode);
            pushSmallValues(tmpNode); // then push all the smaller nodes
        }
        return curData;  // replace this stub with your code
    }

    private void pushSmallValues(BSTnode<K> tmpNode) {
        while (tmpNode.getLeft() != null) {
            tmpNode = tmpNode.getLeft();
            bstNodes.push(tmpNode);
        }
    }

    public void remove() {
        // DO NOT CHANGE: you do not need to implement this method
        throw new UnsupportedOperationException();
    }    
}
