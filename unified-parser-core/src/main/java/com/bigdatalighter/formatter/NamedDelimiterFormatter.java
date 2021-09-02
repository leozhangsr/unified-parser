package com.bigdatalighter.formatter;

import com.bigdatalighter.constant.DelimiterConstant;
import com.bigdatalighter.record.IKeyValueRecord;
import com.google.common.base.Preconditions;
import org.apache.commons.lang3.StringUtils;

import java.util.List;

/**
 * @author: Leo Zhang(johnson5211.work@gmail.com)
 **/
public class NamedDelimiterFormatter extends AbstractNamedFormatter {
    private String itemDelimiter;
    private String kvDelimiter;
    private boolean printValueOnly;
    private StringBuilder stringBuilder;

    public NamedDelimiterFormatter(List<String> keys) {
        this(keys, DelimiterConstant.STRING_AND, DelimiterConstant.STRING_EQUAL, false);
    }

    public NamedDelimiterFormatter(List<String> keys, String itemDelimiter) {
        this(keys, itemDelimiter, null, true);
    }

    public NamedDelimiterFormatter(List<String> keys, String itemDelimiter, String kvDelimiter, boolean printValueOnly) {
        super(keys);
        if (printValueOnly) {
            Preconditions.checkNotNull(itemDelimiter, "itemDelimiter is required");
        } else {
            Preconditions.checkNotNull(itemDelimiter, "itemDelimiter is required");
            Preconditions.checkNotNull(kvDelimiter, "kvDelimiter is required");
        }
        this.itemDelimiter = itemDelimiter;
        this.kvDelimiter = kvDelimiter;
        this.printValueOnly = printValueOnly;
        this.stringBuilder = new StringBuilder();
    }

    @Override
    public String format(IKeyValueRecord record) {
        if (printValueOnly) {
            for (String key : keys) {
                stringBuilder.append(getValueToAppend(record, key)).append(itemDelimiter);
            }
        } else {
            for (String key : keys) {
                stringBuilder.append(key).append(kvDelimiter).append(getValueToAppend(record, key)).append(itemDelimiter);
            }
        }
        String str = stringBuilder.substring(0, stringBuilder.length() - 1);
        stringBuilder.delete(0, stringBuilder.length());
        return str;
    }

    private String getValueToAppend(IKeyValueRecord record, String key) {
        Object obj = record.get(key);
        return obj == null ? "" : obj.toString();
    }

}
