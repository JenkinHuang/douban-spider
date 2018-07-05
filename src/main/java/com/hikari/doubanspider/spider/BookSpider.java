package com.hikari.doubanspider.spider;

import com.alibaba.fastjson.JSON;
import com.hikari.doubanspider.bean.Book;
import com.hikari.doubanspider.mapper.BookMapper;
import org.jsoup.Connection;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

@Service
public class BookSpider {

    private final
    BookMapper bookMapper;

    @Autowired
    public BookSpider(BookMapper bookMapper) {
        this.bookMapper = bookMapper;
    }

    public void top250BookSpider() {
        try {
            String url = "https://book.douban.com/top250";

            ArrayList<String> list = new ArrayList<>();
            Map<String, String> map = new HashMap<>();
            String cookie = "douban";

            Document document;
            Elements elements;

            for (int i = 0; i <= 225; i = i + 25) {
                document = Jsoup.connect(url + "?start=" + i).get();
                elements = document.select("a[title]");

                String id;
                String href;
                for (Element element :
                        elements) {
                    href = element.attr("href");
                    id = href.substring(32, href.length() - 1);

                    list.add(id);
                }
                Thread.sleep(1000);
            }

            String api = "https://api.douban.com/v2/book/";

            for (String s :
                    list) {

                String doc = httpPost(api + s, map, cookie);
                Thread.sleep(30000);
                Book book = JSON.parseObject(doc, Book.class);
                System.out.println(book.toString());
                bookMapper.insert(book);
            }


        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
        }
    }

    private static String httpPost(String url, Map<String, String> map, String cookie) throws IOException {
        //获取请求连接
        Connection con = Jsoup.connect(url);
        //遍历生成参数
        if(map!=null){
            for (Map.Entry<String, String> entry : map.entrySet()) {
                //添加参数
                con.data(entry.getKey(), entry.getValue());
            }
        }
        //插入cookie（头文件形式）
        con.header("Cookie", cookie);
        return con.ignoreContentType(true).post().selectFirst("body").text();
    }
}
