package com.bigdatalighter.parser;

import com.bigdatalighter.record.factory.MapBaseRecordFactory;
import com.bigdatalighter.record.IKeyValueRecord;
import com.bigdatalighter.record.factory.IKeyValueRecordFactory;
import com.google.common.collect.Lists;
import org.apache.commons.lang3.StringUtils;

import java.nio.charset.Charset;
import java.util.List;

/**
 * This parser is used to parse  one-delimiter string record with predefined keys.
 * For example:
 * Record needed to parse is delimited by ',': 1,2,3
 * And we have this predefined keys: 'a,b,c'
 * Then it can parse into a key-value record: {"a":1,"b":2,"c":3}
 *
 * @author: Leo Zhang(johnson5211.work@gmail.com)
 **/
public class SeparatedKeyValueRecordParser implements IKeyValueRecordParser {
    private String[] keys;
    private String valueDelimiter;
    private IKeyValueRecordFactory recordFactory;
    private Charset charset;

    public SeparatedKeyValueRecordParser(String[] keys, String valueDelimiter) {
        this(keys, valueDelimiter, new MapBaseRecordFactory(), Charset.defaultCharset());
    }

    public SeparatedKeyValueRecordParser(String keysInStr, String keyDelimiter, String valueDelimiter, IKeyValueRecordFactory recordFactory, Charset charset) {
        this(keyDelimiter.split(keyDelimiter), valueDelimiter, recordFactory, charset);
    }

    public SeparatedKeyValueRecordParser(String[] keys, String valueDelimiter, IKeyValueRecordFactory recordFactory, Charset charset) {
        this.keys = keys;
        this.valueDelimiter = valueDelimiter;
        this.recordFactory = recordFactory;
        this.charset = charset;
    }

    @Override
    public List<IKeyValueRecord> parse(byte[] bytes) {
        String values = new String(bytes, charset);
        String[] valueArr = StringUtils.splitByWholeSeparatorPreserveAllTokens(values, valueDelimiter);
        IKeyValueRecord iKeyValueRecord = recordFactory.create();
        int len = Math.min(keys.length, valueArr.length);
        for (int i = 0; i < len; i++) {
            iKeyValueRecord.put(keys[i], valueArr[i]);
        }
        return Lists.newArrayList(iKeyValueRecord);
    }

}
