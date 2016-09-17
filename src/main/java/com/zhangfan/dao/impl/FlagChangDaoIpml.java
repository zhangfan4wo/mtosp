package com.zhangfan.dao.impl;

import com.zhangfan.dao.FlagChangDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

/**
 * Created by zhangfanfan on 16-9-17.
 */
@Repository
public class FlagChangDaoIpml implements FlagChangDao {
    @Autowired
    JdbcTemplate jdbcTemplate;
    public void flagNtoY(Integer id) {
        jdbcTemplate.update("UPDATE ops_url o SET o.flag=('Y') WHERE id=?",new Object[]{String.valueOf(id)});
    }


    public void flagYtoN(Integer id) {
        jdbcTemplate.update("UPDATE ops_url o SET o.flag=('N') WHERE id=?",new Object[]{String.valueOf(id)});

    }
}
