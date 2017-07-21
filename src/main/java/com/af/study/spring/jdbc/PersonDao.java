package com.af.study.spring.jdbc;

import com.af.study.spring.bean.Person;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcDaoSupport;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * Created by zyb on 2017/07/17.
 */
@Component
public class PersonDao extends NamedParameterJdbcDaoSupport {

    @Autowired
    public PersonDao(JdbcTemplate jdbcTemplate) {
        setJdbcTemplate(jdbcTemplate);
    }

    List<Person> getBeans(){
        return this.getJdbcTemplate().query("select * from t_person", BeanPropertyRowMapper.newInstance(Person.class));
    }
}
