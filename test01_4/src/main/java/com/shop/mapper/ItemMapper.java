package com.shop.mapper;

import com.shop.domain.Item;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface ItemMapper {
    int save(Item item);
    List<Item> findAll();
    Item findById(Long id);
    Item getLatestItem();
    void update(Long itemId, Item itemParam);
}
