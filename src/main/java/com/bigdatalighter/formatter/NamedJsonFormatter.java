package com.bigdatalighter.formatter;

import com.bigdatalighter.record.IKeyValueRecord;
import com.bigdatalighter.util.JsonUtil;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author: Leo Zhang(johnson5211.work@gmail.com)
 **/
public class NamedJsonFormatter extends AbstractNamedFormatter {

    public NamedJsonFormatter(List<String> keys) {
        super(keys);
    }

    @Override
    public String format(IKeyValueRecord record) {
        Map<String, Object> map = new HashMap<>();
        for (String key : keys) {
            map.put(key, record.get(key));
        }
        return JsonUtil.toJsonString(map);
    }

}
