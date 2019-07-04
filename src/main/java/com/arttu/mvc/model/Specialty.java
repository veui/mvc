package com.arttu.mvc.model;

import java.util.HashSet;
import java.util.Objects;

public class Specialty {

    private int id;
    private String title;
    private HashSet<Department> departmentId;

    public Specialty() {
    }

    public Specialty(int id, String title, HashSet<Department> departmentId) {
        this.id = id;
        this.title = title;
        this.departmentId = departmentId;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public HashSet<Department> getDepartmentId() {
        return departmentId;
    }

    public void setDepartmentId(HashSet<Department> departmentId) {
        this.departmentId = departmentId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Specialty specialty = (Specialty) o;
        return id == specialty.id &&
                title.equals(specialty.title) &&
                Objects.equals(departmentId, specialty.departmentId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, title, departmentId);
    }

    @Override
    public String toString() {
        return "Specialty{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", departmentId=" + departmentId +
                '}';
    }
}
