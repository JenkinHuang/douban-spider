package com.hikari.doubanspider;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.hikari.doubanspider.utils.JSoupPostRequest;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.io.IOException;

@RunWith(SpringRunner.class)
@SpringBootTest
public class DoubanSpiderApplicationTests {

    @Test
    public void contextLoads() {
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
