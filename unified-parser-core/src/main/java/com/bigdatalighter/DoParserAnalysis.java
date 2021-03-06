package com.bigdatalighter;

import com.bigdatalighter.generator.SimpleSerializedRecordGenerator;
import com.bigdatalighter.parser.FasterDelimiterRecordParser;
import com.bigdatalighter.parser.CommonDelimiterRecordParser;
import com.bigdatalighter.generator.DelimiterRecordGenerator;
import com.bigdatalighter.util.ParserPerformanceAnalyser;

import java.nio.charset.StandardCharsets;

/**
 * @author: Leo Zhang(johnson5211.work@gmail.com)
 **/
public class DoParserAnalysis {

    public static void main(String[] args) throws Exception {
        String testCase = "a=1+2=3&b=23&&4&c=456&d=1351351lk&e=algeanlakgeaeb&ef=agna;hellibo";

        ParserPerformanceAnalyser.Builder builder = new ParserPerformanceAnalyser.Builder();
        ParserPerformanceAnalyser analyser = builder.register(CommonDelimiterRecordParser.class)
                .register(FasterDelimiterRecordParser.class)
                .withMaxTestCnt(100000)
                .withTestCaseGenerator(new SimpleSerializedRecordGenerator(testCase.getBytes(StandardCharsets.UTF_8)))
//                .withTestCaseGenerator(new DelimiterRecordGenerator(100, 10, 100))
                .build();
        analyser.doPerformanceAnalysis();
    }

}
