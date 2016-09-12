package com.zhangfan.dao.impl;

import com.zhangfan.dao.QueryZFDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

/**
 * Created by zhangfanfan on 16-9-5.
 */
@Repository
public class QueryZFDaoImpl  implements QueryZFDao {
    @Autowired
    private JdbcTemplate jdbcTemplate;

    public List<Map<String, Object>> queryZF() {
        return jdbcTemplate.queryForList("SELECT * FROM ops_url");
    }
}
