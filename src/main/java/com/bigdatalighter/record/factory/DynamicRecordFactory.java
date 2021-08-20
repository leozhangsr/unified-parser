package com.bigdatalighter.record.factory;

import com.bigdatalighter.record.IKeyValueRecord;

/**
 * @author: Leo Zhang(johnson5211.work@gmail.com)
 **/
public class DynamicRecordFactory implements IKeyValueRecordFactory {
    private Class<? extends IKeyValueRecord> recordImplClass;

    public DynamicRecordFactory(Class<? extends IKeyValueRecord> recordImplClass) {
        this.recordImplClass = recordImplClass;
    }

    @Override
    public IKeyValueRecord create() {
        try {
            recordImplClass.newInstance();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

}
