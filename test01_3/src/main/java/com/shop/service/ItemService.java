package com.shop.service;

import com.shop.domain.Item;

import java.util.List;

public interface ItemService {
    public int save(Item item);
    public List<Item> findAll();
    public Item findById(Long id);
    public Item getLatestItem();
}
