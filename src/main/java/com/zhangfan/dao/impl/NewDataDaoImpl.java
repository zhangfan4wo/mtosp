package com.zhangfan.dao.impl;

import com.zhangfan.dao.NewDateDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

/**
 * Created by admin on 2016/9/13.
 */
@Repository
public class NewDataDaoImpl implements NewDateDao {
    @Autowired
    JdbcTemplate jdbcTemplate;
    public void newdate(String url, String da, String date) {
        jdbcTemplate.update("INSERT INTO `ops_date` (`url`, `star_date`, `date`) VALUES (?,?,?)",new Object[]{url,da,date});
    }
}
