package com.arttu.mvc.service;

import com.arttu.mvc.model.Department;

import java.util.List;

public interface DepartmentService {
    void add(Department department);
    void delete(Department department);
    void edit(Department department);
    List<Department> findAll();
    Department findById(int id);

    void deleteById(int id);
}
