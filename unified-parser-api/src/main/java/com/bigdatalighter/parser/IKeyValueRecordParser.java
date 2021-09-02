package com.bigdatalighter.parser;

import com.bigdatalighter.record.IKeyValueRecord;

import java.util.List;

/**
 * @author: Leo Zhang(johnson5211.work@gmail.com)
 **/
public interface IKeyValueRecordParser {

    public List<IKeyValueRecord> parse(byte[] bytes);

}
