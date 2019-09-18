package com.arttu.mvc.model;

public class ItemSpecialtyWrapper {

    private int id;
    private String item;
    private float price;
    private int specialtyId;
    private int specId;
    private String title;
    private int departmentId;
    private int parentId;

    public ItemSpecialtyWrapper() {
    }

    public ItemSpecialtyWrapper(int id, String item, float price, int specialtyId, int specId, String title,
                                int departmentId, int parentId) {
        this.id = id;
        this.item = item;
        this.price = price;
        this.specialtyId = specialtyId;
        this.specId = specId;
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

    public String getItem() {
        return item;
    }

    public void setItem(String item) {
        this.item = item;
    }

    public float getPrice() {
        return price;
    }

    public void setPrice(float price) {
        this.price = price;
    }

    public int getSpecialtyId() {
        return specialtyId;
    }

    public void setSpecialtyId(int specialtyId) {
        this.specialtyId = specialtyId;
    }

    public int getSpecId() {
        return specId;
    }

    public void setSpecId(int specId) {
        this.specId = specId;
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

    public int getParentId() {
        return parentId;
    }

    public void setParentId(int parentId) {
        this.parentId = parentId;
    }

    @Override
    public String toString() {
        return "ItemSpecialtyWrapper{" +
                "id=" + id +
                ", item='" + item + '\'' +
                ", price=" + price +
                ", specialtyId=" + specialtyId +
                ", specId=" + specId +
                ", title='" + title + '\'' +
                ", departmentId=" + departmentId +
                ", parentId=" + parentId +
                '}';
    }
}
