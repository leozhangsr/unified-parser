package com.bigdatalighter.formatter;

import com.bigdatalighter.reader.IKeyValueReader;
import com.bigdatalighter.record.IKeyValueRecord;
import com.bigdatalighter.record.MapBaseRecord;
import com.bigdatalighter.util.JsonUtil;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.util.HashMap;
import java.util.Map;

/**
 * @author: Leo Zhang(johnson5211.work@gmail.com)
 **/
public class ReaderBaseJsonFormatter extends AbstractReaderBaseFormatter {

    private ObjectMapper mapper;

    public ReaderBaseJsonFormatter(IKeyValueReader reader) {
        super(reader);
        mapper = new ObjectMapper();
    }


    @Override
    public String format(IKeyValueRecord record) {
        if (record instanceof MapBaseRecord) {
            return JsonUtil.toJsonString(record.getContainer());
        }
        Map<String, Object> map = new HashMap();
        reader.readData(record);
        while (reader.hasNext()) {
            map.put(reader.readKey(), reader.readValue());
        }
        return JsonUtil.toJsonString(map);
    }


}
