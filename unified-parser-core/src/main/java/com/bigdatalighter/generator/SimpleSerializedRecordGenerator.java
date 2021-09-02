package com.bigdatalighter.generator;

/**
 *
 * @author: Leo Zhang(johnson5211.work@gmail.com)
 **/
public class SimpleSerializedRecordGenerator implements SerializedRecordGenerator {

    private byte[] bytes;

    public SimpleSerializedRecordGenerator(byte[] bytes) {
        this.bytes = bytes;
    }

    @Override
    public byte[] generate() {
        return bytes;
    }

}
