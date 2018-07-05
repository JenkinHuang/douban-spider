package com.hikari.doubanspider.mapper;

import com.hikari.doubanspider.bean.Book;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

@Mapper
@Repository
public interface BookMapper {
    @Insert("insert into t_book(author, pubdate, image, pages, id, title, summary, price) values(#{author}, #{pubdate}, #{image}, #{pages}, #{id}, #{title}, #{summary}, #{price})")
    void insert(Book book);
}
