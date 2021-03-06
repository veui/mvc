package com.arttu.mvc.dao;

import com.arttu.mvc.model.Item;

import java.util.List;

public interface ItemDao extends BaseDao<Item> {
    void deleteById(int id);
    boolean editItem(Item item);
    Item findByTitle(String title);
    List<Item> hierarchicalItem();
    List<Item> hierarchicalSpec();
}
