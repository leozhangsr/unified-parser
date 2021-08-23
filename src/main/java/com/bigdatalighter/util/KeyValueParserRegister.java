package com.bigdatalighter.util;

import com.bigdatalighter.parser.IKeyValueRecordParser;

import java.util.HashMap;
import java.util.Map;

/**
 * @author: Leo Zhang(johnson5211.work@gmail.com)
 **/
public class KeyValueParserRegister {
    private Map<String, IKeyValueRecordParser> parserMap;

    public KeyValueParserRegister() {
        parserMap = new HashMap<>();
    }

    public void register(Class<? extends IKeyValueRecordParser> clazz) throws Exception {
        parserMap.put(clazz.getSimpleName(), clazz.newInstance());
    }

    public boolean hadRegistered() {
        return parserMap.size() > 0;
    }

    public IKeyValueRecordParser getParser(Class<? extends IKeyValueRecordParser> clazz) {
        return parserMap.get(clazz.getSimpleName());
    }

    public Map<String, IKeyValueRecordParser> getRegisteredParser() {
        return parserMap;
    }

}
