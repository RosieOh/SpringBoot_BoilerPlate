package com.shop.service;

import com.shop.entity.Book;
import com.shop.mapper.BookMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BookServiceImpl implements BookService {

    @Autowired
    private BookMapper bookMapper;

    @Override
    public int createBook(Book book) {
        return bookMapper.createBook(book);
    }

    @Override
    public List<Book> readBookAll() {
        return bookMapper.readBookAll();
    }

    @Override
    public Book readBook(Long seq) {
        return bookMapper.readBook(seq);
    }
}
