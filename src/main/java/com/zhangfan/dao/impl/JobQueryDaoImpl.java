package com.zhangfan.dao.impl;

import com.zhangfan.dao.JobQueryDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.LinkedList;
import java.util.List;
import java.util.Map;

/**
 * Created by admin on 2016/8/25.
 */
    @Repository
    public class JobQueryDaoImpl implements JobQueryDao {
    @Autowired
    JdbcTemplate jdbcTemplate;

    public String queryJobQueryDao(Integer id) {
        List<Map<String, Object>> ruselt=new LinkedList<>();
       ruselt=jdbcTemplate.queryForList("SELECT url FROM `ops_url` WHERE id=? LIMIT  1",new Object[]{String.valueOf(id)});
        String s = ruselt.get(0).get("url").toString();
        return s;
    }

}

