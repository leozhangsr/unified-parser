package com.bigdatalighter.record;

/**
 * @author: Leo Zhang(johnson5211.work@gmail.com)
 **/
public interface IKeyValueRecord<T> {
    /**
     * Return the container which actually holds the key-value pairs
     * @return
     */
    public T getContainer();
    /**
     * Set the value of a field given its name
     */
    public void put(String key, Object value);

    /**
     * Return the value of a field given its name
     */
    public Object get(String key);

}
