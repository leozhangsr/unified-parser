package com.bigdatalighter.formatter;

import com.bigdatalighter.constant.DelimiterConstant;
import com.bigdatalighter.reader.IKeyValueReader;
import com.bigdatalighter.record.IKeyValueRecord;
import com.google.common.base.Preconditions;

/**
 * @author: Leo Zhang(johnson5211.work@gmail.com)
 **/
public class ReaderBaseDelimiterFormatter extends AbstractReaderBaseFormatter {
    private String itemDelimiter;
    private String kvDelimiter;
    private StringBuilder stringBuilder;

    public ReaderBaseDelimiterFormatter(IKeyValueReader reader) {
        this(reader, DelimiterConstant.STRING_AND, DelimiterConstant.STRING_EQUAL);
    }

    public ReaderBaseDelimiterFormatter(IKeyValueReader reader, String itemDelimiter, String kvDelimiter) {
        super(reader);
        Preconditions.checkNotNull(itemDelimiter, "itemDelimiter is required");
        Preconditions.checkNotNull(kvDelimiter, "kvDelimiter is required");
        this.itemDelimiter = itemDelimiter;
        this.kvDelimiter = kvDelimiter;
        this.stringBuilder = new StringBuilder();
    }

    @Override
    public String format(IKeyValueRecord record) {
        reader.readData(record);
        while (reader.hasNext()) {
            Object value = reader.readValue();
            if (value != null) {
                stringBuilder.append(reader.readKey()).append(kvDelimiter).append(value).append(itemDelimiter);
            }
        }
        String str = stringBuilder.substring(0, stringBuilder.length() - 1);
        stringBuilder.delete(0, stringBuilder.length());
        return str;
    }

}
