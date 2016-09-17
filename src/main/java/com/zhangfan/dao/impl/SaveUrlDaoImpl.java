package com.zhangfan.dao.impl;

import com.zhangfan.po.WebPage;
import com.zhangfan.dao.SaveUrlDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

/**
 * Created by admin on 2016/9/10.
 */
@Repository
public class SaveUrlDaoImpl implements SaveUrlDao {
    @Autowired
    JdbcTemplate jdbcTemplate;
    public void insetIntoUrl(WebPage webPage) {
        if (webPage.getId() !=null) {

        }else {
        }
        jdbcTemplate.update("INSERT INTO `ops_url` (`id`, `url`,`flag`) VALUES (?, ?,'N')", new Object[]{webPage.getId(), webPage.getUrl()});

    }
}
