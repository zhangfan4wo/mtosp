package com.zhangfan.dao.impl;

import com.zhangfan.dao.DateJobDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;
@Repository
/**
 * Created by admin on 2016/9/14.
 */
public class DateJobDaoImpl implements DateJobDao {
      @Autowired
    JdbcTemplate jdbcTemplate;
    public List<Map<String, Object>> querydata() {
        return jdbcTemplate.queryForList("SELECT url,data FROM `ops_data` ORDER BY `star_date` DESC LIMIT 0, 20");
    }
}
