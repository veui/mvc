package com.arttu.mvc.dao.enums;

public enum SpecialtyQueries {
    SQL_FIND_ALL("SELECT * FROM SPECIALTY"),
    SQL_FIND_BY_ID("select * from SPECIALTY where specialty_id = ?"),
    SQL_INSERT("insert into SPECIALTY" +
            "(title, department_id) " +
            "values (?, ?)"),
    SQL_UPDATE("update SPECIALTY set title = ?, department_id = ? " +
            "where specialty_id = ?"),
    SQL_DELETE("delete from SPECIALTY where specialty_id = ?");

    private String value;

    public String getValue() {
        return value;
    }

    SpecialtyQueries(String value) {
        this.value = value;
    }
}
