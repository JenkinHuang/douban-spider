package com.hikari.doubanspider.spider;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.hikari.doubanspider.utils.JSoupPostRequest;
import org.springframework.stereotype.Service;

import java.io.IOException;

@Service
public class MovieSpider {

    public void top250MovieSpider() {
        String url = "http://api.douban.com/v2/movie/top250";
        try {
            String data = JSoupPostRequest.post(url);
            JSONObject object = JSON.parseObject(data);
            Object subjects = object.get("subjects");
            System.out.println(subjects.toString());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
