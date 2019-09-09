package com.arttu.mvc.service;

import com.arttu.mvc.model.Item;

import java.util.List;

public interface ItemService {
    void add(Item item);
    void delete(Item item);
    void edit(Item item);
    void deleteById(int id);

    Item findById(int id);
    Item findByTitle(String title);
    List<Item> findAll();
    List<Item> findAllHierarchicalItem();
    List<Item> findAllHierarchicalSpec();
}
