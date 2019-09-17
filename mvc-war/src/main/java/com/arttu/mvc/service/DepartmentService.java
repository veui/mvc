package com.arttu.mvc.service;

import com.arttu.mvc.model.Department;
import com.arttu.mvc.model.Specialty;

import java.util.List;

public interface DepartmentService {
    void add(Department department);
    void delete(Department department);
    void edit(Department department);
    boolean editDepartment(Department department);
    List<Department> findAll();
    List<Specialty> findAttachedSpecialties(int id);
    Department findById(int id);

    void deleteById(int id);

    Department findByTitle(String title);
}
