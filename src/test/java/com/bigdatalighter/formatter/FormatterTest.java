package com.bigdatalighter.formatter;

import com.bigdatalighter.reader.MapBaseRecordReader;
import com.bigdatalighter.record.IKeyValueRecord;
import com.bigdatalighter.record.MapBaseRecord;
import com.google.common.collect.Lists;
import junit.framework.TestCase;

/**
 * @author: Leo Zhang(johnson5211.work@gmail.com)
 **/
public class FormatterTest extends TestCase {

    public void testNamedJsonFormatter() {
        testFormatter(new NamedJsonFormatter(Lists.newArrayList("a","num")));
    }

    public void testNamedDelimiterFormatter() {
        testFormatter(new NamedDelimiterFormatter(Lists.newArrayList("num", "string","empty", "null")));
        testFormatter(new NamedDelimiterFormatter(Lists.newArrayList("num", "string"), "," , "=", false));
        testFormatter(new NamedDelimiterFormatter(Lists.newArrayList("num", "string")));
        testFormatter(new NamedDelimiterFormatter(Lists.newArrayList("num", "string"), ","));
        testFormatter(new NamedDelimiterFormatter(Lists.newArrayList("num", "string","empty", "null"), ","));
    }

    public void testReaderBaseJsonFormatter() {
        testFormatter(new ReaderBaseJsonFormatter(new MapBaseRecordReader()));
    }

    public void testReaderBaseDelimiterFormatter() {
        testFormatter(new ReaderBaseDelimiterFormatter(new MapBaseRecordReader()));
        testFormatter(new ReaderBaseDelimiterFormatter(new MapBaseRecordReader(), ",", ":"));
    }

    public void testFormatter(IKeyValueRecordFormatter formatter) {
        String format = formatter.format(getRecord());
        System.out.println(format);
    }

    private IKeyValueRecord getRecord() {
        IKeyValueRecord record = new MapBaseRecord();
        record.put("num", 1);
        record.put("string", "2");
        record.put("键", "值");
        record.put("null", null);
        record.put("empty", "");
        return record;
    }

}
