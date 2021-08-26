package com.bigdatalighter.reader;

import com.bigdatalighter.parser.CommonDelimiterRecordParser;
import com.bigdatalighter.record.IKeyValueRecord;
import org.junit.Test;

/**
 * @author: Leo Zhang(johnson5211.work@gmail.com)
 **/
public class IKeyValueReaderTest {

    @Test
    public void testMapBaseRecordReader(){
        IKeyValueReader recordReader = new MapBaseRecordReader();
        CommonDelimiterRecordParser recordParser = new CommonDelimiterRecordParser();
        IKeyValueRecord record = recordParser.parse("a=1&b=2&c=3");

        recordReader.readData(record);
        while(recordReader.hasNext()) {
            System.out.println(recordReader.readKey() + "=" + recordReader.readValue());
        }
    }

}
