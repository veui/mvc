package com.arttu.mvc.dao.enums;

public enum ItemQueries {
    SQL_FIND_ALL("SELECT * FROM ITEM"),
    SQL_FIND_BY_ID("select * from ITEM where item_id = ?"),
    SQL_FIND_BY_TITLE("select * from item where title = ?"),
    SQL_INSERT("insert into ITEM" +
            "(item, price, specialty_id) " +
            "values (?, ?, ?)"),
    SQL_UPDATE("update ITEM set item = ?, price = ?, specialty_id = ? " +
            "where item_id = ?"),
    SQL_DELETE("delete from item where item_id = ?"),
    SQL_HIERARCHICAL_PRIOR_ITEM("select * from item connect by prior item_id = specialty_id"),
    SQL_HIERARCHICAL_PRIOR_SPECIALTY("select * from item connect by item_id = prior specialty_id");

    private String value;

    public String getValue() {
        return value;
    }

    ItemQueries(String value) {
        this.value = value;
    }
}
