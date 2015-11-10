package UW.CS367;

import java.util.Iterator;

public class BSTDictionary<K extends Comparable<K>> implements DictionaryADT<K> {
    private BSTnode<K> root;  // the root node
    private int numItems;     // the number of items in the dictionary

    public BSTDictionary() {
        this.root = null;
        numItems = 0;
    }

    public void insert(K key) throws DuplicateException {
        if (lookup(key) != null) {
            throw new DuplicateException();
        }
        root = insert(root, key);
        numItems++;
    }

    public boolean delete(K key) {
        numItems--;
        return delete(root, key) != null;
    }

    public K lookup(K key) {
        return lookup(root,key);
    }

    public boolean isEmpty() { return numItems==0; }

    public int size() { return numItems; }
    
    public int totalPathLength() {
        return totalPathLength(root, 1);
    }
    
    public Iterator<K> iterator() { return new BSTDictionaryIterator<>(root); }

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

    private K smallest(BSTnode<K> n) {
        // precondition: n is not null
        // postcondition: return the smallest value in the subtree rooted at n
        if (n.getLeft() == null) { // found the smallest
            return n.getKey();
        } else {
           return smallest(n.getLeft());
        }
    }

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
}
