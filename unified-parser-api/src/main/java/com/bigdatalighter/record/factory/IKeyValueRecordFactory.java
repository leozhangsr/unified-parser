package com.bigdatalighter.record.factory;

import com.bigdatalighter.record.IKeyValueRecord;

/**
 * @author: Leo Zhang(johnson5211.work@gmail.com)
 **/
public interface IKeyValueRecordFactory<T> {

    public IKeyValueRecord<T> create();

}
