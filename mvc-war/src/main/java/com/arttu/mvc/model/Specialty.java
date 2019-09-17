package com.arttu.mvc.model;

import java.util.Objects;

public class Specialty {

    private int id;
    private String title;
    private int departmentId;
    private int parentId;

    public Specialty() {
    }

    public Specialty(int id, String title, int departmentId, int parentId) {
        this.id = id;
        this.title = title;
        this.departmentId = departmentId;
        this.parentId = parentId;
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

    public int getDepartmentId() {
        return departmentId;
    }

    public void setDepartmentId(int departmentId) {
        this.departmentId = departmentId;
    }

    public int getParentId() { return parentId; }

    public void setParentId(int parentId) { this.parentId = parentId; }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Specialty specialty = (Specialty) o;
        return id == specialty.id &&
                departmentId == specialty.departmentId &&
                parentId == specialty.parentId &&
                title.equals(specialty.title);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, title, departmentId, parentId);
    }

    @Override
    public String toString() {
        return "Specialty{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", departmentId=" + departmentId +
                ", parentId=" + parentId +
                '}';
    }
}
