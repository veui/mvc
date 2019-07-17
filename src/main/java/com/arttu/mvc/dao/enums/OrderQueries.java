package com.arttu.mvc.dao.enums;

public enum OrderQueries {
    SQL_FIND_ALL("SELECT * FROM Order_"),
    SQL_FIND_BY_ID("select * from Order_ where order_id = ?"),
    SQL_INSERT("insert into order_" +
            "(order_date, amount, cost, client_id, item_id) " +
            "values (?, ?, ?, ?, ?)"),
    SQL_UPDATE("update Order_ set order_date = ?, amount = ?, cost = ?, client_id = ?, item_id = ? " +
            "where item_id = ?"),
    SQL_DELETE("delete from order_ where order_id = ?");

    private String value;

    public String getValue() {
        return value;
    }

    OrderQueries(String value) {
        this.value = value;
    }
}
