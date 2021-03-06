package com.zhangfan.controller;

import com.alibaba.fastjson.JSON;
import com.zhangfan.core.VistWebApi;
import com.zhangfan.po.WebPage;
import com.zhangfan.dao.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.text.ParseException;
import java.util.List;
import java.util.Map;
import java.util.Timer;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

/**
 * Error listenerStart
 * Created by zhangfanfan on 16-9-5.
 */
@RequestMapping("/")
@Controller
public class DemoController {
    @Autowired
    QueryZFDao queryZFdao;
    @Autowired
    SaveUrlDao saveUrlDao;
    @Autowired
    QueryIdDao queryIdDao;
    @Autowired
    DelectNoDao delectNoDao;
    @Autowired
    JobQueryDao jobQueryDao;
    @Autowired
    DateJobDao dateJobDao;
    @Autowired
    VistWebApi vistWebApi;
    @Autowired
    FlagChangDao flagChangDao;


    ConcurrentMap<String, Timer> timerList = new ConcurrentHashMap<>();
    private Timer timer;

    @RequestMapping("demo")
    @ResponseBody
    public Object demo() {
        List<Map<String, Object>> result = queryZFdao.queryZF();
        return JSON.toJSONString(result);
    }

    @RequestMapping("ZF")
    @ResponseBody
    public Object queryZF1() {
        return "hello";
    }


    @RequestMapping("add")
    @ResponseBody
    public void inset(String url) {
        WebPage webPage = new WebPage();
        webPage.setUrl(url);
        webPage.setId(queryIdDao);
        saveUrlDao.insetIntoUrl(webPage);
    }

    @RequestMapping("del")
    @ResponseBody
    public void delect(Integer id, String url) {
        delectNoDao.delectNo(id, url);
        if (timerList.get(id.toString()) != null) {
            timerList.get(id.toString()).cancel();
            timerList.remove(id.toString());
        }
    }



    @RequestMapping("jobid")
    @ResponseBody
    public void jobinquery(Integer id) {
        flagChangDao.flagNtoY(id);
        try {
            if (timerList.containsKey(id.toString())) {
                System.out.println("重复了!");
            } else {
                timer = vistWebApi.executeContent(jobQueryDao.queryJobQueryDao(id));
                timerList.put(id.toString(), timer);
            }
        } catch (ParseException e) {
            e.printStackTrace();
        }
    }

    @RequestMapping("stop")
    @ResponseBody
    public void stoprun(Integer id) {
        flagChangDao.flagYtoN(id);
        if (timerList.get(id.toString()) != null) {
            timerList.get(id.toString()).cancel();
            timerList.remove(id.toString());
        } else {
            System.out.println("已经是停止状态了");
        }
    }

    @RequestMapping("date")
    @ResponseBody
    public Object datajob() {
        List<Map<String, Object>> result = dateJobDao.querydata();
        return JSON.toJSONString(result);

    }
}

