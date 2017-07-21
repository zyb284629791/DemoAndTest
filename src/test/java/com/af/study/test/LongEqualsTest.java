package com.af.study.test;

/**
 * Created by zyb on 2017/07/18.
 */
public class LongEqualsTest {

    public static void main(String[] args) {
        Long l = 1L;
        System.out.println(l == Long.valueOf("1"));
        System.out.println(l == Long.valueOf(1));
        System.out.println(l == 1L);
    }
}
