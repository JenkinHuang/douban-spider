package com.hikari.doubanspider.controller;

import com.hikari.doubanspider.spider.BookSpider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class BookController {

    private final
    BookSpider bookSpider;

    @Autowired
    public BookController(BookSpider bookSpider) {
        this.bookSpider = bookSpider;
    }

    @GetMapping("/StartBookSpider")
    public void startBookSpider() {
        bookSpider.top250BookSpider();
    }
}
