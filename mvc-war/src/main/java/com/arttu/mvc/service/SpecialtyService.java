package com.arttu.mvc.service;

import com.arttu.mvc.model.Item;
import com.arttu.mvc.model.Specialty;

import java.util.List;

public interface SpecialtyService {
    void add(Specialty specialty);
    void delete(Specialty specialty);
    void edit(Specialty specialty);
    boolean editSpecialty(Specialty specialty);
    List<Specialty> findAll();
    List<Item> findAttachedItems(int id);
    Specialty findById(int id);

    void deleteById(int id);
}
