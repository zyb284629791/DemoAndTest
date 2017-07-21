package com.af.study.test;

/**
 * Created by zyb on 2017/07/18.
 */
public class InnerClassTest {

    public class Inner{
        public InnerClassTest outer(){
            return InnerClassTest.this;
        }

    }
}
