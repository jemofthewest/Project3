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
public class KeyWord implements Comparable<KeyWord>, Prioritizable {
    private String word;
    private int count;

    public KeyWord(String word) {
        if (word == null || word.isEmpty()) {
            throw new IllegalArgumentException();
        }
        this.word = word.toLowerCase();
        count = 1;
    }

    public int compareTo(KeyWord o) {
        if (o == null) {
            throw new NullPointerException();
        }
        return o.getWord().compareTo(word);
    }

    public boolean equals(Object other) {
        return other != null
                && other instanceof KeyWord
                && ((KeyWord) other).getWord().equals(word);
    }

    public int getOccurrences() { return count; }

    /**
     * Return the priority for this item.
     *
     * @return the priority for this item.
     */
    public int getPriority() { return getOccurrences(); }

    public String getWord() { return word; }

    public void increment() { count += 1; }
}
