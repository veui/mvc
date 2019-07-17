package com.arttu.mvc.dao.enums;

public enum DepartmentQueries {
    SQL_FIND_ALL("SELECT * FROM DEPARTMENT"),
    SQL_FIND_BY_ID("select * from DEPARTMENT where department_id = ?"),
    SQL_INSERT("insert into DEPARTMENT (title) values (?)"),
    SQL_UPDATE("update DEPARTMENT set title = ? where department_id = ?"),
    SQL_DELETE("delete from DEPARTMENT where department_id = ?");

    private String value;

    public String getValue() {
        return value;
    }

    DepartmentQueries(String value) {
        this.value = value;
    }
}
