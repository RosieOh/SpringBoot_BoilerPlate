package com.shop.service;

import com.shop.entity.Book;

import java.util.List;

public interface BookService {
    public int createBook(Book book);
    public List<Book> readBookAll();
    public Book readBook(Long seq);
}
