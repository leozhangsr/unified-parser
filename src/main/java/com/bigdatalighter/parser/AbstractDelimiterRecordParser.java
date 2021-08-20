package com.bigdatalighter.parser;

import com.bigdatalighter.record.IKeyValueRecord;
import com.bigdatalighter.record.factory.IKeyValueRecordFactory;
import com.bigdatalighter.record.factory.MapBaseRecordFactory;

import java.nio.charset.Charset;

/**
 *
 * @author: Leo Zhang(johnson5211.work@gmail.com)
 **/
public abstract class AbstractDelimiterRecordParser<T> implements IKeyValueRecordParser {
    private Charset charset;

    protected T itemDelimiter;

    protected T kvDelimiter;

    protected IKeyValueRecordFactory recordFactory;

    public AbstractDelimiterRecordParser(T itemDelimiter, T kvDelimiter) {
        this(itemDelimiter, kvDelimiter, new MapBaseRecordFactory(), Charset.defaultCharset());
    }

    public AbstractDelimiterRecordParser(T itemDelimiter, T kvDelimiter, IKeyValueRecordFactory recordFactory, Charset charset) {
        this.itemDelimiter = itemDelimiter;
        this.kvDelimiter = kvDelimiter;
        this.recordFactory = recordFactory;
        this.charset = charset;
    }

    @Override
    public IKeyValueRecord parse(byte[] bytes) {
        return parse(new String(bytes, charset));
    }

    public abstract IKeyValueRecord parse(String str);

    public T getItemDelimiter() {
        return itemDelimiter;
    }

    public void setItemDelimiter(T itemDelimiter) {
        this.itemDelimiter = itemDelimiter;
    }

    public T getKvDelimiter() {
        return kvDelimiter;
    }

    public void setKvDelimiter(T kvDelimiter) {
        this.kvDelimiter = kvDelimiter;
    }

}
