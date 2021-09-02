package com.bigdatalighter.generator;

import com.bigdatalighter.constant.DelimiterConstant;

import java.nio.charset.Charset;
import java.util.Random;

/**
 * Used to generate serialized delimiter record like 'a=1&b=2'
 * @author: Leo Zhang(johnson5211.work@gmail.com)
 **/
public class DelimiterRecordGenerator implements SerializedRecordGenerator {
    private final int ASCII_RANGE_INT_127 = 0b1111_111;
    private final Random random;
    private final StringBuilder kvsBuilder,stringBuilder;
    private final char itemDelimiter, kvDelimiter;
    private final int maxItems, maxKeyLength, maxValueLength;
    private final Charset charset;

    public DelimiterRecordGenerator(int maxItems, int maxKeyLength, int maxValueLength) {
        this(DelimiterConstant.CHAR_AND, DelimiterConstant.CHAR_EQUAL, maxItems, maxKeyLength, maxValueLength, Charset.defaultCharset());
    }

    public DelimiterRecordGenerator(char itemDelimiter, char kvDelimiter, int maxItems, int maxKeyLength, int maxValueLength, Charset charset) {
        this.itemDelimiter = itemDelimiter;
        this.kvDelimiter = kvDelimiter;
        this.maxItems = maxItems;
        this.maxKeyLength = maxKeyLength;
        this.maxValueLength = maxValueLength;
        this.charset = charset;
        this.stringBuilder = new StringBuilder();
        this.kvsBuilder = new StringBuilder();
        this.random = new Random();
    }

    @Override
    public byte[] generate() {
        for (int i = 0; i < maxItems; i++) {
            kvsBuilder.append(genKey()).append(kvDelimiter).append(genValue()).append(itemDelimiter);
        }
        kvsBuilder.deleteCharAt(kvsBuilder.length() - 1);
        String testCase = kvsBuilder.toString();
        kvsBuilder.delete(0, kvsBuilder.length());
        return testCase.getBytes(charset);
    }

    private String genKey() {
        long length = random.nextInt(maxKeyLength);
        stringBuilder.append(genLetter());
        for (int i = 0; i < length - 1; i++) {
            stringBuilder.append(genLetter());
        }
        String str = stringBuilder.toString();
        stringBuilder.delete(0, stringBuilder.length());
        return str;
    }

    private String genValue() {
        long length = random.nextInt(maxValueLength);
        for (int i = 0; i < length; i++) {
            stringBuilder.append(genChar());
        }
        String str = stringBuilder.toString();
        stringBuilder.delete(0, stringBuilder.length());
        return str;
    }

    private char genChar() {
        int ascii = fakeRandomAscii();
        return isLetter(ascii) || isDigit(ascii) ? (char) ascii : genChar();
    }

    private char genDigit() {
        int ascii = fakeRandomAscii();
        return isDigit(ascii) ? (char) ascii : genDigit();
    }

    private char genLetter() {
        int ascii = fakeRandomAscii();
        return isLetter(ascii) ? (char) ascii : genLetter();
    }


    private int fakeRandomAscii() {
        return random.nextInt(ASCII_RANGE_INT_127);
    }

    private boolean isDigit(int ascii) {
        return ascii >= 48 && ascii <= 57;
    }

    private boolean isLetter(int ascii) {
        return (ascii >= 65 && ascii <= 90) || (ascii >= 97 && ascii <= 122);
    }

}
