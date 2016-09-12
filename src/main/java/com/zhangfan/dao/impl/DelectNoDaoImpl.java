package com.zhangfan.dao.impl;

import com.zhangfan.dao.DelectNoDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;


/**
 * Created by admin on 2016/9/11.
 */
@Repository
public class DelectNoDaoImpl implements DelectNoDao {
    @Autowired
    JdbcTemplate jdbcTemplate;
    public Object delectNo(Integer id,String url){
        jdbcTemplate.update("DELETE FROM `ops_url` WHERE (`id`=?) AND (`url`=?) LIMIT 1",new Object[]{String.valueOf(id),url});
        return "删除了";
    }
}
