package com.bigdatalighter.parser;

import com.bigdatalighter.constant.DelimiterConstant;
import com.bigdatalighter.reader.CoupleDelimiterRawStringReader;
import com.bigdatalighter.reader.CoupleDelimiterStringReader;
import com.bigdatalighter.reader.IKeyValueReader;
import com.bigdatalighter.record.IKeyValueRecord;
import com.bigdatalighter.record.factory.IKeyValueRecordFactory;
import com.bigdatalighter.record.factory.MapBaseRecordFactory;

import java.nio.charset.Charset;

/**
 * @author: Leo Zhang(johnson5211.work@gmail.com)
 **/
public class FasterDelimiterRecordParser extends AbstractDelimiterRecordParser<Character> {

    private IKeyValueReader<String> stringReader;
    private IKeyValueReader<byte[]> bytesReader;

    public FasterDelimiterRecordParser() {
        this(DelimiterConstant.CHAR_AND, DelimiterConstant.CHAR_EQUAL, new MapBaseRecordFactory(), Charset.defaultCharset());
    }

    public FasterDelimiterRecordParser(Character itemsDelimiter, Character kvDelimiter, IKeyValueRecordFactory recordFactory, Charset charset) {
        super(itemsDelimiter, kvDelimiter, recordFactory, charset);
        this.stringReader = new CoupleDelimiterStringReader(itemDelimiter, kvDelimiter);
        this.bytesReader = new CoupleDelimiterRawStringReader(itemDelimiter, kvDelimiter);
    }

    @Override
    public IKeyValueRecord parse(byte[] bytes) {
        IKeyValueRecord result = recordFactory.create();
        if (bytes != null) {
            bytesReader.readData(bytes);
            bytesReader.readIntoRecord(result);
        }
        return result;
    }

    @Override
    public IKeyValueRecord parse(String keyValuePairs) {
        IKeyValueRecord result = recordFactory.create();
        if (keyValuePairs != null) {
            stringReader.readData(keyValuePairs);
            stringReader.readIntoRecord(result);
        }
        return result;
    }

}
