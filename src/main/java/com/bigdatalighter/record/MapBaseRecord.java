package com.bigdatalighter.record;

import java.util.HashMap;
import java.util.Map;

/**
 * @author: Leo Zhang(johnson5211.work@gmail.com)
 **/
public class MapBaseRecord implements IKeyValueRecord<Map>{
    private Map<String,Object> container;

    public MapBaseRecord() {
        this(new HashMap<>());
    }

    public MapBaseRecord(Map<String, Object> container) {
        this.container = container;
    }

    @Override
    public Map getContainer() {
        return container;
    }

    @Override
    public void put(String key, Object value) {
        container.put(key, value);
    }

    @Override
    public Object get(String key) {
        return container.get(key);
    }

    @Override
    public String toString() {
        return container.toString();
    }
}
