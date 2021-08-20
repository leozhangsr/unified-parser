package com.bigdatalighter.parser;

import com.bigdatalighter.constant.DelimiterConstant;
import com.bigdatalighter.record.IKeyValueRecord;

/**
 *
 * @author: Leo Zhang(johnson5211.work@gmail.com)
 **/
public class CommonDelimiterRecordParser extends AbstractDelimiterRecordParser<String> {

    public CommonDelimiterRecordParser() {
        super(DelimiterConstant.STRING_AND, DelimiterConstant.STRING_EQUAL);
    }

    @Override
    public IKeyValueRecord parse(String keyValuePairs) {
        IKeyValueRecord result = recordFactory.create();
        if (keyValuePairs != null && keyValuePairs.length() > 0) {
            String[] kvs = keyValuePairs.split(itemDelimiter);
            for (String kv : kvs) {
                String[] kvArr = kv.split(kvDelimiter);
                switch (kvArr.length) {
                    case 1:
                        result.put(kvArr[0], null);
                        break;
                    case 2:
                        result.put(kvArr[0], kvArr[1]);
                        break;
                    default:
                        result.put(kvArr[0], kv.substring(kvArr[0].length() + 1));
                }
            }
        }
        return result;
    }

}
