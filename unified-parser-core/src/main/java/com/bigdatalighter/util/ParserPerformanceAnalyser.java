package com.bigdatalighter.util;

import com.bigdatalighter.generator.SerializedRecordGenerator;
import com.bigdatalighter.generator.DelimiterRecordGenerator;
import com.bigdatalighter.parser.IKeyValueRecordParser;
import com.google.common.base.Preconditions;

import java.util.Map;

/**
 * @author: Leo Zhang(johnson5211.work@gmail.com)
 **/
public class ParserPerformanceAnalyser {
    private final String newLine = "\r\n";
    private StringBuilder reportMaker;

    private KeyValueParserRegister register;
    private byte[] testCase;
    private int maxTestCnt;

    private ParserPerformanceAnalyser(KeyValueParserRegister register, byte[] testCase, int maxTestCnt) {
        this.reportMaker = new StringBuilder();
        this.register = register;
        this.testCase = testCase;
        this.maxTestCnt = maxTestCnt;
    }

    public static long doPerformanceAnalysis(IKeyValueRecordParser keyValueParser, byte[] testCase, int maxTestCnt) {
        long start = System.currentTimeMillis();
        for (int i = 0; i < maxTestCnt; i++) {
            keyValueParser.parse(testCase);
        }
        long end = System.currentTimeMillis();
        return end - start;
    }

    public void doPerformanceAnalysis() {
        printPerformanceReportHead();
        Map<String, IKeyValueRecordParser> regieteredParser = register.getRegisteredParser();
        for (Map.Entry<String, IKeyValueRecordParser> entry : regieteredParser.entrySet()) {
            printPerformance(entry.getKey(), doPerformanceAnalysis(entry.getValue()));
        }
    }

    public long doPerformanceAnalysis(IKeyValueRecordParser urlLikeParser) {
        return doPerformanceAnalysis(urlLikeParser, testCase, maxTestCnt);
    }

    private void printPerformanceReportHead() {
        String paragraphSplitter = "---------------------------------------------------------";
        reportMaker.append("Test IUrlLikeParse Performance Report").append(newLine)
                .append(paragraphSplitter).append(newLine)
                .append("Test case: ").append(testCase).append(newLine)
                .append("Test parse times:").append(maxTestCnt).append(newLine)
                .append(paragraphSplitter);
        System.out.println(reportMaker.toString());
        reportMaker.delete(0, reportMaker.length());
    }

    private void printPerformance(String name, long usage) {
        reportMaker.append("Parser name: ").append(name).append("\t")
                .append("Time Usage: ").append(usage);
        System.out.println(reportMaker.toString());
        this.reportMaker.delete(0, this.reportMaker.length());
    }

    public static class Builder {
        private final int DEFAULT_TEST_CNT = 1000000;
        private KeyValueParserRegister register;
        private byte[] testCase;
        private int maxTestCnt;
        private SerializedRecordGenerator testCaseGenerator;

        public Builder() {
            register = new KeyValueParserRegister();
        }

        public Builder register(Class<? extends IKeyValueRecordParser> parserClass) {
            try {
                register.register(parserClass);
            }catch (Exception e) {
                e.printStackTrace();
            }
            return this;
        }

        public Builder withTestCase(byte[] testCase) {
            this.testCase = testCase;
            return this;
        }

        public Builder withMaxTestCnt(int maxTestCnt) {
            this.maxTestCnt = maxTestCnt;
            return this;
        }

        public Builder withTestCaseGenerator(SerializedRecordGenerator testCaseGenerator) {
            this.testCaseGenerator = testCaseGenerator;
            return this;
        }

        public ParserPerformanceAnalyser build() {
            Preconditions.checkArgument(register.hadRegistered(), "Require at least one parser");
            int maxTestCnt = this.maxTestCnt <= 0 ? DEFAULT_TEST_CNT : this.maxTestCnt;

            return new ParserPerformanceAnalyser(register, getTestCase(), maxTestCnt);
        }

        private byte[] getTestCase() {
            if (testCase != null)
                return testCase;
            if (testCaseGenerator != null)
                return testCaseGenerator.generate();
            return new DelimiterRecordGenerator(100, 10,500).generate();
        }

    }

}
