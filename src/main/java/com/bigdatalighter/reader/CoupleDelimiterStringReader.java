package com.bigdatalighter.reader;

/**
 * @author: Leo Zhang(johnson5211.work@gmail.com)
 **/
public class CoupleDelimiterStringReader implements IKeyValueReader<String> {

    //delimiters
    private final char itemDelimiter, kvDelimiter;
    //fields to manager index
    private int currentIndex;
    private int lastItemDelimiterIndex;
    private int lastKvDelimiterIndex;
    //data trying to read
    private String keyValuePairs;
    private char[] chars;

    public CoupleDelimiterStringReader(char itemDelimiter, char kvDelimiter) {
        this.itemDelimiter = itemDelimiter;
        this.kvDelimiter = kvDelimiter;
    }

    @Override
    public void readData(String data) {
        resetIndex();
        keyValuePairs = data;
        chars = data.toCharArray();
    }

    @Override
    public boolean hasNext() {
        return currentIndex < chars.length;
    }

    public String readKey() {
        int start = currentIndex;
        while (currentIndex < chars.length) {
            char c = chars[currentIndex];
            if (c == kvDelimiter) {
                lastKvDelimiterIndex = currentIndex++;
                if (lastKvDelimiterIndex > lastItemDelimiterIndex) {
                    return new String(chars, start, lastKvDelimiterIndex - start);
                }
            } else {
                currentIndex++;
            }
        }
        return null;
    }

    public String readValue() {
        int start = this.currentIndex;
        while (currentIndex < chars.length) {
            char c = chars[currentIndex];
            if (c == itemDelimiter) {
                lastItemDelimiterIndex = currentIndex++;
            } else if(c == kvDelimiter) {
                lastKvDelimiterIndex = currentIndex++;
                if (lastKvDelimiterIndex - lastItemDelimiterIndex > 1 && lastItemDelimiterIndex >= start) {
                    currentIndex = lastItemDelimiterIndex + 1;
                    int end = lastItemDelimiterIndex - 1;
                    while (itemDelimiter == chars[end]) {
                        end--;
                    }
                    return new String(chars, start, end - start + 1);
                }
            } else {
                currentIndex++;
            }
        }
        return new String(chars, start, currentIndex - start);
    }

    private void resetIndex() {
        currentIndex = 0;
        lastItemDelimiterIndex = 0;
        lastKvDelimiterIndex = 0;
    }

}
