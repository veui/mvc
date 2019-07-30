package com.arttu.mvc.service.serviceImpl;

import com.arttu.mvc.dao.ItemDao;
import com.arttu.mvc.model.Item;
import com.arttu.mvc.service.ItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ItemServiceImpl implements ItemService {

    private ItemDao itemDao;

    @Autowired
    public ItemServiceImpl(ItemDao itemDao) {
        this.itemDao = itemDao;
    }

    @Override
    public void add(Item item) {
        itemDao.add(item);
    }

    @Override
    public void delete(Item item) {
        itemDao.delete(item);
    }

    @Override
    public void edit(Item item) {
        itemDao.edit(item);
    }

    @Override
    public List<Item> findAll() {
        return itemDao.findAll();
    }

    @Override
    public Item findById(int id) {
        return itemDao.findById(id);
    }

    @Override
    public void deleteById(int id) {
        itemDao.deleteById(id);
    }

    @Override
    public List<Item> findAllHierarchicalItem() {
        return itemDao.hierarchicalItem();
    }

    @Override
    public List<Item> findAllHierarchicalSpec() {
        return itemDao.hierarchicalSpec();
    }
}
