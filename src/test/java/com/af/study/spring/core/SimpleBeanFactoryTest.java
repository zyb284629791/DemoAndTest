package com.af.study.spring.core;

import com.af.study.spring.bean.Person;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * Created by zyb on 2017/06/05.
 */
public class SimpleBeanFactoryTest {

    public static void main(String[] args) {

        ClassPathXmlApplicationContext ctx = new ClassPathXmlApplicationContext("applicationContext.xml");
        Person p = ctx.getBean(Person.class);
        p.setName("p");
        System.out.println(p.getName());
    }
}
