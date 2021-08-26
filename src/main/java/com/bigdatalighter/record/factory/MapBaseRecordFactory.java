package com.bigdatalighter.record.factory;

import com.bigdatalighter.record.IKeyValueRecord;
import com.bigdatalighter.record.MapBaseRecord;

import java.util.Map;

/**
 * @author: Leo Zhang(johnson5211.work@gmail.com)
 **/
public class MapBaseRecordFactory implements IKeyValueRecordFactory<Map> {
    private Class<? extends Map> mapClass;

    public MapBaseRecordFactory() {
    }

    public MapBaseRecordFactory(Class<? extends Map> mapClass) {
        this.mapClass = mapClass;
    }

    @Override
    public IKeyValueRecord<Map> create() {
        try {
            return mapClass == null ? new MapBaseRecord() : new MapBaseRecord(mapClass.newInstance());
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

}
