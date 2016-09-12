package com.zhangfan;

import com.zhangfan.dao.QueryIdDao;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * Created bydmin on 2016/9/10.
 */
public class WebPage {
    private Integer id = 0;
    private String url = null;

    @Autowired
    QueryIdDao queryIdDao;

    public Integer getId() {
        return id;
    }

    public WebPage setId(QueryIdDao queryIdDao) {
        if (queryIdDao.queryid() == null) {
            this.id = 1;
        } else {
            this.id = Integer.valueOf(queryIdDao.queryid()) + 1;
//        this.id = id;
        }
        return this;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }


}
