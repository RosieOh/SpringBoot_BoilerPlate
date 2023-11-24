package com.shop.service;

import com.shop.domain.Item;
import com.shop.mapper.ItemMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ItemServiceImpl implements ItemService {

    @Autowired
    private ItemMapper itemMapper;

    @Override
    public int save(Item item) {
        return itemMapper.save(item);
    }

    @Override
    public List<Item> findAll() {
        return itemMapper.findAll();
    }

    @Override
    public Item findById(Long id) {
        return itemMapper.findById(id);
    }

    @Override
    public Item getLatestItem() {
        return itemMapper.getLatestItem();
    }
}
