package com.bigdatalighter.formatter;

import com.bigdatalighter.reader.IKeyValueReader;
import com.google.common.base.Preconditions;

/**
 * @author: Leo Zhang(johnson5211.work@gmail.com)
 **/
public abstract class AbstractReaderBaseFormatter implements IKeyValueRecordFormatter {

    protected IKeyValueReader reader;

    public AbstractReaderBaseFormatter(IKeyValueReader reader) {
        Preconditions.checkNotNull(reader, "reader can't be null");
        this.reader = reader;
    }

    public IKeyValueReader getReader() {
        return reader;
    }

    public void setReader(IKeyValueReader reader) {
        this.reader = reader;
    }

}

