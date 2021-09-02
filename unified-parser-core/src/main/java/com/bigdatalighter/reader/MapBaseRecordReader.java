package com.bigdatalighter.reader;

import com.bigdatalighter.record.MapBaseRecord;

import java.util.Iterator;
import java.util.Map;
import java.util.Set;

/**
 * @author: Leo Zhang(johnson5211.work@gmail.com)
 **/
public class MapBaseRecordReader implements IKeyValueReader<MapBaseRecord> {

    private Iterator<Map.Entry<String, Object>> iterator;
    private Map.Entry<String, Object> nextEntry;

    @Override
    public void readData(MapBaseRecord mapBaseRecord) {
        Set<Map.Entry<String,Object>> entrySet = mapBaseRecord.getContainer().entrySet();
        iterator = entrySet.iterator();
    }

    @Override
    public boolean hasNext() {
        boolean hasNext = iterator.hasNext();
        if (hasNext){
            nextEntry = iterator.next();
        }
        return hasNext;
    }

    @Override
    public String readKey() {
        return nextEntry.getKey();
    }

    @Override
    public Object readValue() {
        return nextEntry.getValue();
    }

}
