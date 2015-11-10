package UW.CS367;
///////////////////////////////////////////////////////////////////////////////
// Main Class File:  WordCloudGenerator.java
// File:             BSTDictionary.java
// Semester:         CS367 Fall 2015
//
// Author:           Jeremy West
// CS Login:         west8
// Lecturer's Name:  Charles Fischer
// Lab Section:      3
//////////////////////////// 80 columns wide //////////////////////////////////

import java.util.Iterator;

/**
 * Binary search tree to hold KeyWords
 *
 * @author Jeremy West
 */
public class BSTDictionary<K extends Comparable<K>> implements DictionaryADT<K> {
    private BSTnode<K> root;  // the root node
    private int numItems;     // the number of items in the dictionary

    public BSTDictionary() {
        this.root = null;
        numItems = 0;
    }

    /**
     *
     * Inserts the given key to the dictionary
     *
     * @param key the key to insert into the Dictionary
     * @throws DuplicateException
     */
    public void insert(K key) throws DuplicateException {
        if (lookup(key) != null) {
            throw new DuplicateException();
        }
        root = insert(root, key);
        numItems++;
    }

    /**
     *
     * Deletes the given key from the dictionary
     *
     * @param key the key  to delete from the Dictionary
     * @return True if the item was found and deleted
     */
    public boolean delete(K key) {
        numItems--;
        return delete(root, key) != null;
    }

    /**
     *
     * Looks to see if the key exists in the dictionary.
     *
     * @param key the key to search for
     * @return The reference to the found key
     */
    public K lookup(K key) {
        return lookup(root,key);
    }

    /**
     * Checks if the dictionary is empty
     * @return True if the dictionary is empty
     */
    public boolean isEmpty() { return numItems==0; }

    /**
     * The size of the dictionary
     * @return the size of the dictionary
     */
    public int size() { return numItems; }

    /**
     * Calculates the total path length of the tree. This is the sum of the
     * lengths of the paths to each key in the dictionary.
     * @return the total path length
     */
    public int totalPathLength() {
        return totalPathLength(root, 1);
    }

    /**
     * Creates and returns an in-order iterator for the dictionary
     * @return an in-order iterator for the dictionary
     */
    public Iterator<K> iterator() { return new BSTDictionaryIterator<>(root); }

    /**
     * Recursive helper function to insert a key into the tree
     * @param n current node
     * @param key Given key to insert
     * @return the node and it's descendants
     * @throws DuplicateException
     */
    private BSTnode<K> insert(BSTnode<K> n, K key) throws DuplicateException {
       if (n == null) {
           return new BSTnode<>(key, null, null);
       }

        if (n.getKey().equals(key)) {
            throw new DuplicateException();
        }

        if (key.compareTo(n.getKey()) < 0) {
            n.setLeft(insert(n.getLeft(), key));
            return n;
        } else {
            n.setRight(insert(n.getRight(), key));
            return n;
        }
    }

    /**
     * Recursive helper function to delete a given key from the dictionary
     * @param n the current node
     * @param key the key to delete
     * @return the tree and it's descendents without the given key
     */
    private BSTnode<K> delete(BSTnode<K> n, K key) {
        if (n == null) {
            return null;
        }

        if (key.equals(n.getKey())) {
            if (n.getLeft() == null && n.getRight() == null) { // leaf
                return null;
            }
            if (n.getLeft() == null) { // only one child
                return n.getRight();
            }
            if (n.getRight() == null) { // only one child
                return n.getLeft();
            }

            K smallVal = smallest(n.getRight());
            n.setKey(smallVal);
            n.setRight(delete(n.getRight(), smallVal));
            return n;
        } else if (key.compareTo(n.getKey()) < 0) {
            n.setLeft(delete(n.getLeft(), key));
            return n;
        } else {
            n.setRight(delete(n.getRight(), key));
            return n;
        }

    }

    /**
     * Recursive helper function to find the smallest node of a tree
     * @param n The current node
     * @return The left child until there is no left child
     */
    private K smallest(BSTnode<K> n) {
        // precondition: n is not null
        // postcondition: return the smallest value in the subtree rooted at n
        if (n.getLeft() == null) { // found the smallest
            return n.getKey();
        } else {
           return smallest(n.getLeft());
        }
    }

    /**
     * Recursive helper function to lookup a given key
     * @param n the current node
     * @param key the key to lookup
     * @return a reference to the found key
     */
    private K lookup(BSTnode<K> n, K key) {
        if (n == null) {
            return null;
        }

        if (n.getKey().equals(key)) {
            return n.getKey();
        }

        if (key.compareTo(n.getKey()) < 0) {
            return lookup(n.getLeft(), key);
        } else {
            return lookup(n.getRight(), key);
        }
    }

    /**
     * Recursive helper function to calculate the total path length
     * @param N the current node
     * @param D the depth of the current node
     * @return the total path length for N
     */
    private int totalPathLength(BSTnode<K> N, int D) {
        if (N == null) {
            return 0;
        } else if (N.getLeft() == null && N.getRight() == null) {
            return D;
        } else {
            return D
                    + totalPathLength(N.getLeft(), D+1)
                    + totalPathLength(N.getRight(), D+1);
        }
    }

    /**
     * Custo toString function
     * @return in-order string of KeyWords
     */
    @Override
    public String toString() {
        Iterator<K> itr = iterator();
        String returnString = "";
        while (itr.hasNext()) {
            returnString += itr.next().toString() + ",";
        }
        return returnString;
    }
}
