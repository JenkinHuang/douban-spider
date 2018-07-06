package com.hikari.doubanspider.spider;

import com.alibaba.fastjson.JSON;
import com.hikari.doubanspider.bean.Book;
import com.hikari.doubanspider.mapper.BookMapper;
import com.hikari.doubanspider.utils.JSoupPostRequest;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.ArrayList;

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

                String doc = JSoupPostRequest.post(api + s);
                Thread.sleep(60000);
                Book book = JSON.parseObject(doc, Book.class);
                System.out.println(book.toString());
                bookMapper.insert(book);
            }

        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
        }
    }
}
