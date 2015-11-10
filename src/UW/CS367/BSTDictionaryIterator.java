package UW.CS367;
///////////////////////////////////////////////////////////////////////////////
// Main Class File:  WordCloudGenerator.java
// File:             BSTDictionaryIterator.java
// Semester:         CS367 Fall 2015
//
// Author:           Jeremy West
// CS Login:         west8
// Lecturer's Name:  Charles Fischer
// Lab Section:      3
//////////////////////////// 80 columns wide //////////////////////////////////

import java.util.*;

/**
 * BSTDictionaryIterator implements an iterator for a binary search tree (BST)
 * implementation of a Dictionary.  The iterator iterates over the tree in 
 * order of the key values (from smallest to largest).
 *
 * @author Jeremy West
 */
public class BSTDictionaryIterator<K> implements Iterator<K> {
    private Stack<BSTnode<K>> bstNodes; // The stack of nodes still to visit

    /**
     * Recursively creates a stack of the smallest values
     * @param root the node to start with
     */
    public BSTDictionaryIterator(BSTnode<K> root) {
        bstNodes = new Stack<>();
        bstNodes.push(root);
        pushSmallValues(root);
    }

    /**
     * If there is another node to visit, which is if the stack is not empty
     * @return true if there is another node to visit
     */
    public boolean hasNext() { return !bstNodes.isEmpty(); }

    /**
     * Returns the next node in-order
     * @return the next in-order node
     */
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

    /**
     * Pushes all of the left children onto the stack
     * @param N node to start with
     */
    private void pushSmallValues(BSTnode<K> N) {
        while (N.getLeft() != null) {
            N = N.getLeft();
            bstNodes.push(N);
        }
    }

    public void remove() {
        // DO NOT CHANGE: you do not need to implement this method
        throw new UnsupportedOperationException();
    }    
}
