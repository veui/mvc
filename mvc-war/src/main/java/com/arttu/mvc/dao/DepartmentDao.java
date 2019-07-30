package com.arttu.mvc.dao;

import com.arttu.mvc.model.Department;
import com.arttu.mvc.model.Specialty;

import java.util.List;

public interface DepartmentDao extends BaseDao<Department> {
    void deleteById(int id);
    List<Specialty> findAttachedSpecialties(int id);
}
