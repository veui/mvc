package com.arttu.mvc.dao;

import com.arttu.mvc.model.Item;
import com.arttu.mvc.model.Specialty;

import java.util.List;

public interface SpecialtyDao extends BaseDao<Specialty> {
    void deleteById(int id);
    List<Item> findAttachedItems(int id);
}
