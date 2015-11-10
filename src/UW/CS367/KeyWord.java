package UW.CS367;
///////////////////////////////////////////////////////////////////////////////
// Main Class File:  WordCloudGenerator.java
// File:             KeyWord.java
// Semester:         CS367 Fall 2015
//
// Author:           Jeremy West
// CS Login:         west8
// Lecturer's Name:  Charles Fischer
// Lab Section:      3
//////////////////////////// 80 columns wide //////////////////////////////////
/**
 * Class to contain a word and keep track of the number of occurrences.
 *
 * @author Jeremy West
 */
public class KeyWord implements Comparable<KeyWord>, Prioritizable {
    private String word; // the stored word in lower case
    private int count; // the number of occurrences for the word

    public KeyWord(String word) {
        if (word == null || word.isEmpty()) {
            throw new IllegalArgumentException();
        }
        this.word = word.toLowerCase();
        count = 1;
    }

    /**
     * Compares the given KeyWord's word with word
     *
     * @param (o) Object to compare
     * @return The string compareTo of this and the given KeyWord
     */
    public int compareTo(KeyWord o) {
        if (o == null) {
            throw new NullPointerException();
        }
        return word.compareTo(o.getWord());
    }

    /**
     * Compares an unknown object with this KeyWord's word
     *
     * @param (other) Object to compare with
     * @return True if the object is not null, is an instance of KeyWord, and
     * it's word is equal to this word
     */
    public boolean equals(Object other) {
        return other != null
                && other instanceof KeyWord
                && ((KeyWord) other).getWord().equals(word);
    }

    /**
     * Returns the number of this word's occurrences
     *
     * @return The number of this word's occurrences
     */
    public int getOccurrences() { return count; }

    /**
     * Return the priority for this item.
     *
     * @return the priority for this item.
     */
    public int getPriority() { return getOccurrences(); }

    /**
     * Returns this word
     *
     * @return This word
     */
    public String getWord() { return word; }

    /**
     * Increments the number of occurrences
     */
    public void increment() { count += 1; }

    /**
     * Custom toString method
     *
     * @return The word and count
     */
    @Override
    public String toString() {
        return word + "(" + count + ")";
    }
}
