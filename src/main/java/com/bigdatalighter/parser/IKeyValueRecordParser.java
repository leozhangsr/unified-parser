package com.bigdatalighter.parser;

import com.bigdatalighter.record.IKeyValueRecord;

/**
 * @author: Leo Zhang(johnson5211.work@gmail.com)
 **/
public interface IKeyValueRecordParser {

    public IKeyValueRecord parse(byte[] bytes);

}
