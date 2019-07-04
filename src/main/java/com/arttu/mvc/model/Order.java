package com.arttu.mvc.model;

import java.sql.Date;
import java.util.HashSet;
import java.util.Objects;

public class Order {

    private int id;
    private Date date;
    private int amount;
    private float cost;
    private HashSet<Client> clientId;
    private HashSet<Item> itemId;

    public Order() {
    }

    public Order(int id, Date date, int amount, float cost, HashSet<Client> clientId, HashSet<Item> itemId) {
        this.id = id;
        this.date = date;
        this.amount = amount;
        this.cost = cost;
        this.clientId = clientId;
        this.itemId = itemId;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }

    public float getCost() {
        return cost;
    }

    public void setCost(float cost) {
        this.cost = cost;
    }

    public HashSet<Client> getClientId() {
        return clientId;
    }

    public void setClientId(HashSet<Client> clientId) {
        this.clientId = clientId;
    }

    public HashSet<Item> getItemId() {
        return itemId;
    }

    public void setItemId(HashSet<Item> itemId) {
        this.itemId = itemId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Order order = (Order) o;
        return id == order.id &&
                amount == order.amount &&
                Float.compare(order.cost, cost) == 0 &&
                date.equals(order.date) &&
                Objects.equals(clientId, order.clientId) &&
                Objects.equals(itemId, order.itemId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, date, amount, cost, clientId, itemId);
    }

    @Override
    public String toString() {
        return "Order{" +
                "id=" + id +
                ", date=" + date +
                ", amount=" + amount +
                ", cost=" + cost +
                ", clientId=" + clientId +
                ", itemId=" + itemId +
                '}';
    }
}
