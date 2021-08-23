package com.bigdatalighter.reader;

/**
 * @author: Leo Zhang(johnson5211.work@gmail.com)
 **/
public class CoupleDelimiterRawStringReader implements IKeyValueReader<byte[]> {

    //delimiters
    private final byte itemDelimiter, kvDelimiter;
    //fields to manager index
    private int currentIndex;
    private int lastItemDelimiterIndex;
    private int lastKvDelimiterIndex;
    //data trying to read
    private byte[] bytes;

    public CoupleDelimiterRawStringReader(char itemDelimiter, char kvDelimiter) {
        this.itemDelimiter = (byte)itemDelimiter;
        this.kvDelimiter = (byte)kvDelimiter;
    }

    @Override
    public void readData(byte[] data) {
        resetIndex();
        bytes = data;
    }

    @Override
    public boolean hasNext() {
        return currentIndex < bytes.length;
    }

    public String readKey() {
        int start = currentIndex;
        while (currentIndex < bytes.length) {
            byte c = bytes[currentIndex];
            if (c == kvDelimiter) {
                lastKvDelimiterIndex = currentIndex++;
                if (lastKvDelimiterIndex > lastItemDelimiterIndex) {
                    return new String(bytes, start, lastKvDelimiterIndex - start);
                }
            } else {
                currentIndex++;
            }
        }
        return null;
    }

    public String readValue() {
        int start = this.currentIndex;
        while (currentIndex < bytes.length) {
            byte b = bytes[currentIndex];
            if (b == itemDelimiter) {
                lastItemDelimiterIndex = currentIndex++;
            } else if(b == kvDelimiter) {
                lastKvDelimiterIndex = currentIndex++;
                if (lastKvDelimiterIndex - lastItemDelimiterIndex > 1 && lastItemDelimiterIndex >= start) {
                    currentIndex = lastItemDelimiterIndex + 1;
                    int end = lastItemDelimiterIndex - 1;
                    while (itemDelimiter == bytes[end]) {
                        end--;
                    }
                    return new String(bytes, start, end - start + 1);
                }
            } else {
                currentIndex++;
            }
        }
        return new String(bytes, start, currentIndex - start);
    }

    private void resetIndex() {
        currentIndex = 0;
        lastItemDelimiterIndex = 0;
        lastKvDelimiterIndex = 0;
    }

}
