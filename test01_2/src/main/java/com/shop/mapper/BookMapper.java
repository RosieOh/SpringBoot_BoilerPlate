package com.shop.mapper;

import com.shop.entity.Book;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface BookMapper {
    int createBook(Book book);
    List<Book> readBookAll();
    Book readBook(Long seq);
}
