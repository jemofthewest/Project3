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
        count = 0;
    }

    /**
     * Compares this object with the specified object for order.  Returns a
     * negative integer, zero, or a positive integer as this object is less
     * than, equal to, or greater than the specified object.
     * <p>
     * <p>The implementor must ensure <tt>sgn(x.compareTo(y)) ==
     * -sgn(y.compareTo(x))</tt> for all <tt>x</tt> and <tt>y</tt>.  (This
     * implies that <tt>x.compareTo(y)</tt> must throw an exception iff
     * <tt>y.compareTo(x)</tt> throws an exception.)
     * <p>
     * <p>The implementor must also ensure that the relation is transitive:
     * <tt>(x.compareTo(y)&gt;0 &amp;&amp; y.compareTo(z)&gt;0)</tt> implies
     * <tt>x.compareTo(z)&gt;0</tt>.
     * <p>
     * <p>Finally, the implementor must ensure that <tt>x.compareTo(y)==0</tt>
     * implies that <tt>sgn(x.compareTo(z)) == sgn(y.compareTo(z))</tt>, for
     * all <tt>z</tt>.
     * <p>
     * <p>It is strongly recommended, but <i>not</i> strictly required that
     * <tt>(x.compareTo(y)==0) == (x.equals(y))</tt>.  Generally speaking, any
     * class that implements the <tt>Comparable</tt> interface and violates
     * this condition should clearly indicate this fact.  The recommended
     * language is "Note: this class has a natural ordering that is
     * inconsistent with equals."
     * <p>
     * <p>In the foregoing description, the notation
     * <tt>sgn(</tt><i>expression</i><tt>)</tt> designates the mathematical
     * <i>signum</i> function, which is defined to return one of <tt>-1</tt>,
     * <tt>0</tt>, or <tt>1</tt> according to whether the value of
     * <i>expression</i> is negative, zero or positive.
     *
     * @param o the object to be compared.
     * @return a negative integer, zero, or a positive integer as this object
     * is less than, equal to, or greater than the specified object.
     * @throws NullPointerException if the specified object is null
     * @throws ClassCastException   if the specified object's type prevents it
     *                              from being compared to this object.
     */
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
    public int getPriority() {
        return count;
    }

    public String getWord() { return word; }

    public void increment() { count += 1; }
}
