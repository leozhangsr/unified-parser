package com.bigdatalighter.formatter;

import com.google.common.base.Preconditions;

import java.util.List;

/**
 * @author: Leo Zhang(johnson5211.work@gmail.com)
 **/
public abstract class AbstractNamedFormatter implements IKeyValueRecordFormatter{

    protected List<String> keys;

    public AbstractNamedFormatter(List<String> keys) {
        Preconditions.checkState(keys != null && keys.size() > 0, "keys is must be not null and has elements");
        this.keys = keys;
    }


}
