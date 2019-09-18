package com.arttu.mvc.dao.enums;

public enum SpecialtyQueries {
    SQL_FIND_ALL("SELECT * FROM SPECIALTY"),
    SQL_FIND_BY_ID("select * from SPECIALTY where specialty_id = ?"),
    SQL_INSERT("insert into SPECIALTY" +
            "(title, department_id, parent_id) " +
            "values (?, ?, ?)"),
    SQL_FIND_ITEMS("select * from item " +
            "inner join specialty on specialty.specialty_id = item.specialty_id " +
            "where specialty.specialty_id = ?"),
    SQL_UPDATE("update SPECIALTY set title = ?, department_id = ?, parent_id = ? " +
            "where specialty_id = ?"),
    SQL_DELETE("delete from SPECIALTY where specialty_id = ?"),
    SQL_HIERARCHICAL_QUERY("select Specialty.specialty_id, Specialty.title, Specialty.department_id, Specialty.parent_id, " +
            " i.item_id, i.item, i.price, i.specialty_id " +
            "from Specialty " +
            "left join item i on i.specialty_id = specialty.specialty_id " +
            "start with parent_id is null " +
            "connect by prior specialty.specialty_id =  specialty.parent_id"),
    SQL_HIERARCHICAL_SELECTED_QUERY("select Specialty.specialty_id, Specialty.title, Specialty.department_id, Specialty.parent_id, " +
            " i.item_id, i.item, i.price, i.specialty_id " +
            "from Specialty " +
            "left join item i on i.specialty_id = specialty.specialty_id " +
            "start with specialty.specialty_id = ? " +
            "connect by prior specialty.specialty_id =  specialty.parent_id");

    private String value;

    public String getValue() {
        return value;
    }

    SpecialtyQueries(String value) {
        this.value = value;
    }
}
