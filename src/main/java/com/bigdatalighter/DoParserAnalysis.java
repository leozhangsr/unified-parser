package com.bigdatalighter;

import com.bigdatalighter.parser.CommonDelimiterRecordParser;
import com.bigdatalighter.parser.FasterDelimiterRecordParser;
import com.bigdatalighter.generator.DelimiterRecordGenerator;
import com.bigdatalighter.util.ParserPerformanceAnalyser;

/**
 * @author: Leo Zhang(johnson5211.work@gmail.com)
 **/
public class DoParserAnalysis {

    public static void main(String[] args) throws Exception {
        String testCase = "a=1+2=3&b=23&&4&c=456&d=";

        ParserPerformanceAnalyser.Builder builder = new ParserPerformanceAnalyser.Builder();
        ParserPerformanceAnalyser analyser = builder.register(CommonDelimiterRecordParser.class)
                .register(FasterDelimiterRecordParser.class)
                .withMaxTestCnt(100000)
//                .withTestCaseGenerator(new SimpleKeyValueGenerator(testCase))
                .withTestCaseGenerator(new DelimiterRecordGenerator(100, 10, 100))
                .build();
        analyser.doPerformanceAnalysis();
    }

}
