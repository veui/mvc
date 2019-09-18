package com.arttu.mvc.dao;

import com.arttu.mvc.model.Item;
import com.arttu.mvc.model.ItemSpecialtyWrapper;
import com.arttu.mvc.model.Specialty;

import java.util.List;

public interface SpecialtyDao extends BaseDao<Specialty> {
    void deleteById(int id);
    boolean editSpecialty(Specialty specialty);
    List<Item> findAttachedItems(int id);
    List<ItemSpecialtyWrapper> hierarchicalQuery();
    List<ItemSpecialtyWrapper> hierarchicalQueryId(int id);
}
