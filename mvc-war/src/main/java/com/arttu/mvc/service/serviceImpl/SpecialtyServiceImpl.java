package com.arttu.mvc.service.serviceImpl;

import com.arttu.mvc.dao.SpecialtyDao;
import com.arttu.mvc.model.Item;
import com.arttu.mvc.model.ItemSpecialtyWrapper;
import com.arttu.mvc.model.Specialty;
import com.arttu.mvc.service.SpecialtyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SpecialtyServiceImpl implements SpecialtyService {

    private SpecialtyDao specialtyDao;

    @Autowired
    public SpecialtyServiceImpl(SpecialtyDao specialtyDao) {
        this.specialtyDao = specialtyDao;
    }

    @Override
    public void add(Specialty specialty) {
        specialtyDao.add(specialty);
    }

    @Override
    public void delete(Specialty specialty) {
        specialtyDao.delete(specialty);
    }

    @Override
    public void edit(Specialty specialty) {
        specialtyDao.edit(specialty);
    }

    @Override
    public boolean editSpecialty(Specialty specialty) {
        return specialtyDao.editSpecialty(specialty);
    }

    @Override
    public List<Specialty> findAll() {
        return specialtyDao.findAll();
    }

    @Override
    public List<Item> findAttachedItems(int id) {
        return specialtyDao.findAttachedItems(id);
    }

    @Override
    public List<ItemSpecialtyWrapper> hierarchicalSpecialty() {
        return specialtyDao.hierarchicalQuery();
    }

    @Override
    public List<ItemSpecialtyWrapper> hierarchicalSpecialtyId(int id) {
        return specialtyDao.hierarchicalQueryId(id);
    }

    @Override
    public Specialty findById(int id) {
        return specialtyDao.findById(id);
    }

    @Override
    public void deleteById(int id) {
        specialtyDao.deleteById(id);
    }
}
