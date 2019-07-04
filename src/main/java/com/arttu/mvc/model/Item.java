package com.arttu.mvc.model;

import java.util.HashSet;
import java.util.Objects;

public class Item {

    private int id;
    private String item;
    private float price;
    private HashSet<Specialty> specialtieId;

    public Item() {
    }

    public Item(int id, String item, float price, HashSet<Specialty> specialtieId) {
        this.id = id;
        this.item = item;
        this.price = price;
        this.specialtieId = specialtieId;
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

    public HashSet<Specialty> getSpecialtieId() {
        return specialtieId;
    }

    public void setSpecialtieId(HashSet<Specialty> specialtieId) {
        this.specialtieId = specialtieId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Item item1 = (Item) o;
        return id == item1.id &&
                Float.compare(item1.price, price) == 0 &&
                item.equals(item1.item) &&
                Objects.equals(specialtieId, item1.specialtieId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, item, price, specialtieId);
    }

    @Override
    public String toString() {
        return "Item{" +
                "id=" + id +
                ", item='" + item + '\'' +
                ", price=" + price +
                ", specialtieId=" + specialtieId +
                '}';
    }
}
