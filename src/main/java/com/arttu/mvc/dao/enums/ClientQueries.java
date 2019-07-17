package com.arttu.mvc.dao.enums;

public enum ClientQueries {
    SQL_FIND_ALL("SELECT * FROM CLIENT"),
    SQL_FIND_BY_ID("select * from client where client_id = ?"),
    SQL_INSERT("insert into Client" +
            "(username, password, last_name, first_name, email, phone) " +
            "values (?, ?, ?, ?, ?, ?)"),
    SQL_UPDATE("update client set username = ?, password = ?, last_name = ?, first_name = ?, " +
                       "email = ?, phone = ?" +
                       "where client_id = ?"),
    SQL_DELETE("delete from client where client_id = ?");

    private String value;

    public String getValue() {
        return value;
    }

    ClientQueries(String value) {
        this.value = value;
    }
}