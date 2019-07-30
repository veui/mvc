package com.arttu.mvc.service.serviceImpl;

import com.arttu.mvc.dao.DepartmentDao;
import com.arttu.mvc.model.Department;
import com.arttu.mvc.model.Specialty;
import com.arttu.mvc.service.DepartmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DepartmentServiceImpl implements DepartmentService {

    private DepartmentDao departmentDao;

    @Autowired
    public DepartmentServiceImpl(DepartmentDao departmentDao) {
        this.departmentDao = departmentDao;
    }

    @Override
    public void add(Department department) {
        departmentDao.add(department);
    }

    @Override
    public void delete(Department department) {
        departmentDao.delete(department);
    }

    @Override
    public void edit(Department department) {
        departmentDao.edit(department);
    }

    @Override
    public List<Department> findAll() {
        return departmentDao.findAll();
    }

    @Override
    public List<Specialty> findAttachedSpecialties(int id) {
        return departmentDao.findAttachedSpecialties(id);
    }

    @Override
    public Department findById(int id) {
        return departmentDao.findById(id);
    }

    @Override
    public void deleteById(int id) {
        departmentDao.deleteById(id);
    }
}
