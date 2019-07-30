package com.arttu.mvc.dao;

import java.util.List;

public interface BaseDao<T> {
    void add(T t);
    void delete(T t);
    void edit(T t);
    List<T> findAll();
    T findById(int id);
}
