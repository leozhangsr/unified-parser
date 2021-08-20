package com.bigdatalighter.reader;

import com.bigdatalighter.record.IKeyValueRecord;

/**
 * A key-value reader which can read all the key-value iteratively.
 * It should by used in this way:
 * <pre>{@code
 *     IKeyValueReader reader = new IKeyValueReaderImpl();
 *     reader.readData(data);
 *     while(reader.hasNext()) {
 *         String key = reader.readKey();
 *         Object value = reader.readerValue();
 *     }
 * }</pre>
 *
 * @author: Leo Zhang(johnson5211.work@gmail.com)
 **/
public interface IKeyValueReader<T> {

    /**
     * set the data where we read from
     * @param t
     */
    public void readData(T t);

    /**
     * return true if had another key-value pair
     * @return
     */
    public boolean hasNext();

    /**
     * @return key in key-value pair
     */
    public String readKey();

    /**
     * @return value in key-value pair
     */
    public Object readValue();

    /**
     * read and put all the key-value pair into a IKeyValueRecord
     * @param record
     * @return
     */
    default IKeyValueRecord readIntoRecord(IKeyValueRecord record) {
        while (hasNext()) {
            record.put(readKey(), readValue());
        }
        return record;
    }

}
