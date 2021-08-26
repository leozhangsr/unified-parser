package com.bigdatalighter.parser;

import com.bigdatalighter.record.IKeyValueRecord;
import junit.framework.TestCase;

import java.nio.charset.StandardCharsets;

/**
 * @author: Leo Zhang(johnson5211.work@gmail.com)
 **/
public class ParserTest extends TestCase {

//    private byte[] testCase = "a=1+2=3&b=23&&4&c=456&d=".getBytes(StandardCharsets.UTF_8);
    private byte[] testCase = "K=8Dr6L&hhad=Vjti1NYEIegmfYvLdisGgaBBQmdqom7ktT5dNCsIegmMz5QNMBKzPdU0B1oL7cOWnvVxhvChShnuOxB8sT&E=XW&qohOIgL=AeEyDwoaqP2opWGcreY3DpTsoxvocfBpDXmw2P0KbmBgfIOY1QLrCApr77gpT6GPuu62oxvdwndppMFg2PLFuDCO4i&yLjUhaous=aIrR3KZf2B9anwrch7AcIBt5eVXKKNs8Ykcpbi6vJJh9Hel2PQTtj3OYa3g&vFrZAKyL=jmKuCZE3TbWe9JyG0AP6q2GO35OgiDHdfikAAcG6EddQa1t8NAlDauO9LS3Zo1gUk84ZHtZu3Rce90vUk7883&M=8Ws5ykDUbUUttQFskM6c3YDx6Rirz7s8&uRfzQmaj=YnEKspcRofb0gc977G5kRQhi4MiRGO9qkFtdDJ1YscadHXKG26SKeNpPxkrw171s9EnRRTAE39&m=3JFb1Su1RWnmuvPcbjAdQ7ai0Tz47fkMY&Pc=36hH1D2XTDhyNZxYwqh5k9kcc2&cucBcgs=xTX".getBytes(StandardCharsets.UTF_8);

    public void testSeparatedKeyValueRecordParser() {
        IKeyValueRecordParser parser = new SeparatedKeyValueRecordParser(new String[]{"a","b","c"}, ",");
        String value = "1,2,4,6";
        IKeyValueRecord record = parser.parse(value.getBytes());
        System.out.println(record);
        assertNotNull(record);
    }

    public void testCommonUrlParser() {
        testUrlLikeParser(new CommonDelimiterRecordParser());
    }

    public void testFasterUrlParser() {
        testUrlLikeParser(new FasterDelimiterRecordParser());
    }

    public void testUrlLikeParser(IKeyValueRecordParser parser) {
        IKeyValueRecord result = parser.parse(testCase);
        System.out.println(result);
        assertNotNull(result);
    }
}
