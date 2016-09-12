package com.zhangfan.dao.impl;


import com.zhangfan.dao.QueryIdDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.LinkedList;
import java.util.List;
import java.util.Map;

/**
 * Created by admin on 2016/9/10.
 */
@Repository
public class QueryIdDaoImpl implements QueryIdDao {
    @Autowired
    JdbcTemplate jdbcTemplate;
    public String queryid(){
        List<Map<String, Object>> ruselt=new LinkedList<>();
        ruselt = jdbcTemplate.queryForList("SELECT id  FROM `ops_url` ORDER BY `id` DESC LIMIT 1");
        if (ruselt.size()==0){
            return "0";
        }else {
            String s = ruselt.get(0).get("id").toString();
            return s;
        }
    }
}
