package com.af.study.spring.jdbc;

import com.af.study.spring.bean.Person;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.util.List;

/**
 * Created by zyb on 2017/07/17.
 */
public class PersonDaoTest {

    public static void main(String[] args) {
        ApplicationContext ctx = new ClassPathXmlApplicationContext("applicationContext.xml");
        PersonDao personDao = ctx.getBean("personDao", PersonDao.class);
        List<Person> personList = personDao.getBeans();
        for (Person person : personList) {
            System.out.println(person.getId() + " --- " + person.getName() + " --- " + person.getSex());
        }
    }
}
