package com.arttu.mvc.service;

import com.arttu.mvc.model.Specialty;

import java.util.List;

public interface SpecialtyService {
    void add(Specialty specialty);
    void delete(Specialty specialty);
    void edit(Specialty specialty);
    List<Specialty> findAll();
    Specialty findById(int id);

    void deleteById(int id);
}
