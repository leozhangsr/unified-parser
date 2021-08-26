package com.bigdatalighter.generator;

import org.junit.Test;

/**
 * @author: Leo Zhang(johnson5211.work@gmail.com)
 **/
public class GeneratorTest {

    @Test
    public void test(){
        DelimiterRecordGenerator generator = new DelimiterRecordGenerator(10, 10,100);
        byte[] testCase = generator.generate();
        System.out.println(new String(testCase));
    }

}
